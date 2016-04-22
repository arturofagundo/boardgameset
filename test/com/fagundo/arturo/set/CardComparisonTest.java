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
	Dimension[] dimensions4;

	Card card1;
	Card card2;
	Card card3;
	Card card4;

	CardComparison selfCompare;
	CardComparison allFalseCompare;

	@Before
	public final void testSetup() {
		final int baseLen = 65;
		dimensions1 = new Dimension[baseLen];
		dimensions2 = new Dimension[baseLen];
		dimensions3 = new Dimension[baseLen + 1];
		dimensions4 = new Dimension[baseLen];

		for (int i = 0; i < dimensions1.length; i++) {
			dimensions1[i] = new Dimension(i, i);
			dimensions2[i] = new Dimension(i, i + 1);
			dimensions3[i] = new Dimension(i, i);
			dimensions4[i] = new Dimension(i + 1, i);
		}
		dimensions3[dimensions3.length - 1] = new Dimension(dimensions3.length - 1, dimensions3.length - 1);

		card1 = new Card(dimensions1);
		card2 = new Card(dimensions2);
		card3 = new Card(dimensions3);
		card4 = new Card(dimensions4);

		selfCompare = new CardComparison(card1, card1);
		allFalseCompare = new CardComparison(card1, card2);
	}

	@Test
	public final void testGetBitMask() {
		int[] bitMask = selfCompare.getBitMask();
		if (dimensions1.length > 32) {
			// Verify all but last bitMask field is all 1s
			for (int i = 0; i < bitMask.length - 1; i++) {
				assertEquals(bitMask[i], 0xffffffff);
			}
		}
		// Now make sure the last field in the bitMask is set correctly
		assertEquals(bitMask[bitMask.length - 1], (0x1 << (dimensions1.length % 32)) - 1);

		bitMask = allFalseCompare.getBitMask();
		for (int i = 0; i < bitMask.length; i++) {
			assertEquals(bitMask[i], 0);
		}
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

	@Test
	public final void testIncompatibleTypeCheck() {
		thrown.expect(IllegalArgumentException.class);
		@SuppressWarnings("unused")
		CardComparison illegalArgumentExceptionComparison = new CardComparison(card1, card4);
	}

}
