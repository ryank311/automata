package com.king.automata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.king.automata.config.ConfigHolder;
import com.king.automata.data.LevelStatus;
import com.king.automata.view.adapter.LevelGroupAdapter;

public class LevelGroupSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_group_select);
		// Show the Up button in the action bar.
		setupActionBar();
		GridView gridview = (GridView) findViewById(R.id.level_select_grid_view);
		gridview.setAdapter(new LevelGroupAdapter(this));
		final Activity context = this;
		gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Intent target = new Intent(context, LevelSelectActivity.class);
	        	target.setType(LevelStatus.CONTENT_ITEM_TYPE);
	        	target.setAction(Intent.ACTION_VIEW);
	        	target.setData(LevelStatus.CONTENT_ID_URI_BASE);
	        	target.putExtra(ConfigHolder.SELECTED_REALM_VALUE, position);
	            startActivityForResult(target, 0);
	        }
	    });
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		GridView gridview = (GridView) findViewById(R.id.level_select_grid_view);
		gridview.invalidateViews();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_group_select, menu);
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

}
