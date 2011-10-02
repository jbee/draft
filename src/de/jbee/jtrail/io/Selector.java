package de.jbee.jtrail.io;

import de.jbee.jtrail.content.Action;
import de.jbee.jtrail.content.Module;
import de.jbee.jtrail.content.Page;
import de.jbee.jtrail.content.Project;

/**
 * Beschreiben welche(r) Pfad(e) durchlaufen werden sollen
 */
public interface Selector {

	public Project getProject();

	public Module getModule();

	public Page<?> getPage(); // oder �berall die enums liefern ?

	public Action<?> getAction();
}
