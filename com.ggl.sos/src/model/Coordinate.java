package com.ggl.sos.model;

public class Coordinate {
	
	private final int column, row;

	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinate [row=");
		builder.append(row);
		builder.append(", column=");
		builder.append(column);
		builder.append("]");
		return builder.toString();
	}
	


}
