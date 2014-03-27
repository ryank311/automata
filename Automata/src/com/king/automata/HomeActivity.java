package com.king.automata;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.king.automata.data.LevelStatus;
import com.king.automata.task.TitleUpdateTask;
import com.king.automata.view.TitleScreenGameBoardView;

public class HomeActivity extends Activity {
	
	private TitleScreenGameBoardView mGLSurfaceView;
	private Timer gameTimer;
	private static final int UPDATE_INTERVAL = 25;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mGLSurfaceView = (TitleScreenGameBoardView)findViewById(R.id.title_screen_game_view);
        mGLSurfaceView.getRenderer().clearColor = new float[] {.94f, .94f, .94f, 1f };
        mGLSurfaceView.getRenderer().zoom = -2f;
        mGLSurfaceView.getRenderer().mTranslateX += .5f;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();
        onClickPlay();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        onClickPause();
        mGLSurfaceView.onPause();
    }
    
    public void onClickPause() {
    	mGLSurfaceView.togglePause(true);
    	if(gameTimer != null) {
    		gameTimer.cancel();
        	gameTimer.purge();
    	}
    }
    
    public void onClickPlay() {
    	mGLSurfaceView.togglePause(false);
    	gameTimer = new Timer();
    	gameTimer.scheduleAtFixedRate(
    			new TitleUpdateTask(
    				mGLSurfaceView.getGameboard(), 
    				mGLSurfaceView),
    			0l,
    			UPDATE_INTERVAL);
    }
    
    public void onSelectLevel(View v) {
    	Intent target = new Intent(this, LevelGroupSelectActivity.class);
    	target.setType(LevelStatus.CONTENT_ITEM_TYPE);
    	target.setAction(Intent.ACTION_VIEW);
    	target.setData(LevelStatus.CONTENT_ID_URI_BASE);
    	startActivity(target);
    }
    
    public void onStartGame(View v) {
    	Intent target = new Intent(this, GameActivity.class);
    	target.putExtra(GameActivity.GAME_ACTIVITY_TITLE, "Free Play");
        startActivity(target);
    }
    
}
