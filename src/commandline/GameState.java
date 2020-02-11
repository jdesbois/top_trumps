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
	private int roundNumber = 1;
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
	public GameState(Deck d) {
		
		gameDeck = d;
		
		ArrayList<PlayerHand> h = gameDeck.deal();
		
		humanPlayer = new Player("You", h.remove(0));
		players.add(humanPlayer);
		scores.put(humanPlayer, 0);
		
		for(int i = 1; i < 5; i++) {
			
			ComputerPlayer p = new ComputerPlayer("AIPlayer" + (i + 1), h.remove(0));
			
			scores.put(p, 0);
			players.add(p);
		}
		
		int rand = (int)(Math.floor(Math.random() * 5));
		
		if(rand == 4) {
			
			activePlayer = humanPlayer;
		} else {
			
			activePlayer = players.get(rand);
		}
		
	}
	
	/**
	 * Returns an int representing the round result: 1 - win, 2 - draw
	 */
	public int getResult() {
		
			
		if(!getWinningPlayer()) {
			
			addCommunalPile();
			return 2;
		}
		
		return 1;
	}
	
	/**
	 * Calculates and returns the player with the highest attribute value
	 * @return Player round winner
	 */
	private boolean getWinningPlayer() {
		
		// Stores the highest value for the chosen attribute
		int highestVal = 0;
		// Counts the amount of matching values (resets to 1 if a high values is found)
		int highCount = 0;
		// Stores round winner
		Player roundWinner = null;
		
		/*
		 * checks all aiPlayer cards
		 */
		for(int i = 0; i < players.size(); i++) {
			
			// Stores value of chosen attribute for this player
			int n = players.get(i).getCard().getValue(chosenAttribute);
			
			// Checks if value matches a previous value
			if(n == highestVal) {
				
				// If matching, increments highCount
				highCount++;
			}

			// If new highest value is found: set round winner and set highCount to 1
			if(n > highestVal) {
				
				roundWinner = players.get(i);
				highCount = 1;
			}
			
			// Highest value is the biggest value (out of current player value and stored highest value)
			highestVal = Math.max(highestVal, n);
		}
		
		if(highCount > 1) {
			
			return false;
		} else {
			
			winner = roundWinner;
		}
		
		// Updates active player
		changeActivePlayer(winner);
		// Deals active cards
		dealActiveCards();
		// Updates scores
		updateScores();
		
		// If the communal pile isn't empty, deal it to winner
		if(communalPile.size() != 0) {
			
			dealCommunalPile();
		}
		
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
	 * Adds active cards to winners hand
	 */
	private void dealActiveCards() {
		
		ArrayList<Card> activeCards = new ArrayList<Card>();
		
		// Loops through all players and adds their current card to an array
		for(int i = 0; i < players.size(); i++) {
			
			activeCards.add(players.get(i).getCard());
		}
		
		winner.addCardAtBottom(activeCards);
		activeCards.clear();
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
		
		if(humanPlayer != null &&
				humanPlayer.getHandSize() == 0) {
			
			humanPlayer = null;
		}
		
		return eliminatedPlayer;
	}
	
	/**
	 * Updates Player scores
	 * @param Player p
	 */
	private void updateScores() {
		
		int n = scores.get(winner);
		scores.put(winner, ++n);
	}
	
	/**
	 * Returns all active player hands
	 */
	public ArrayList<PlayerHand> getHands() {
		
		ArrayList<PlayerHand> playerHands = new ArrayList<PlayerHand>();
		
		for(int i = 0; i < players.size(); i++) {
			
			playerHands.add(players.get(i).getPlayerHand());
		}
		
		if(humanPlayer != null) {
			
			playerHands.add(humanPlayer.getPlayerHand());
		}
		
		return playerHands;
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
	 * Get currentAttribute values
	 * @return int[] Card attributes
	 */
	public int[] getAttributeValues() {
		
		int[] no = new int[5];
		
		for(int i = 0; i < players.size(); i++) {
			
			no[i] = players.get(i).getCard().getValue(chosenAttribute);
		}
		
		return no;
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
	 * Returns players ArrayList
	 * @return ArrayList<Player> players
	 */
	public ArrayList<Player> getPlayers(){
		
		return players;
	}
	
	/**
	 * Returns players
	 * @return ArrayList<Player> 
	 */
	public int getPlayersSize(){
		
		return players.size();
	}
}
