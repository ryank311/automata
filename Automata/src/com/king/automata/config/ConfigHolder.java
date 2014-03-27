package com.king.automata.config;

import java.io.IOException;
import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.SparseArray;

import com.king.automata.levelconfig.LevelConfig;
import com.king.automata.levelconfig.LevelGroup;
import com.king.automata.levelconfig.RealmConfig;
import com.king.automata.levelconfig.RealmsConfig;

public class ConfigHolder {
	
	private static SparseArray<LevelGroup> levelGroups;
	private static RealmsConfig realmsConfig;
	
	public static final String SELECTED_REALM_VALUE = "com.king.automata.selected.realm.value";
	public static final String SELECTED_LEVEL_VALUE = "com.king.automata.selected.level.value";
	
	private ConfigHolder(){};
	
	private static synchronized LevelGroup getLevelGroup(Context appContext, int realm) {
		if(levelGroups == null) {
			levelGroups = new SparseArray<LevelGroup>();
		}
		RealmsConfig realmsConfig = getRealmsConfig(appContext);
		RealmConfig realmConfig = realmsConfig.getRealms().get(realm);
		LevelGroup levelGroup = levelGroups.get(realmConfig.getRealm());
		if(levelGroup == null) {
	        try {
				Serializer serializer = new Persister();
				InputStream source = appContext.getAssets().open(
						realmConfig.getConfigFileName());
				levelGroup = serializer.read(LevelGroup.class, source);
				levelGroups.put(realmConfig.getRealm(), levelGroup);
			} catch (IOException e) {
				e.printStackTrace();
				//Fail
			} catch (Exception e) {
				//Failed to parse
				e.printStackTrace();
			}
		}
		return levelGroup;
	}
	
	public static synchronized RealmsConfig getRealmsConfig(Context appContext) {
		if(realmsConfig == null) {
	        try {
				Serializer serializer = new Persister();
				InputStream source = appContext.getAssets().open("realm-config.xml");
				realmsConfig = serializer.read(RealmsConfig.class, source);
			} catch (IOException e) {
				e.printStackTrace();
				//Fail
			} catch (Exception e) {
				//Failed to parse
				e.printStackTrace();
			}
		}
		return realmsConfig;
	}
	
	public static RealmConfig getRealmConfig(int realm, Context appContext) {
		RealmsConfig realmsConfig = getRealmsConfig(appContext);
		return realmsConfig.getRealms().get(realm);
	}
	
	public static LevelGroup getLevelGroup(int realm, Context appContext) {
		return getLevelGroup(appContext, realm);
	}
	
	public static LevelConfig getLevel(int realm, int level, Context appContext) {
		LevelGroup levelGroup = getLevelGroup(appContext, realm);
		return levelGroup.getLevels().get(level);
	}
	
	public static String getLevelName(int realm, int level) {
		return "Level " 
    			+ (realm + 1) + " - " 
    			+ (level + 1 );
	}
	
}
