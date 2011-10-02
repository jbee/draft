package de.jbee.dict;

import de.jbee.dict.Chars.CharNode;
import de.jbee.dict.Chars.HWord;
import de.jbee.dict.Chars.WordEnd;

public class WordIndexer
		implements Indexer {

	/**
	 * Das zu indizierende Word
	 */
	Letter[] wordLetters;

	int posIndex = 0;

	WordIndexer( Word word ) {
		super();
		this.wordLetters = word.letters;
	}

	/*
	 * Hier kann man durch verschiedene Indexer vorwärts und rückwärs-Indizierung erreichen
	 */

	public CharNode continueWordAt( final CharNode root ) {
		return isLastLetter()
			? root.indexWord( wordLetters[posIndex] )
			: root.indexSuffix( wordLetters[posIndex++], this );
	}

	private boolean isLastLetter() {
		return posIndex == wordLetters.length - 1;
	}

	public CharNode completeWordAfter( final CharNode current ) {
		return isLastLetter()
			? new WordEnd( wordLetters[posIndex] )
			: new HWord( wordLetters[posIndex++], this );
	}

}
