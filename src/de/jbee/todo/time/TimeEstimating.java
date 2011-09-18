package de.jbee.todo.time;

import org.joda.time.Duration;

/**
 * Quasi eine Fabrik für {@link TimeDuration}s.
 * 
 * Aufwandsschätzungen werden automatisch über den Pain-Level modifiziert.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 * 
 */
public class TimeEstimating {

	private final TimeSlicing slicing;

	TimeEstimating( TimeSlicing slicing ) {
		this.slicing = slicing;
	}

	public TimeDuration effort( Duration optimistic, Duration pessimistic ) {

		return null;
	}
}
