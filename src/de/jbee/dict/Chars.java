package de.jbee.dict;

public class Chars {

	public static final CharNode EPSILON = new EpsilonChar();

	static interface CharNode
			extends Char {

		int calcMaxLength();

		int calcMinLength();

		int calcMaxWeightSum();

		Footprint calcFootprint();

		CharNode indexSuffix( Letter intermediate, Indexer indexer );

		CharNode indexWord( Letter ending );

	}

	private static final class EpsilonChar
			implements CharNode {

		EpsilonChar() {
			super();
		}

		@Override
		public Footprint calcFootprint() {
			return Footprint.valueOf( 0 );
		}

		@Override
		public int calcMaxLength() {
			return 0;
		}

		@Override
		public int calcMaxWeightSum() {
			return 0;
		}

		@Override
		public int calcMinLength() {
			return 0;
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return new WordEnd( ending );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return new HWord( intermediate, indexer );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.charComplete();
		}

		@Override
		public Letter getLetter() {
			return null;
		}

		@Override
		public String toString() {
			return "[EMPTY]";
		}

	}

	/*
	 * Was cool wäre: Eine art Pattern-Suche die ? für ein beliebigen Buchstaben und * für beliebig
	 * viele beliebige Buchstaben performant verarbeiten kann. Wenn der generelle Fall darüber
	 * abgebildet werden können wäre das wohl optimal. Wahrscheinlich ist es auch praktisch noch
	 * Längenangaben wie ?{1-3} anbringen zu können. Dann kann man ziemlich exakt beschreiben was
	 * gesucht wird.
	 */

