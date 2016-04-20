package com.fagundo.arturo.set;

/* 
 * Used to define the dimensions associated with a card in the board game Set
 */
public class Dimension {
	private final int type;
	private final int value;

	Dimension(int type, int value) {
		this.type = type;
		this.value = value;
	}

	Dimension(Dimension otherDimension) {
		this.type = otherDimension.getType();
		this.value = otherDimension.getValue();
	}

	public int getValue() {
		return value;
	}

	public int getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object dimension) {
		if (!(dimension instanceof Dimension))
			return false;

		return (value == ((Dimension) dimension).getValue() && type == ((Dimension) dimension).getType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * See pp. 45 - 47 in Effective Java, 2nd Edition by Joshua Bloch
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + type;
		hash = hash * 31 + value;
		return hash;
	}

	@Override
	public String toString() {
		return new String("Type: " + type + ", Value: " + value);
	}
}
