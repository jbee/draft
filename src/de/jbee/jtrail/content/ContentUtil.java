package de.jbee.jtrail.content;

import java.util.Map;

import de.jbee.jtrail.io.Data;
import de.jbee.jtrail.io.Out;
import de.jbee.jtrail.io.WriteCycle;

public class ContentUtil {

	private static class ForEachContent<T> implements Content {
		private final Data<Iterable<T>>	entries;
		private final Out<? super T>[]	body;

		ForEachContent( final Data<Iterable<T>> elements, final Out<? super T> ... body ) {
			this.entries = elements;
			this.body = body;
		}

		@Override
		public boolean isFlowControlling() {
			return true;
		}

		@Override
		public boolean isImmutable() {
			return true; // sonst br�uchte man wohl keine forschleife ?!
		}

		@Override
		public boolean isSynthetic() {
			return false; // nein, da expliziet vom coder erzeugt
		}

		@Override
		public void write( final WriteCycle cycle ) {
			for ( T entry : entries.read( cycle ) ) {
				for ( Out<? super T> bodyPart : body ) {
					cycle.write( entry, bodyPart );
				}
			}
		}

	}

	//	public static <T extends State> Content forEach( Data<Iterable<T>> entries, Component<? super T> body ) {
	//
	//		return null;
	//	}

	/**
	 * Iteriert �ber die eintr�ge und leitet jeden an alle entries weiter, welche diesen umwandeln
	 * und
	 */
	public static <T> Content forEach( final Data<Iterable<T>> entries, final Out<? super T> ... body ) {
		return new ForEachContent<T>( entries, body );
	}

	public static Content ifThen( final Data<Boolean> condition, final Content thanBody ) {

		return null;
	}

	public static Content ifThenElse( final Data<Boolean> condition, final Content thanBody, final Content elseBody ) {

		return null;
	}

	// TODO Map<T, Content> durch eine Klasse ersetzen, die dies umsetzt
	public static <T> Content switchOf( final Data<T> value, final Map<T, Content> cases, final Content defaultCase ) {

		return null;
	}

}
