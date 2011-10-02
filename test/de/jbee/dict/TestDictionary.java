package de.jbee.dict;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestDictionary {

	public static void main( String[] args ) {

		File src = new File( "etc/dict/all-de.txt" );
		final List<String> allWords = new ArrayList<String>( 40000 );
		new FileLearner( src ).learnAll( new Learning() {

			@Override
			public void learn( String word ) {
				allWords.add( word );
			}
		} );

		Timer t = new Timer();

		t.start();
		Alphabet alphabet = new Alphabet();
		alphabet.spell( "äabcdefghijklmnöopqrsßtüuvwxyzÄABCDEFGHIJKLMNÖOPQRSTÜUVWXYZ " );
		Dictionary dict = new PrefixTreeDictionary( alphabet );
		for ( String word : allWords ) {
			dict.learn( word );
		}
		t.stop();
		System.out.println( "learn DICT: " + t );
		printDict( dict );

		t.start();
		final HashSet<String> set = new HashSet<String>();
		for ( String word : allWords ) {
			set.add( word );
		}
		t.stop();
		System.out.println( "learn SET :" + t );

		t.start();
		for ( String word : allWords ) {
			ContainsLookup contains = new ContainsLookup( alphabet.spell( word ).letters );
			dict.lookup( contains );
			if ( !contains.isFound() ) {
				System.err.println( word );
				dict.learn( word );
			}
		}
		t.stop();
		System.out.println( "contains DICT:" + t );

		t.start();
		for ( String word : allWords ) {
			set.contains( word );
		}
		t.stop();
		System.out.println( "contains SET :" + t );

	}

	private static void printDict( Dictionary dict ) {
		FileWriter w;
		try {
			w = new FileWriter( new File( "etc/result.txt" ) );
			dict.lookup( new SimpleCharWriter( w ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
