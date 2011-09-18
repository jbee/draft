package de.jbee.todo.time;

import java.io.Serializable;

/**
 * Eine genauer Zeitabschnitt, zwischen 2 Zeitscheiben.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public final class TimePeriod
		implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final TimePeriod ANYTIME = new TimePeriod( TimeSlice.BEGINNING,
			TimeSlice.INFINITY );

	private final TimeSlice start;
	private final TimeSlice end;

	TimePeriod( TimeSlice start, TimeSlice end ) {
		this.start = start;
		this.end = end;
	}

	public TimeSlice start() {
		return start;
	}

	public TimeSlice end() {
		return end;
	}

	public TimeDuration duration() {
		return TimeSlice.duration( start, end );
	}

	public boolean contains( TimeSlice slot ) {
		return start.isEqual( slot ) || end.isEqual( slot )
				|| ( startsBefore( slot ) && endsAfter( slot ) );
	}

	public boolean startsBefore( TimeSlice slot ) {
		return start.isBefore( slot );
	}

	public boolean endsAfter( TimeSlice slot ) {
		return end.isAfter( slot );
	}

	public boolean isIrreducible() {
		return start.isEqual( end );
	}
}
