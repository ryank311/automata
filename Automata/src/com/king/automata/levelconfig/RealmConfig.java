package com.king.automata.levelconfig;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class RealmConfig {
	@Element
    protected String groupName;
	@Element
    protected String color;
	@Element
	private String configFileName;
	@Element
	private int realm;

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public int getRealm() {
		return realm;
	}

	public void setRealm(int realm) {
		this.realm = realm;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
