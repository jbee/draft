package de.jbee.dict;

public class Alphabet {

	int letterCount = 0;

	Letter[] letters = new Letter[Character.MAX_VALUE];

	public Word spell( final String word ) {
		final char[] characters = word.toCharArray();
		final Letter[] wordLetters = new Letter[characters.length];
		for ( int i = 0; i < characters.length; i++ ) {
			final char c = characters[i];
			Letter letter = letters[c];
			if ( letter == null ) {
				letter = new Letter( letterCount, c );
				letters[c] = letter;
				++letterCount;
			}
			wordLetters[i] = letter;
		}
		return new Word( wordLetters );
	}

}
