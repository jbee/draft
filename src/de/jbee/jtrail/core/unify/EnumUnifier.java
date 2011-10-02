package de.jbee.jtrail.core.unify;

import de.jbee.jtrail.io.Unique;
import de.jbee.jtrail.io.WriteCycle;

public class EnumUnifier<E extends Enum<E>> implements Unifier<E> {

	private final static class UniqueItem<E extends Enum<E>> implements Unique<E> {

		final E	item;

		UniqueItem( final E item ) {
			this.item = item;
		}

		@Override
		public long numeric() {
			return item.ordinal();
		}

		@Override
		public String textual() {
			return item.name();
		}

		@Override
		public E value() {
			return item;
		}

	}

	@SuppressWarnings( "unchecked" )
	private static EnumUnifier	UNIFIER	= new EnumUnifier();

	@SuppressWarnings( "unchecked" )
	public static <E extends Enum<E>> EnumUnifier<E> getInstance() {
		return UNIFIER;
	}

	@Override
	public Unique<E> convert( final E source, final WriteCycle cycle ) {
		return unify( source );
	}

	@Override
	public Unique<E> unify( final E element ) {
		return new UniqueItem<E>( element );
	}

}
