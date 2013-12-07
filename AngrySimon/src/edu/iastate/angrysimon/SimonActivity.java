package edu.iastate.angrysimon;

import java.util.ArrayList;
import java.util.Random;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
//hi eric
//lskdjf
//dsf

//hi peter
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
		gameMode = Mode.CLASSIC;
		nActions = 4;

		gameState = State.START;
		actions = new ArrayList<Action>();
		rand = new Random();

		// Initialize game visual layout settings
		initButtons();
		buildGameLayout(gameMode);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		isRunning = true;
		runGameLoop();
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
	private void buildGameLayout(Mode mode) {
		switch (mode) {
		// TODO - build game layout
		}
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

	}

	/*
	 * Red Button onClick method, does nothing unless game is listening If game
	 * is listening, test if this is the next action in the pattern
	 */
	public void buttonOnClick(View view) {
		Toast t = Toast.makeText(getApplicationContext(),
				"ID: " + view.getId(), Toast.LENGTH_SHORT);
		t.show();
		if (gameState == State.LISTENING) {

		}
	}
}
