package de.jbee.jtrail.core;

import de.jbee.jtrail.io.Out;

/**
 * Nimmt werte vom typ S = source entgegen und sorgt daf�r, dass der Zieltyp D = destination
 * geschrieben wird.
 * 
 * @author Jan
 * 
 * @param <S>
 * @param <D>
 */
public interface Trail<S, D>
		extends Out<S> {

	public Out<D> getNext();

	public void setNext( Out<D> next );

}