	public static class HWord
			implements CharNode {

		final Letter letter;
		protected final CharNode suffix;

		HWord( final Letter letter, final CharNode suffix ) {
			this.letter = letter;
			this.suffix = suffix;
		}

		HWord( final Letter letter, final Indexer indexer ) {
			this.letter = letter;
			this.suffix = indexer.completeWordAfter( this );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.take( this ).pursueSuffix( suffix ).charComplete();
		}

		@Override
		public Footprint calcFootprint() {
			return letter.footprint().unionWith( suffix.calcFootprint() );
		}

		@Override
		public int calcMaxLength() {
			return suffix.calcMaxLength() + 1;
		}

		@Override
		public int calcMaxWeightSum() {
			return letter.weight() + suffix.calcMaxWeightSum();
		}

		@Override
		public int calcMinLength() {
			return suffix.calcMinLength() + 1;
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? new HSuffix( letter, suffix )
				: ending.higher( letter )
					? new TSuffix( letter, new WordEnd( ending ), suffix )
					: new IWord( ending, this );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new HWord( letter, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TSuffix( letter, new LSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public Letter getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return letter.toString();
		}

	}

	private static class HSuffix
			extends HWord {

		HSuffix( final Letter letter, final CharNode suffix ) {
			super( letter, suffix );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? this
				: ending.higher( letter )
					? new TWord( letter, new WordEnd( ending ), suffix )
					: new IWord( ending, this );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new HSuffix( letter, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TWord( letter, new LSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer );
		}

	}

	/**
	 * Nur ein Buchstabe - keine Weitere Endung - kein höherer anderer Buchstabe an selber Position
	 */
	public static final class WordEnd
			implements CharNode {

		final Letter letter;

		WordEnd( final Letter letter ) {
			this.letter = letter;
		}

		@Override
		public int calcMaxLength() {
			return 1;
		}

		@Override
		public int calcMaxWeightSum() {
			return letter.weight();
		}

		@Override
		public int calcMinLength() {
			return 1;
		}

		@Override
		public Footprint calcFootprint() {
			return letter.footprint();
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new LWord( intermediate, indexer )
				: intermediate.higher( letter )
					? new TSuffix( intermediate, this, indexer )
					: new ISuffix( letter, new LSuffix( intermediate, indexer ) );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == this.letter
				? this
				: ending.higher( this.letter )
					? new ISuffix( this.letter, new WordEnd( ending ) )
					: new ISuffix( ending, this );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.take( this ).charComplete();
		}

		@Override
		public Letter getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return letter.toString();
		}

	}

	/**
	 * Weitere höhere Buchstaben auf selber Position, aber kein Endung für diesen Buchstaben FIXME -
	 * is das nich unlogisch ? dann müsste es doch auch einen suffix für diesen buchstaben geben,
	 * der das wort abschließt ?! - oder der kommt genau jetzt noch ?!
	 */
	private static class ISuffix
			implements CharNode {

		final Letter letter;
		final CharNode higherInfix;

		ISuffix( final Letter letter, final CharNode higherInfix ) {
			this.letter = letter;
			this.higherInfix = higherInfix;
		}

		@Override
		public Footprint calcFootprint() {
			return letter.footprint().unionWith( higherInfix.calcFootprint() );
		}

		@Override
		public int calcMaxLength() {
			return higherInfix.calcMaxLength(); // hier ohnehin nur 1 und das bringt higher in jedem fall auch
		}

		@Override
		public int calcMaxWeightSum() {
			return Math.max( letter.weight(), higherInfix.calcMaxLength() );
		}

		@Override
		public int calcMinLength() {
			return 1; // dieser knoten hat keine weiteren Endungen - also 1
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new TSuffix( letter, higherInfix, indexer )
				: intermediate.higher( letter )
					? new ISuffix( letter, higherInfix.indexSuffix( intermediate, indexer ) )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? new IWord( letter, higherInfix )
				: ending.higher( letter )
					? new ISuffix( letter, higherInfix.indexWord( ending ) )
					: new IWord( ending, this );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.take( this ).pursueInfix( higherInfix ).charComplete();
		}

		@Override
		public Letter getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return letter.toString();
		}

	}

	private static class IWord
			extends ISuffix {

		IWord( final Letter letter, final CharNode higherInfix ) {
			super( letter, higherInfix );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new TWord( letter, higherInfix, indexer )
				: intermediate.higher( letter )
					? new IWord( letter, higherInfix.indexSuffix( intermediate, indexer ) )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? this
				: ending.higher( letter )
					? new IWord( letter, higherInfix.indexWord( ending ) )
					: new IWord( ending, this );
		}

	}

	/**
	 * kein höherer anderer Buchstabe an selber Position, aber nachfolgende Buchstabe(n)
	 */
	static class LSuffix
			implements CharNode {

		final Letter letter;
		final CharNode suffix;

		LSuffix( final Letter letter, final CharNode suffix ) {
			this.letter = letter;
			this.suffix = suffix;
		}

		LSuffix( final Letter letter, final Indexer indexer ) {
			this.letter = letter;
			this.suffix = indexer.completeWordAfter( this );
		}

		@Override
		public int calcMaxLength() {
			return suffix.calcMaxLength() + 1;
		}

		@Override
		public int calcMaxWeightSum() {
			return letter.weight() + suffix.calcMaxWeightSum();
		}

		@Override
		public int calcMinLength() {
			return suffix.calcMinLength() + 1;
		}

		@Override
		public Footprint calcFootprint() {
			return letter.footprint().unionWith( suffix.calcFootprint() );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new LSuffix( letter, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TSuffix( letter, new LSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? new LWord( letter, suffix )
				: ending.higher( letter )
					? new TSuffix( letter, new WordEnd( letter ), suffix )
					: new IWord( ending, this );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.take( this ).pursueSuffix( suffix ).charComplete();
		}

		@Override
		public Letter getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return letter.toString();
		}

	}

	public static class LWord
			extends LSuffix {

		LWord( final Letter letter, final Indexer indexer ) {
			super( letter, indexer );
		}

		public LWord( final Letter letter, final CharNode suffix ) {
			super( letter, suffix );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new LWord( letter, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TWord( letter, new LSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? this
				: ending.higher( letter )
					? new TWord( letter, new WordEnd( ending ), suffix )
					: new IWord( ending, this );
		}
	}

	/**
	 * Ein höherer Buchstabe an gleicher Position, und auch weitere Endung.
	 * 
	 */
	private static class TSuffix
			implements CharNode {

		final Letter letter;
		final CharNode suffix;
		final CharNode higherInfix;

		TSuffix( final Letter letter, final CharNode higherInfix, final CharNode suffix ) {
			this.letter = letter;
			this.suffix = suffix;
			this.higherInfix = higherInfix;
		}

		TSuffix( final Letter letter, final CharNode higherInfix, final Indexer indexer ) {
			this.letter = letter;
			this.higherInfix = higherInfix;
			this.suffix = indexer.completeWordAfter( this );
		}

		@Override
		public Footprint calcFootprint() {
			return letter.footprint().unionWith( suffix.calcFootprint() ).unionWith(
					higherInfix.calcFootprint() );
		}

		@Override
		public int calcMaxLength() {
			return Math.max( suffix.calcMaxLength() + 1, higherInfix.calcMaxLength() );
		}

		@Override
		public int calcMaxWeightSum() {
			return Math.max( letter.weight() + suffix.calcMaxWeightSum(),
					higherInfix.calcMaxWeightSum() );
		}

		@Override
		public int calcMinLength() {
			return Math.min( suffix.calcMinLength() + 1, higherInfix.calcMinLength() );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new TSuffix( letter, higherInfix, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TSuffix( letter, higherInfix.indexSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? new TWord( letter, higherInfix, suffix )
				: ending.higher( letter )
					? new TSuffix( letter, higherInfix.indexWord( ending ), suffix )
					: new IWord( ending, this );
		}

		@Override
		public void lookup( LookupStrategy strategy ) {
			strategy.take( this ).pursueSuffix( suffix ).pursueInfix( higherInfix ).charComplete();
		}

		@Override
		public Letter getLetter() {
			return letter;
		}

		@Override
		public String toString() {
			return letter.toString();
		}

	}

	private static class TWord
			extends TSuffix {

		public TWord( final Letter letter, final CharNode higherInfix, final Indexer indexer ) {
			super( letter, higherInfix, indexer );
		}

		public TWord( final Letter letter, final CharNode higherInfix, final CharNode suffix ) {
			super( letter, higherInfix, suffix );
		}

		@Override
		public CharNode indexSuffix( final Letter intermediate, final Indexer indexer ) {
			return intermediate == letter
				? new TWord( letter, higherInfix, indexer.continueWordAt( suffix ) )
				: intermediate.higher( letter )
					? new TWord( letter, higherInfix.indexSuffix( intermediate, indexer ), suffix )
					: new TSuffix( intermediate, this, indexer.continueWordAt( suffix ) );
		}

		@Override
		public CharNode indexWord( final Letter ending ) {
			return ending == letter
				? this
				: ending.higher( letter )
					? new TWord( letter, higherInfix.indexWord( ending ), suffix )
					: new IWord( ending, this );
		}

	}

}
