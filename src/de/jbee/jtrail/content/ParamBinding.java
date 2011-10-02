package de.jbee.jtrail.content;

import de.jbee.jtrail.io.Data;
import de.jbee.jtrail.io.Param;
import de.jbee.jtrail.io.Source;

public final class ParamBinding<E extends Enum<E>> {

	private final Data<?>[] bindings;
	private final Class<E> enumClass;

	private ParamBinding( Class<E> enumClass ) {
		this.enumClass = enumClass;
		this.bindings = new Source<?>[enumClass.getEnumConstants().length];
	}

	public static <E extends Enum<E>> ParamBinding<E> valueOf( Class<E> enumClass ) {
		return new ParamBinding<E>( enumClass );
	}

	@SuppressWarnings("unchecked")
	public <T> Data<T> getData( Param<T> a ) {
		return (Data<T>)bindings[a.getId().ordinal()];
	}

	public <T> void set( Param<T> param, Data<T> data ) {
		if ( param.getId().getClass() != enumClass)
			throw new IllegalArgumentException("The parameter given `"+param+"` is not a member of this ParameterSet: "+toString() );
		bindings[param.getId().ordinal()] = data;
	}
}
