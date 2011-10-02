package de.jbee.jtrail.content;

public interface Modules<E extends Enum<E> & Modules<E> & Project> {

	public Module getModule();
}
