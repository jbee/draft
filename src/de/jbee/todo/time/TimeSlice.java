package de.jbee.todo.time;

import java.io.Serializable;

public final class TimeSlice
		implements Comparable<TimeSlice>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final TimeSlice BEGINNING = new TimeSlice( 0 );
	public static final TimeSlice INFINITY = new TimeSlice( Integer.MAX_VALUE );

	private final int nr;

	private TimeSlice( int nr ) {
		this.nr = nr;
	}

	public static TimeSlice at( int nr ) {
		validateSlice( nr );
		return new TimeSlice( nr );
	}

	private static void validateSlice( int nr ) {
		if ( nr <= 0 ) {
			throw new IllegalArgumentException( "Has to be grether than zero - but was: " + nr );
		}
	}

	public static TimeDuration duration( TimeSlice start, TimeSlice end ) {
		return TimeDuration.exact( end.nr - start.nr );
	}

	@Override
	public int compareTo( TimeSlice other ) {
		return Integer.signum( nr - other.nr );
	}

	public boolean isAfter( TimeSlice other ) {
		return nr > other.nr;
	}

	public boolean isBefore( TimeSlice other ) {
		return nr < other.nr;
	}

	public boolean isEqual( TimeSlice other ) {
		return nr == other.nr;
	}
}
