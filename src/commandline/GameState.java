package commandline;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState {

	// Stores all active Player objects
	private ArrayList<Player> players = new ArrayList<Player>();
	// Current Player object responsible for choosing attribute
	private Player activePlayer;
	// Stores which Player Object is controlled by the player
	private Player humanPlayer;
	// The current round number
	private int roundNumber = 0;
	// Stores Round winner
	private Player winner;
	// Stores the amount of round each player wins
	private HashMap<Player, Integer> scores = new HashMap<Player, Integer>(); // Might change
	// The attribute Chosen by activePlayer
	private int chosenAttribute;
	// Stores the communal pile in the result of a draw
	private ArrayList<Card> communalPile = new ArrayList<Card>();
	// Stores the gameDeck
	private Deck gameDeck = new Deck();
	
	/**
	 * Constructor
	 */
	public GameState() {
		
		gameDeck.shuffleDeck();
		
		ArrayList<PlayerHand> h = gameDeck.deal();
		
		for(int i = 0; i < 5; i++) {
			
			Player p = new Player("Player" + (i + 1), h.get(i));
			
			players.add(p);
			if(humanPlayer == null) {
				
				humanPlayer = p;
			}
		}
		
		int rand = (int)(Math.floor(Math.random() * 5));
		
		activePlayer = players.get(rand);
	}
	
	/**
	 * Calculates and returns the player with the highest attribute value
	 * @return Player round winner
	 */
	public void getWinningPlayer() {
		
		int highestVal = 0;
		Player winner = null;
		
		for(int i = 0; i < players.size(); i++) {
			
			int n = players.get(i).getCard().getValue(chosenAttribute);
			
			highestVal = Math.max(highestVal, n);
			
			if(n == highestVal) {
				
				winner = players.get(i);
			}
		}
		
		this.winner = winner;
		
		changeActivePlayer(winner);
	}
	
	/**
	 * Returns and ArrayList<Player> and deletes them from players
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> userEliminated(){
		
		ArrayList<Player> eliminatedPlayer = new ArrayList<Player>();
		
		for(int i = 0; i < players.size(); i++) {
			
			Player p = players.get(i);
			
			if(p.handSize() == 0) {
				
				eliminatedPlayer.add(p);
				players.remove(i);
			}
			
			return eliminatedPlayer;
		}
	}
	
	/**
	 * Updates Player scores
	 * @param Player p
	 */
	private static void updateScores(Player p) {
		
		
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
	private static void changeActivePlayer(Player p) {
		
		activePlayer = p;
	}
	
	/**
	 * Calls all Player objects in players to draw a new Card
	 * Updates roundNumber
	 */
	public void drawNewCard() {
		
		for(int i = 0; i < players.size(); i++) {
			
			players.get(i).drawNewCard();
		}
		
		roundNumber++;
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
	public void addCommunalPile() {
		
		for(int i = 0; i < players.size(); i++) {
			
			communalPile.add(players.get(i).getCard());
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
	public ArrayList<Card> getCommunalPile(){
		
		return communalPile;
	}
	
	/**
	 * Returns the round number
	 * @return int roundNumber
	 */
	public int getRoundMumber() {
		
		return roundNumber;
	}
	
	/**
	 * Returns round winner
	 * @return Player winner
	 */
	public Player getWinner() {
		
		return winner;
	}
}
