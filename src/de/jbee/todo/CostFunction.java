package de.jbee.todo;

import de.jbee.todo.time.TimePeriod;

public final class CostFunction {

	private CostFunction() {
		// util
	}

	public static ICostFunction linear( Money costsPerSlice ) {
		return new LinearCostFunction( costsPerSlice );
	}

	public static ICostFunction onceOnly( Money costsInSlice ) {
		return new OnceOnlyCostFunction( costsInSlice );
	}

	private static final class LinearCostFunction
			implements ICostFunction {

		private final Money costsPerSlice;

		LinearCostFunction( Money costsPerSlice ) {
			this.costsPerSlice = costsPerSlice;
		}

		@Override
		public Money sumOf( TimePeriod period ) {
			return costsPerSlice.multipliedBy( period.duration() );
		}
	}

	private static final class OnceOnlyCostFunction
			implements ICostFunction {

		private final Money costsInSlice;

		OnceOnlyCostFunction( Money costsInSlice ) {
			this.costsInSlice = costsInSlice;
		}

		@Override
		public Money sumOf( TimePeriod period ) {
			return costsInSlice;
		}
	}
}
