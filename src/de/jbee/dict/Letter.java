package de.jbee.dict;

public class Letter {

	/**
	 * Nach dem index *kann* der {@link Indexer} eine pos in die nachfolger einsortieren. dann kann
	 * man binary-search verwenden. Irgendwie müsste man da von verlinkung (gut zum dazwischen
	 * bringen) auf fix indizierung (schneller binary search) umgeschaltet werden, wenn das dict
	 * aufgebaut ist.
	 */
	private final Footprint footprint;
	private final char character;
	private final int index;

	Letter( final int index, final char character ) {
		super();
		this.index = index;
		this.footprint = Footprint.valueOf( 1 << index );
		this.character = character;
	}

	@Override
	public String toString() {
		return String.valueOf( character );
	}

	public Footprint footprint() {
		return footprint;
	}

	public int weight() {
		return 1; //TODO
	}

	public boolean higher( final Letter other ) {
		return index > other.index;
	}

}
