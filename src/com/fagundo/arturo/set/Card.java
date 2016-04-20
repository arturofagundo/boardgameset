package com.fagundo.arturo.set;

import java.util.Arrays;

/*
 * Used to implement the card game Set
 */
public class Card {

	private final Dimension[] dimensions;

	Card(Dimension[] dimensions) {
		if (dimensions == null)
			throw new NullPointerException();

		this.dimensions = new Dimension[dimensions.length];
		for (int i = 0; i < dimensions.length; i++) {
			if (dimensions[i] == null)
				throw new IllegalArgumentException();

			this.dimensions[i] = new Dimension(dimensions[i]);
		}
	}

	/*
	 * Return defensive copy of internal dimensions.
	 * 
	 * @returns dimensions
	 */
	public Dimension[] getDimensions() {
		Dimension[] safeCopy = new Dimension[dimensions.length];
		for (int i = 0; i < dimensions.length; i++) {
			safeCopy[i] = new Dimension(dimensions[i]);
		}
		return safeCopy;
	}

	@Override
	public String toString() {
		return Arrays.toString(dimensions);
	}
}
