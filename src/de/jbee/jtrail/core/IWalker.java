package de.jbee.jtrail.core;

import java.util.Locale;

import de.jbee.jtrail.core.auth.Judge;
import de.jbee.jtrail.core.convert.Converter;
import de.jbee.jtrail.core.unify.Unifier;
import de.jbee.jtrail.core.validate.Sheriff;
import de.jbee.jtrail.io.Unique;

public interface IWalker<S, D> {

	public <O> IWalker<D, O> convert( Converter<D, O> c );

	//public StringPipe tokenise(Converter<D, String> c);

	public IWalker<D, Unique<D>> unify( Unifier<D> u );

	public IWalker<D, D> eye( Sheriff<D> c );

	public IWalker<D, D> i18n();

	public IWalker<D, D> i18n( IWalker<?, Locale> locale );

	public IWalker<D, D> cache( long ms );

	public IWalker<D, D> grant( Judge<? super D> a );

	public IWalker<D, D> onNull( IWalker<D, ?> p );

	public Trail<S, D> halt();

	//	public void process( String in ) {
	//	}
	//	public void assign( final Out<T> p ) {
	//	}

}