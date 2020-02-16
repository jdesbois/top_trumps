package commandline;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
		
		// Scanner object for reading user input
		Scanner t = new Scanner(System.in);

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			/*
			 * Select between game and historical data
			 */	
			
			
			// User choice
			String playChoice = ""; 
			
			
			  /* Prompt the user to select between playing
			  * a new game or viewing statistics from previous games
			  */ 
			while(!playChoice.toUpperCase().contentEquals("G") ) {
				
				System.out.println("Select 'G' to play, "
						+ "or 'S' to view statistics from previous games:");
				
				playChoice = t.nextLine();
								
				if(playChoice.toUpperCase().equals("S")) {
					
					System.out.println("Player selected 'S' to see statistics\n");

					averageDraws ad = new averageDraws();
					int average = ad.drawsAverage();
					System.out.println(String.format("%d : average number of draws", average));
					
					computeWins cw = new computeWins();
					int AIWins = cw.AIWins();
					int humanWins = cw.youWins();
					System.out.println(String.format("%d : AI wins", AIWins));
					System.out.println(String.format("%d : your wins", humanWins));
					
					mostRounds mr = new mostRounds(); 
					int maxRounds = mr.countRounds(); 
					System.out.println(String.format("%d : most rounds in a game", maxRounds));
					
					totalGames gm = new totalGames();
					int total = gm.overallGameCount();
					System.out.println(String.format("%d : total games played", total));	
					

				}
			}

			System.out.println("Player selected 'G' to play a new game\n");	

			// Creates a TestLog file which will only activate if the log option is chosen
			TestLog log = new TestLog(new File("toptrumps.log"), writeGameLogsToFile);
						
			// Initialises new deck
			Deck deck = new Deck();
			
			log.logDeck(deck.toString());
			deck.shuffleDeck();
			log.logShuffledDeck(deck.toString());
			
			//MVC initialisation
			GameState model = new GameState(deck, 5); // In CLI, number of players defaults to 5
			CLIView view = new CLIView(model, t);
			CLIController controller = new CLIController(model, view);
			
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
				
				// Logs round number
				log.logRoundNumber(model.getRoundNumber());
				
				//Displayer remaining number of cards in hand
				view.displayHumanHandSize();
				
				// Attribute selection
				int result;
				
				// All Player Objects draw new Card
				model.drawNewCard();
				
				// Logs current cards
				log.logCurrentCards(model.getPlayers());
				
				// Logs player hands
				log.logHands(model.getPlayers(), (model.getHumanPlayer().getHandSize() != 0));
				
				// Temporarily stores communal pile
				// This is due to get result automatically dealing communal Pile to a winner
				ArrayList<Card> communalPile = model.getCommunalPile();
				
				
				// if human player is active
				if(model.getActivePlayer().getName().equals(
						model.getHumanPlayer().getName())) {
					result = controller.humanRound();
				}
				// if AI player is active 	
				else {
					result = controller.AIRound();
				}

				// Converts chosen attribute to string
				String att = model.getActivePlayer().getCard().getAttri()[model.getCurrentAttribute()];	
				// Logs category and values
				log.logCategoryandValues(att, model.getPlayers(), model.getCurrentAttribute(), (model.getHumanPlayer().getHandSize() != 0));

				// Result
				
				// Logs communal pile after new cards are added
				if(result == 2) {
					
					log.logCommunalPile(model.getCommunalPile());
					
					// If someone won this round log the previously stored communalPile if it is not empty
				} else if(communalPile.size() != 0) {
					
					log.logCommunalPile(communalPile);
				}
				
				// Display winner name and winner card
				view.displayResult(result);
				
				// Display any eliminated users
				view.displayElimination();
				
				// Break line between rounds
				view.breakLine();
				
				// Check if game finished

				if(model.getPlayersSize() == 1) {
					
					// logs winner
					log.logWinner(model.getWinner().getName());
					
					// Displays winner
					view.displayWinner();	
					
					// Ends loop
					gamePlay = false;
				}
			}
			
			 // creates instance of insertGameStats class
			insertGameStats inG = new insertGameStats();
			
			//Creates GameState object, gets from model
			GameStats newGame = model.getGameStats();
			
			//create integer for game_id
			int gid = (int) inG.insert(newGame); 
			
			//Gets PlayerStats to fill ArrayList
			ArrayList<PlayerStats> stats = model.getPlayerStats();
		
			//creates instance of insertPlayerStats class
			insertPlayerStats in = new insertPlayerStats();
			
			//inserts game_id, stats ArrayList into table
			in.insertData(gid, stats);
	
			
		}


	}

}
