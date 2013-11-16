import java.util.ArrayList;
import java.util.Random;

/**
 * GameLevel Class for AngrySimon Game
 * 
 * @author raj
 * 
 */
public class GameLevel {

	/**
	 * Random number generator for Simon Game
	 */
	private Random Generator;
	
	/**
	 * For every correct answer cycle will increase by one
	 */
	
	private int cycles;
	/**
	 * level of complex of game 1- 1 button 2- 2 buttons 3- 3 buttons 4- 4
	 * buttons 5- rotate screen left 6- rotate screen right So on
	 */

	private int level;
	/**
	 * Array List to store the sequence of the game
	 */
	
	private ArrayList<Integer> Sequence ;

	/**
	 * GameLevel method to start game 
	 * Seed value for testing purpose
	 * @param seed
	 * Level of diffulties
	 * @param level
	 * cycle of sequence;
	 * @param cycles
	 */
	public GameLevel(int seed, int level, int cycles) {

		Generator = new Random(seed);
		this.cycles = cycles;
		this.level = level;
		genSequence();
		
	}

	/**
	 * GameLevel Method with no seed value
	 * @param level
	 * @param cycles
	 */

	public GameLevel(int level, int cycles) {

		Generator = new Random();
		this.cycles = cycles;
		this.level = level;
		genSequence();
		// Check_Index=0;
	}

	/**
	 * Default game 
	 */
	public GameLevel() {
		Generator = new Random();
		this.cycles = 1;
		// four colors
		this.level = 4;
		genSequence();
		
	}

	/**
	 * Method use to increase or decrease cycles
	 * @param cycles
	 */
	public void setCycle(int cycles) {
		this.cycles = cycles;
	}

	/**
	 * Method to increase level
	 * @param level
	 */
	
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Method to generate sequence for user
	 */
	public void genSequence() {

		 Sequence =  new ArrayList<Integer>();

		int interations = 0;
		while (interations < cycles) {

			int RanNum = Generator.nextInt(level) + 1;
			Sequence.add(RanNum);
			interations++;
		}

	}

	// may not be needed??
	public boolean checkSequenceAll(int[] userInput) {
		boolean check = false;
		return false;
	}
    
    
    /**
     * Method to check what user has entered
     * @param userInput
     * @param index
     * @return
     */
	public boolean checkSingleInput(int userInput, int index) {

		if (index > cycles) {
			return false;
		}

		if (Sequence.get(index) == userInput)
			return true;

		return false;
	}

	
	/**
	 *  Method to display Sequence to user
	 *  Mainly used for testing
	 *  
	 *  real game will not print out sequence
	 */
	public void playSquence() {
		
		// display sequence
		for (int i = 0; i < cycles; i++) {
			System.out.print(Sequence.get(i) + " ");

		}
		System.out.println();
	}

}
