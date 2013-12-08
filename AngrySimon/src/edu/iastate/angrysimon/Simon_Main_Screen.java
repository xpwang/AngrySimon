package edu.iastate.angrysimon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import edu.iastate.scoreboard.ScoreboardActivity;

public class Simon_Main_Screen extends Activity {

	private String Game_Mode = "Classic";

	// private CustomGestureListener Gesture_Main_Screen = new
	// CustomGestureListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_screen);
		// super.setGestureDetector(new
		// GestureDetector(this.getApplicationContext(), this));
		// super.setLeftRight(ScoreBoardActivity.class, SimonActivity.class);

		// Gesture_Main_Screen.setLeftRight(Simon_Settings_Screen.class,
		// ScoreBoardActivity.class);

		// 3 buttons
		ImageButton Classic_Button = (ImageButton) findViewById(R.id.imagebutton_classic);
		ImageButton Angry_Button = (ImageButton) findViewById(R.id.imagebutton_angry);
		ImageButton Rage_Button = (ImageButton) findViewById(R.id.imagebutton_rage);
		ImageButton Scoreboard_Button = (ImageButton) findViewById(R.id.imagebutton_scoreboard);

		Classic_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent GotoGame_Intent = new Intent(this,
				// SimonActivity.class);
				Game_Mode = "Classic";
				Intent GotoGame_Intent = new Intent(Simon_Main_Screen.this,
						SimonActivity.class);
				GotoGame_Intent.putExtra("Game_Mode", Game_Mode);
				startActivity(GotoGame_Intent);
			}

		});

		Angry_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Mode = Angry;
				Game_Mode = "Angry";
				Intent GotoGame_Intent = new Intent(Simon_Main_Screen.this,
						SimonActivity.class);
				GotoGame_Intent.putExtra("Game_Mode", Game_Mode);
				startActivity(GotoGame_Intent);
			}

		});

		Rage_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Game_Mode = "Rage";
				Intent GotoGame_Intent = new Intent(Simon_Main_Screen.this,
						SimonActivity.class);
				GotoGame_Intent.putExtra("Game_Mode", Game_Mode);
				startActivity(GotoGame_Intent);

			}

		});

		Scoreboard_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent scoreboardIntent = new Intent(Simon_Main_Screen.this,
						ScoreboardActivity.class);
				scoreboardIntent.putExtra("source", "menu");
				startActivity(scoreboardIntent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.simon_main_screen_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			// intent add open new activity
			Intent Settings_Intent = new Intent(this,
					Simon_Settings_Screen.class);
			startActivity(Settings_Intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
