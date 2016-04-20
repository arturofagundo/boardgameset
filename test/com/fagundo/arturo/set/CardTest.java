package com.fagundo.arturo.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CardTest {

	@Test
	public final void testGetDimensions() {
		Dimension[] dimensions = new Dimension[3];
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] = new Dimension(i, i);
		}
		Card myCard = new Card(dimensions);

		Dimension[] returnedDimensions = myCard.getDimensions();
		assertNotNull(returnedDimensions);
		assertEquals(returnedDimensions.length, dimensions.length);
		for (int i = 0; i < dimensions.length; i++) {
			assertTrue(dimensions[i].equals(returnedDimensions[i]));
		}
	}

	@Test
	public final void testToString() {
		Dimension[] dimensions = new Dimension[3];
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] = new Dimension(i, i);
		}
		Card myCard = new Card(dimensions);
		assertNotNull(myCard.getDimensions());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public final void testNullArgumentCheck() {
		thrown.expect(NullPointerException.class);
		@SuppressWarnings("unused")
		Card nullArgumentCard = new Card(null);
	}
}
