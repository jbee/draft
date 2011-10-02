package de.jbee.jtrail.io;

public interface Writer {

	public void writePlain( String s );

	public void writeEncoded( String s );


	// der writer sollte nur strings schreiben, die soweit vorberechnet
	// (statisch) sind.
	// die dynamischen inhalte lassen sich dann besser �ber einen adapter
	// in den writer schreiben der dann strings daraus macht und in den writer
	// schreibt.


}
