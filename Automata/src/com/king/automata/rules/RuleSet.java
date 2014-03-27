package com.king.automata.rules;

import com.king.automata.model.CellState;


public interface RuleSet {

	public CellState getDetermineGameState(CellState currentState, int numberOfLiveNeighbors);
}
