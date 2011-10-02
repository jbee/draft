package de.jbee.dict;

// Searcher ? Crawler
public interface LookupStrategy {

	LookupStrategy take( Char character );

	LookupStrategy pursueSuffix( Char suffix );

	LookupStrategy pursueInfix( Char infix );

	void charComplete();

	// man könnte auch allgemein sagen, dass man so über diese methoden das wörterbuch durchläuft 
	// eine gewisste flusskontrolle hat der writer dabei, da er selbst für suffix und infix entscheidet,
	// ob er weiter absteigt.

}
