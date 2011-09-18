package de.jbee.todo;

/**
 * Eine Utitlity-Hilfsklasse, um Todos zu analysieren.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 * 
 */
public final class TodoInspector {

	private final ITodo todo;

	TodoInspector( ITodo todo ) {
		super();
		this.todo = todo;
	}

	public boolean isTimeSensitive() {

		return false;
	}
}
