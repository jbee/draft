package de.jbee.jtrail.io;

public final class Param<T> implements Data<T> {

	private final Enum<?> id;

	private Param( Enum<?> id ) {
		this.id = id;
	}

	public Enum<?> getId() {
		return id;
	}

	@Override
	public T read(WriteCycle cycle) {
		return cycle.read(this);
	}

	public static <T> Param<T> valueOf( Enum<?> id ) {
		return new Param<T>( id );
	}
}
