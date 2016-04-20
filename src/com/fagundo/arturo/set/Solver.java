package com.fagundo.arturo.set;

import java.util.ArrayList;
import java.util.List;

/*
 * Used to calculate all possible "set"s of cards (i.e., the cards must either all be the same
 * or all be different for each of the dimensions associated with the cards.
 */
public class Solver {

	private final int setSize;

	Solver(int setSize) {
		if (setSize < 3)
			throw new IllegalArgumentException("Minimum set size is 3");

		this.setSize = setSize;
	}

	/*
	 * Get the list of "sets" in the Hand of Cards provided
	 * 
	 * @return list of "sets" (i.e., triples of cards) where the dimensions for
	 * all setSize sets either agree or are different
	 */
	public List<Integer[]> getSets(Card[] handOfCards) {
		if (handOfCards == null)
			throw new NullPointerException();

		if (handOfCards.length < setSize)
			throw new IllegalArgumentException("Need to supply at least 3 cards");

		for (int i = 0; i < handOfCards.length; i++) {
			if (handOfCards[i] == null)
				throw new IllegalArgumentException("Null pointer at index " + i);
		}

		List<Integer[]> sets = new ArrayList<Integer[]>();
		for (int i = 0; i < handOfCards.length; i++) {
			for (int j = i + 1; j < handOfCards.length; j++) {
				// Calculate the equality array for the outter double
				CardComparison outterComparison = new CardComparison(handOfCards[i], handOfCards[j]);
				for (int k = j + 1; k < handOfCards.length; k++) {
					CardComparison inner1 = new CardComparison(handOfCards[j], handOfCards[k]);
					CardComparison inner2 = new CardComparison(handOfCards[i], handOfCards[k]);
					if (outterComparison.equals(inner1) && outterComparison.equals(inner2)) {
						// Congratulations! You have a set.
						Integer[] set = new Integer[3];
						set[0] = i;
						set[1] = j;
						set[2] = k;
						sets.add(set);
					}
				}
			}
		}

		return sets;
	}
}
