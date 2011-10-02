package de.jbee.jtrail.core.validate;

/**
 * Es muss m�glich sein, die validierung dort zu machen wo sie sinnvoll ist. Datenzusammenh�nge also
 * in der logik, eingabefehler eher durch JS oder �hnliches direkt im frontend.
 * 
 * 
 * @author Jan
 * 
 * @param <T>
 */
public interface Sheriff<T> {

	/**
	 * validieren
	 * 
	 * @param value
	 * @return
	 */
	public boolean eye( T value );
}
