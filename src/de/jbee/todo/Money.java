package de.jbee.todo;

import java.io.Serializable;

import de.jbee.todo.time.TimeDuration;

/**
 * Geldbetrag
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public final class Money
		implements Comparable<Money>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final Money NOTHING = exact( 0L );

	private final long amount;
	private final IVariance variance;

	public Money( long amount, IVariance variance ) {
		this.amount = amount;
		this.variance = variance;
	}

	private Money amountingTo( long amount ) {
		return new Money( amount, variance );
	}

	private static Money exact( long amount ) {
		return new Money( amount, Variance.ZERO );
	}

	public Money multipliedBy( TimeDuration duration ) {
		return amountingTo( amount * duration.intValue() );
	}

	public boolean isCredit() {
		return amount > 0L;
	}

	public boolean isDebit() {
		return amount < 0L;
	}

	public boolean isNeutral() {
		return amount == 0L;
	}

	public Money maximum() {
		return exact( variance.maximum( amount ) );
	}

	@Override
	public int compareTo( Money other ) {
		return Long.signum( maximum().amount - other.maximum().amount );
	}
}
