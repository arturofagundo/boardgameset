package com.fagundo.arturo.set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Used to calculate all possible "set"s of cards (i.e., the cards must either all be the same
 * or all be different for each of the dimensions associated with the cards.
 * 
 * Algorithm:
 * 1. Calculate a comparison vector/bitmask for all possible combinations of 2 cards
 * 2. Use comparison vector/bitmask as the key to store a list of card sets (i.e., in a HashMap)
 * 3. Loop across all comparison vector/bitmasks created previously
 * 	a) Loop across all card sets associated with that comparison vector/bitmask
 *  	(i) Look at all remaining cards in the hand and compare that card to the 
 *  		existing cards in the card set
 *  	(ii)If the resulting comparison vector/bitmask is the same, create a new card set and 
 *  	    add it to the next HashMap
 * 4. Set the curr HashMap equal to the next HashMap
 * 5. Increment the count of cards in each set within the current working HashMap
 * 6. If the count of cards exceeds the set size passed into the constructor, terminate loop, else goto 3
 * 7. Create the list of return values
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
			throw new IllegalArgumentException("Need to supply at least " + setSize + " cards");

		for (int i = 0; i < handOfCards.length; i++) {
			if (handOfCards[i] == null)
				throw new IllegalArgumentException("Null pointer at index " + i);
		}

		// Create a comparison map for all combinations of 2 cards
		Map<CardComparison, List<Integer[]>> currSet = new HashMap<CardComparison, List<Integer[]>>();
		List<Integer[]> sets = new ArrayList<Integer[]>();
		for (int i = 0; i < handOfCards.length - setSize + 1; i++) {
			for (int j = i + 1; j < handOfCards.length - setSize + 2; j++) {
				// Calculate the equality array for the outer double
				CardComparison outerComparison = new CardComparison(handOfCards[i], handOfCards[j]);
				Integer[] cards = new Integer[2];
				cards[0] = i;
				cards[1] = j;
				List<Integer[]> cardList;
				if (currSet.containsKey(outerComparison)) {
					cardList = currSet.get(outerComparison);
				} else {
					cardList = new ArrayList<Integer[]>();
				}
				cardList.add(cards);
				currSet.put(outerComparison, cardList);
			}
		}

		int currSetSize = 2;

		// Now loop through all outer comparison objects and run the
		// corresponding cards against the remaining cards in the deck
		// to see if we can 'extend' the associated set by 1.
		while (currSet != null && currSet.size() > 0 && currSetSize < setSize) {
			Map<CardComparison, List<Integer[]>> nextSet = new HashMap<CardComparison, List<Integer[]>>();
			for (Entry<CardComparison, List<Integer[]>> entry : currSet.entrySet()) {
				CardComparison outerComparison = entry.getKey();
				List<Integer[]> candSetList = entry.getValue();
				for (Integer[] cards : candSetList) {
					for (int k = cards[cards.length - 1] + 1; k < handOfCards.length - setSize + currSetSize + 1; k++) {
						boolean foundSet = true;

						// Compare the candidate card to all current members of
						// the set
						for (int i = 0; i < cards.length; i++) {
							CardComparison inner = new CardComparison(handOfCards[cards[i]], handOfCards[k]);
							if (!outerComparison.equals(inner)) {
								foundSet = false;
								break;
							}
						}

						if (foundSet) {
							// Congratulations! You have a set.
							Integer[] set = new Integer[cards.length + 1];
							for (int i = 0; i < cards.length; i++) {
								set[i] = cards[i];
							}
							set[set.length - 1] = k;
							List<Integer[]> newSetList;
							if (nextSet.containsKey(outerComparison))
								newSetList = nextSet.get(outerComparison);
							else
								newSetList = new ArrayList<Integer[]>();
							newSetList.add(set);
							nextSet.put(outerComparison, newSetList);
						}
					}
				}
			}

			currSetSize++;
			currSet = nextSet;
		}

		// Now we just have to build the output result.
		for (Entry<CardComparison, List<Integer[]>> entry : currSet.entrySet()) {
			for (Integer[] cards : entry.getValue()) {
				sets.add(cards);
			}
		}

		return sets;
	}
}
