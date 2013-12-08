package edu.iastate.angrysimon;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.iastate.scoreboard.ScoreboardActivity;
//hi eric
//hello

public class SimonActivity extends CustomGestureListener {

	/*
	 * Different states of the game loop
	 */
	private enum State {
		START, SHOWING, LISTENING, END
	};

	/*
	 * Different game modes
	 */
	private enum Mode {
		CLASSIC, ANGRY, RAGE
	};

	/*
	 * Different actions that can be performed
	 */
	private enum Action {
		RED, GREEN, BLUE, YELLOW, ORANGE, VIOLET, SHAKE
	}

	/*
	 * Game state variables
	 */
	private boolean isRunning;
	private State gameState;
	private Mode gameMode;
	private int score;
	private int run;
	private int progress;
	private ArrayList<Action> actions;

	/*
	 * Useful variables
	 */
	private Random rand;
	private int nActions;

	/*
	 * Button holders
	 */
	private Button redButton;
	private Button blueButton;
	private Button greenButton;
	private Button yellowButton;
	private Button orangeButton;
	private Button violetButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Make Activity fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_simon);

		// Select game mode based on intent extra
		Intent intent = getIntent();
		String mode = intent.getStringExtra("Game_Mode");
		if (mode.equals("Classic")) {
			gameMode = Mode.CLASSIC;
			nActions = 4;
		} else if (mode.equals("Angry")) {
			gameMode = Mode.ANGRY;
			nActions = 7;
		} else if (mode.equals("Rage")) {
			gameMode = Mode.RAGE;
			nActions = 7;
		} else {
			gameMode = Mode.CLASSIC;
			nActions = 4;
		}

		actions = new ArrayList<Action>();
		rand = new Random();

		// Initialize game visual layout settings
		initButtons();
		buildGameLayout();
		initGame();
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		isRunning = true;
		runGameLoop();
	}

	protected void onPause() {
		super.onPause();
		isRunning = false;
	}

	/*
	 * sets Button variables
	 */
	private void initButtons() {
		redButton = (Button) findViewById(R.id.button_red);
		blueButton = (Button) findViewById(R.id.button_blue);
		greenButton = (Button) findViewById(R.id.button_green);
		yellowButton = (Button) findViewById(R.id.button_yellow);
		orangeButton = (Button) findViewById(R.id.button_orange);
		violetButton = (Button) findViewById(R.id.button_violet);
	}

	/*
	 * Builds game layout based on selected mode
	 */
	@SuppressLint("NewApi")
	private void buildGameLayout() {

		// Get screen size
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		switch (gameMode) {
		case CLASSIC:
			orangeButton.setVisibility(View.GONE);
			violetButton.setVisibility(View.GONE);

			redButton.setWidth(width / 2);
			redButton.setHeight(height / 2);
			redButton.setX(0);
			redButton.setY(0);
			blueButton.setWidth(width / 2);
			blueButton.setHeight(height / 2);
			blueButton.setX(width / 2);
			blueButton.setY(0);
			greenButton.setWidth(width / 2);
			greenButton.setHeight(height / 2);
			greenButton.setX(0);
			greenButton.setY(height / 2);
			yellowButton.setWidth(width / 2);
			yellowButton.setHeight(height / 2);
			yellowButton.setX(width / 2);
			yellowButton.setY(height / 2);

			break;
		case ANGRY:
		case RAGE:

			redButton.setWidth(width / 2);
			redButton.setHeight(height / 2);
			redButton.setX(0);
			redButton.setY(0);
			blueButton.setWidth(width / 2);
			blueButton.setHeight(height / 2);
			blueButton.setX(width / 2);
			blueButton.setY(0);
			greenButton.setWidth(width / 2);
			greenButton.setHeight(height / 2);
			greenButton.setX(0);
			greenButton.setY(height / 3);
			yellowButton.setWidth(width / 2);
			yellowButton.setHeight(height / 2);
			yellowButton.setX(width / 2);
			yellowButton.setY(height / 3);
			orangeButton.setWidth(width / 2);
			orangeButton.setHeight(height / 2);
			orangeButton.setX(0);
			orangeButton.setY(2 * (height / 3));
			violetButton.setWidth(width / 2);
			violetButton.setHeight(height / 2);
			violetButton.setX(width / 2);
			violetButton.setY(2 * (height / 3));

			break;
		default:
			break;
		}
	}

	/*
	 * Sets up the game to be started
	 */
	private void initGame() {
		gameState = State.START;
		actions.clear();
	}

	/*
	 * Game Loop
	 */
	private void runGameLoop() {
		while (isRunning) {
			switch (gameState) {
			/*
			 * Start out by initializing variables
			 */
			case START:
				score = 0;
				run = 0;
				gameState = State.SHOWING;
				break;
			/*
			 * Randomly select a new action Show each of the prior actions
			 * followed by the new one
			 */
			case SHOWING:
				// Select next action and add to list of actions
				int next = rand.nextInt(nActions);
				actions.add(Action.values()[next]);
				run++;

				// Show each action in order;
				for (int i = 0; i < run; i++) {
					showAction(actions.get(i));
				}
				progress = 0;
				gameState = State.LISTENING;
				break;
			/*
			 * Listen for each action, testing if it is the correct input in the
			 * pattern
			 */
			case LISTENING:
				for (int i = 0; i < run; i++) {
					// TODO - wait for user to input
				}
				break;
			/*
			 * Show score dialog
			 */
			case END:
				isRunning = false;
				finishDialog();
				break;
			default:
				break;
			}
		}
	}

	/*
	 * Highlights action to be performed, then waits for a short time
	 */
	private void showAction(Action action) {
		Toast t = null;
		switch (action) {
		case RED:
			t = Toast.makeText(getApplicationContext(), "RED",
					Toast.LENGTH_SHORT);
			break;
		case BLUE:
			t = Toast.makeText(getApplicationContext(), "BLUE",
					Toast.LENGTH_SHORT);
			break;
		case GREEN:
			t = Toast.makeText(getApplicationContext(), "GREEN",
					Toast.LENGTH_SHORT);
			break;
		case YELLOW:
			t = Toast.makeText(getApplicationContext(), "YELLOW",
					Toast.LENGTH_SHORT);
			break;
		case ORANGE:
			t = Toast.makeText(getApplicationContext(), "ORANGE",
					Toast.LENGTH_SHORT);
			break;
		case VIOLET:
			t = Toast.makeText(getApplicationContext(), "VIOLET",
					Toast.LENGTH_SHORT);
			break;
		case SHAKE:
			t = Toast.makeText(getApplicationContext(), "RUMBLE RUMBLE",
					Toast.LENGTH_SHORT);
			break;
		default:
			t = Toast.makeText(getApplicationContext(), "AH SHIT",
					Toast.LENGTH_SHORT);
			break;
		}
		t.show();
		SystemClock.sleep(1000);
	}

	/*
	 * Creates and displays the game over dialog
	 */
	private void finishDialog() {
		isRunning = false;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.game_over);
		LayoutInflater inflater = this.getLayoutInflater();

		builder.setView(inflater.inflate(R.layout.game_finished_dialog, null));

		// Reset everything and play again
		builder.setPositiveButton(R.string.play_again,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						initGame();
						runGameLoop();
					}
				});

		// Send to main menu
		builder.setNegativeButton(R.string.finish,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent homeIntent = new Intent(SimonActivity.this,
								Simon_Main_Screen.class);
						startActivity(homeIntent);
						finish();
					}
				});

		// Save score and send to scoreboard screen
		builder.setNeutralButton(R.string.save_score,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent scoreboardIntent = new Intent(
								SimonActivity.this, ScoreboardActivity.class);
						scoreboardIntent.putExtra("source", "game");
						scoreboardIntent.putExtra("score", score);
						EditText nameText = (EditText) ((AlertDialog) dialog)
								.findViewById(R.id.name_text_box);
						scoreboardIntent.putExtra("name", nameText.getText()
								.toString().trim());
						startActivity(scoreboardIntent);
						finish();
					}
				});

		Dialog dialog = builder.create();
		TextView scoreText = (TextView) dialog
				.findViewById(R.id.score_num_text);
		scoreText.setText(score + "");
		dialog.show();
	}

	/*
	 * Red Button onClick method, does nothing unless game is listening If game
	 * is listening, test if this is the next action in the pattern
	 */
	public boolean buttonOnClick(View view) {
		Toast t = Toast.makeText(getApplicationContext(),
				"ID: " + view.getId(), Toast.LENGTH_SHORT);
		t.show();
		Action incomingAction = null;
		if (gameState == State.LISTENING) {
			switch (view.getId()) {
			case R.id.button_red:
				incomingAction = Action.RED;
				break;
			case R.id.button_blue:
				incomingAction = Action.BLUE;
				break;
			case R.id.button_green:
				incomingAction = Action.GREEN;
				break;
			case R.id.button_yellow:
				incomingAction = Action.YELLOW;
				break;
			case R.id.button_orange:
				incomingAction = Action.ORANGE;
				break;
			case R.id.button_violet:
				incomingAction = Action.VIOLET;
				break;
			default:
				break;
			}
			if (incomingAction == actions.get(progress)) {
				return true;
			}
		}
		return false;
	}
}
