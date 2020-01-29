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
			view.gameStarted();
			
			// loop for game rounds
			while(gamePlay) {
				
				
				// Draft logic added with temp variable/method names
				// to be updated once relevant classes created
				
				// Display round number
				view.roundNumber();
				
				// Print top card
				// Needs to be passed a card from model
				
				if(model.getHumanPlayer() != null) {
					view.displayCard(model.getHumanPlayer().getCard());
				}
				
				//Attribute selection
				int result;
				// if human player is active
				if(model.getActivePlayer().equals(model.getHumanPlayer())) {
					result = controller.humanRound();
				}
				// if AI player is active 	
				else {
					result = controller.AIRound();
				}
				
				// Result
				// Needs to be passed a card from model
				view.displayResult(result);
				
				// Display any eliminated users
				if(!model.userEliminated().isEmpty()) {
					view.displayElimination();
				}
				
				/*
				 * Check if game finished
				 */
				if(model.getPlayers.size() == 1) {
					view.displayWinner();	
					gamePlay = false;
				}
				 
				// temp to avoid infinite loop
				gamePlay = false;
			}
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
