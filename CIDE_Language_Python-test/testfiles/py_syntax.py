#source: http://codesyntax.netfirms.com/lang-python.htm


##############################################################################
# name: main
# purpose: entry point of the script
##############################################################################

# sys.argv is a list of command line argument strings (pos 0 is script name)
if __name__ == "__main__":
  print "hello world"         # if no comma (,) at end \n is auto included

##############################################################################
# name: main
# purpose: show the basic datatypes
##############################################################################

### scalars (bools, ints, floats, strings)
pi = 3.14                 # can hold real / whole numbers
false0 = False            # can hold boolean values (False / True)
false1 = 0                # 0 counts as False, non-zero is True
hello = "hello"           # can hold strings (immutable)
false2 = ""               # en empty string is False of course
false2 = None             # false2 not defined anymore, counts as False
line = "hello\n"          # line is hello and then a new-line (LF) - 6 chars
firm = r"hello\n"         # line is hello\n - 7 chars
quot1 = "a\"b'c"          # quot1 is a"b'c
quot2 = 'a"b\'c'          # quot2 is a"b'c
#TODO multiline =               # long text with a few lines
#"""This is long text.
#With several lines."""

# scalar operations
num = num*2 + 3 - myfloat # num is 23.86
num = 2**4 % 5            # num is 1 - exp then modulus
num += 1                  # num is 2 - c-like syntax
m = (1<<3)&0xff|0x03^0x01 # m is 0x0a
strop = 'he'+'lo'*3       # strop is 'helololo'
print hello[0]            # prints 'h', [-1] is 'o', hello[0] = 'j' is illegal
print hello[0:2]          # prints 'he', [2:] is 'llo', [:3] is 'hel'

### lists and tuples (sequences of scalars)
nums = [1,2,3]            # lists are mutable and initialized with []
mixed = [pi,"three",3.13] # any scalar can be placed in a list
empty = []                # empty list counts as False, non-empty is True
[one,two] = [1,2]         # one is 1, two is 2
tup1 = (1,2,3)            # tuples are immutable (fast) and initialized with ()
tup2 = (pi,"three",3.13)  # any scalar can be placed in a tuple
empty_tuple = ()          # empty tuple counts as False, non-empty is True
single_tuple = ("a",)     # tuple with 1 member must have a trailing comma (,)
(one,two) = (-1,-2)       # one is -1, two is -2

# list and tuple operations
first = nums[0]           # first is 1, [-1] is 3 (lists/tuples)
sl = nums[0:2]            # sl is [1,2], [2:] is [3], [:1] is [1] (lists/tuples)
mixed[1] = "neo"          # mixed is [3.14,'neo',3.13] (lists only)
mixed[0:2] = ["a","b"]    # mixed is ['a','b',3.13] (lists only)
joined = nums*3 + ["a",9] # joined is [1,2,3,1,2,3,1,2,3,'a',9] (lists/tuples)
nested = [nums,8]         # nested is [[1,2,3],8]
length = len(nums)        # len() function returns length (lists/tuples)
bool = 1 in nums          # bool is True since 1 is nums[0] (lists/tuples)
t = tuple(nums)           # tuple turns lists into tuples, list does opposite
new = [x*2 for x in nums] # new is [2,4,6], this is called list comprehensions
nums.extend([4,5])        # nums is [1,2,3,4,5], lists have methods (lists only)
dir(nums)                 # view all methods of a list - object (lists only)

### dictionaries (hashes of keys with values)
dict1 = {10:"ten","key":"val"}  # key 10 with val "ten", "key" with val "val"
ages = {"jim":18,"ted":21}      # any type can be used as a key or value
empty_dict = {}                 # empty hash counts as False, non-empty is True

# dictionary operations
val = dict1[10]           # val is "ten"
ages["ron"] = 24          # key "ron" with value 24 added to ages
ages["ron"] += 1          # key "ron" just incremented to 25 (a key has one val)
del ages["ron"]           # key "ron" with its value are removed from ages
b = ages.has_key("billy") # b is False, dicts have methods (use dir() to see)

