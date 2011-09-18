package de.jbee.todo;

import java.io.Serializable;

public interface IVariance
		extends Serializable {

	boolean isEqual( IVariance other );

	int maximum( int base );

	long maximum( long base );
}
