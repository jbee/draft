package de.jbee.jtrail.core.unify;


import de.jbee.jtrail.core.convert.Converter;
import de.jbee.jtrail.io.Unique;

public interface Unifier<E> extends Converter<E, Unique<E>> {

	public Unique<E> unify( E element );
}
