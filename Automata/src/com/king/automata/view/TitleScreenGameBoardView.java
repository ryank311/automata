package com.king.automata.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.king.automata.levelconfig.ConfigCell;
import com.king.automata.levelconfig.LevelConfig;
import com.king.automata.model.Cell;
import com.king.automata.model.GameBoard;
import com.king.automata.model.Location;

public class TitleScreenGameBoardView extends GameBoardView {
	
	GameBoard gameboard;
	//private static final float SCALE = 5f;
	Random random;
	
	public TitleScreenGameBoardView(Context context, AttributeSet attributeset) {
		
		super(context, attributeset);
		random = new Random();
		LevelConfig levelConfig = new LevelConfig();
		levelConfig.setGameBoardSize(10);
		levelConfig.setFreePlay(true);
		levelConfig.setCounter(-1);
		levelConfig.setHasGameBoard(false);
		
		List<ConfigCell> cells = new ArrayList<ConfigCell>();
		
		/*for(float i = -1; i < 2; i++) {
			for(float j = -1; j < 2; j++) {
				addCells(cells, 
					new Location(
							Math.round(SCALE * i * random.nextFloat()), 
							Math.round(SCALE * j * random.nextFloat()), 0f), 
					new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat() });
			}
		}*/
		
		addCells(cells, 
				new Location(0f, 0f, 0f), 
				new float[] {random.nextFloat(), random.nextFloat(), random.nextFloat() });
		
		levelConfig.setCells(cells);
		
		gameboard = new GameBoard(levelConfig);
		super.init(context, gameboard);
		requestRender();
	}
	
	public void addCells(List<ConfigCell> cells, Location location, float[] color) {
		ConfigCell configCell1 = new ConfigCell();
		configCell1.setCellLocation(new Location(location.x - 1f, location.y - 1f, 0f));
		configCell1.setCell(new Cell(color[0], color[1], color[2]));
		cells.add(configCell1);
		
		ConfigCell configCell2 = new ConfigCell();
		configCell2.setCellLocation(new Location(location.x, location.y - 1f, 0f));
		configCell2.setCell(new Cell(color[0], color[1], color[2]));
		cells.add(configCell2);
		
		ConfigCell configCell3 = new ConfigCell();
		configCell3.setCellLocation(new Location(location.x + 1f, location.y - 1f, 0f));
		configCell3.setCell(new Cell(color[0], color[1], color[2]));
		cells.add(configCell3);
		
		ConfigCell configCell4 = new ConfigCell();
		configCell4.setCellLocation(new Location(location.x + 1f, location.y, 0f));
		configCell4.setCell(new Cell(color[0], color[1], color[2]));
		cells.add(configCell4);
		
		ConfigCell configCell5 = new ConfigCell();
		configCell5.setCellLocation(new Location(location.x, location.y + 1f, 0f));
		configCell5.setCell(new Cell(color[0], color[1], color[2]));
		cells.add(configCell5);
	}

	@Override
	public boolean onTrackballEvent(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return true;
	}

	public GameBoard getGameboard() {
		return gameboard;
	}

	public void setGameboard(GameBoard gameboard) {
		this.gameboard = gameboard;
	}
	
}
