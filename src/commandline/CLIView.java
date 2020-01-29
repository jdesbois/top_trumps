package commandline;

import java.util.Scanner;

/**
 * View class for Top Trumps CLI program
 * @author Andrew Allan
 * <br><br>
 * 
 * Constructor:<br>
 * 	CLIViewr()<br><br>
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
	 *  To be updated to take model and controller as parameters
	 */
	public CLIView(){
		
	}
	
	/**
	 * Method to prompt the user to select between playing
	 * a new game or viewing statistics from previous games
	 * 
	 * @return 2 if game selected, 1 if stats selected, 0 if input invalid
	 */
	public int selectStats() {
		
		// String to store answer
		String choice = "";

		System.out.println("Select 'G' to play, "
				+ "or 'S' to view statistics from previous games:");
		
		// Take user input from scanner
		choice = s.nextLine();
		
		// If new game is selected
		if(choice.toUpperCase().equals("G")) {
			System.out.println("Player selected 'G' to play a new game\n");
			return 1;
		}
		
		// If view statistics is selected
		else if(choice.toUpperCase().equals("S")) {
			System.out.println("Player selected 'S' to see statistics\n");
			return 2;
		}
		
		System.out.println();
		return 0;

	}
	
	/**
	 * Method to indicate game has started
	 */
	public void gameStarted() {
		System.out.println("New game started.\n");
	}
	
	/**
	 * Method to display current round number
	 */
	public void roundNumber() {
		//to be updated with reference to model
		int roundNumber = 1;
		
		System.out.println("Round number: " + roundNumber);
	}
	
	/**
	 * Method to display contents of a card
	 */
	public void displayCard(Card c) {
		System.out.println(c);
	}
	
	/**
	 * Method to take user input (Integer from 1-5)
	 */
	public int selectCategory() {
		
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
			
			System.out.println("\nSelect a category (1-5):");
			
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
		
		return category;
	}
	
	/**
	 * Method to display round result
	 */
	public void displayResult(Card winner) {
		int result = 1;
		int commonCards = 5;
		String winningPlayer = "AI-1";
		
		// replace with boolean representing draw based on model
		if(result == 0) {
			System.out.println("Draw. The common pile now has " 
								+ commonCards + " cards.");
		}
		else {
			System.out.println(winningPlayer + " won this round.");
			
			// Update to include winning card with selected attribute highlighted
			System.out.println("Winning card:\n" + winner);
		}
	}
	
	/**
	 * Method to display any players who have been eliminated
	 * Possible that multiple players eliminated in one round
	 */
	public void displayElimination() {
		ArrayList<Player> usersEliminated = model.userEliminated();
		for(int i = 0; i < usersEliminated.size(); i++) {
			System.out.println(usersEliminated().get(i));
		}
	}
	
	/**
	 * Method to display overall game winner
	 * Player parameter to be added
	 */
	public void displayWinner() {
		System.out.println(model.getWinner());
	}
}