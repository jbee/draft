package de.jbee.jtrail.io;

/**
 * Ein eindeutiger Wert in akteullen kontext, der sich als Zahl und text ausdr�cken l�sst
 */
public interface Unique<E> {

	public long numeric();

	public String textual();

	public E value();
}
