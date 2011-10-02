package de.jbee.jtrail;

import java.util.Calendar;

import de.jbee.jtrail.core.IWalker;
import de.jbee.jtrail.core.convert.Converter;
import de.jbee.jtrail.io.Out;
import de.jbee.jtrail.io.Unique;
import de.jbee.jtrail.io.WriteCycle;

public class Proof {

	final static Converter<String, Integer> STRING_TO_INT = null;
	final static Converter<Integer, String> INT_TO_STRING = null;
	final static Converter<Integer, Calendar> INT_TO_CALENDAR = null;
	final static Converter<Calendar, String> CALENDAR_TO_STRING = null;

	public static class Buffer<T>
			implements Converter<T, T>, ExpireListener {

		T data;

		Buffer( final Expireable e ) {
			e.add( this );
		}

		@Override
		public T convert( final T in, WriteCycle cylce ) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onExpire() {

		}

	}

	public static interface ExpireListener {

		public void onExpire();
	}

	public static interface Expireable {

		public void add( ExpireListener l );
	}

	/**
	 * Selbst kontextlos. Kontext entsteht durch die Einbettung in andere Komponenten. Eine Seite
	 * besteht letztlich auf einer Grundkomponente, die alle anderen verschachtelt enth�lt.
	 */
	public static interface Component<T> {

	}

	public static enum MyTestPage {

		COUNTRY,
		NICKNAME
	}

	public static enum Day {
		MONDAY,
		TUESDAY,
		WEDNESDAY
	}

	public interface OPipe<S, D>
			extends Out<S> {

		public <T> OPipe<S, T> pipe( Converter<D, T> c );
	}

	public static void testAccessibility() {

		IWalker<Calendar, String> in = null;
		IWalker<Unique<Day>, Calendar> out = null;
		OPipe<Unique<Day>, Calendar> out2 = null;

		//		TagFactory<MyTestPage> tags = null;
		//		tags.inputText( MyTestPage.NICKNAME,
		//				in.halt(),
		//				null
		//				);
		//		tags.selectBox( MyTestPage.COUNTRY,
		//				null,
		//				Trail.<Day>in().unify(EnumUnifier.valueOf(Day.class)).halt(),
		//				out2.pipe(CALENDAR_TO_STRING).pipe(STRING_TO_INT)
		//				);
	}
}
