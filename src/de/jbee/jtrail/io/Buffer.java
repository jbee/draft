package de.jbee.jtrail.io;

public final class Buffer<E extends Enum<E>> {

	private final Class<E> idClass;

	private final Object[] data;
	private long loaded = 0;

	private Buffer( Class<E> idClass ) {
		this.idClass = idClass;
		this.data = new Object[idClass.getEnumConstants().length];
	}

	@SuppressWarnings ( "unchecked" )
	public <T> T getValue( Enum<?> e, Loader<T> l, WriteCycle cycle ) {
		validate( e );
		if ( ( loaded & ( 1 << e.ordinal() ) ) == 0 ) {
			setValue( e, l.load( cycle ) ); // oder doch besser gleich hier cycle.load(Loader) mit Loader.load();
		}
		return (T) data[e.ordinal()];
	}

	private void setValue( Enum<?> e, Object value ) {
		data[e.ordinal()] = value;
		loaded |= ( 1 << e.ordinal() );
	}

	private void validate( Enum<?> e ) {
		if ( e == null || e.getClass() != idClass )
			throw new IllegalArgumentException( "Not a buffer member id: " + e );
	}

	public static <E extends Enum<E>> Buffer<E> valueOf( Class<E> idClass ) {
		if ( idClass == null )
			throw new IllegalArgumentException( "null is not a valid idClass" );
		return new Buffer<E>( idClass );
	}

	public int capacity() {
		return data.length;
	}

	public int size() {
		return Long.bitCount( loaded );
	}
}
