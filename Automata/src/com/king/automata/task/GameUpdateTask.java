package com.king.automata.task;

import java.util.TimerTask;

import com.king.automata.GameActivity;
import com.king.automata.model.GameBoard;
import com.king.automata.rules.VictoryConditionSet;
import com.king.automata.rules.VictoryConditionsFactory;
import com.king.automata.view.GameBoardView;

/**
 * A task class that handles the progression of the game over time
 * Each time the run method is invoked, we increment the counter by 1,
 * Once it passes the threashold, we will simulate a turn of the game.
 * @author king
 *
 */
public class GameUpdateTask extends TimerTask {

	protected GameBoard gameBoard;
	protected GameBoardView gameBoardView;
	protected GameActivity gameActivity;
	protected VictoryConditionSet victoryConditions;
	protected int interval = 15;
	
	public GameUpdateTask(
			GameBoard gameBoard, 
			GameBoardView gameBoardView,
			GameActivity activity) {
		this.gameBoard = gameBoard;
		this.gameBoardView = gameBoardView;
		this.gameActivity = activity;
		this.victoryConditions = VictoryConditionsFactory.getVictoryConditions(gameBoard.currentLevel);
	}
	
	@Override
	public void run() {
		int tick = gameBoardView.getRenderer().tick;
		if(tick > interval) {
			if(gameBoard.useCounter 
				&& gameBoard.counter == 0
				&& gameActivity != null) {
				int victoryStatus = victoryConditions.isVictorious(gameBoard);
				gameActivity.endGame(victoryStatus);
				return;
			}
			gameBoard.simulateTurn();
			gameBoardView.getRenderer().tick = 0;
		}
		gameBoardView.requestRender();
		gameBoardView.getRenderer().tick++;
	}

}
