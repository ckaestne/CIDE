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

	PATSIB = 0 # 1 << 0 => 1
	def __checkSiblingPattern__(self, listifdefs):
		'''This method returns a tuple with (listdisciplined,
		listundisciplined) #ifdef elements. The pattern works on the basis
		of the sibling pattern. If the xml elements of #if-#elif-#else-#endif
		are siblings, we determine them as disciplined.'''
		listdisciplined = list()
		listundisciplined = list()

		for listcorifdef in listifdefs:
			nodeifdef = listcorifdef[0]
			nodeifdefsibs = [sib for sib in nodeifdef.itersiblings()]

			error=0;
			for corifdef in listcorifdef[1:]:
				if not corifdef in nodeifdefsibs:
					error=1
			if error==1:
				listundisciplined.append(listcorifdef)
			else:
				listdisciplined.append(listcorifdef)

		return (listdisciplined, listundisciplined)

	def __getParentTag__(self, tag):
		parent = tag.getparent()
		return parent.tag.split('}')[1]	

	PATTLS = 2 # 1 << 0 => 1
	def __checkStrictTLSPattern__(self, listifdefs):
		'''like sibling pattern, but only top level and statement elements are considered disciplined'''
		listdisciplined = list()
		listundisciplined = list()

		for listcorifdef in listifdefs:
			nodeifdef = listcorifdef[0]
			nodeifdefsibs = [sib for sib in nodeifdef.itersiblings()]

			error=0
			for corifdef in listcorifdef[1:]:
				if not corifdef in nodeifdefsibs:
					error=1

			if error==0:
				parenttag = self.__getParentTag__(nodeifdef)
				if not parenttag in ['unit','block','public']:
					print parenttag+', line '+str(nodeifdef.getparent().sourceline)
					error=1
						
			if error==1:
				listundisciplined.append(listcorifdef)
			else:
				listdisciplined.append(listcorifdef)

		return (listdisciplined, listundisciplined)	

	PATELSE = 1 # 1 << 1 => 2
	def __checkElsePattern__(self, listifdefs):
		'''This method returns a tuple with (listdisciplined,
		listundisciplined) #ifdef elements. The pattern matches the following
		situation. The if-then in C is enframed by #if-#endif. The else part of
		the if-then in C is not enframed. The sibling pattern does not work here
		since the annatation cannot work properly here.'''
		listdisciplined = list()
		listundisciplined = list()

		for ifdef in listifdefs:
			if len(ifdef) != 2:
				listundisciplined.append(ifdef)

			# check that the endif is the first child of its parent and the parent
			# is an else
			endif = ifdef[1]
			poselse = endif.getparent()
			poselsetag = poselse.tag.split('}')[1]
			if (poselsetag == 'else') or endif.getprevious() == None:
				#print "found disciplined else patter"
				listdisciplined.append(ifdef)
			else:
				listundisciplined.append(ifdef)

		return (listdisciplined, listundisciplined)

#	PATXXX = 2 # 2 << 2 => 4
#	def __checkXXXPattern__(self, listifdefs):
#		''' documentation '''
#		listdisciplined = list()
#		listundisciplined = list()
#
#		for ifdef in listifdefs:
#			pass
#
#		return (listdisciplined, listundisciplined)


	def __checkDiscipline__(self, treeifdefs):
		'''This method checks a number of patterns in the given treeifdefs.
		The checks are in that order, that ifdef patterns not recognized
		are passed to the next pattern.'''
		listundisciplined = self.__createListFromTreeifdefs__(treeifdefs)
		print('INFO: %s elements to check' % len(listundisciplined))

		# check sibling pattern
		if (self.opts.check & (1 << DisciplinedAnnotations.PATSIB)):
			listifdefs = list(listundisciplined)
			(listdisciplined, listundisciplined) = self.__checkSiblingPattern__(listifdefs)
			self.disciplined += len(listdisciplined)

		# check else pattern
		if (self.opts.check & (1 << DisciplinedAnnotations.PATELSE)):
			listifdefs = list(listundisciplined)
			(listdisciplined, listundisciplined) = self.__checkElsePattern__(listifdefs)
			self.disciplined += len(listdisciplined)

		# check TLS pattern
		if (self.opts.check & (1 << DisciplinedAnnotations.PATTLS)):
			listifdefs = list(listundisciplined)
			(listdisciplined, listundisciplined) = self.__checkStrictTLSPattern__(listifdefs)
			self.disciplined += len(listdisciplined)

		# check XXX pattern
		# if (self.opts.check & (1 << DisciplinedAnnotations.PATXXX)):
		#	listifdefs = list(undisciplined)
		#	(listdisciplined, listundisciplined) = self.__checkXXXPattern__(listifdefs)
		#	self.disciplined += len(listdisciplined)

		# wrap up listundisciplined
		self.undisciplined += len(listundisciplined)

	def checkFile(self, file):
		print('INFO: processing (%s)' % file)

		try:
			tree = etree.parse(file)

			f = open(file, 'r')
		except etree.XMLSyntaxError:
			print('ERROR: file (%s) is not valid. Skipping it.' % file)
			return

		#get LOC
		self.loc=self.loc+len(f.readlines())-2;

		# get root of the xml and iterate over it
		root = tree.getroot()
		treeifdefs = self.__getIfdefAnnotations__(root)
		olddis = self.disciplined
		oldundis = self.undisciplined;
		self.__checkDiscipline__(treeifdefs)
		print(["Disciplined annotations: ", self.disciplined-olddis,
				"Undisciplined annotations: ", self.undisciplined-oldundis])

	def checkFiles(self):
		xmlfiles = returnFileNames(self.opts.dir, ['.xml'])
		for xmlfile in  xmlfiles:
			self.checkFile(xmlfile)
		print(["Total Disciplined annotations: ", self.disciplined,
				"Undisciplined annotations: ", self.undisciplined,
				"Ratio: ", self.disciplined/(0.0+self.disciplined+self.undisciplined)])
		f = open("disciplined_stats.txt","a")
		#file,disciplined,undisciplined,loc
		f.write(self.opts.dir+","+str(self.disciplined)+","+str(self.undisciplined)+","+str(self.loc)+"\n")

##################################################
if __name__ == '__main__':
	DisciplinedAnnotations()
