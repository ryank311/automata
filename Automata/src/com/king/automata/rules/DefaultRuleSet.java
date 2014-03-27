package com.king.automata.rules;

import com.king.automata.model.CellState;

public class DefaultRuleSet implements RuleSet {

	@Override
	public CellState getDetermineGameState(CellState currentState,
			int numberOfLiveNeighbors) {
		CellState returnState = CellState.DEAD;
		switch(numberOfLiveNeighbors) {
			case 0:
			case 1:
				returnState = CellState.DEAD;
				break;
			case 2:
				if(CellState.ALIVE == currentState)
					returnState = CellState.ALIVE;
				else
					returnState = CellState.DEAD;
				break;
			case 3:
				returnState = CellState.ALIVE;
				break;
			default:
				returnState = CellState.DEAD;
				break;
		}
		return returnState;
	}

}