### sets (a list without regard to order, when every member appears once)
set1 = set(["c","a","c"]) # the list will not contain duplicates anymore
set2 = set("abba")        # will create a set of unique letters

# set operations
bool = "a" in set1        # bool is True, fast membership testing
set3 = set1 - set2        # members is set1 but not in set2 ["c"]
set3 = set1 | set2        # members in either set1 or set2 ["a","b","c"]
set3 = set1 & set2        # members in both set1 and set2 ["a"]
set3 = set1 ^ set2        # members in set1 or set2 but not both ["b","c"]

#Conditionals
##############################################################################
# name: main
# purpose: show the basic conditionals
##############################################################################

# regular c style if statement
if (value is not None) and (value == 1):        # is tests for same object
  print "value equals 1",
  print " more can come in this block"

# if-else block
if (job == "millionaire") or (state != "dead"): # !=,== can be used for strings
  print "a suitable husband found"              # <> for lexographical compare
else:
  print "not suitable"

list1 = (1,2,3,9)
list2 = (1,2,7)
# comparing lists / tuples compares var by var until one is different
if (list1 <= list2) and (not age < 80):
  print "1 = 1, 2 = 2, but 3 <= 7 so its True"

# short form with no blocks is also ok
if ok: print "ok"

# since there is no switch statement, this is the shortest way to reproduce
if (i == 1): do_something1()
elif (i == 2): do_something2()
elif (i == 3): do_something3()
else: do_something4()

#Loops and Iterations
##############################################################################
# name: main
# purpose: show the flow blocks
##############################################################################

# for statement (iterate on members of a sequence)
sentence = ['Marry','had','a','little','lamb']
for word in sentence:               # word holds a member in each iteration
  print word, len(word)

# simulate a c for statement
for i in range(10):                 # range(3) returns [0,1,2]
  print i
for i in xrange(1000):              # xrange() does not allocate all before
  print i                           # more efficient for large ranges

# while statements are very similar to c
i = 0
while (i < 10):                     # enter block if condition is TRUE
  print "iteration number ",i
  i += 1

# short forms (no blocks needed if a single statement comes before)
for i in xrange(10): print i
while not end_of_file(): read_next_line()

# continue and break statements are similar to c
for i in xrange(10):
  if i == 3: continue               # skip printing 3 (go to next iteration)
  if i == 5: break                  # exit the loop before printing 5
  print i,                          # will print 0 1 2 4

# pass statement does nothing but required when statement syntactically needed
while True:
  pass                              # loop endlessly until keyboard interrupt

#Functions
##############################################################################
# name: main
# purpose: show function and subroutine syntax
##############################################################################

# return values

def print_hello():          ### does not return anything (actually returns None)
  print "hello"

def seventeen():            ### returns the number 17
  return 17
num = seventeen() + 53      # function call

def retlist():              ### all datatypes can be returned
  return [1,2,3]
[one,two,thr] = retlist()   # function call

# arguments

def has_args(arg1,arg2):    ### all types can be passed as args
  num = arg1 + 4
  mylist = arg2 + ['a',7]
has_args(5.16,[1,'b'])      # function call

def sqrt(num,debug=False):  ### some arguments can have default values
  if debug: print "Sqrt:"   #  defaults are evaluated once so don't use mutables
  return calc_sqrt(num)
sqrt(9)                     # function call without default arguments
sqrt(16,True)               # function call with default arguments
sqrt(25,debug=True)         # function call with named arguments (supply middle)

def many(num,**keywords):   ### keywords is a hash of all named arguments given
  print "num is ",num       # num is regular mandetory arg
  print "named args are:"
  print keywords.keys()
many(1)                     # function call with no named args, keywords is {}
many(2,debug=True)          # function call with, keywords is {'debug':True}

def arg_list(num,*arglist): ### arglist is a tuple of the arguments
  print "num is ",num       # num is regular mandetory arg
  print "arg list is:"
  print arglist
