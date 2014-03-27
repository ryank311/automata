package com.king.automata.task;

import com.king.automata.model.GameBoard;
import com.king.automata.view.GameBoardView;

public class TitleUpdateTask extends GameUpdateTask {
	
	protected float SCALEX = 0.25f;
	protected float SCALEY = 0.25f;
	
	public TitleUpdateTask(GameBoard gameBoard, GameBoardView gameBoardView) {
		super(gameBoard, gameBoardView, null);
	}

	@Override
	public void run() {
		super.run();
		gameBoardView.getRenderer().mTranslateX -= 1f/16f * SCALEX;
		gameBoardView.getRenderer().mTranslateY += 1f/16f * SCALEY;
	}

}
