package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;
import commandline.Deck;
import commandline.GameState;
import commandline.GameStats;
import commandline.Player;
import commandline.PlayerStats;
import commandline.averageDraws;
import commandline.computeWins;
import commandline.insertGameStats;
import commandline.insertPlayerStats;
import commandline.mostRounds;
import commandline.totalGames;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that expose the game commands
 * to the Web page.
 * @author Andrew Allan
 * <br><br>
 * 
 * Constructor:
 * 	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf)
 * 
 * Public methods:<br>
 * 	public String newSessionID()<br>
 * 	public String newGame(@QueryParam("sid") String sid)<br>
 * 	public String getRoundNo(@QueryParam("sid") String sid)<br>
 * 	public String checkTurn(@QueryParam("sid") String sid)<br>
 * 	public String drawCards(@QueryParam("sid") String sid)<br>
 * 	public String selectCategory(@QueryParam("Category") String Category, @QueryParam("sid") String sid)<br>
 *	public String selectCategoryAI(@QueryParam("sid")<br>
 *	public String getResult(@QueryParam("sid") String sid)<br>
 *	public String getActivePlayer(@QueryParam("sid") String sid)<br>
 *	public String showPlayer(@QueryParam("sid") String sid)<br>
 *	public String showPlayers(@QueryParam("sid") String sid)<br>
 *	public String checkEliminations(@QueryParam("sid") String sid)<br>
 *	public String communalPileSize(@QueryParam("sid") String sid)<br>
 *	public String checkHumanPlayer(@QueryParam("sid") String sid)<br>
 *	public String logGameStats(@QueryParam("sid") String sid)<br>
 *	public String getGameStats()
 *
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	
	/**
	 * HashMap to store model instances for different session IDs
	 */
	private HashMap<Integer, GameState> modelMap;
	
	/**
	 * Variable to store next sessionID to be assigned
	 */
	private int sessionID;
	
	/**
	 * Configuration file
	 */
	private TopTrumpsJSONConfiguration jsonConfig;
	


	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------

		
		/**
		 * Model hashmap
		 */
		modelMap = new HashMap<Integer, GameState>();
		
		/**
		 * First key
		 */
		sessionID = 0;
		
		/**
		 *  Set config file
		 */
		this.jsonConfig = conf;

	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	
	@GET
	@Path("/newSessionID")
	/**
	 * Method to request a new session id
	 * @return viewID New session ID
	 * @throws IOException
	 */
	public String newSessionID() throws IOException {
		
		// local variable for sessionID to be returned
		int viewID = this.sessionID;
		
		// increment sessionID
		this.sessionID++;

		// return local sessionID
		return "" + viewID;

	}
	
	
	@GET
	@Path("/newGame")
	/**
	 * Start a new game by reseting the model and deck
	 * @param sid sessionID associated client request
	 * @return "1"
	 * @throws IOException
	 */
	public String newGame(@QueryParam("sid") String sid) throws IOException {
		
		// Create a new deck
		Deck deck = new Deck();

		// Shuffle deck
		deck.shuffleDeck();
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		 
		// Create a new model and store it in modelMap under key of sidInt
		this.modelMap.put(sidInt, new GameState(deck, (this.jsonConfig.getNumAIPlayers() + 1)));
		
		// Get the attribute names to be used
		String[] attri = this.modelMap.get(sidInt).getHumanPlayer().getPlayerHand().getHand().get(0).getAttri();
		String attriStr = oWriter.writeValueAsString(attri);
		
		// for testing
		// System.out.println(attriStr);
		
		// Return attributes to be used for this game
		return attriStr;

	}
	
	
	
	@GET
	@Path("/getRoundNo")
	/**
	 * Method to return round number
	 * @param sid sessionID associated client request
	 * @return round number as string
	 * @throws IOException
	 */
	public String getRoundNo(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get round number for the model associated with given sid
		String result = "" + this.modelMap.get(sidInt).getRoundNumber();
		
		// return round number
		return result;

	}
	
	
	@GET
	@Path("/checkTurn")
	/**
	 * Method to check if human player turn or AI turn
	 * @param sid sessionID associated client request
	 * @return round number as string
	 * @throws IOException
	 */
	public String checkTurn(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		String result;
		
		// if human player is active
		if(this.modelMap.get(sidInt).getActivePlayer().getName().equals(
				this.modelMap.get(sidInt).getHumanPlayer().getName())) {
			result = "1";
		}
		// if AI player is active 	
		else {
			result = "2";
		}
	
		return result;

	}
	
	
	@GET
	@Path("/drawCards")
	/**
	 * Method to make all players draw new cards
	 * @param sid sessionID associated client request
	 * @return Message "New cards drawn"
	 * @throws IOException
	 */
	public String drawCards(@QueryParam("sid") String sid) throws IOException{
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// draw new cards for all players
		this.modelMap.get(sidInt).drawNewCard();
		
		return "New cards drawn";

	}
	
	

	@GET
	@Path("/selectCategory")
	/**
	 * Method to set human player category
	 * @param Category
	 * @param sid sessionID associated client request
	 * @return Category
	 */
	public String selectCategory(@QueryParam("Category") String Category, @QueryParam("sid") String sid) {
		
		// Parse query category and convert to int
		int catInt = Integer.parseInt(Category);
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// set attribute
		this.modelMap.get(sidInt).setCurrentAttribute(catInt - 1);
		
		return Category;
		
	}
	
	
	@GET
	@Path("/AISelectCategory")
	/**
	 * Method to set category when AI player active
	 * @param sid sessionID associated client request
	 * @return Selected category
	 */
	public String selectCategoryAI(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// Get AI player category
		int catInt = this.modelMap.get(sidInt).getActivePlayer().getHighestAttribute();
		
		// For testing
		// System.out.println("integer input to model: " + catInt);
		
		// set attribute
		this.modelMap.get(sidInt).setCurrentAttribute(catInt - 1);
		
		String catStr = Integer.toString(catInt);
		
		return catStr;
	}
	
	
	@GET
	@Path("/getResult")
	/**
	 * Method to check result (win or draw)
	 * @param sid sessionID associated client request
	 * @return 1 if win, 2 if draw
	 */
	public String getResult(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get result from model
		return "" + this.modelMap.get(sidInt).getResult();
	}
	
	
	@GET
	@Path("/getActivePlayer")
	/**
	 * Method to get the name of the active player
	 * @param sid sessionID associated client request
	 * @return active player name
	 */
	public String getActivePlayer(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// Get active player from model
		return this.modelMap.get(sidInt).getActivePlayer().name;
	}
	
	
	@GET
	@Path("/showPlayer")
	/**
	 * Method to return JSON string representing human player information
	 * @param sid sessionID associated client request
	 * @return human player as JSON object
	 * @throws IOException
	 */
	public String showPlayer(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get human player object from model and convert to JSON
		String playerStr = oWriter.writeValueAsString(this.modelMap.get(sidInt).getHumanPlayer());
	
		return playerStr;

	}
	
	
	@GET
	@Path("/showPlayers")
	/**
	 * Method to return JSON string representing all players and their information
	 * @param sid sessionID associated client request
	 * @return players array as JSON string
	 * @throws IOException
	 */
	public String showPlayers(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get list of players from model
		ArrayList<Player> players = this.modelMap.get(sidInt).getPlayers();
		
		// convert list of players to JSON
		String playersStr = oWriter.writeValueAsString(players);
		
		// for testing
		// System.out.println(playersStr);
		
		return playersStr;
	}
	
	
	@GET
	@Path("/checkEliminations")
	/**
	 * Method to return a list of any eliminated players
	 * @param sid sessionID associated client request
	 * @return list of eliminated players, otherwise '0'
	 * @throws IOException
	 */
	public String checkEliminations(@QueryParam("sid") String sid) throws IOException{
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// Get eliminated users from model
		ArrayList<Player> usersEliminated = this.modelMap.get(sidInt).userEliminated();
			
			// if at least 1 user eliminated
			if(usersEliminated.size() > 0) {
				
				// convert eliminated player list to string
				String elimStr = oWriter.writeValueAsString(usersEliminated);
				
				// for testings
				// System.out.println(elimStr);
				
				return elimStr;
			
			}
			
			return "0";
	}
	
	
	@GET
	@Path("/communalPileSize")
	/**
	 * Method to get communal pile size
	 * @param sid sessionID associated client request
	 * @return communal pile size
	 */
	public String communalPileSize(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		return "" + this.modelMap.get(sidInt).getCommunalPileSize();
	}
	
	
	@GET
	@Path("/checkHumanPlayer")
	/**
	 * Method to check if human player still in game
	 * @param sid sessionID associated client request
	 * @return 1 if player still in game, 0 if not
	 * @throws IOException
	 */
	public String checkHumanPlayer(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get human player from model
		Player humanPlayer = this.modelMap.get(sidInt).getHumanPlayer();
		
		// if human player still in game
		if(humanPlayer.getHandSize() != 0) {
			
			// for testing
			// System.out.println("check human player gives 1");
			
			return "1";
		}
		else {
			// for testing
			// System.out.println("check human player gives 0");
			
			return "0";
		}
			
	}
	
	@GET
	@Path("/logGameStats")
	/**
	 * Method to log game stats to database
	 * @param sid sessionID associated client request
	 * @return "1" to indicate completion
	 * @throws IOException
	 */
	public String logGameStats(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		GameStats gameStats = this.modelMap.get(sidInt).getGameStats();
		
//		// for testing
//		System.out.println("Rounds: " + gameStats.getRounds()
//				+ "\nDraws: " + gameStats.getDraws()
//				+ "\nWinner: " + gameStats.getWinner());
		
		ArrayList<PlayerStats> playerStats = this.modelMap.get(sidInt).getPlayerStats();
		
//		// for testing
//		for(PlayerStats p: playerStats) {
//			System.out.println("\nPlayer:" + p.getPlayer() 
//								+ "\nRounds won: " + p.getScore());
//		
//		}
		
		 // creates instance of insertGameStats class
		insertGameStats inG = new insertGameStats();
		
		//create integer for game_id
		int gid = (int) inG.insert(gameStats); 
		
		//creates instance of insertPlayerStats class
		insertPlayerStats in = new insertPlayerStats();
		
		//inserts game_id, stats ArrayList into table
		in.insertData(gid, playerStats);

		return "1";	
	}
	
	@GET
	@Path("/getGameStats")
	/**
	 * Method to retrieve game stats from database
	 * @return statsString JSON list of stats
	 * @throws IOException
	 */
	public String getGameStats() throws IOException {
		
		ArrayList<Integer> stats = new ArrayList<Integer>();
		
		//Calls method to compute average draws per game
		averageDraws ad = new averageDraws();
		int average = ad.drawsAverage();
		stats.add(average);
		
		// for testing
		// System.out.println(String.format("%d : average number of draws", average));
		
		//Calls method to compute how many times AI's have won
		computeWins cw = new computeWins();
		int AIWins = cw.AIWins();
		stats.add(AIWins);
		
		// for testing
		// System.out.println(String.format("%d : AI wins", AIWins));
		
		//Calls method to compute how many times human has once
		computeWins yWins = new computeWins();
		int humanWins = yWins.youWins();
		stats.add(humanWins);
		
		// for testing
		//System.out.println(String.format("%d : your wins", humanWins));
		
		//Calls method to compute most rounds in single game
		mostRounds mr = new mostRounds(); 
		int maxRounds = mr.countRounds(); 
		stats.add(maxRounds);
		
		// for testing
		// System.out.println(String.format("%d : most rounds in a game", maxRounds));
		
		//Calls method to compute how many games player overall
		totalGames gm = new totalGames();
		int total = gm.overallGameCount();
		stats.add(total);
		
		// for testing
		// System.out.println(String.format("%d : total games played", total));	
		
		String statsString = oWriter.writeValueAsString(stats);

		return statsString;	
	}
	
	
}