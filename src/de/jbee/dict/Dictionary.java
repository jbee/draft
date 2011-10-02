package de.jbee.dict;

public interface Dictionary
		extends Learning {

	void lookup( LookupStrategy strategy );
}
