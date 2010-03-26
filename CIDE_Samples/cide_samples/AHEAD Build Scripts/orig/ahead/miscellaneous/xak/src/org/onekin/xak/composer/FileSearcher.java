/*
 * Created on 23-mar-2005
 *
 * @
 */
package org.onekin.xak.composer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 * This class search for certain files with certain extensions
 * @author Salva
 * @
 *
 */
public class FileSearcher {

    private String[] _sExtensions ;    
    /**
     * This constructor creates a FileSearcher
     * @param sExtensions String
     */
    public FileSearcher(String[] sExtensions)
    {
        super();
        _sExtensions = sExtensions ;                
        String sExtensionsText = "" ;
        for (int i = 0; i < sExtensions.length; i++) {
            sExtensionsText += sExtensions[i] + "/" ;
        }
        System.out.println("FileSearcher created with patterns '" + sExtensionsText + "'") ;
    }

    public ArrayList search (String sBaseDir)
    {
        System.out.println("Searching for files in directory '" + sBaseDir + "'") ;        
		ArrayList aList = fileSearch(new File (sBaseDir), _sExtensions) ;
		System.out.println("Files: " + aList.toString()) ;        
        return aList ;
    }    

    private ArrayList fileSearch (File fBaseDir, String[] sExtensions)
    {
        ArrayList result = null ;
        MyFileFilter filter = new MyFileFilter(sExtensions);
        if (!filter.accept(fBaseDir)) return result ;
        // Search file
        result = getFiles(fBaseDir, filter) ;
        return result ;
    } 
    
    private ArrayList getFiles(File parent, FileFilter filter) {
        System.out.println ("Getting files from directory: '"+ parent.getName() + "'") ;
        ArrayList current = new ArrayList();
        if(parent!=null&&filter!=null) {
            File[] files = parent.listFiles(filter);
            if(files!=null)
                for(int i=0;i<files.length;i++) {
                    if(files[i].isDirectory())
                        current.addAll(getFiles(files[i], filter));
                    else
                        current.add(files[i]);
                }
        }
        return current;
    }    
   
    public static void main(String[] args)
    {
        //String sDir = "c:/temp" ;
        String sDir = "E:\\Salva\\UPV.Doctorado\\3.1.ProyectosOnekin\\05PplGeneration\\04.SourceCode\\CalcWebApp4PPLayers\\base-jboss" ;
        File fDir = new File (sDir) ;
        String[] sExtensions = new String[]{".xml",".jsp",".tld",".rdf"} ;
        String sExtText = "" ;
        for (int i = 0; i < sExtensions.length; i++) {
            sExtText += sExtensions[i] + "/" ;
        }        
        FileSearcher fs = new FileSearcher (sExtensions) ;
        ArrayList aList = fs.search(sDir) ;        
        // System.out.println(aList.toString()) ;
    }
}
