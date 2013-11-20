package edu.iastate.angrysimon;

import java.util.ArrayList;
import java.util.Random;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

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
	}

	@Override
	protected void onResume() {
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		isRunning = true;
		runGameLoop();
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

	}
	
	/*
	 * Creates and displays the game over dialog
	 */
	private void finishDialog(){
		
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
