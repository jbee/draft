package de.jbee.dict;

public class Timer {

	private enum Unit {
		NS( "ns" ),
		MiS( "µs" ),
		MS( "ms" ),
		S( "sec" );

		private final String caption;

		Unit( String caption ) {
			this.caption = caption;
		}

		@Override
		public String toString() {
			return caption;
		}

	}

	private long nanoBefore;
	private long nanoAfter;

	public long duration() {
		return nanoAfter - nanoBefore;
	}

	public void start() {
		nanoBefore = System.nanoTime();
	}

	public void stop() {
		nanoAfter = System.nanoTime();
	}

	@Override
	public String toString() {
		long left = duration();
		StringBuilder b = new StringBuilder();
		for ( Unit unit : Unit.values() ) {
			if ( left > 0L ) {
				b.insert( 0, unit );
				b.insert( 0, unit == Unit.S
					? left
					: left % 1000L );
				b.insert( 0, ' ' );
				left = left / 1000L;
			}
		}
		return b.toString();
	}
}
