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

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	
	/**
	 * HashMap to store model instances different ID's
	 */
	private HashMap<Integer, GameState> modelMap;
	
	/**
	 * Variable to store sessionID
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
	
	/**
	 * Method to request a new session id
	 * @return viewID New session ID
	 * @throws IOException
	 */
	@GET
	@Path("/newSessionID")

	public String newSessionID() throws IOException {
		
		// local variable for sessionID to be returned
		int viewID = this.sessionID;
		
		// increment sessionID
		this.sessionID++;

		// return local sessionID
		return "" + viewID;

	}
	
	/**
	 * Start a new game but reseting the model and deck
	 * @param sid sessionID associated client request
	 * @return "1"
	 * @throws IOException
	 */
	@GET
	@Path("/newGame")

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
	
	
	/**
	 * Method to return round number
	 * @return round number as string
	 * @throws IOException
	 */
	@GET
	@Path("/getRoundNo")

	public String getRoundNo(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get round number for the model associated with given sid
		String result = "" + this.modelMap.get(sidInt).getRoundNumber();
		
		// return round number
		return result;

	}
	
	/**
	 * Method to check if human player turn or AI turn
	 * @return int 1 if player turn, two if AI turn
	 * @throws IOException
	 */
	@GET
	@Path("/checkTurn")

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
	
	/**
	 * Method to make all players draw new cards
	 * @return Message "New cards drawn"
	 * @throws IOException
	 */
	@GET
	@Path("/drawCards")

	public String drawCards(@QueryParam("sid") String sid) throws IOException{
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// draw new cards for all players
		this.modelMap.get(sidInt).drawNewCard();
		
		return "New cards drawn";

	}
	
	
	/**
	 * Method to set human player category
	 * @param Category
	 * @return Category
	 */
	@GET
	@Path("/selectCategory")
	
	public String selectCategory(@QueryParam("Category") String Category, @QueryParam("sid") String sid) {
		
		// Parse query category and convert to int
		int catInt = Integer.parseInt(Category);
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// set attribute
		this.modelMap.get(sidInt).setCurrentAttribute(catInt - 1);
		
		return Category;
		
	}
	
	/**
	 * Method to set category when AI player active
	 * @return Selected category
	 */
	@GET
	@Path("/AISelectCategory")
	
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
	
	/**
	 * Method to check result (win or draw)
	 * @return 1 if win, 2 if draw
	 */
	@GET
	@Path("/getResult")
	
	public String getResult(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get result from model
		return "" + this.modelMap.get(sidInt).getResult();
	}
	
	/**
	 * Method to get the name of the active player
	 * @return active player name
	 */
	@GET
	@Path("/getActivePlayer")
	
	public String getActivePlayer(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// Get active player from model
		return this.modelMap.get(sidInt).getActivePlayer().name;
	}
	
	/**
	 * Method to return JSON string representing human player information
	 * @param sid
	 * @return human player as JSON object
	 * @throws IOException
	 */
	@GET
	@Path("/showPlayer")

	public String showPlayer(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		// get human player object from model and convert to JSON
		String playerStr = oWriter.writeValueAsString(this.modelMap.get(sidInt).getHumanPlayer());
	
		return playerStr;

	}
	
	/**
	 * Method to return JSON string representing all players and their information
	 * @param sid
	 * @return players array as JSON string
	 * @throws IOException
	 */
	@GET
	@Path("/showPlayers")
	
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
	
	/**
	 * Method to return a list of any eliminated players
	 * @param sid
	 * @return list of eliminated players, otherwise '0'
	 * @throws IOException
	 */
	@GET
	@Path("/checkEliminations")
	
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
	
	/**
	 * Method to get communal pile size
	 * @param sid
	 * @return communal pile size
	 */
	@GET
	@Path("/communalPileSize")
	
	public String communalPileSize(@QueryParam("sid") String sid) {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		return "" + this.modelMap.get(sidInt).getCommunalPileSize();
	}
	
	/**
	 *  Method to check if human player still in game
	 * @param sid
	 * @return 1 if player still in game, 0 if not
	 * @throws IOException
	 */
	@GET
	@Path("/checkHumanPlayer")
	
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
	
	public String logGameStats(@QueryParam("sid") String sid) throws IOException {
		
		// Parse query sid and convert to int
		int sidInt = Integer.parseInt(sid);
		
		GameStats gameStats = this.modelMap.get(sidInt).getGameStats();
		
		System.out.println("Rounds: " + gameStats.getRounds()
				+ "\nDraws: " + gameStats.getDraws()
				+ "\nWinner: " + gameStats.getWinner());
		
		ArrayList<PlayerStats> playerStats = this.modelMap.get(sidInt).getPlayerStats();
		
		for(PlayerStats p: playerStats) {
			System.out.println("\nPlayer:" + p.getPlayer() 
								+ "\nRounds won: " + p.getScore());
		}
		

		return "1";	
	}
	
}