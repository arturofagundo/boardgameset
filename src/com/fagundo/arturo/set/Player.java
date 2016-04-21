package com.fagundo.arturo.set;

import java.util.List;
import java.util.Random;

public class Player {

	public static void main(String[] args) {
		// static collection of cards for now.
		final int numDimType = 4;
		final int numDimVal = 3;

		Random random = new Random(0);

		final int numCards = 9;
		Card[] handOfCards = new Card[numCards];
		for (int i = 0; i < numCards; i++) {
			Dimension[] currDimensions = new Dimension[numDimType];
			for (int j = 0; j < numDimType; j++) {
				int randomDimVal = random.nextInt(numDimVal);
				currDimensions[j] = new Dimension(j, randomDimVal);
			}
			handOfCards[i] = new Card(currDimensions);
		}

		System.out.println("Hand of Cards:");
		for (int i = 0; i < handOfCards.length; i++) {
			System.out.println(" " + handOfCards[i]);
		}

		Solver solver = new Solver(3);
		List<Integer[]> sets = solver.getSets(handOfCards);

		System.out.println("Sets: ");
		int setCount = 0;
		for (Integer[] set : sets) {
			System.out.println("Set " + setCount + ":");
			for (int i = 0; i < set.length - 1; i++) {
				if (set[i] < 10)
					System.out.println("Card " + set[i] + " : " + handOfCards[set[i]].toString() + ", ");
				else
					System.out.println("Card " + set[i] + ": " + handOfCards[set[i]].toString() + ", ");
			}
			if (set[set.length - 1] < 10)
				System.out.println("Card " + set[set.length - 1] + " : " + handOfCards[set[set.length - 1]]);
			else
				System.out.println("Card " + set[set.length - 1] + ": " + handOfCards[set[set.length - 1]]);
			setCount++;
		}
	}
}
