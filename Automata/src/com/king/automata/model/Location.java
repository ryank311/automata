package com.king.automata.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Location {

	private static final Location tempLocation = new Location(0f,0f,0f);
	
	@Element
	public float x;
	
	@Element
	public float y;
	
	@Element
	public float z;
	
	public Location() {
		x = 0f;
		y = 0f;
		z = 0f;
	}
	
	public Location(
			@Element(name="x") float locX, 
			@Element(name="y") float locY, 
			@Element(name="z") float locZ) {
		x = locX;
		y = locY;
		z = locZ;
	}
	
	public Location clone() {
		return new Location(this.x, this.y, this.z);
	}
	
	@Override
	public String toString() {
		return "" + x + ", " + y + ", " + z;
	}
	
	@Override
	public boolean equals(Object comparable) {
		if (comparable == this) return true;
		if (!(comparable instanceof Location)) return false;
		Location compareTo = (Location)comparable;
		return x == compareTo.x 
				&& y == compareTo.y
				&& z == compareTo.z;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	public static Location tempLocationFor(float locX, float locY, float locZ) {
		tempLocation.x = locX;
		tempLocation.y = locY;
		tempLocation.z = locZ;
		return tempLocation;
	}
}
