package de.jbee.jtrail.core.auth;

/**
 * hooks
 * 
 * @author Jan
 * 
 */
public interface Trapper<T> {

	public void trap( T value );
}
