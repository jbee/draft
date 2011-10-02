package de.jbee.jtrail.io;

/**
 * Datenzugriff / Einlesen von Daten ..aus DB oder sonstwoher... K�nnte auch enums oder �hnliches
 * sein, die hier nur "umgesetzt" werden.
 */
public interface Data<T> {

	/**
	 * Der Cycle wird verwendet um Daten zu lesen. Allgemein k�nnte dazu ein Callable vom
	 * Datenknoten (der Data implementiert) erzeugt werden, welches dann dem Cycle zum aufrufen
	 * gegeben wird, damit dieser die kontrolle dar�ber hat, bevor diese wieder an den aufrufer
	 * zur�cktransferiert wird.
	 * 
	 * @param cylce
	 * @return
	 */
	public T read( WriteCycle cycle );
}
