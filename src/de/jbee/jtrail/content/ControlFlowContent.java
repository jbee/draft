package de.jbee.jtrail.content;

public abstract class ControlFlowContent implements Content {

	protected final Content	body;

	public ControlFlowContent( final Content body ) {
		assert (body != null);
		this.body = body;
	}

	@Override
	public boolean isFlowControlling() {
		return true;
	}

	@Override
	public boolean isImmutable() {
		return body.isImmutable();
	}

	@Override
	public boolean isSynthetic() {
		return body.isSynthetic();
	}

}
