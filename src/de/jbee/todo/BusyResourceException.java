package de.jbee.todo;

public class BusyResourceException
		extends BusinessConstraintViolationException {

	private static final long serialVersionUID = 1L;

	public BusyResourceException( String message, Throwable cause ) {
		super( message, cause );
	}

	public BusyResourceException( String message ) {
		super( message );
	}

}
