package de.jbee.todo;

import de.jbee.todo.time.TimeDuration;
import de.jbee.todo.time.TimePeriod;
import de.jbee.todo.time.TimeSlice;

public interface IBusinessEffect {

	/**
	 * Die Dauer des Effekts, wenn er auftritt.
	 */
	TimeDuration effectiveDuration();

	/**
	 * Der Zeitraum, in dem der Effekt auftreten kann/wird.
	 */
	TimePeriod appearancePeriod();

	/**
	 * Der Effekt wirkt sich {@link TimeSlice}-weise (nur Zeitscheiben, in welchen auch Änderungen
	 * auftreten) auf die Umgebung aus.
	 */
	void affect( TimePeriod effectivePeriod, IBusinessProcess process );
}
