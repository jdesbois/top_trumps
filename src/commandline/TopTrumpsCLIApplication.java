package commandline;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			//MVC initialisation (draft)
			
			GameState model = new GameState();
			CLIView view = new CLIView(model);
			CLIController controller = new CLIController(model, view);
			
		
			/*
			 * Select between game and historical data
			 */	
			
			int playChoice = 0;
			
			/*
			 * Loop until new game selected (when value of 1 is returned from view)
			 */
			while(playChoice != 1) {
				playChoice = view.selectStats();
				if(playChoice == 2) {
					/*
					 *  Insert logic to view stats
					 */
				}
			}
			
			/*
			 * Game loop
			 */
						
			// boolean to track whether game is finished
			boolean gamePlay = true;
			
			// game started
			view.breakLine();
			view.gameStarted();
			view.breakLine();
			
			// loop for game rounds
			while(gamePlay) {
				
				// Display round number
				view.roundNumber();
				
				//Displayer remaining number of cards in hand
				view.displayHumanHandSize();
				
				// Attribute selection
				int result;
				
				// if human player is active
				if(model.getActivePlayer().getName().equals(
						model.getHumanPlayer().getName())) {
					result = controller.humanRound();
				}
				// if AI player is active 	
				else {
					result = controller.AIRound();
				}
				
				// Result
				
				// Display winner name and winner card
				view.displayResult(result);
				
				// Display any eliminated users
				view.displayElimination();
				
				// Break line between rounds
				view.breakLine();
				
				// Check if game finished

				if(model.getPlayersSize() == 1) {
					view.displayWinner();	
					gamePlay = false;
				}
			}
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
