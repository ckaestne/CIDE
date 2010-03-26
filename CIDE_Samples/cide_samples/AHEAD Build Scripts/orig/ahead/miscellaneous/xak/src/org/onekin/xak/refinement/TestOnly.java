/*
 * Created on 14-abr-2005
 *
 */
package org.onekin.xak.refinement;

/**
 * @author Salva
 *

 */
public class TestOnly {

    /**
     * 
     */
    public TestOnly() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {       
        //String sA = "E:\\Salva\\UPV.Doctorado\\3.1.ProyectosOnekin\\05PplGeneration\\04.SourceCode\\CalcWebApp4PPLayers\\1.0.BASE\\build.xml" ;
        String sA = "E:\\Salva\\UPV.Doctorado/3.1.ProyectosOnekin/05PplGeneration/04.SourceCode/CalcWebApp4PPLayers/1.0.BASE/build.xml" ;
        String sB = "E:\\Salva\\UPV.Doctorado/3.1.ProyectosOnekin/05PplGeneration/04.SourceCode/CalcWebApp4PPLayers/1.0.BASE" ;
        String sC = "" ;
        if (sA.startsWith(sB)) {
            sC = sA.substring(sB.length()) ;
        }
        //int i = sA.indexOf(sB) ;
        //int i = sA.lastIndexOf(sB) ;
        // int i = sA.compareTo(sB) ;
        // System.out.println(i);
        System.out.println(sC);
        
    }
}
