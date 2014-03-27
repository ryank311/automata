package com.king.automata.data;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class LevelStatusCommands {

	public static void insertOrUpdate(Activity activity,
			int realm, int level, int progress) {
		Uri uri = ContentUris.withAppendedId(activity.getIntent().getData(), realm);
		uri = ContentUris.withAppendedId(uri, level);
		ContentValues contentValues = new ContentValues();
		contentValues.put(LevelStatus.COLUMN_NAME_REALM, realm);
		contentValues.put(LevelStatus.COLUMN_NAME_LEVEL, level);
		contentValues.put(LevelStatus.COLUMN_NAME_SCORE, progress);
		
		Cursor c = activity.getContentResolver().query(
				uri, 
				LevelStatusProvider.READ_SCORES_PROJECTION, 
				null, 
				null, 
				null);
		if(c.moveToFirst()) {
			int oldProgress = c.getInt(LevelStatusProvider.READ_NOTE_SCORE_INDEX);
			if(oldProgress >= progress) return;
			activity.getContentResolver().update(uri, contentValues, null, null);
		}
		else {
			activity.getContentResolver().insert(uri, contentValues);
		}
	}
	
	public static int getLevelProgress(Activity activity,
			int realm, int level) {
		Uri uri = activity.getIntent().getData();
		uri = Uri.withAppendedPath(uri, String.valueOf(realm));
		uri = Uri.withAppendedPath(uri, String.valueOf(level));
		ContentValues contentValues = new ContentValues();
		contentValues.put(LevelStatus.COLUMN_NAME_REALM, realm);
		contentValues.put(LevelStatus.COLUMN_NAME_LEVEL, level);
		Cursor c = activity.getContentResolver().query(
				uri, 
				LevelStatusProvider.READ_SCORES_PROJECTION, 
				null, 
				null, 
				null);
		if(c.moveToFirst()) {
			return c.getInt(LevelStatusProvider.READ_NOTE_SCORE_INDEX);
		}
		return 0;
	}
	
	public static int getRealmProgress(Activity activity,
			int realm) {
		Uri uri = activity.getIntent().getData();
		uri = Uri.withAppendedPath(uri, String.valueOf(realm));
		Cursor c = activity.getContentResolver().query(
				uri, 
				LevelStatusProvider.READ_SCORES_PROJECTION, 
				null, 
				null, 
				null);
		return c.getCount();
	}
}
