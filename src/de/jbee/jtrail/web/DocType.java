package de.jbee.jtrail.web;

import de.jbee.jtrail.io.Encodeable;

public enum DocType
		implements Encodeable {
	HTML_TRANSITIONAL,
	HTML_STRICT,
	HTML_FRAMESET,
	XHTML_TRANSITIONAL,
	XHTML_STRICT,
	XHTML_FRAMESET;

	@Override
	public String encode() {
		// TODO Auto-generated method stub
		return null;
	}
}
