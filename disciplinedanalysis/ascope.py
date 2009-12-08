#!/usr/bin/python
# -*- coding: utf-8 -*-

# modules from the std-library
import os
import re
import sys
from optparse import OptionParser, OptionGroup

# external libs
# python-lxml module
try:
	from lxml import etree
except ImportError:
	print("python-lxml module not found! (python-lxml)")
	print("see http://codespeak.net/lxml/")
	print("programm terminating ...!")
	sys.exit(-1)


def returnFileNames(folder, extfilt = ['.xml']):
	'''This function returns all files of the input folder <folder>
	and its subfolders.'''
	filesfound = list()

	if os.path.isdir(folder):
		wqueue = [os.path.abspath(folder)]

		while wqueue:
			currentfolder = wqueue[0]
			wqueue = wqueue[1:]
			foldercontent = os.listdir(currentfolder)
			tmpfiles = filter(lambda n: os.path.isfile(
					os.path.join(currentfolder, n)), foldercontent)
			tmpfiles = filter(lambda n: os.path.splitext(n)[1] in extfilt,
					tmpfiles)
			tmpfiles = map(lambda n: os.path.join(currentfolder, n),
					tmpfiles)
			filesfound += tmpfiles
			tmpfolders = filter(lambda n: os.path.isdir(
					os.path.join(currentfolder, n)), foldercontent)
			tmpfolders = map(lambda n: os.path.join(currentfolder, n),
					tmpfolders)
			wqueue += tmpfolders

	return filesfound


class DisciplinedAnnotations:
	##################################################
	# constants:
	__cppnscpp = 'http://www.sdml.info/srcML/cpp'
	__cppnsdef = 'http://www.sdml.info/srcML/src'
	__cpprens = re.compile('{(.+)}(.+)')
	__conditionals = ['if', 'ifdef', 'ifndef', 'else', 'elif', 'endif']
	__conditions   = ['if', 'ifdef', 'ifndef']
	##################################################

	def __init__(self):
		oparser = OptionParser()
		oparser.add_option('-d', '--dir', dest='dir',
				help='input directory (mandatory)')
		oparser.add_option('-c', '--check', dest='check', type='int',
				default=1, help='pattern check (default=1)')
		groupc = OptionGroup(oparser, 'Check',
				'This option allows to set the patterns, that are '
				'checked during the program run: '
				'(1) check sibling '
				'(2) check else '
		)
		oparser.add_option_group(groupc)
		groupr = OptionGroup(oparser, 'Result',
				'This program counts the number of the disciplined '
				'cpp usage in software projects. To this end, it '
				'checks xml representations of header and source '
				'files and returns the number of disciplined ifdefs '
				'in those. Disciplined annotations are: '
		)
		oparser.add_option_group(groupr)
		(self.opts, self.args) = oparser.parse_args()

		if not self.opts.dir:
			oparser.print_help()
			sys.exit(-1)

		self.disciplined = 0
		self.undisciplined = 0
		self.loc=0
		self.checkFiles()

	def __getIfdefAnnotations__(self, root):
		'''This method returns all nodes of the xml which are ifdef
		annotations in the source code.'''
		treeifdefs = list()

		for action, elem in etree.iterwalk(root):
			ns, tag = DisciplinedAnnotations.__cpprens.match(elem.tag).\
					groups()

			if ns == DisciplinedAnnotations.__cppnscpp \
					and tag in DisciplinedAnnotations.__conditionals:
				treeifdefs.append(elem)

		return treeifdefs

	def __createListFromTreeifdefs__(self, treeifdefs):
		'''This method returns a list representation for the input treeifdefs
		(xml-objects). Corresponding #ifdef elements are in one sublist.'''

		if not treeifdefs: return []

		listifdefs = list()
		workerlist = list()
		for nifdef in treeifdefs:
			tag = nifdef.tag.split('}')[1]
			if tag in ['if', 'ifdef', 'ifndef']:
				workerlist.append(list())
				workerlist[-1].append(nifdef)
			elif tag in ['elif', 'else']:
				workerlist[-1].append(nifdef)
			elif tag in ['endif']:
				workerlist[-1].append(nifdef)
				listifdefs.append(workerlist[-1])
				workerlist = workerlist[:-1]
			else:
				print('ERROR: tag (%s) unknown!' % tag)

		return listifdefs

	
	def __getParentTag__(self, tag):
		parent = tag.getparent()
		return parent.tag.split('}')[1]	


	def __checkDiscipline__(self, treeifdefs, loc,stats,statsU,screensize):
		'''This method checks a number of patterns in the given treeifdefs.
		The checks are in that order, that ifdef patterns not recognized
		are passed to the next pattern.'''
		listundisciplined = self.__createListFromTreeifdefs__(treeifdefs)
		print('INFO: %s annotations to check' % len(listundisciplined))

		allannotations=[]
		for ifdef in listundisciplined:
		  allannotations.append([ifdef[0].sourceline,ifdef[1].sourceline,self.__findFeatures__(ifdef[0])]);

		screensize=50
		for screen in range(0,loc-screensize/2,screensize/2):
			screenend=min(loc,screen+screensize)
			annotationsOnScreen=set()
			annotationsOnScreenCount=0
			for annotation in allannotations:
				  if annotation[0]<=screenend:
				  	 if annotation[1]>screen:
				  		annotationsOnScreen.add(annotation[2])
				  		annotationsOnScreenCount=annotationsOnScreenCount+1
			#print screen, screenend,annotationsOnScreenCount,len(annotationsOnScreen),annotationsOnScreen
			stats[annotationsOnScreenCount]=stats[annotationsOnScreenCount]+1
			statsU[len(annotationsOnScreen)]=statsU[len(annotationsOnScreen)]+1

		print stats
		print statsU

		
		# wrap up listundisciplined
		self.undisciplined += len(listundisciplined)

	

	def __findFeatures__(self, ifdef):
		result=""
		context = etree.iterwalk(ifdef)
		for action, elem in context:
			if action=="end": 
				if elem.tag.split('}')[1]=="name":
					result=result+elem.text
		return result

	def checkFile(self, file,stats,statsU,screensize):
		print('INFO: processing (%s)' % file)

		try:
			tree = etree.parse(file)

			f = open(file, 'r')
		except etree.XMLSyntaxError:
			print('ERROR: file (%s) is not valid. Skipping it.' % file)
			return

		#get LOC
		thisloc=len(f.readlines())-2
		self.loc=self.loc+thisloc

		# get root of the xml and iterate over it
		root = tree.getroot()
		treeifdefs = self.__getIfdefAnnotations__(root)
		olddis = self.disciplined
		oldundis = self.undisciplined;
		self.__checkDiscipline__(treeifdefs, thisloc,stats,statsU,screensize)


	def checkFiles(self):
		xmlfiles = returnFileNames(self.opts.dir, ['.xml'])
		stats=[0]*25
		statsU=[0]*25
		screensize=50
		for xmlfile in  xmlfiles:
			self.checkFile(xmlfile,stats,statsU,screensize)
		#print(["Total Disciplined annotations: ", self.disciplined,
		#		"Undisciplined annotations: ", self.undisciplined,
		#		"Ratio: ", self.disciplined/(0.0+self.disciplined+self.undisciplined)])
		f = open("count.csv","a")
		#file,disciplined,undisciplined,loc
		f.write(self.opts.dir+";"+str(screensize)+";")
		for i in stats:
		      f.write(str(i)+";")
		for i in statsU:
		      f.write(str(i)+";")
		f.write("\n")


##################################################
if __name__ == '__main__':
	DisciplinedAnnotations()
