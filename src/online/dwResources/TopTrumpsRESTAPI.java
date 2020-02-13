package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
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
import commandline.Player;

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
	GameState model;
	
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
//		model = new GameState();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	// create a new game
	@GET
	@Path("/newGame")

	public String newGame() throws IOException {
		
		Deck deck = new Deck();
		
		this.model = new GameState(deck, 5);

		return "1";

	}
	
	
	/**
	 * Method to return round number
	 * @return round number as string
	 * @throws IOException
	 */
	@GET
	@Path("/getRoundNo")

	public String getRoundNo() throws IOException {
		
		String result = "" + this.model.getRoundNumber();

		return result;

	}
	
	/**
	 * Method to check if human player turn or AI turn
	 * @return int 1 if player turn, two if AI turn
	 * @throws IOException
	 */
	@GET
	@Path("/checkTurn")

	public String checkTurn() throws IOException {
		String result;
		if(model.getActivePlayer().getName().equals(
				model.getHumanPlayer().getName())) {
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

	public String drawCards() throws IOException{

		// remove this when in actual game
		model.drawNewCard();
		
		return "New cards drawn";

	}
	
	
	/**
	 * Method to set human player category
	 * @param Category
	 * @return Category
	 */
	@GET
	@Path("/selectCategory")
	
	public String selectCategory(@QueryParam("Category") String Category) {
		
		int catInt = Integer.parseInt(Category);
		
		this.model.setCurrentAttribute(catInt - 1);
		
		return Category;
		
	}
	
	/**
	 * Method to set AI player category
	 * @return Selected category
	 */
	@GET
	@Path("/AISelectCategory")
	
	public String selectCategoryAI() {
		
		int catInt = model.getActivePlayer().getHighestAttribute();

		// System.out.println("integer input to model: " + catInt);
		
		this.model.setCurrentAttribute(catInt - 1);
		
		String catStr = Integer.toString(catInt);
		
		return catStr;
	}
	
	/**
	 * Method to check result (win or draw)
	 * @return 1 if win, 2 if draw
	 */
	@GET
	@Path("/getResult")
	
	public String getResult() {
		
		return "" + this.model.getResult();
	}
	
	/**
	 * Method to get the name of the active player
	 * @return active player name
	 */
	@GET
	@Path("/getActivePlayer")
	
	public String getActivePlayer() {
		return this.model.getActivePlayer().name;
	}
	
	
	@GET
	@Path("/showPlayer")

	public String showPlayer() throws IOException {

		String playerStr = oWriter.writeValueAsString(model.getHumanPlayer());
	
		return playerStr;

	}
	
	@GET
	@Path("/showPlayers")
	
	public String showPlayers() throws IOException {
		
		ArrayList<Player> players = this.model.getPlayers();
		
		String playersStr = oWriter.writeValueAsString(players);
		// System.out.println(playersStr);
		
		return playersStr;
	}
	
	@GET
	@Path("/checkEliminations")
	
	public String checkEliminations() throws IOException{
		ArrayList<Player> usersEliminated = model.userEliminated();
		
			if(usersEliminated.size() > 0) {
			
				String elimStr = oWriter.writeValueAsString(usersEliminated);
				// System.out.println(elimStr);
				return elimStr;
			
			}
			
			return "0";
	}
	
	/*
	 * Get communal pile size
	 */
	@GET
	@Path("/communalPileSize")
	
	public String communalPileSize() {
		
		return "" + this.model.getCommunalPileSize();
	}
	
	
	@GET
	@Path("/checkHumanPlayer")
	
	public String checkHumanPlayer() throws IOException {
		
		Player humanPlayer = model.getHumanPlayer();
		
		if(humanPlayer.getHandSize() != 0) {
			// System.out.println("check human player gives 1");
			return "1";
		}
		else {
			// System.out.println("check human player gives 0");
			return "0";
		}
			
	}
	
	
}