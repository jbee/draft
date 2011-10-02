package de.jbee.dict;

public class Word {

	final Letter[] letters;

	Word( final Letter[] letters ) {
		super();
		this.letters = letters;
	}

	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder( letters.length );
		for ( final Letter l : letters ) {
			b.append( l.toString() );
		}
		return b.toString();
	}

}
