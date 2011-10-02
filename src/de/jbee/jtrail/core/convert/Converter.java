package de.jbee.jtrail.core.convert;

import de.jbee.jtrail.io.WriteCycle;

public interface Converter<S,D> {

	public D convert( S value, WriteCycle cycle );
}