arg_list(1)                 # function call with no extra list, arglist is ()
arg_list(2,'a',3.14)        # function call with, arglist is ('a',3.14)
args = [3,6]                # other way around is also possible (give a list)
r = range(*args)            # calls range(3,6), r is [3,4,5]

# lambda form (on the fly functions)

def duplicate_n_maker(n):   ### this function creates a function
  return lambda arg1:arg1*n
dup3 = duplicate_n_maker(3) # dup3() is a new function
dup_str = dup3('go')        # dup_str is 'gogogo'

#Exceptions
##############################################################################
# name: main
# purpose: show basic exception and error handling
##############################################################################

# needed for file ops and exc_info()
import sys

# simple exception usage
try:                          # wrap "dangerous" code in a try block
  f = open("file.txt")
except IOError:               # catch IOError and deriving exceptions
  print "Could not open"
else:                         # optional, run block if no exception thrown
  f.close()

# a bit more complex usage
a = [1,2,3]                   # init some simple list
try:
  a[7] = 0                    # will throw an IndexError exception
except (IndexError,TypeError):# handle several types with (,) syntax
  print "IndexError caught"   # handle the exception
except Exception, e:          # catch all deriving from Exception (instance e)
  print "Exception: ", e      # address the instance, print e.__str__()
except:                       # catch everything
  print "Unexpected:"         # handle unexpected exceptions
  print sys.exc_info()[0]     # info about curr exception (type,value,traceback)
  raise                       # re-throw caught exception
try:
  a[7] = 0                    # will throw an IndexError exception
finally:                      # cleanup code that should always run (no except)
  print "Will run regardless" # will run if thrown, not thrown, caught, uncaught

# implement a very simple personalized exception class
class MyException(Exception): # derive from builtin Exception class (not a must)
  """My general exception with an output message."""
  def __init__(self,msg):     # implement a ctor with one mandatory argument
    self.msg = msg            # place the msg as a member on this class
  def __str__(self):          # implement so instance could be printed directly
    return repr(self.msg)     # builtin repr() func returns string rep of objs

# implement the simplest personalized exception class possible
class MyMiniEx(Exception):    # use Exception's __init__, __str__, etc
  pass

# play with our personalized exceptions
try:
  raise MyException("hello")  # must supply argument since __init__ requires it
except MyException, e:        # catch just thrown MyException with instance e
  print e                     # run implemented __str__ function
try:
  raise MyMiniEx              # arguments here are optional (as in Exception)
except Exception:             # catch MyMiniEx since derives from Exception
  pass                        # do nothing

#Classes
##############################################################################
# name: MyVector
# purpose: implement a simple class
##############################################################################
class MyVector:
  """A simple vector class."""
  num_created = 0               # static member, initialized to 0
  def __init__(self,x=0,y=0):   # ctor, no overloading possible so only one
    self.__x = x                # create members by placing variables on self
    self.__y = y                # __ variable prolog indicated private variable
    MyVector.num_created += 1   # update the static variable
  def get_size(self):           # all methods must accept the self argument
    return self.__x+self.__y
#TODO  @staticmethod                 # static methods added only in new versions
  def get_num_created():        #  with decorator @staticmethod (no self needed)
    return MyVector.num_created

# usage
print MyVector.num_created      # access static variables
v = MyVector()                  # create a vector with default values
w = MyVector(0.23,0.98)         # create a vector with given values
print w.get_size()              # call a member on an instance
bool = isinstance(v, MyVector)  # check if v is an instance of MyVector, True

##############################################################################
# name: MyObject
# purpose: implement a simple base class (which Soap will derive from)
##############################################################################
class MyObject:
  def __init__(self,weight):    # simple ctor with a mandatory argument
    self.__weight = weight      # init private member __weight
  def print_name(self): pass    # this will be overridden, all methods virtual
  def get_weight(self):         # another method
    return self.__weight

