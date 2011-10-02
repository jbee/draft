package de.jbee.jtrail.io;

public interface Decoder<T> {

	public T decode( String value );
}
