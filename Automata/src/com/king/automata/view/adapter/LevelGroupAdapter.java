package com.king.automata.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.king.automata.R;
import com.king.automata.config.ConfigHolder;
import com.king.automata.data.LevelStatusCommands;
import com.king.automata.levelconfig.LevelGroup;
import com.king.automata.levelconfig.RealmConfig;

public class LevelGroupAdapter extends BaseAdapter {
	
	Activity parentActivity;
	
	public LevelGroupAdapter(Activity parentActivity) {
		this.parentActivity = parentActivity;
	}
	
	@Override
	public int getCount() {
		return ConfigHolder.getRealmsConfig(parentActivity).getRealms().size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View levelSelectItem = null;
		if(convertView == null) {
			levelSelectItem = (View) LayoutInflater.from(parentActivity).inflate(
					R.layout.level_group_select_item, null);
		} else {
			levelSelectItem = convertView;
		}
		RealmConfig realmConfig = ConfigHolder.getRealmConfig(position, parentActivity);
		levelSelectItem.setBackgroundColor((int) Long.parseLong(realmConfig.getColor(),16));
		TextView titleLabel = (TextView) levelSelectItem.findViewById(R.id.level_group_name);
		titleLabel.setText(realmConfig.getGroupName());
		TextView levelLabel = (TextView) levelSelectItem.findViewById(R.id.level_group_description);
		levelLabel.setText("Realm " + (position + 1));
		ProgressBar progressBar = (ProgressBar) levelSelectItem.findViewById(R.id.level_group_percentage);
		LevelGroup levelGroup = ConfigHolder.getLevelGroup(position, parentActivity);
		progressBar.setMax(levelGroup.getLevels().size());
		int progress = LevelStatusCommands.getRealmProgress(parentActivity, position);
		progressBar.setProgress(progress);
		return levelSelectItem;
	}

}
