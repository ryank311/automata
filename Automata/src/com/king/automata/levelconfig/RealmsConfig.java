package com.king.automata.levelconfig;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class RealmsConfig {
	
	@ElementList
	List<RealmConfig> realms;

	public List<RealmConfig> getRealms() {
		return realms;
	}

	public void setRealms(List<RealmConfig> realms) {
		this.realms = realms;
	}
}
