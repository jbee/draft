package de.jbee.todo;

/**
 * Tritt auf, wenn während der Anwendung eines {@link IBusinessEffect}s auf ein
 * {@link IBusinessProcess} dessen interne Gültigkeitsbedingungen verletzt werden.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public class BusinessConstraintViolationException
		extends RuntimeException {

	private static final long serialVersionUID = 1L;

	BusinessConstraintViolationException( String message, Throwable cause ) {
		super( message, cause );
	}

	BusinessConstraintViolationException( String message ) {
		super( message );
	}

}
