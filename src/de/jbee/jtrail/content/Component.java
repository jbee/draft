package de.jbee.jtrail.content;

import java.util.List;

import de.jbee.jtrail.io.Data;
import de.jbee.jtrail.io.Out;
import de.jbee.jtrail.io.Param;
import de.jbee.jtrail.io.Source;
import de.jbee.jtrail.io.Template;
import de.jbee.jtrail.io.WriteCycle;

public interface Component {

	public void clear();

	public void add( Content c );

	public Content parse( Template t );

	public final static class ParamWrapper<E extends Enum<E>> extends ControlFlowContent {

		private final ParamBinding<E> params;

		private ParamWrapper( Content body, Class<E> paramEnumClass ) {
			super(body);
			this.params = ParamBinding.valueOf( paramEnumClass );
		}

		@Override
		public void write(WriteCycle cycle) {
			cycle.beginParameterScope(params); // damit der die params auf seinen param-stack aufbringen kann, sodass die nun g�ltig sind
			body.write(cycle);
			cycle.endParameterScope(); // die params wieder vom stack sodass die evt. davor aufgebrachten g�ltig werden
		}

		public <T> ParamWrapper<E> bind( Param<T> param, Data<T> data ) {
			params.set(param, data);
			return this;
		}

		public static <E extends Enum<E>> ParamWrapper<E> wrap( Content body, Class<E> paramEnumClass ) {
			return new ParamWrapper<E>( body, paramEnumClass );
		}

	}

	public static class TestComponent implements Content, Out<Integer> {

		static enum MyTestParams {
			FOO, BAR;
		}

		final static Param<Integer> FOO_PARAM = Param.valueOf(MyTestParams.FOO);
		final static Param<List<String>> BAR_PARAM = Param.valueOf(MyTestParams.BAR);

		// man k�nnte das so nutzen, wenn man hier ein Erbauer draus macht, den man im static block zun�chst �ber
		// clear() zur�cksetzet, und die eigentliche liste mit einer zuweisungsmethode, die List<Content> rausgibt auf
		// einen feld in der jeweiligen komponente zuweist -ein template kann dann ein parameter sein, den man nutzt,
		// um diese zuweisung zu parametrisieren.

		public final static Component BODY = null;

		static {
			BODY.add(null);
		}

		public static final Content BODY_TEMPLATE_A = BODY.parse( null );

		public static Content getInstance( Source<Integer> foo, Source<List<String>> bar ) {
			return ParamWrapper.wrap( BODY_TEMPLATE_A, MyTestParams.class ).bind(FOO_PARAM, foo).bind(BAR_PARAM, bar);
		}

		@Override
		public boolean isImmutable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isSynthetic() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFlowControlling() {
			// TODO Auto-generated method stub
			return false;
		}



		@Override
		public void write(Integer value, WriteCycle cycle) {
			//params.set(MyTestParams.FOO_PARAM, Constant.valueOf(value));
			// entweder man muss hier synchronisieren- was bl�d is - oder f�r jeden neuen parameter auch eine neue instanz erzeugen,
			// da sonst nicht mehrere threads gleichzeitig arbeiten k�nnen ohne sich gegenseititg in die paramde zu fahren
		}

		@Override
		public void write(WriteCycle cycle) {
			// contents.wirte(cycle); // oder so - halt den inhalt generieren
		}

	}

}
