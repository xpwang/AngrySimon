package edu.iastate.scoreboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class helps with database setup. None of its methods (except for the
 * constructor) should be called directly.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	// Table name
	public static final String TABLE_SCORES = "scores";

	// Table columns
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_SCORE = "score";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_MODE = "mode";
	public static final String COLUMN_DETAILS = "details";

	// Database name
	private static final String DATABASE_NAME = "agenda.db";

	// Increment this number to clear everything in database
	private static final int DATABASE_VERSION = 1;

	/**
	 * Returns an instance of this helper object given the activity
	 * 
	 * @param context
	 */
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * TODO: Create database table "events" COLUMN_ID should be of type
		 * "integer primary key autoincrement" All other columns should be of
		 * type "text not null" Columns names have been created as constants at
		 * top of this class
		 */
		String str = "CREATE TABLE " + TABLE_SCORES + " ( " 
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ COLUMN_USERNAME + " TEXT NOT NULL, "
				+ COLUMN_SCORE + " TEXT NOT NULL, "
				+ COLUMN_TIME + " TEXT NOT NULL, "
				+ COLUMN_MODE + " TEXT NOT NULL, "
				+ COLUMN_DETAILS + " TEXT NOT NULL"
				+ ");";
		db.execSQL(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		onCreate(db);
	}

}