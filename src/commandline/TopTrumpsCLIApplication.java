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
			
//			TopTrumpsModel model = new TopTrumpsModel();
//			CliController controller = new CliController(model);
//			CliView view = new CliView(model, controller);
//			controller.setView(view);
			
			// temporary
			CLIView view = new CLIView();
		
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
//			while(gamePlay) {
//				
//				
//				// Draft logic added with temp variable/method names
//				// to be updated once relevant classes created
//				
//				// Display round number
//				view.roundNumber();
//				
//				// Print top card
//				// Needs to be passed a card from model
//				view.displayCard();
//				
//				
//				//Attribute selection
//				 
//				// if human player is active
//				if(model.getPlayer.isActivePlayer()) {
//					view.selectCategory();
//				}
//				// if AI player is active 	
//				else {
//					model.getActivePlayer().AIPlay();
//				}
//				
//				// Result
//				// Needs to be passed a card from model
//				view.displayResult();
//				
//				// Display any eliminated users
//				if(model.userEliminated()) {
//					view.displayElimination();
//				}
//				
//				/*
//				 * Check if game finished
//				 */
//				if(model.getPlayers.size() == 1) {
//					view.displayWinner();	
//					gamePlay = false;
//				}
//				
//				// temp to avoid infinite loop
//				gamePlay = false;
//			}
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
