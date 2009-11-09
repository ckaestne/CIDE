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
	disciplined=0;
	undisciplined=0;

	def __init__(self):
		oparser = OptionParser()
		oparser.add_option('-d', '--dir', dest='dir',
				help='input directory (mandatory)')
		group = OptionGroup(oparser, 'Result',
				'This program counts the number of the disciplined '
				'cpp usage in software projects. To this end, it '
				'checks xml representations of header and source '
				'files and returns the number of disciplined ifdefs '
				'in those. Disciplined annotations are: '
		)
		oparser.add_option_group(group)
		(self.opts, self.args) = oparser.parse_args()

		if not self.opts.dir:
			oparser.print_help()
			sys.exit(-1)
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

	def __checkDiscipline__(self, treeifdefs):
		'''This method checks for a given list of ifdef nodes, whether
		corresponding ifdefs are siblings. Using ifdef disciplined all
		nodes in e.g. if-elif-else-endif are siblings. Sibling property
		is transitive.'''

		if not treeifdefs: return

		# create a list of lists with corresponding ifdefs out of the
		# input list, which represents a tree
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

		# the the sibling property of the first element against the
		# rest of the lists
		for listcorifdef in listifdefs:
			#print("checking ifdef")
			nodeifdef = listcorifdef[0]
			listcorifdef = listcorifdef[1:]
			nodeifdefsibs = [sib for sib in nodeifdef.itersiblings()]

			error=0;
			for corifdef in listcorifdef:
				if not corifdef in nodeifdefsibs:
					#print('ERROR: node (%s) in line (%s) is not a sibling'
					#		' of node (%s) in line (%s)!' % \
					#		(corifdef.tag, corifdef.sourceline,
					#		nodeifdef.tag, nodeifdef.sourceline))
					error=1
			if error==1:
				DisciplinedAnnotations.undisciplined=DisciplinedAnnotations.undisciplined+1
			else:
				DisciplinedAnnotations.disciplined=DisciplinedAnnotations.disciplined+1


	def checkFile(self, file):
		print('INFO: processing (%s)' % file)
		try:
			tree = etree.parse(file)
		except etree.XMLSyntaxError:
			print('ERROR: file (%s) is not valid. Skipping it.' % file)
			return

		# get root of the xml and iterate over it
		root = tree.getroot()
		treeifdefs = self.__getIfdefAnnotations__(root)
		olddis=DisciplinedAnnotations.disciplined;
		oldundis=DisciplinedAnnotations.undisciplined;
		self.__checkDiscipline__(treeifdefs)
		print(["Disciplined annotations: ",DisciplinedAnnotations.disciplined-olddis,"Undisciplined annotations: ",DisciplinedAnnotations.undisciplined-oldundis])

	def checkFiles(self):
		xmlfiles = returnFileNames(self.opts.dir, ['.xml'])
		for xmlfile in  xmlfiles:
			self.checkFile(xmlfile)
		print(["Total Disciplined annotations: ",DisciplinedAnnotations.disciplined,"Undisciplined annotations: ",DisciplinedAnnotations.undisciplined,"Ratio: ",DisciplinedAnnotations.disciplined/(0.0+DisciplinedAnnotations.disciplined+DisciplinedAnnotations.undisciplined)])
		f=open("disciplined_stats.txt","a")
		print f.write(self.opts.dir+","+str(DisciplinedAnnotations.disciplined)+","+str(DisciplinedAnnotations.undisciplined)+"\n")

##################################################
if __name__ == '__main__':
	DisciplinedAnnotations()
