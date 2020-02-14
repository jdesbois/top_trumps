package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * GameState class for Top Trumps Game
 * Functions as Model in MVC design pattern
 * @author Aaron Callaghan
 * <br><br>
 *
 *Constructor:<br>
 *	GameState(Deck d, int n)<br><br>
 *
 *Public methods:<br>
 *	public int getResult()<br>
 *	public ArrayList<Player> userEliminated()<br>
 *	public void drawNewCard()<br>
 *	public int getCurrentAttribute()<br>
 *	public void setCurrentAttribute<br>
 *	public Player getHumanPlayer()<br>
 *	public int getCommunalPileSize()<br>
 *	public int getRoundNumber()<br>
 *	public Player getWinner()<br>
 *	public int getPlayerSize()<br>
 *	public ArrayList<Player> getPlayers()<br>
 *	public int[] getAttributeValues()<br>
 *	public ArrayList<PlayerStats> getPlayerStats()<br>
 *	public GameStats getGameStats()
 */
public class GameState {

	// Stores all active Player objects
	private ArrayList<Player> players = new ArrayList<Player>();
	// Current Player object responsible for choosing attribute
	private Player activePlayer;
	// Stores which Player Object is controlled by the player
	private Player humanPlayer;
	// The current round number
	private int roundNumber = 1;
	// The current number of draws
	int draws = 0;
	// Stores Round winner
	private Player winner;
	// Stores the amount of round each player wins
	private HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
	// The attribute Chosen by activePlayer
	private int chosenAttribute;
	// Stores the communal pile in the result of a draw
	private ArrayList<Card> communalPile = new ArrayList<Card>();
	// Stores the gameDeck
	private Deck gameDeck;
	
	/**
	 * Constructor
	 */
	public GameState(Deck d, int n) {
		
		// Initialises gameDeck
		gameDeck = d;
		
		// Gets a new hand for each player object
		ArrayList<PlayerHand> h = gameDeck.deal(n);
		
		// Creates new human player
		Player p = new Player("You", h.remove(0));
		// Stores reference to human player
		humanPlayer = p;
		// Stores human player in players array
		players.add(p);
		// Stores human player in scores array
		scores.put(p, 0);
		
		// Loops through the amount of AIPlayers, 
		// sets up new player objects and deals hands
		for(int i = 1; i < n; i++) {
			
			// Creates new player object
			p = new Player("AIPlayer" + i, h.remove(0));
			
			// Adds new player object to players array
			players.add(p);
			// Stores new player in scores
			scores.put(p, 0);
		}
		
		// Selects a random number between 1 and n
		int rand = (int)(Math.floor(Math.random() * n));
		
		// Assigns random first player
		activePlayer = players.get(rand);
	}
	
	/**
	 * Returns an int representing the round result: 1 - win, 2 - draw
	 * @return int
	 */
	public int getResult() {
		
		// Always adds to communalPile as in case of winner pile is dealt anyway
		addCommunalPile();
		
		// Increments round number
		roundNumber++;
		
		// If there's no round winner, increment draws counter
		// and return 2
		if(!isWinningPlayer()) {
			
			draws++;
			return 2;
		}
		
		// If there is a round winner update scores and return 1
		updateScores();
		return 1;
	}
	
	/**
	 * Calculates and returns the player with the highest attribute value
	 * @return Player round winner
	 */
	private boolean isWinningPlayer() {
		
		// Int to store greatest value
		int highestVal = -1;
		
		// Int to store number of attributes equal to greatest value
		int highCount = 0;
		
		// Index of the attribute with greater value (only if no draw)
		int index = -1;
				
		for(int i = 0; i < players.size(); i++) {
			
			// Get selected attribute
			int n = players.get(i).getCard().getValue(chosenAttribute);
			
			// if this is the greatest value encountered so far
			if(n > highestVal) {
				// new highest value
				highestVal = n;
				// start counting if any others are equal
				highCount = 1;
				// update 
				index = i;
			}
			
			// otherwise, if this is equal to current greatest value
			else if(n == highestVal) {
				// increment counter
				highCount++;				
			}	
		}
		
		 // If highest value has been counted more than once 
		 // this indicated a draw (return false)
		if(highCount > 1) {
			return false;
		}
		
		 // Otherwise set player with greatest value to active 
		 // player and assign them the cards they have won
		 // (return true)
		winner = players.get(index);
		changeActivePlayer(winner);
		dealCommunalPile();
		return true;
	}
	
	/**
	 * Adds communalPile to winner hand
	 */
	private void dealCommunalPile() {
		
		winner.addCardAtBottom(communalPile);
		communalPile.clear();
	}
	
