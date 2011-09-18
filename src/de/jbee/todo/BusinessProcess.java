package de.jbee.todo;

import de.jbee.todo.time.TimeSlice;

public class BusinessProcess
		implements IBusinessProcess {

	private TimeSlice currentSlice;

	@Override
	public void allocate( Name<BusinessResource> resource ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void allocateOneOf( Name<BusinessResource>... alternatives ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrease( Name<Characteristic> characteristic, Variation variation ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exclude( Name<BusinessResource> resource ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void increase( Name<Characteristic> characteristic, Variation variation ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void proceedTo( TimeSlice slice ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transfer( Money amount, Name<BusinessResource> from, Name<BusinessResource> to ) {
		// TODO Auto-generated method stub

	}

}
