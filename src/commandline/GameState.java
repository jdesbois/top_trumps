package commandline;

import java.util.ArrayList;
//import java.util.HashMap;

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
	//private HashMap<Player, Integer> scores = new HashMap<Player, Integer>(); // Might change
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
			
			Player p = new Player("Player" + (i + 1), h.remove(0));
			
			players.add(p);
			if(humanPlayer == null) {
				
				humanPlayer = p;
			}
		}
		
		int rand = (int)(Math.floor(Math.random() * 5));
		
		activePlayer = players.get(rand);
	}
	
	/**
	 * Returns an int representing the round result: 1 - win, 2 - draw
	 */
	public int getResult() {
		
		addCommunalPile();
		
		if(!getWinningPlayer()) {
			
			return 2;
		}
		
		return 1;
	}
	
	/**
	 * Calculates and returns the player with the highest attribute value
	 * @return Player round winner
	 */
	private boolean getWinningPlayer() {
		
		//Original

		// int highestVal = 0;
		// int index = 10;
		
		// for(int i = 0; i < players.size(); i++) {
			
		// 	int n = players.get(i).getCard().getValue(chosenAttribute);
			
		// 	if(n == highestVal) {
				
		// 		return false;
		// 	}

		// 	if(n > highestVal) {
				
		// 		index = i;
		// 	}
			
		// 	highestVal = Math.max(highestVal, n);
		// }
		
		/*
		 * Updates to fix bugs
		 */
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
		
		/*
		 * If highest value has been counted more than once
		 * this indicated a draw (return false)
		 */
		if(highCount > 1) {
			return false;
		}
		
		/*
		 * Otherwise set player with greatest value to active 
		 * player and assign them the cards they have won
		 * (return true)
		 */
		System.out.println("Winner index: " + index);
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
	
	/**it 
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
//	private void updateScores(Player p) {
//		
//		
//	}
	
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
		return players;
	}
}
