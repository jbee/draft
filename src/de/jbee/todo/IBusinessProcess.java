package de.jbee.todo;

import de.jbee.todo.time.TimeSlice;

public interface IBusinessProcess {

	/**
	 * @throws IllegalStateException
	 *             Wenn die Zeitscheibe bereits in der Vergangenheit (der Simulation) liegt
	 */
	void proceedTo( TimeSlice slice );

	/**
	 * @throws DepletedResourceException
	 *             Die Quelle darf nicht mehr in der Höhe des Betrags belastet werden (oder
	 *             unwahrscheinlicher: Das Ziel nicht um den Betrag aufgestockt)
	 */
	void transfer( Money amount, Name<BusinessResource> from, Name<BusinessResource> to );

	/**
	 * @throws BusyResourceException
	 *             Die angeforderte Resource ist belegt oder nicht mehr existent
	 */
	void allocate( Name<BusinessResource> resource );

	/**
	 * Es wird eine der genannten Alternativen benötigt. Welche belegt wird, spielt für den Aufrufer
	 * keine Rolle.
	 * 
	 * @throws BusyResourceException
	 *             Keine der angeforderten Resourcen ist frei (alle sind belegt oder nicht mehr
	 *             existent)
	 */
	void allocateOneOf( Name<BusinessResource>... alternatives );

	/**
	 * @throws BusinessConstraintViolationException
	 *             Die Resource ist bereits nicht mehr existent
	 */
	void exclude( Name<BusinessResource> resource );

	/**
	 * @throws BusinessConstraintViolationException
	 *             Wenn die Kenngröße damit einen Höchst-Grenzwert überschreitet
	 */
	void increase( Name<Characteristic> characteristic, Variation variation );

	/**
	 * @throws BusinessConstraintViolationException
	 *             Wenn die Kenngröße damit einen Mindest-Grenzwert unterschreitet
	 */
	void decrease( Name<Characteristic> characteristic, Variation variation );
}
