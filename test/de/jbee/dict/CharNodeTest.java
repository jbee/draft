package de.jbee.dict;

import java.io.OutputStreamWriter;

import de.jbee.dict.Chars.CharNode;

public class CharNodeTest {

	public static void main( final String... args ) {
		final Alphabet a = new Alphabet();
		a.spell( "abcdefghijklmnopqrstuvwxyz" );
		String[] words = { "Hamburg", "Hammer", "Hallo", "Halle", "Hamberg", "Amsel", "Ampel",
				"Anderer", "Anders", "Albern", "Albatros", "Alfons", "Albaner", "Albanien" };
		CharNode root = Chars.EPSILON;
		for ( String word : words ) {
			root = index( word, a, root );
		}
		LookupStrategy w = new SimpleCharWriter( new OutputStreamWriter( System.out ) );
		root.lookup( w );
	}

	private static CharNode index( String word, Alphabet a, CharNode root ) {
		return root = new WordIndexer( a.spell( word ) ).continueWordAt( root );
	}
}
