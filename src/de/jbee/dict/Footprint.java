package de.jbee.dict;

/**
 * Wort oder nicht als Teil des Footprint ?
 * 
 * kapselt im grunde nur eine bitmaske der genutzten Tokens.
 */
public class Footprint {

	private final int value;

	private Footprint( final int value ) {
		super();
		this.value = value;
	}

	public static Footprint valueOf( final int value ) {
		return new Footprint( value );
	}

	public Footprint unionWith( final Footprint other ) {
		return new Footprint( this.value | other.value );
	}
}
