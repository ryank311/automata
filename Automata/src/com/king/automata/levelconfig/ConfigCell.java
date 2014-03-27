package com.king.automata.levelconfig;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.king.automata.model.Cell;
import com.king.automata.model.Location;

@Root
public class ConfigCell {

	@Element
	private Location cellLocation;
	
	@Element
	private Cell cell;
	
	public Location getCellLocation() {
		return cellLocation;
	}
	public void setCellLocation(Location cellLocation) {
		this.cellLocation = cellLocation;
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
}
