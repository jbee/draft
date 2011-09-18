package de.jbee.todo;

/**
 * Bei Softwarentwicklung:
 * 
 * Code-rot / Komplexität
 * 
 * IDEE: verbesserung als Event ohne kosten, aber abhängig vom der fertigstellung eines todo
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public final class MutableInteger
		extends Number {

	private static final long serialVersionUID = 1L;

	private int value;

	public MutableInteger( int initialValue ) {
		super();
		this.value = initialValue;
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value;
	}

	public void setValue( int value ) {
		this.value = value;
	}

}
