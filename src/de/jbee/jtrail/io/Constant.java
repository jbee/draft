package de.jbee.jtrail.io;

public final class Constant<T> implements Source<T> {

	private final T value;

	private Constant( T value ) {
		this.value = value;
	}

	@Override
	public Enum<?> getId() {
		return null;
	}

	@Override
	public T read(WriteCycle cycle) {
		return value;
	}

	public static <T> Constant<T> valueOf( T value ) {
		return new Constant<T>( value );
	}

	@Override
	public T load( WriteCycle cylce ) {
		return value;
	}

}
