package com.king.automata.rules;

import com.king.automata.levelconfig.LevelConfig;

public class VictoryConditionsFactory {
	
	public static VictoryConditionSet getVictoryConditions(LevelConfig levelConfig){
		if(levelConfig.getSurvivalConfig() != null) {
			return new SurvivalVictoryCondition();
		}
		else if (levelConfig.getEscapeConfig() != null) {
			return new EscapeVictoryCondition();
		}
		return null;
	}
	
}
