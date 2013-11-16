package edu.iastate.angrysimon;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;

public class ScoreBoardActivity extends CustomGestureListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
		super.setGestureDetector(new GestureDetector(this.getApplicationContext(), this));
		super.setLeftRight(SimonActivity.class, SimonActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_board, menu);
		return true;
	}

}
