package de.jbee.todo;

public interface ITodo {

	/**
	 * Todos, die vor einer möglichen umsetzung dieses todos abgeschlossen werden müssen.
	 */
	TodoUnits preconditions();

	/**
	 * Alternative, mögliche Resourcen. Deren Events auftreten, während das Todo bearbeitet wird.
	 * Danach gilt das Todo als bearbeitet.
	 */
	BusinessEffects duringWork();

	/**
	 * Treten auf, solange das todo nicht erledigt ist.
	 */
	BusinessEffects whileIncomplete();

	/**
	 * Treten auf, wenn das Todo erledigt ist.
	 * 
	 * Für die Analyse bedeutet dies: Zunächst können die Todos identifiziert werden, deren
	 * Fertigstellung kurzfristig oder nachhaltig eine positiven Effekt auf die Umgebung haben.
	 * Dijenigen mit den negativsten {@link #whileIncomplete()} Auswirkungen, werden wiederrum als
	 * 2. Sortierkriterium bevorzugt. Danach muss noch geklärt werden, über welchen Weg
	 * {@link #duringWork()} Das Todo ausgeführt wird bzw. welches die beste Alternative ist.
	 */
	BusinessEffects afterCompletion();

}
