/** Loader.java
 * Loads a resource using a Class loader.
 * AHEAD Project
 * @author Salvador Trujillo
 * Last Update: February 18, 2006 4:00 pm
 */

package org.onekin.xak.refinement;

// import java.util.*;
import java.io.*;
import java.net.URL;

public class Loader
{
	public URL getResource(String resource)
	{
		// System.out.println("Looking up resource: " + resource);
		URL url = null;
		try
		{
			url = getClass().getResource(resource);
			System.out.println("URL 1: " + url.getFile());
			return url ;
		} 
		catch (Exception except)
		{
			System.out.println("warning resource \"" + resource + "\" not found");
		}
		return null;
	}
	public BufferedReader getResourceAsStrean(String sResourceName)
	{
		// System.out.println("Looking up resource: " + sResourceName);
		InputStream stream = null;
		try
		{
			stream = getClass().getResourceAsStream(sResourceName);
			// System.out.println("Strean 1: " + stream);
			if (stream == null)
			{
				ClassLoader loader = getClass().getClassLoader();
				stream = loader.getResourceAsStream(sResourceName);
				// System.out.println("Strean 2: " + stream);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			return br;
		}		
		catch (NullPointerException except)
		{
			System.out.println("warning resource \"" + sResourceName + "\" not found");
		}
		/*
		 * catch (IOException except) { System.out.println("error loading resource
		 * \"" + resource + '"' + except) ; }
		 */
		return null;
	} // of getResource

} // of Loader