class Soap(MyObject):             # derives from MyObject, multiple is ok
  """A throwable branded soap."""
  def __init__(self,brand):       # override the ctor
    MyObject.__init__(self,1.33)  # must always call base ctor manually
    self.__brand = brand
  def print_name(self):           # override the base method with same name
    print "Soap produced by ", self.__brand,
    print "which weighs ", self.get_weight()
  def __del__(self):              # dtor, called by garbage collector when freed
    print "No more soap"          #  similar to java finalize, do not rely on it
    MyObject.__del__(self)        # must manually call base dtor if exists

# usage
s = Soap("Gillette")            # create a soap with given value
m = s.get_weight()              # call base method (in MyObject)
s.print_name()                  # call overriden method (in Soap)
bool = isinstance(s, MyObject)  # check if s is an instance of MyObject, True
del s                           # manually decrease reference count (optional)

##############################################################################
# name: CustomizedVector
# purpose: override some special methods for vector customization
##############################################################################
class MyVector: # continued class from above
  def __repr__(self):         # called by repr() func for string representation
    return "MyVector("+repr(self.__x)+","+repr(self.__y)+")"
  def __str__(self):          # called by print() when obj is printed
    return "vector with coords ("+repr(self.__x)+","+repr(self.__y)+")"
  def __cmp__(self,other):    # obj comparison (<>==), retval similar to strcmp
    return (self.get_size() - other.get_size())
  def __nonzero__(self):      # bool() truth value testing
    if (self.get_size()>0): return True
    else: return False
  def __add__(self,other):    # implement + operator for object
    return MyVector(self.__x+other.__x,self.__y+other.__y)

# usage
print repr(v)
print w
if w>v: print "w is bigger"
if not v: print "v is False"
print v+w

#Interactive Interpreter
##############################################################################
# name: main
# purpose: show interpreter oriented functions and internal help system
##############################################################################

# entering an object in the interpreter prints it value
#TODO >>> lst = [1,2,3] # init a list
#TODO >>> lst           # prints on console: [1, 2, 3]
# var _ is the last returned object
#TODO >>> _             # prints on console: [1, 2, 3]

# get a list of varible names, function names, module names in a namespace
dir()             # names defined in current namespace (main)
dir(__builtins__) # module __builtins__ includes all builtin functions
dir(sys)          # module sys provides access to operating system calls
dir(lst)          # methods and members of list object named lst

# document a class or a function
def sqrt(n):        # one liners (the doc string has to be first in the func)
  """Calc the square root of a number."""
def sqrt(n):        # or more elaborate descriptions (blank line by convention)
  """Calc the square root of a number.

  Arguments:
    n - Number to calc square root of.
  Returns:
    Square root of n.
  """
print sqrt.__doc__  # doc string accessible through object __doc__ member

# use the internal help system
help(sqrt)          # print the doc string of a given object
help(help)          # print basic help about the help command
help(sys)           # prints long documentation of the sys module
help()              # start the interactive help system

#Regular Expressions
##############################################################################
# name: main
# purpose: show regular expression usage
##############################################################################

# needed for regular expressions
import re

# matching (actually searching since match checks beginning only)
c = 'Someone, call 911.'          # the string we want to match upon
mo = re.search(r'call',c)         # mo is a match obj instance (or None)
s = mo.group(0)                   # s is 'call' - entire matched string
t = mo.span(0)                    # t is (9,13) - tuple of (start,end) pos
mo = re.search(r'Some(...)',c)    # mo is a match obj instance (or None)
s = mo.group(1)                   # s is 'one' - mo.group(0) is 'Someone'
t = mo.groups()                   # t is ('one') - tuple of mo.group from 1 up
t = mo.span(1)                    # t is (4,7) - mo.span(0) is (0,7)
# global matching (get all found, like /g in perl)
i = re.finditer(r'.o.',c)         # i is an iterator of all mo found
for mo in i: print mo.group(0)    # will print 'Som' 'eon'
l = re.findall(r'.o.',c)          # l is ['Som','eon'] - without mo, just strs
l = re.findall(r'o(.)(.)',c)      # l is [('m','e'),('n','e')] - groups are ok

