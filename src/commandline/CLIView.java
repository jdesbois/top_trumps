package commandline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * View class for Top Trumps CLI program
 * @author Andrew Allan
 * <br><br>
 * 
 * Constructor:<br>
 * 	public CLIView(GameState model, Scanner s)<br><br>
 * 
 * Public methods:<br>
 * 	public void gameStarted()<br>
 * 	public void roundNumber()<br>
 * 	public void displayCard(Card c)<br>
 * 	public void displayHumanPlayerCard()<br>
 * 	public void displayHumanHandSize()<br>
 * 	public int selectCategory()<br>
 * 	public void displayResult(int r)<br>
 * 	public void displayElimination()<br>
 * 	public void breakLine()<br>
 * 	public void displayWinner()
 *
 */

public class CLIView {
	
	// Game model
	private GameState model;
	
	// Scanner object for reading user input
	Scanner s;
	
	/*
	 *  View constructor - passed game model and scanner
	 */
	public CLIView(GameState model, Scanner s){
		this.model = model;
		this.s = s;
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
	 * @param r result 1 indicated win, 2 indicated draw
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