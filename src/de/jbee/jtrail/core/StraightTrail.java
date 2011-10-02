package de.jbee.jtrail.core;

import de.jbee.jtrail.core.convert.Converter;
import de.jbee.jtrail.io.Out;
import de.jbee.jtrail.io.WriteCycle;

public abstract class StraightTrail<S, D>
		implements Trail<S, D> {

	private Out<D> next;

	@Override
	public final Out<D> getNext() {
		return next;
	}

	@Override
	public void setNext( Out<D> next ) {
		this.next = next;
	}

	private final static class ConvertTrail<S, D>
			extends StraightTrail<S, D> {

		private final Converter<S, D> converter;

		protected ConvertTrail( Converter<S, D> c ) {
			this.converter = c;
		}

		@Override
		public void write( S value, WriteCycle cycle ) {
			cycle.write( cycle.convert( value, converter ), getNext() );
		}

	}

	public static <S, D> Trail<S, D> along( Converter<S, D> c ) {
		return new ConvertTrail<S, D>( c );
	}
}