# substituting
g = "hello world"                 # the string we want to replace in
g = re.sub(r'hello','world',g)    # g is now 'goodbye world'

# splitting
l = re.split(r'\W+',c)          # l is ['Someone','call','911','']
l = re.split(r'(\W+)',c)        # l is ['Someone',', ','call',' ','911','.','']

# pattern syntax (to make things short g0 is retval.group(0), g1 is group(1))
re.search(r'c.11',c)       # . is anything but \n, g0 is 'call'
re.search(r'c.11',c,re.S)  # S is singe-line, . will include \n, g0 is 'call'
re.search(r'911\.',c)      # \ escapes metachars {}[]()^$.|*+?\, g0 is '911.'
re.search(r'o..',c)        # matches earliest, g0 is 'ome'
re.search(r'g?one',c)      # ? is 0 or 1 times, g0 is 'one'
re.search(r'cal+',c)       # + is 1 or more times, g0 is 'call', * for 0 or more
re.search(r'cal{2}',c)     # {2} is exactly 2 times, g0 is 'call'
re.search(r'cal{0,3}',c)   # {0,3} is 0 to 3 times, g0 is 'call', {2,} for >= 2
re.search(r'S.*o',c)       # matches are greedy, g0 is 'Someo'
re.search(r'S.*?o',c)      # ? makes match non-greedy, g0 is 'So'
re.search(r'^.o',c)        # ^ must match beginning of line, g0 is 'So'
re.search(r'....$',c)      # $ must match end of line, g0 is '911.'
re.search(r'9[012-9a-z]',c)# one of the letters in [...], g0 is '91'
re.search(r'.o[^m]',c)     # none of the letters in [^...], g0 is 'eon'
re.search(r'\d*',c)        # \d is digit, g0 is '911'
re.search(r'S\w*',c)       # \w is word [a-zA-Z0-9_], g0 is 'Someone'
re.search(r'..e\b',c)      # \b is word boundry, g0 is 'one', \B for non-boundry
re.search(r' \D...',c)     # \D is non-digit, g0 is ' call', \W for non-word
re.search(r'\s.*\s',c)     # \s is whitespace char [\t\n ], g0 is ' call '
re.search(r'\x39\x31+',c)  # \x is hex byte, g0 is '911'
re.search(r'Some(.*),',c)  # (...) extracts, g1 is 'one', g0 is 'Someone,'
re.search(r'e(one|two)',c) # | means or, g0 is 'eone', g1 is 'one'
re.search(r'e(?:one|tw)',c)# (?:...) does not extract, g0 is 'eone', g1 is None
re.search(r'(.)..\1',c)    # \1 is memory of first brackets, g0 is 'omeo'
re.search(r'some',c,re.I)  # I is case-insensitive, g0 is 'Some'
re.search(r'^Some',c,re.M) # M is multi-line, ^ will match start of each line

#Standard IO
##############################################################################
# name: main
# purpose: show some basic IO and file handling
##############################################################################

# copy textual lines from input file to an output file
fin = None                      # init fin (so cleanup will not throw)
fout = None                     # init fout for same reason
try:                            # file IO is "dangerous"
  fin = open("input.txt","r")   # open input.txt, mode as in c fopen
  fout = open("output.txt","w") # open output.txt, mode as in c fopen
  first = fin.readline()        # read line with "\n" at end, "" (False) on EOF
  print "First line:", line
  for line in fin:              # implements iterator interface (readline loop)
    fout.write(line)            # writes a string to a file
except IOError, e:              # catch IOErrors, e is the instance
  print "Error in file IO: ", e # print exception info if thrown
if fin: fin.close()             # cleanup, close fin only if open (not None)
if fout: fout.close()           # cleanup, close fout only if open (not None)

