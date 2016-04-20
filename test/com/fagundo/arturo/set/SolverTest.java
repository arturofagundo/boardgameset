package com.fagundo.arturo.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SolverTest {

	@Test
	public final void testGetSets() {
		Solver solver = new Solver(3);

		Card[] handOfCards = new Card[3];
		Dimension[] dimensions1 = new Dimension[4];
		for (int i = 0; i < dimensions1.length; i++) {
			dimensions1[i] = new Dimension(i, i);
		}

		for (int i = 0; i < handOfCards.length; i++) {
			handOfCards[i] = new Card(dimensions1);
		}

		List<Integer[]> sets = solver.getSets(handOfCards);
		assertNotNull(sets);
		assertEquals(sets.size(), 1);
		assertEquals(sets.get(0).length, 3);
		for (int i = 0; i < sets.get(0).length; i++) {
			assertTrue(sets.get(0)[i].equals(i));
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public final void testNullArgumentCheck() {
		Solver nullArgumentSolver = new Solver(3);

		thrown.expect(NullPointerException.class);
		nullArgumentSolver.getSets(null);
	}

	@Test
	public final void testIllegalArgumentCheck() {
		thrown.expect(IllegalArgumentException.class);
		@SuppressWarnings("unused")
		Solver illegalArgumentExceptionSolver = new Solver(2);
	}
}
