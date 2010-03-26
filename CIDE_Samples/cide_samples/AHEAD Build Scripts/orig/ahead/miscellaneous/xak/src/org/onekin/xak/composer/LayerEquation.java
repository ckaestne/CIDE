/*
 * Created on 14-abr-2005
 *
 */
package org.onekin.xak.composer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * This class represents an AHEAD Layer Equation, and reads layers from '.equation' files
 * @author Salva
 *
 */
public class LayerEquation {
    // Define a static logger variable so that it references the
    // Logger instance named "LayerEquation".
    static Logger logger = Logger.getLogger(LayerEquation.class);     

    // Private Field
    /** 
     * This variable stores equation layers
     */
    private ArrayList _layers ;
    private File _fLayerEquation ;
    private String _sEquationFileContent ;
    
    /**
     * This constructor buils a Layer Equation and reads it from File 'fLayerEquation'
     * @param fLayerEquation File
     */
    public LayerEquation(File fLayerEquation) {
        super();
        _fLayerEquation = fLayerEquation ;
        this.readLayersFromEquation() ;
    }
    /**
     * 
     * @param fLayerEquation File This variable contains descriptor to 'layer.equation' file
     * @return
     */
    private boolean readLayersFromEquation ()
    {
        _layers = new ArrayList () ;
        StringWriter sWriter = new StringWriter () ;
        try {
            logger.debug("Reading from File: \"" + _fLayerEquation.getCanonicalPath() +"\"") ;            
            FileReader fReader = new FileReader (_fLayerEquation) ;
            BufferedReader bReader = new BufferedReader(fReader) ;
            String sLine = bReader.readLine() ;
            while (sLine != null)
            {
                if (!sLine.startsWith("#")) _layers.add(sLine) ;
                sWriter.write(sLine+"\n") ;
                sLine = bReader.readLine() ;
            }
            logger.debug("Content File:\n"+sWriter.toString()) ;
            logger.debug("Content Layers ["+ String.valueOf(_layers.size()) +"]:\n"+_layers.toString()) ;
        } catch (Exception e) {
            logger.error("Error: "+e.getMessage()) ;
            logger.debug("Content File:\n"+sWriter.toString()) ;
            logger.debug("Content Layers:\n"+_layers.toString()) ;
        } finally {
            _sEquationFileContent = sWriter.toString() ;
        }
        return true ;
    }
    /**
     * This method returns a set of layers read from equation.
     * @return ArrayList
     * @see java.util.ArrayList
     */
    public ArrayList getLayers ()
    {
        return _layers ;
    }
    /**
     * Returns the content of 'layer.equation' file
     */
    public String toString () 
    {
        return _sEquationFileContent ;
    }
    /**
     * Testing purposes
     * @param args
     */
    public static void main(String[] args)
    {
        String sPath = "E:\\Salva\\UPV.Doctorado\\3.1.ProyectosOnekin\\05PplGeneration\\04.SourceCode\\CalcWebApp4PPLayers\\base-tomcat-es_ES.equation" ; 
        File fTest = new File (sPath) ;
        LayerEquation layerEquation = new LayerEquation (fTest) ;
        layerEquation.getLayers() ;
        layerEquation.toString() ;
    }

}
