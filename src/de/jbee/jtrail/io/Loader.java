package de.jbee.jtrail.io;

public interface Loader<T> {

	public T load( WriteCycle cycle );
}
