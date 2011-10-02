package de.jbee.jtrail.content;

import de.jbee.jtrail.io.Transcoder;

public interface ActionTranscoder<S extends State>
		extends Transcoder<S> {

	public Action<S> getAction();

	public void setAction( Action<S> action );
}
