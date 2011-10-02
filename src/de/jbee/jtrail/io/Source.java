package de.jbee.jtrail.io;

/**
 * Eine Datenquelle : Durch die Typisierung kann der {@link WriteCycle} die Daten einem
 * Speicherplatz zuordnen, den er daf�r anlegt.
 * 
 * @author Jan
 * 
 */
public interface Source<T>
		extends Data<T>, Loader<T> {

	public Enum<?> getId();

}