	/** 
	 * Returns and ArrayList<Player> and deletes them from players
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> userEliminated(){
		
		ArrayList<Player> eliminatedPlayer = new ArrayList<Player>();
		
		for(int i = 0; i < players.size(); i++) {
			
			while(i < players.size() && 
					players.get(i).getHandSize() == 0) {
				
				eliminatedPlayer.add(players.remove(i));
			}
		}
		
		return eliminatedPlayer;
	}
	
	/**
	 * Updates Player scores
	 * @param Player p
	 */
	private void updateScores() {
		
		// Gets the round winners score
		int n = scores.get(winner);
		// Increments score and updates scores
		scores.put(winner, ++n);
	}
	
	/**
	 * Returns a random Player to start the game
	 * @return Player firstPlayer
	 */
	public Player getActivePlayer() {
		
		// Returns player stored at index rand
		return activePlayer;
	}
	
	/**
	 * Updates the active Player object
	 * @param Player p
	 */
	private void changeActivePlayer(Player p) {
		
		activePlayer = p;
	}
	
	/**
	 * Calls all Player objects in players to draw a new Card
	 * Updates roundNumber
	 */
	public void drawNewCard() {
		
		// Loops through all players and draws a new card
		for(int i = 0; i < players.size(); i++) {
			
			players.get(i).drawNewCard();
		}
	}
	
	/**
	 * Returns the attribute selected by the activePlayer
	 * @return String attribute selected
	 */
	public int getCurrentAttribute() {
		
		return chosenAttribute;
	}
	
	/**
	 * Sets the chosenAttribute after one has been selected
	 */
	public void setCurrentAttribute(int n) {
		
		chosenAttribute = n;
	}
	
	/**
	 * Adds an ArrayList of Cards to the communalPile
	 * @param ArrayList<Card> c 
	 */
	private void addCommunalPile() {
		
		// Loops through all players and adds their card to communalPile
		for(int i = 0; i < players.size(); i++) {
			
			Card c = players.get(i).getCard();
			
			communalPile.add(c);
		}
	}
	
	/**
	 * Returns the Player Object controlled by the human player
	 * @return Player humanPlayer
	 */
	public Player getHumanPlayer() {
		
		return humanPlayer;
	}
	
	/**
	 * Returns the communalPile ArrayList
	 * @return ArrayList<Card> communalPile
	 */
	public int getCommunalPileSize(){
		
		return communalPile.size();
	}
	
	/**
	 * Returns the round number
	 * @return int roundNumber
	 */
	public int getRoundNumber() {
		
		return roundNumber;
	}
	
	/**
	 * Returns round winner
	 * @return Player winner
	 */
	public Player getWinner() {
		
		return winner;
	}
	
	/**
	 * Returns players
	 * @return ArrayList<Player> 
	 */
	public int getPlayersSize(){
		
		return players.size();
	}

	/**
	 * Gets players array
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		
		ArrayList<Player> p = players;
		
		return p;
	}
	
	/**
	 * Get currentAttribute values
	 * @return int[] Card attributes
	 */
	public int[] getAttributeValues() {
		
		// Creates an array to store attribute values
		int[] no = new int[players.size()];
		
		// Loops through every player and gets their card value
		// for the current attribute
		for(int i = 0; i < players.size(); i++) {
			
			no[i] = players.get(i).getCard().getValue(chosenAttribute);
		}
		
		return no;
	}
	
	/**
	 * Returns an ArrayList of PlayerStats Objects for game statistics
	 * @return ArrayList<PlayerStats> 
	 */
	public ArrayList<PlayerStats> getPlayerStats(){
		
		// ArrayList to be returned
		ArrayList<PlayerStats> playerList = new ArrayList<PlayerStats>();
		// Temporary storage for new PlayerStats objects
		PlayerStats playerStats;
		// Temporary storage for Player objects returned by playerstats iterator
		Player player;
		// Stores all player HashMap keys
		Set<Player> playerSet = scores.keySet();
		// Iterator to iterate through all playerstats entries
		Iterator<Player> it = playerSet.iterator();
		
		while(it.hasNext()) {
			
			// Stores next iterator value
			player = it.next();
			// Creates new PlayerStats object to store
			playerStats = new PlayerStats(player, scores.get(player));
			// Adds PlayerStats object to list
			playerList.add(playerStats);
		}
		
		return playerList;
	}
	
	/**
	 * Returns a GameStats Object for game statistics
	 * @return GameStats
	 */
	public GameStats getGameStats() {
		
		return new GameStats(this.winner, this.roundNumber, this.draws);
	}
}
