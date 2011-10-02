package de.jbee.jtrail.web.html;

import java.util.List;


import de.jbee.jtrail.io.Data;
import de.jbee.jtrail.io.Out;
import de.jbee.jtrail.io.Unique;
import de.jbee.jtrail.web.DocType;
import de.jbee.jtrail.web.MimeType;
import de.jbee.jtrail.web.Url;
import de.jbee.jtrail.web.js.JsCall;

/**
 * 
 * Liefert alle (variablen/interessanten) tags
 * 
 * ALLE In / Out 's vertauschen !! bzw. data sollte eher "In" sein- Data zu speziell
 * 
 */
public interface TagFactory<N extends Enum<N>> {

	/* Documents */

	public ATag a( N name, Data<Url> href );

	public BodyTag body();

	public FormTag formGet( N name, Data<Url> action );

	/* Forms */

	public FormTag formPost( N name, Data<Url> action );

	public HeadTag head();

	public HtmlTag html( DocType docType );

	public ImgTag img( N name, Data<Url> src );

	public InputTag inputButton( N name, Data<JsCall> script );

	public InputTag inputCheckbox( N name, Data<Boolean> initalValue, Out<Boolean> submitValue );

	public InputTag inputFile( N name, Data<MimeType> accept );

	public InputTag inputHidden( N name, Data<String> initalValue, Out<String> submitValue );

	//TODO lieber "select" und type-enum: list, radio, combo-box
	public <E> InputTag inputRadio( N name, Data<List<Unique<E>>> options, Data<Unique<E>> initalValue,
		Out<Unique<E>> submitValue );

	public InputTag inputReset( Data<JsCall> script );

	public InputTag inputSubmit( Data<JsCall> script );

	public InputTag inputText( N name, Data<String> initalValue, Out<String> submitValue );

	public PlainText plainText( N name, Data<String> text );

	public <E> SelectTag selectBox( N name, Data<List<Unique<E>>> options, Data<Unique<E>> initialValue,
		Out<Unique<E>> submitValue );

	/* Links */

	public <E> SelectTag selectList( N name, Data<List<Unique<E>>> options, Data<List<Unique<E>>> initialValues,
		Out<Unique<E>> submitValue );

	/* Images */

	public <E> SelectTag selectMultiple( N name, Data<List<Unique<E>>> options, Data<List<Unique<E>>> initialValues,
		Out<List<Unique<E>>> submitValues );

	/* Plain Text */

	public TextareaTag textarea( N name, Data<String> initalValue, Out<String> submitValue );

}
