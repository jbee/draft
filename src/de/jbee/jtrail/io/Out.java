package de.jbee.jtrail.io;

/**
 * Die Signal-Richtung bezieht sich auf die Daten, welche hier geschrieben werden - also output
 * generieren bzw. weitergegeben werden.
 */
public interface Out<T> {

	/**
	 * Schreibt value auf cycle
	 * 
	 * @param value
	 * @param cycle
	 */
	public void write( T value, WriteCycle cycle );
}
