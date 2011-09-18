package de.jbee.todo.time;

import java.io.Serializable;

import de.jbee.todo.IVariance;
import de.jbee.todo.Variance;

public final class TimeDuration
		implements Serializable, Comparable<TimeDuration> {

	private static final long serialVersionUID = 1L;

	private final int sliceCount;
	private final IVariance variance;

	private TimeDuration( int sliceCount, IVariance variance ) {
		this.sliceCount = sliceCount;
		this.variance = variance;
	}

	public static TimeDuration exact( int sliceCount ) {
		return new TimeDuration( sliceCount, Variance.ZERO );
	}

	public int intValue() {
		return sliceCount;
	}

	public TimeDuration maximum() {
		return exact( variance.maximum( sliceCount ) );
	}

	public boolean isLongerThan( TimeDuration other ) {
		return maximum().sliceCount > other.maximum().sliceCount;
	}

	public boolean isShorterThan( TimeDuration other ) {
		return !isLongerThan( other ) && !isEqual( other );
	}

	public boolean isEqual( TimeDuration other ) {
		return maximum().sliceCount == other.maximum().sliceCount;
	}

	public boolean isIdentical( TimeDuration other ) {
		return sliceCount == other.sliceCount && variance.isEqual( other.variance );
	}

	@Override
	public int compareTo( TimeDuration other ) {
		return Integer.signum( maximum().sliceCount - other.maximum().sliceCount );
	}
}
