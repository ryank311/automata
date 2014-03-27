package com.king.automata.rules;

import com.king.automata.levelconfig.EscapeConfig;
import com.king.automata.levelconfig.LevelConfig;
import com.king.automata.model.GameBoard;

public class EscapeVictoryCondition implements VictoryConditionSet {

	@Override
	public int isVictorious(GameBoard gameBoard) {
		LevelConfig currentLevel = gameBoard.currentLevel;
		EscapeConfig escapeConfig = currentLevel.getEscapeConfig();
		int cellCount = gameBoard.getEscapedCellCount();
		if(cellCount >= escapeConfig.getThreeStarRange())
			return 3;
		else if (cellCount >= escapeConfig.getTwoStarRange())
			return 2;
		else if (cellCount >= escapeConfig.getOneStarRange())
			return 1;
		return 0;
	}

}
