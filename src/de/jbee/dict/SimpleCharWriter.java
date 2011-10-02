package de.jbee.dict;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

public class SimpleCharWriter
		implements LookupStrategy {

	private final Writer out;

	private final LinkedList<Integer> moveBack = new LinkedList<Integer>();
	int offset = 0;

	public SimpleCharWriter( Writer out ) {
		super();
		this.out = out;
	}

	@Override
	public void charComplete() {
		if ( !moveBack.isEmpty() ) {
			Integer backBy = moveBack.pollLast();
			offset -= backBy;
		}
	}

	@Override
	public LookupStrategy take( Char character ) {
		try {
			out.write( character.getLetter().toString() );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public LookupStrategy pursueInfix( Char infix ) {
		try {
			out.write( '\n' );
			for ( int i = 0; i < offset; i++ ) {
				out.write( ' ' );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		moveBack.add( 0 );
		infix.lookup( this );
		return this;
	}

	@Override
	public LookupStrategy pursueSuffix( Char suffix ) {
		offset++;
		moveBack.add( 1 );
		suffix.lookup( this );
		return this;
	}

}
