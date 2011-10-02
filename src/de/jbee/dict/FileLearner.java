package de.jbee.dict;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileLearner {

	private final File dictFile;

	public FileLearner( File dictFile ) {
		this.dictFile = dictFile;
	}

	public void learnAll( Learning learning ) {
		DataInputStream in = null;
		FileInputStream fstream = null;
		BufferedReader br = null;
		try {
			// Open the file that is the first
			// command line parameter
			fstream = new FileInputStream( dictFile );
			// Get the object of DataInputStream
			in = new DataInputStream( fstream );
			br = new BufferedReader( new InputStreamReader( in ) );
			String strLine;
			// Read File Line By Line
			while ( ( strLine = br.readLine() ) != null ) {
				learning.learn( strLine.trim() );
			}
		} catch ( IOException e ) {// Catch exception if any
			System.err.println( "Error: " + e.getMessage() );
		} finally {
			// Close the input stream
			try {
				if ( br != null ) {
					br.close();
				}
				if ( in != null ) {
					in.close();
				}
				if ( fstream != null ) {
					fstream.close();
				}
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
	}
}
