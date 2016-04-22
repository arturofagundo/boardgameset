package com.fagundo.arturo.set;

import java.util.Arrays;

/*
 * Represents a comparison of the dimensions between 2 cards in the game of set. This 
 * entity can be used compared to other CardComparison objects to determine how 3 or more
 * cards may compare to one another.
 * 
 * @throws IllegalArgumentException if the type of dimension does not match across all 
 * dimensions in the dimension array.
 */
public class CardComparison {

	private int[] bitMask;
	private static final int SIZE_OF_INT = 32;

	CardComparison(Card lhCard, Card rhCard) {
		if (lhCard == null || rhCard == null)
			throw new NullPointerException();

		Dimension[] lhDimensions = lhCard.getDimensions();
		Dimension[] rhDimensions = rhCard.getDimensions();
		if (lhDimensions.length != rhDimensions.length)
			throw new IllegalArgumentException("Cards include different number of dimensions");

		// Need to calculate the size of the bitMask array
		final int bitMaskLen = lhDimensions.length / SIZE_OF_INT + 1;
		bitMask = new int[bitMaskLen];

		int bitMaskIndex = 0;
		for (int i = 0; i < lhDimensions.length; i++) {
			bitMaskIndex = i / SIZE_OF_INT;
			if (lhDimensions[i].getType() != rhDimensions[i].getType()) {
				throw new IllegalArgumentException("Dimension types " + i + " do not match");
			}

			if (lhDimensions[i].getValue() == rhDimensions[i].getValue()) {
				bitMask[bitMaskIndex] |= 1 << i;
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
	public int[] getBitMask() {
		// return defensive copy of internal bitMask array
		return Arrays.copyOf(bitMask, bitMask.length);
	}

	@Override
	public boolean equals(Object otherCardComparison) {
		if (!(otherCardComparison instanceof CardComparison))
			return false;

		int[] otherBitMask = ((CardComparison) otherCardComparison).getBitMask();
		if (otherBitMask.length != bitMask.length)
			return false;

		for (int i = 0; i < bitMask.length; i++) {
			if (bitMask[i] != otherBitMask[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(bitMask);
	}
}
