package de.jbee.todo;

/**
 * Grad der Abhängigkeit von etwas.
 * 
 * this.guessing = guessing;
 * 
 * this.pain = pain;
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public final class Variance {

	private Variance() {
		// util
	}

	public static final IVariance ZERO = new ZeroVariance();

	public static IVariance independent( int hundredth ) {
		return hundredth == 0
			? ZERO
			: new IndependentVariance( hundredth );
	}

	/**
	 * Da es sich bei der Nummer um eine veränderliches Objekt handeln kann, ist die erzeugte
	 * Abweichung auch variable und somit von anderen Faktoren abhängig.
	 * 
	 * @see MutableInteger
	 */
	public static IVariance dependent( Number hundredth ) {
		return new DependentVariance( hundredth );
	}

	static final class ZeroVariance
			implements IVariance {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isEqual( IVariance other ) {
			return other == ZERO;
		}

		@Override
		public int maximum( int base ) {
			return base;
		}

		@Override
		public long maximum( long base ) {
			return base;
		}
	}

	static abstract class HundredthVariance
			implements IVariance {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isEqual( IVariance other ) {
			return other.maximum( 100 ) == maximum( 100 );
		}

		@Override
		public int maximum( int base ) {
			return base + ( base * hundredth() / 100 );
		}

		@Override
		public long maximum( long base ) {
			return base + ( base * hundredth() / 100L );
		}

		protected abstract int hundredth();
	}

	static final class IndependentVariance
			extends HundredthVariance {

		private static final long serialVersionUID = 1L;

		private final int hundredth;

		public IndependentVariance( int hundredth ) {
			this.hundredth = hundredth;
		}

		@Override
		protected int hundredth() {
			return hundredth;
		}
	}

	static final class DependentVariance
			extends HundredthVariance {

		private final Number hundredth;

		DependentVariance( Number hundredth ) {
			this.hundredth = hundredth;
		}

		@Override
		protected int hundredth() {
			return hundredth.intValue();
		}
	}

	static final class CombinedVariance
			implements IVariance {

		private static final long serialVersionUID = 1L;

		private final IVariance first;
		private final IVariance second;

		CombinedVariance( IVariance first, IVariance second ) {
			this.first = first;
			this.second = second;
		}

		@Override
		public boolean isEqual( IVariance other ) {
			return other.maximum( 100 ) == maximum( 100 );
		}

		@Override
		public int maximum( int base ) {
			return second.maximum( first.maximum( base ) );
		}

		@Override
		public long maximum( long base ) {
			return second.maximum( first.maximum( base ) );
		}
	}

}
