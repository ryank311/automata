package com.king.automata.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LevelStatusHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "automata_scores.db";
	private static final int DATABASE_VERSION = 1;
	
	
	public LevelStatusHelper(Context context) {

        // calls the super constructor, requesting the default cursor factory.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + LevelStatus.SCORES_TABLE_NAME + " ("
                + LevelStatus._ID + " TEXT PRIMARY KEY,"
                + LevelStatus.COLUMN_NAME_REALM + " INTEGER, "
                + LevelStatus.COLUMN_NAME_LEVEL + " INTEGER, "
                + LevelStatus.COLUMN_NAME_SCORE + " INTEGER, "
                + LevelStatus.COLUMN_NAME_ATTEMPTS + " INTEGER"
                + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// Do nothing yet
	}

}
