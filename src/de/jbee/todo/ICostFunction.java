package de.jbee.todo;

import de.jbee.todo.time.TimePeriod;

public interface ICostFunction {

	Money sumOf( TimePeriod period );
}
