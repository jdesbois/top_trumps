package commandline;

import java.util.Scanner;

/**
 * View class for Top Trumps CLI program
 * @author Andrew Allan
 * <br><br>
 * 
 * Constructor:<br>
 * 	CLIController()<br><br>
 * 
 * Public methods:<br>
 * 	public void selectCategory()<br>
 * 	public void selectStats()
 *
 */

public class CLIView {
	
	// Scanner object for reading user input
	Scanner s = new Scanner(System.in);
	
	/*
	 *  Constructor
	 *  To be updated to take model and view as parameters
	 */
	public CLIView(){
		
	}
	
	/**
	 * Method to take user input (Integer from 1-5)
	 */
	public void selectCategory() {
		
		// Boolean to indicate whether integer in range 1-5 has been submitted
		boolean intGiven = false;
		
		// Range of valid integer inputs
		int maxVal = 5;
		int minVal = 1;
		
		/*
		 *  Variable to store selected category
		 *  Initially -1 to ensure loop runs
		 */
		int category = -1;
		
		/*
		 *  Loop to prompt user to input integer
		 *  Continues until valid integer within range entered
		 */
		while(!intGiven || category < minVal || category > maxVal) {
			
			System.out.println("Select a category (1-5):");
			
			// If integer entered via scanner assign value to category
			if(s.hasNextInt()) {
				category = s.nextInt();
				s.nextLine();
				intGiven = true;
			}
			else {
				s.next();
			}
		}
		
		System.out.println("Stored number: " + category + "\n");
		
		/*
		 * Method call to model to select appropriate category
		 * to be added.
		 */	
		
		
	}
	
	/**
	 * Method to prompt the user to select between playing 
	 * a new game or viewing statistics from previous games
	 */
	public void selectStats() {
		
		// String to store answer
		String choice = "";
		
		// Boolean to indicate whether loop should continue
		Boolean promptLoop = true;
		
		// Loop to continue until a game is started
		while(promptLoop) {
			
			System.out.println("Select 'G' to play, "
					+ "or 'S' to view statistics from previous games:");
			
			// Take user input from scanner
			choice = s.nextLine();
			
			// If new game is selected
			if(choice.toUpperCase().equals("G")) {
				promptLoop = false;
				System.out.println("Player selected 'G' to play a new game\n");
			}
			
			// If view statistics is selected
			else if(choice.toUpperCase().equals("S")) {
				System.out.println("Player selected 'S' to see statistics\n");
			}
		}

	}
}