FOR /R %1 %%G IN (*.c) DO src2srcml "%%G" "%%G.xml"
FOR /R %1 %%G IN (*.h) DO src2srcml "%%G" "%%G.xml"
