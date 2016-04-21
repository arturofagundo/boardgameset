package com.fagundo.arturo.set;

/*
 * Represents a comparison of the dimensions between 2 cards in the game of set. This 
 * entity can be used compared to other CardComparison objects to determine how 3 or more
 * cards may compare to one another.
 */
public class CardComparison {

	private long bitMask;
	private static final int MAX_DIMENSIONS = 64;

	CardComparison(Card lhCard, Card rhCard) {
		if (lhCard == null || rhCard == null)
			throw new NullPointerException();

		Dimension[] lhDimensions = lhCard.getDimensions();
		Dimension[] rhDimensions = rhCard.getDimensions();
		if (lhDimensions.length != rhDimensions.length)
			throw new IllegalArgumentException("Cards include different number of dimensions");

		if (lhDimensions.length > MAX_DIMENSIONS)
			throw new IllegalArgumentException("The dimensional comparison cannot be expressed as a bit mask");

		for (int i = 0; i < lhDimensions.length; i++) {
			if (lhDimensions[i].getValue() == rhDimensions[i].getValue()) {
				bitMask |= 1 << i;
			}
		}
	}

	/*
	 * Needed to provide the equals operation
	 * 
	 * @return internal bit mask representation of equality of the various
	 * dimensions associated with the cards passed into the constructor of this
	 * object
	 */
	public long getBitMask() {
		return bitMask;
	}

	@Override
	public boolean equals(Object otherCardComparison) {
		if (!(otherCardComparison instanceof CardComparison))
			return false;

		return bitMask == ((CardComparison) otherCardComparison).getBitMask();
	}

	@Override
	public int hashCode() {
		return (int) (bitMask ^ (bitMask >>> 32));
	}
}
