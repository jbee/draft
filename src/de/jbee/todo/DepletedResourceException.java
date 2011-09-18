package de.jbee.todo;

public class DepletedResourceException
		extends BusinessConstraintViolationException {

	private static final long serialVersionUID = 1L;

	public DepletedResourceException( String message, Throwable cause ) {
		super( message, cause );
	}

	public DepletedResourceException( String message ) {
		super( message );
	}

}
