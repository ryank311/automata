package com.king.automata.rules;

import com.king.automata.levelconfig.LevelConfig;
import com.king.automata.levelconfig.SurvivalConfig;
import com.king.automata.model.GameBoard;

public class SurvivalVictoryCondition implements VictoryConditionSet {

	@Override
	public int isVictorious(GameBoard gameBoard) {
		LevelConfig currentLevel = gameBoard.currentLevel;
		SurvivalConfig survivalConfig = currentLevel.getSurvivalConfig();
		int cellCount = gameBoard.getLivingCellCount();
		if(cellCount >= survivalConfig.getThreeStarRange())
			return 3;
		else if (cellCount >= survivalConfig.getTwoStarRange())
			return 2;
		else if (cellCount >= survivalConfig.getOneStarRange())
			return 1;
		return 0;
	}

}
