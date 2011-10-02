package de.jbee.dict;

public class ContainsLookup
		implements LookupStrategy {

	private final Letter[] word;

	private int index = 0;
	private boolean found = false;
	private boolean pursureSuffix = false;
	private boolean pursureInfix = false;

	public ContainsLookup( Letter... word ) {
		super();
		this.word = word;
	}

	@Override
	public void charComplete() {

	}

	@Override
	public LookupStrategy pursueInfix( Char infix ) {
		if ( pursureInfix ) {
			infix.lookup( this );
		}
		return this;
	}

	@Override
	public LookupStrategy pursueSuffix( Char suffix ) {
		if ( pursureSuffix ) {
			suffix.lookup( this );
		}
		return this;
	}

	@Override
	public LookupStrategy take( Char character ) {
		Letter letter = character.getLetter();
		Letter wordLetter = word[index];
		if ( letter == wordLetter ) {
			index++;
			if ( index == word.length ) {
				found = true;
			}
			pursureSuffix = !found;
			pursureInfix = false;
		} else {
			pursureSuffix = false;
			pursureInfix = wordLetter.higher( letter );
		}
		return this;
	}

	public boolean isFound() {
		return found;
	}

}
