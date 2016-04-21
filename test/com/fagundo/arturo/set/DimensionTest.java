package com.fagundo.arturo.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DimensionTest {

	@Test
	public final void testAggregate() {
		Dimension dimension1 = new Dimension(1, 2);

		assertEquals(dimension1.getType(), 1);
		assertEquals(dimension1.getValue(), 2);
	}

	@Test
	public final void testEqualsObject() {
		Dimension dimension1 = new Dimension(1, 2);
		Dimension copyOfDimension1 = new Dimension(dimension1);
		Dimension dimension2 = new Dimension(1, 3);
		Dimension dimension3 = new Dimension(2, 2);

		assertTrue(dimension1.equals(dimension1));
		assertTrue(dimension1.equals(copyOfDimension1));
		assertTrue(!dimension1.equals(dimension2));
		assertTrue(!dimension1.equals(dimension3));
		assertTrue(!dimension1.equals(new Integer(0)));
	}

	@Test
	public final void testToString() {
		Dimension dimension1 = new Dimension(1, 2);

		assertEquals(dimension1.toString(), "Value: 2");
	}
}
