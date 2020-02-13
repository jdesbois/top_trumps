package commandline;

import java.util.ArrayList;
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
	private GameState model;
	
	// Scanner object for reading user input
	Scanner s = new Scanner(System.in);
	
	/*
	 *  Constructor
	 *  To be updated to take model and controller as parameters
	 */
	public CLIView(GameState model){
		this.model = model;
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
		
		// If no valid choice input, return 0
		System.out.println();
		return 0;

	}
	
	/**
	 * Method to indicate game has started
	 */
	public void gameStarted() {
		System.out.println("New game started. You are Player 1\n");
	}
	
	/**
	 * Method to display current round number
	 */
	public void roundNumber() {
		
		System.out.println("Round number: " + model.getRoundNumber() + "\n");
	}
	
	/**
	 * Method to display contents of a card
	 */
	public void displayCard(Card c) {
		System.out.println(c);
	}
	
	/**
	 * Method to display user's current card
	 */
	public void displayHumanPlayerCard() {
		
		// Check human players still in game
		if(model.getHumanPlayer().getHandSize() > 0) {
			System.out.println("Your card:\n");
			this.displayCard(this.model.getHumanPlayer().getCard());
		}
	}
	
	/**
	 * Method to display number of cards in user's hand
	 */
	public void displayHumanHandSize() {
		// Check human players still in game
		if(model.getHumanPlayer().getHandSize() > 0) {
			System.out.println(this.model.getHumanPlayer().getHandSize() + 
								" cards left in hand\n");
		}
		
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
		
		System.out.println("Selected category: " + category + "\n");
		
		return category;
	}
	
	/**
	 * Method to display round result
	 */
	public void displayResult(int r) {
		int result = r;
		
		// Result of 2 indicates draw
		if(result == 2) {
			System.out.println("Draw. The common pile now has " 
								+ model.getCommunalPileSize() + " cards.\n");
		}
		// Else displayer winner info
		else {
			
			// Update to include winning card with selected attribute highlighted
			System.out.println("Winner: " + model.getWinner().getName() + "\n");
			System.out.println("Winning card:\n" + model.getWinner().getCard());
		}
	}
	
	/**
	 * Method to display any players who have been eliminated
	 * Possible that multiple players eliminated in one round
	 */
	public void displayElimination() {
		ArrayList<Player> usersEliminated = model.userEliminated();
		
		// Check if any players have been eliminated
		if(usersEliminated.size() > 0) {
			
			System.out.println("Users Eliminated:");
			
			// Print any eliminated players
			for(int i = 0; i < usersEliminated.size(); i++) {
				System.out.println(usersEliminated.get(i).getName());
			}
			
			System.out.println();
			
		}

	}
	
	/**
	 * Method to insert line to separate blocks of output
	 */
	public void breakLine() {
		System.out.println("------------------------------------\n");
	}
	
	/**
	 * Method to display overall game winner
	 */
	public void displayWinner() {
		System.out.println(model.getWinner().getName() + " has won the game!\n");
	}
}