package edu.iastate.scoreboard;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * This class contains methods that open, close, and query the Scoreboard database.
 */
public class ScoreboardDataSource {

	/**
	 * Singleton instance of ScoreboardDataSource
	 */
	private static ScoreboardDataSource dsInstance = null;

	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;

	/**
	 * Array of all column USERNAMEs in SCORES table
	 */
	private String[] allColumns = { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_USERNAME, SQLiteHelper.COLUMN_SCORE,
			SQLiteHelper.COLUMN_TIME, SQLiteHelper.COLUMN_MODE,
			SQLiteHelper.COLUMN_DETAILS };

	public static ScoreboardDataSource getInstance(Context context) {
		if (dsInstance == null) {
			dsInstance = new ScoreboardDataSource(context.getApplicationContext());
		}
		return dsInstance;
	}

	/**
	 * Constructor that should never be called by user
	 * 
	 * @param context
	 *            The Activity that called this method
	 */
	private ScoreboardDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	/**
	 * Opens the Scoreboard database for writing
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Closes the Scoreboard database
	 */
	public void close() {
		dbHelper.close();
	}

	/**
	 * Creates new row in database and stores all of the Score's details. Then
	 * creates an Score object from the details stored in the database and
	 * returns it.
	 * 
	 * @param USERNAME
	 * @param score
	 * @param start
	 * @param end
	 * @param details
	 * @return Score object that was created
	 */
	public Score createScore(String username, String score, String time,
			String mode, String details) {

		// TODO
		// Put keys (row columns) and values (parameters) into ContentValues
		// object
		// Insert ContentValues into row in SCORES table and obtain row ID
		// HINT: database.insert(...) returns the id of the row you insert
		Log.v("Create Score Enter", "");
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_USERNAME, username);
		values.put(SQLiteHelper.COLUMN_SCORE, score);
		values.put(SQLiteHelper.COLUMN_TIME, time);
		values.put(SQLiteHelper.COLUMN_MODE, mode);
		values.put(SQLiteHelper.COLUMN_DETAILS, details);
		long id = database.insert(SQLiteHelper.TABLE_SCORES, null, values);
		id--;
		// Query database for Score row just added using the getScore(...) method
		// NOTE: You need to write a query to get an Score by id at the to-do
		// marker in the getScore(...) method
		Log.v("Create Score Exit", "id: " + id);
		Score newScore = getScore(id);
		
		return newScore;
	}

	/**
	 * Returns an instance of ScoreboardDataSource if it exists, otherwise creates a
	 * new ScoreboardDataSource object and returns it
	 * 
	 * @param context
	 *            The Activity that called this method
	 * @return An instance of ScoreboardDataSource
	 */
	public void deleteScore(Score score) {
		long id = score.getId();
		String id_string = "" + id;
		database.delete(SQLiteHelper.TABLE_SCORES, SQLiteHelper.COLUMN_ID
				+ "=?", new String[] { id_string });
	}

	/**
	 * Queries and returns Score based on ID
	 * 
	 * @param id
	 *            ID of Score to return
	 * @return Score with ID "id"
	 */
	public Score getScore(long id) {
		Log.v("GetScore entering", "" + id);
		
		Cursor cursor = null;
		id++;
		String strId = "" + id;
		cursor = database.query(SQLiteHelper.TABLE_SCORES, allColumns, SQLiteHelper.COLUMN_ID + "="+ strId, null, null, null, null);
		Log.v("cursor pos 1", "" + cursor.getPosition());
		cursor.moveToFirst();
		Log.v("Cursor pos 2", "" + cursor.getPosition());
		Score toReturn = cursorToScore(cursor);
		cursor.close();
		Log.v("GetScore Exit", "");
		return toReturn;
	}
	
	/*
	 * Helper method to convert row data into Score
	 */
	private Score cursorToScore(Cursor cursor) {

		// TODO: Fill Score object with data from Cursor
		Log.v("cursorToScore", "Entering: " + cursor.getPosition());
		Score Score = new Score(cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_USERNAME)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_SCORE)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_TIME)),cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_MODE)),"");//cursor.getPosition(1));
		Log.v("cursorToScore", "Exiting");
		return Score;
	}

	/**
	 * Queries database for all SCORES stored and creates list of Score objects
	 * from returned data.
	 * 
	 * @return List of all Score objects in database
	 */
	public List<Score> getAllScores() {
		List<Score> scorelist = new ArrayList<Score>();

		// Query of all SCORES
		Cursor cursor = database.query(SQLiteHelper.TABLE_SCORES, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();

		// Create Score objects for each item in list
		while (!cursor.isAfterLast()) {
			Score score = cursorToScore(cursor);
			scorelist.add(score);
			cursor.moveToNext();
		}

		cursor.close();
		return scorelist;
	}
	
	public List<Score> getScoresByMode(String mode) {
		List<Score> scorelist = new ArrayList<Score>();

		String[] selArgs = { mode };
		// Query of all SCORES
		Cursor cursor = database.query(SQLiteHelper.TABLE_SCORES, allColumns,
				SQLiteHelper.COLUMN_MODE + "=?", selArgs , null, null, SQLiteHelper.COLUMN_SCORE);

		cursor.moveToFirst();

		// Create Score objects for each item in list
		while (!cursor.isAfterLast()) {
			Score score = cursorToScore(cursor);
			scorelist.add(score);
			cursor.moveToNext();
		}

		cursor.close();
		return scorelist;
	}

}
