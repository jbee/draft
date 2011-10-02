package de.jbee.jtrail.io;

/**
 * Some basic {@link Out} implementation realy append some content to the
 * {@link WriteCycle} using its {@link Writer}.
 *
 * @author Jan Bernitt
 *
 */
public final class Output {

	private Output() {
		// a util class
	}

	private final static Out<String> PLAIN_TEXT = new Out<String>() {
		@Override
		public void write(String value, WriteCycle cycle) {
			cycle.getWriter().writePlain(value);
		}
	};

	public static Out<String> plainText() {
		return PLAIN_TEXT;
	}

}
