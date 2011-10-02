package de.jbee.jtrail.core.auth;

public interface Judge<T> {

	/**
	 * kontollieren/authorisieren und freigeben von objekten / aktionen
	 */
	public boolean judge( T accessedValue );
}
