package de.jbee.jtrail.core;

import de.jbee.jtrail.io.Out;

public interface OWalker<S,D> {

	public Out<S> write( Out<S> formatter );
}
