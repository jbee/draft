package de.jbee.jtrail.io;

import de.jbee.jtrail.content.Page;

/**
 * Beim beginn des {@link WriteCycle} wird jede {@link Page} nach der Klasse des
 * Aufz�hlungstyps zum Datenzugriff gefragt. Damit kann der Cycle ein
 * {@link Object}[] anlegen, das zum Puffern einmal gelesener Daten dient. Da
 * die Daten nur via enum-Wert geschrieben und gelesen werden k�nnen, ist
 * sichergestellt, dass kein unerwarteteter Zugriff m�glich ist, und die Klasse
 * der Objekte stimmt.
 *
 * @author Jan
 *
 * @param <E>
 */
public interface DataLink<E extends Enum<E>> {
	// das ganze is eher eine util-klasse - allerdings nicht statisch damit der enum-typ immer der selbe ist
	// im kontext einer seite. - es ginge auch anders proviziert aber nur falschen gebrauch

	public <T> Source<T> link( E name, Data<T> accessor );
	// vielleicht den accessor als eigenes Interface - hier muss vom cycle der page-state zugreifbar sein, um zu bestimmen,
	// welche daten eigentlich geladen werden m�ssen
}
