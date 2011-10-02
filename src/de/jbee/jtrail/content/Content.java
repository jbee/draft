package de.jbee.jtrail.content;

import de.jbee.jtrail.io.Writeable;

public interface Content extends Writeable {

	/**
	 * Kontrollstrukturen generieren selbst keinen output, sind sogesehen transparent was die
	 * ausgabe angeht. Es ist f�r den Template-Mechanismus, Validierung und Visualisierung der
	 * Seitenstruktur jedoch wichtig, Kontrollstrukturen von Ausgabe-Bl�cken zu unterscheinden, was
	 * hierdurch m�glich wird.
	 * 
	 * TODO besseren namen
	 * 
	 * @return
	 */
	public boolean isFlowControlling();

	/**
	 * Der Inhalt ist fix und wurde beim Laden der Seite bestimmt - ist also nicht von weiteren
	 * Laufzeit-Daten - oder allgemeiner - von einem Zustand abh�ngig.
	 * 
	 * @return
	 */
	public boolean isImmutable();

	/**
	 * (Aus dem Tamplate) generierter (statischer) Inhalt ? -> true
	 * 
	 * @return
	 */
	public boolean isSynthetic();
}