# read binary records from a file
from struct import *            # needed for struct.unpack function
fin = None                      # init fin (so cleanup will not throw)
try:                            # file IO is "dangerous"
  fin = open("input.bin","rb")  # open input.bin in binary read mode
  s = f.read(8)                 # read 8 bytes (or less) into a string
  while (len(s) == 8):          # continue as long as we have full records
    x,y,z = unpack(">HH<L", s)  # parse 2 big-end ushorts, 1 little-end ulong
    print "Read record: " \
      "%04x %04x %08x"%(x,y,z)  # formatting similar to c printf
    s = f.read(8)               # read another record
except IOError:                 # catch IOErrors, no instance
  pass                          # just continue on problem
if fin: fin.close()             # cleanup, close fin only if open (not None)

# print a line without automatic new-line at end
print "without a new line",

# read entire file to memory at once
data = fin.read()

# format strings sprintf-style (% is a basic property of any str)
s = "%s has %d eyes" % (name,2)

# serialize and deserialize complex objects into files
import pickle                   # needed for serialization logic
ages = {"ron":18,"ted":21}      # create a complex data structure
pickle.dump(ages,fout)          # serialize the map into a writable file
ages = pickle.load(fin)         # deserialize the map from a readable file

# navigating the file system and listing files (dir modules for more info)
import os                       # needed for basic OS interaction
print os.getcwd()               # get current directory
os.chdir('..')                  # change current directory
import glob                     # needed for file globbing with wildcards
lst = glob.glob('*.txt')        # get a list of files according to wildcard
import shutil                   # needed for file management tasks
shutil.copyfile('a.py','a.bak') # copy a file from source to destination

#Useful Functions and Modules
##############################################################################
# name: main
# purpose: show some basic functions and useful modules
# notes: dir() and help() modules to navigate them from the interpreter
##############################################################################

# builtin functions (module __builtins__)
b = len('hello') > len([1,2,3])       # b is True since 5 > 3 (returns length)
print 'a is',chr(ord('a'))            # ord converts chr to num, chr is opposite
i = int('38')+int('0x3a',16)          # i is 96 (integer), long() func similar
print repr(96)                        # repr is a formal (eval) string rep
print str(96)                         # str is for user rep (optional in print)
print hex(58)                         # prints '0x3a'
l = map(hex,[10,20])                  # run hex() on list, l is ['0xa','0x14']
l = range(0,10,3)                     # create lists, l is [0,3,6,9]
i = reduce(lambda x,y:x+y,[1,2,3,4])  # runs func as list reduces 10=((1+2)+3)+4

# import a module (echo) from a package (Sound.Effects) to current namespace
import Sound.Effects.echo         # in code: Sound.Effects.echo.echofilter()
from Sound.Effects import echo    # in code: echo.echofilter()
reload(echo)                      # reload imported module
from Sound.Effects.echo import *  # in code: echofilter()

import sys                        # system specific parameters and functions
sys.exit("bye")                   # exit the program (raises SystemExit)
sys.path.append('c:\\')           # module search path, init from PYTHONPATH

import math                       # math related operations
print math.sqrt(9)                # print 3

import random                     # generate pseudo-random numbers
f = random.random()               # random float in range [0.0,1.0)
i = randint(1,10)                 # random int in range [1,10]
print random.choice(['a','b'])    # returns a random member of the sequence

from datetime import date         # date and calendar related functions
a = date.today()-date(1981,11,21) # supports date arithmetics
print age.days                    # how old am i in days?

import zlib                       # compression related functions
c = zlib.compress("hello world")  # c is a byte string (shorter hopefully)
print zlib.decompress(c)          # prints "hello world"

# command execution in runtime
exec('x=1')                           # interpret new python code in runtime
y = eval('x+1')                       # evaluate an python expression and ret it

import os                             # operating system interface
os.system('del *.txt')                # run commands in the shell (blocking)
os.startfile("calc.exe")              # run commands in the shell (nonblocking)
stdin,stdouterr = os.popen4('dir /b') # returns file handles for child comm.
print stdouterr.read()                # print the output, popen3 gives 3 stream