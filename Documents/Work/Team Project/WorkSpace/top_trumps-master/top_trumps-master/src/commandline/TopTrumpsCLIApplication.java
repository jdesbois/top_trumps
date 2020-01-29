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
			
			/*
			 * Select between game and historical data
			 */
			
			// boolean whether user wants to view data
			boolean checkData = false;
						
			if(checkData) {
				// Functionality for accessing database to be placed here
			}
			
			/*
			 *  Game logic 
			 */
			
			
			//MVC initialisation (draft)
			
//			TopTrumpsModel model = new TopTrumpsModel();
//			CliController controller = new CliController(model);
//			CliView view = new CliView(model, controller);
//			controller.setView(view);
			
			/*
			 * Game loop
			 */
			
			// boolean to track whether game is finished
			boolean gamePlay = true;
			
			while(gamePlay) {
				
				
				// Draft logic added with temp variable/method names
				// to be updated once relevant classes created
				
//				/*
//				 * Print top card
//				 */
//				
//				view.printTopCard();
//				
//				
//				/*
//				 * Attribute selection
//				 */
//				 
//				// if human player is active
//				if(model.getPlayer.isActivePlayer()) {
//					controller.selectCategory();
//				}
//				// if AI player is active 	
//				else {
//					model.getActivePlayer().AIPlay();
//				}
//				
//				/*
//				 * Result
//				 */
//				if(model.getResult = draw) {
//					view.displayDraw();
//				}
//				else {
//					view.DisplayRoundWinner();
//				}
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
//				view.displayWinner();	
//				gamePlay = false;
//				}
				
				// temp to avoid infinite loop
				gamePlay = false;
			}
			
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
 