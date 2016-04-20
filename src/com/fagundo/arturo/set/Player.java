package com.fagundo.arturo.set;

import java.util.List;
import java.util.Random;

public class Player {

	public static void main(String[] args) {
		// static collection of cards for now.
		final int numDimType = 10;
		final int numDimVal = 10;

		Random random = new Random(0);

		final int numCards = 15;
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
		for (Integer[] set : sets) {
			for (int i = 0; i < set.length - 1; i++) {
				System.out.print("Card " + i + ": " + set[i] + ", ");
			}
			System.out.println("Card " + (set.length - 1) + ": " + set[set.length - 1]);
		}
	}
}
