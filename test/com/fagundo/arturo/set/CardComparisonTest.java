package com.fagundo.arturo.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CardComparisonTest {
	Dimension[] dimensions1;
	Dimension[] dimensions2;
	Dimension[] dimensions3;

	Card card1;
	Card card2;
	Card card3;

	CardComparison selfCompare;
	CardComparison allFalseCompare;

	@Before
	public final void testSetup() {
		dimensions1 = new Dimension[3];
		dimensions2 = new Dimension[3];
		dimensions3 = new Dimension[4];

		for (int i = 0; i < dimensions1.length; i++) {
			dimensions1[i] = new Dimension(i, i);
			dimensions2[i] = new Dimension(i, i + 1);
			dimensions3[i] = new Dimension(i, i);
		}
		dimensions3[dimensions3.length - 1] = new Dimension(dimensions3.length - 1, dimensions3.length - 1);

		card1 = new Card(dimensions1);
		card2 = new Card(dimensions2);
		card3 = new Card(dimensions3);

		selfCompare = new CardComparison(card1, card1);
		allFalseCompare = new CardComparison(card1, card2);
	}

	@Test
	public final void testGetBitMask() {
		long bitMask = selfCompare.getBitMask();
		assertEquals(bitMask, (0x1 << dimensions1.length) - 1);

		bitMask = allFalseCompare.getBitMask();
		assertEquals(bitMask, 0);
	}

	@Test
	public final void testEqualsObject() {
		assertTrue(selfCompare.equals(selfCompare));
		assertTrue(!selfCompare.equals(allFalseCompare));
		assertTrue(!selfCompare.equals(new Integer(0)));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public final void testNullArgumentCheck() {
		thrown.expect(NullPointerException.class);
		@SuppressWarnings("unused")
		CardComparison nullArgumentComparison = new CardComparison(card1, null);
	}

	@Test
	public final void testIllegalArgumentCheck() {
		thrown.expect(IllegalArgumentException.class);
		@SuppressWarnings("unused")
		CardComparison illegalArgumentExceptionComparison = new CardComparison(card1, card3);
	}
}
