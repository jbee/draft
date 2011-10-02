package de.jbee.dict;

import de.jbee.dict.Chars.CharNode;

public class PrefixTreeDictionary
		implements Dictionary {

	private final Alphabet alphabet;
	private CharNode root = Chars.EPSILON;

	PrefixTreeDictionary( final Alphabet alphabet ) {
		super();
		this.alphabet = alphabet;
	}

	public void learn( final String word ) {
		insert( alphabet.spell( word ) );
	}

	private void insert( final Word word ) {
		Indexer i = new WordIndexer( word );
		root = i.continueWordAt( root );
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public void lookup( LookupStrategy strategy ) {
		root.lookup( strategy );
	}

}
