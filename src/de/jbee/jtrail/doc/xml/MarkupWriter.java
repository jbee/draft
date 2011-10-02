package de.jbee.jtrail.doc.xml;

import de.jbee.jtrail.io.Writer;


/**
 * Konkrete Writer k�nnte man als Util klasse schreiben, die in jeder methode den Writer als
 * Parameter erwartet und lediglich einen <i>nicht-String-Typ<i> in String umwandlt und schreibt.
 *
 * @author Jan
 *
 */
public interface MarkupWriter {

	public static enum Tag { A, EM, I, U }; // usw.

	public static enum Attribute { NAME, CLASS, ID }; // usw.

	//public void writeAsInlineJavaScript( Class<?> cls ); // doof weil nicht vorberechnet

	public void beginTag( Writer w, Tag tag );

	public void setAttribute( Writer w, Attribute attribute, String value );
}
