package com.king.automata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.king.automata.config.ConfigHolder;
import com.king.automata.data.LevelStatus;
import com.king.automata.view.adapter.LevelAdapter;

public class LevelSelectActivity extends Activity implements OnItemClickListener {

	int currentRealm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_select);
		currentRealm = getIntent().getIntExtra(ConfigHolder.SELECTED_REALM_VALUE, 0);
		
		// Show the Up button in the action bar.
		setupActionBar();
		ListView gridview = (ListView) findViewById(R.id.level_select_list);
		gridview.setAdapter(new LevelAdapter(this, currentRealm));
		gridview.setOnItemClickListener(this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_select, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Courtesy method to request re-rendering of the list.
	 */
	public void invalidateView() {
		ListView gridview = (ListView) findViewById(R.id.level_select_list);
		gridview.invalidateViews();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		invalidateView();
	}
	
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    	Intent target = new Intent(this, GameActivity.class);
    	target.setType(LevelStatus.CONTENT_ITEM_TYPE);
    	target.setAction(Intent.ACTION_EDIT);
    	target.setData(LevelStatus.CONTENT_URI);
    	target.putExtra(ConfigHolder.SELECTED_REALM_VALUE, currentRealm);
    	target.putExtra(ConfigHolder.SELECTED_LEVEL_VALUE, position);
    	target.putExtra(GameActivity.GAME_ACTIVITY_TITLE, 
    			ConfigHolder.getLevelName(currentRealm, position));
        startActivityForResult(target, 0);
    }
}
