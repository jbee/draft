package de.jbee.jtrail.io;


import de.jbee.jtrail.content.ParamBinding;
import de.jbee.jtrail.core.convert.Converter;

public interface WriteCycle {

	public <S,D> D convert( S source, Converter<S, D> c );

	public Selector getSelector(); // die is hier eigentlich fehl am platz da es ums schreiben geht

	public Writer getWriter();

	/**
	 * A cycle normally simply calls {@link Out#write(Object, WriteCycle)}
	 * passing itself as the 2nd parameter. This it once again to give the cycle
	 * the full control on the control-flow.
	 *
	 * @param <T>
	 * @param value
	 * @param out
	 */
	public <T> void write( T value, Out<T> out );

	/**
	 * Der Wert einer Quellen wird w�hrend der Verarbeitung von 3. �ber
	 * {@link #read(Source)} aufgerufen. Der {@link WriteCycle} ruft
	 * {@link Source#getId()} auf. Ist diese:
	 * <dl>
	 * <dt>null</dt>
	 * <dd>wird {@link Data#read(WriteCycle)} der Quelle aufgerufen, das
	 * Ergebnis nicht bespeichert.</dd>
	 * <dt>ungleich null</dt>
	 * <dd>wird gepr�ft, ob der Wert bereits im Speicher ist, ansonsten wird
	 * ebenfalls {@link Data#read(WriteCycle)} der Quelle aufgerufen, das
	 * Ergebnis im Speicher der Id hinlegt und zur�ckgeleifert, sodass es beim
	 * erneuten Zugriff sofort vorliegt.</dd>
	 * </dl>
	 * Auf diese Weise wird jede Quelle nur 1 mal / Schreibzyklus geladen, ohne
	 * dass der Programmierer einer Seite sich gedanken �ber was Wann oder die
	 * Reihenfolge und den generellen Ablauf des Schreibens machen muss.
	 *
	 * Wieder hat der cycle die volle Kontrolle was tats�chlich passiert.
	 *
	 * @param <T>
	 * @param source
	 * @return
	 */
	public <T> T read( Source<T> source );

	/**
	 * Der Wert eines Parameters (von "Komponeneten") wird w�rend der
	 * Verarbeitung von 3. �ber {@link #read(Param)} aufgerufen. Der
	 * {@link WriteCycle} hat zuvor �ber
	 * {@link #beginParameterScope(ParamBinding)} das {@link ParamBinding}
	 * erhalten, zu welchem der Parameter geh�rt. Von diesem l�st er �ber
	 * {@link ParamBinding#getData(Param)} die Quelle auf, welche den Wert
	 * liefern kann. Die wird dann �ber {@link #read(Source)} gelesen.
	 *
	 * @param <T>
	 * @param param
	 * @return
	 */
	public <T> T read( Param<T> param );

	public void beginParameterScope( ParamBinding<?> params );

	public void endParameterScope();

	// um noch mehr informationen im cycle zu haben sollten noch methoden als atomare operationen im cycle realisiert sein:
	// - null-Test (+ Raktion -> Trap ?)
	// - Kontrollstrukturen auf Out<T> basis
	//		- if-than
	//		- if-than-else
	// 		- for
	//		- switch
	//		- loops

	// Evt. die methoden auf verschiedene teil-interfaces unterteilen (wie etwa getWriter und Writer) und in einzelnen objekten kapseln, damit
	// auch der Cycle m�glichst baukastenartig verwendet werden kann, obwohl er der zentrale punkt des generierungsprozesses ist.
}
