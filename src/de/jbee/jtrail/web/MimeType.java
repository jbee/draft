package de.jbee.jtrail.web;

import de.jbee.jtrail.io.Encodeable;

public interface MimeType extends Encodeable {

	public static enum MediaType {
		TEXT, // = f�r Textdateien
		IMAGE, // = f�r Grafikdateien
		VIDEO, // = f�r Videodateien
		AUDIO, // = f�r Sound-Dateien
		APPLICATION, // = f�r Dateien, die an ein bestimmtes Programm gebunden sind
		MULTIPART, // = f�r mehrteilige Daten
		MESSAGE, // = f�r Nachrichten
		MODEL, // = f�r Dateien, die mehrdimensionale Strukturen repr�sentieren
	}

	public MediaType getMediaType();

	public String getSubtype();

}
