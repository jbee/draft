package de.jbee.dict;

/**
 * A {@link Letter} in a {@link Word}.
 */
public interface Char {

	Letter getLetter();

	void lookup( LookupStrategy strategy );

	// hier später alle "info" methoden um das zeichem im wort als information auszuwerten
	// das wörterbuch kann man hier dann nicht mehr verändern 
}
