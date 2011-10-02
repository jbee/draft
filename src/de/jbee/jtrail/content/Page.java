package de.jbee.jtrail.content;

public interface Page<S extends State> {

	public <E extends Enum<E>> Class<E> getDataLinkClass();

	public Content getHeadContent();

	public Content getBodyContent();
}
