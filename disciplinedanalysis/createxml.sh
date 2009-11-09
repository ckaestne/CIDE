#!/bin/sh

# parameter
inputdir=${1}

#which src2srcml > /dev/null
#if [ $? -ne 0 ]; then
#	echo '### program src2srcml missing!'
#	echo '    see http://sdml.info/projects/srcml/'
#	exit -1
#fi

for i in .h .c;
do
	find ${inputdir} -type f -iname "*${i}" -exec src2srcml '{}' '{}'.xml \;
done
