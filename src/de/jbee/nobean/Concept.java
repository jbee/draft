package de.jbee.nobean;

import java.util.NoSuchElementException;

public class Concept {

	static interface Optional {

		boolean isNothing();

		boolean isJust();
	}

	static interface Maybe<V>
			extends Optional {

		V fromMaybe();
	}

	static class Nothing
			implements Maybe<java.lang.Object> {

		static final Nothing INSTANCE = new Nothing();

		@Override
		public java.lang.Object fromMaybe() {
			throw new NoSuchElementException( "Nothing here" );
		}

		@Override
		public boolean isJust() {
			return false;
		}

		@Override
		public boolean isNothing() {
			return true;
		}

	}

	static class Just<V>
			implements Maybe<V> {

		private final V value;

		private Just( V value ) {
			super();
			this.value = value;
		}

		public static <V> Maybe<V> a( V value ) {
			return new Just<V>( value );
		}

		@SuppressWarnings ( "unchecked" )
		public static <V> Maybe<V> nothingOnNull( V value ) {
			return value == null
				? (Maybe<V>) Nothing.INSTANCE
				: Just.a( value );
		}

		@Override
		public V fromMaybe() {
			return value;
		}

		@Override
		public boolean isJust() {
			return true;
		}

		@Override
		public boolean isNothing() {
			return false;
		}

	}

	static class Subdata<T, S>
			implements Data<T, S> {

		private final Class<T> fromType;
		private final Class<S> toType;
		private final String section;

		private Subdata( Class<T> fromType, Class<S> toType, String section ) {
			super();
			this.fromType = fromType;
			this.toType = toType;
			this.section = section;
		}

		public static <T, C> Data<T, C> at( Class<T> fromType, Class<C> toType, String section ) {
			return new Subdata<T, C>( fromType, toType, section );
		}

		@Override
		public <V> Field<T, V> sub( Field<? super S, V> path ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <O> Data<T, O> sub( Data<? super S, O> path ) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	static interface Field<T, V> {

	}

	static interface Data<T, S> {

		<V> Field<T, V> sub( Field<? super S, V> path );

		<O> Data<T, O> sub( Data<? super S, O> path );
	}

	static interface List<T, S> {

		<V> Field<T, V> sub( int index, Field<? super S, V> path );

		<O> Data<T, O> sub( int index, Data<? super S, O> path );
	}

	/**
	 * Base interface to implement with different data structures which can be wrapped to
	 * {@link Dataset}s to enrich them with utility methods.
	 */
	static interface NarrowDataset<T>
			extends Optional {

		<V> Maybe<V> get( Field<? super T, V> field );

		<S> NarrowDataset<S> subset( Data<? super T, S> path );

		Dataset<T> enlarge();
	}

	static interface Dataset<T>
			extends Optional {

		NarrowDataset<T> narrow();

		<V> Maybe<V> get( Field<? super T, V> field );

		<V> V get( Field<? super T, V> path, V nothingValue );

		<V> V require( Field<? super T, V> field )
				throws NoSuchElementException;

		<S> Dataset<S> subset( Data<? super T, S> path );

		<S> Dataset<S> subset( Data<? super T, S> path, Dataset<S> nothingValue );

		<S, E extends RuntimeException> Dataset<S> subset( Data<? super T, S> path,
				E nothingException )
				throws E;

		<S> Dataset<S> mandatorySubset( Data<? super T, S> path )
				throws NoSuchElementException;

		<S> Dataset<S> entry( List<? super T, S> path, int index );
	}

	/**
	 * Modeling some example data structures...
	 */

	static interface Titeled {

		Field<Titeled, String> TITLE = null;

	}

	static interface Artist {

		Field<Titeled, String> NAME = null;
	}

	static interface Track
			extends Titeled {

		Data<Track, Artist> ARTIST = Subdata.at( Track.class, Artist.class, "artist" );
		Field<Track, Long> LENGTH = null;

	}

	static interface Album
			extends CD {

		Data<Album, Artist> ARTIST = Subdata.at( Album.class, Artist.class, "artist" );

	}

	static interface CD
			extends Titeled {

		Field<CD, Long> LENGTH = null;
		Data<CD, Track> BONUS_TRACK = Subdata.at( CD.class, Track.class, "bonus-track" );
		List<CD, Track> TRACKS = null;
	}

	static interface CdBox {

		Data<CdBox, Album> CD_1 = Subdata.at( CdBox.class, Album.class, "cd1" );

	}

	/**
	 * And see how it works:
	 */

	static class Proof {

		static final Field<CD, Long> TRACK_1_LENGTH = Album.BONUS_TRACK.sub( Track.LENGTH );

		void test( Dataset<Track> track ) {
			Maybe<Long> v1 = track.get( Track.LENGTH );
			Long v2 = track.get( Track.LENGTH, 1L );
			v2 = track.require( Track.LENGTH );
		}

		void test2( Dataset<Album> album ) {
			album.get( Album.BONUS_TRACK.sub( Track.LENGTH ) );
			album.get( Album.LENGTH );
			album.get( Album.TITLE );
			album.get( TRACK_1_LENGTH );
			album.mandatorySubset( Album.BONUS_TRACK ).get( Track.LENGTH );
			album.subset( Album.BONUS_TRACK ).get( Track.LENGTH );
		}

		void test3( Dataset<CdBox> cdBox ) {
			cdBox.get( CdBox.CD_1.sub( Album.BONUS_TRACK ).sub( Track.LENGTH ) );
			cdBox.mandatorySubset( CdBox.CD_1 ).mandatorySubset( Album.BONUS_TRACK ).get(
					Track.LENGTH );
		}

		void test4( Dataset<Album> album ) {
			album.entry( Album.TRACKS, 1 );
		}
	}
}
