/*
 * Created on 23-mar-2005
 *
 */
package org.onekin.xak.composer;

import java.io.File;

/**
 * This class sets a filter to search files into layers 
 * @author Salva
 *
 */
public class MyFileFilter implements java.io.FileFilter 
{
    private String[] ext;
    /**
     * 
     */
/*    public MyFileFilter()
    {
        super();
        this.ext = null ;
    }*/
    /**
     * Takes an array of file extensions
     * @param ext String[]
     */
    public MyFileFilter(String[] ext)
    {
        this.ext = ext;
    }
    
    public boolean accept(File file) {
        if(file.isDirectory())
        {
            if(file.getName().startsWith(".svn")) return false ;
            return true ;
        }
        else if(ext==null&&file.exists())
            return true;
        else
            for(int i=0;i<ext.length;i++)
                if(file!=null&&file.exists()&&file.getName().indexOf(ext[i])>0)
                    return true;
        return false;
    }
    
/*    public static void main(String[] args)
    {        
    } 
*/    
}