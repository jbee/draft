package de.jbee.dict;

import de.jbee.dict.Chars.CharNode;

public interface Indexer {

	CharNode continueWordAt( final CharNode root );

	CharNode completeWordAfter( final CharNode current );
}
