package commandline;

import java.util.ArrayList;

public class GameState {

	// Stores all active Player objects
	private ArrayList<Player> players = new ArrayList<Player>();
	// Current Player object responsible for choosing attribute
	private Player activePlayer;
	// The Player object assigned to the human player
	private Player humanPlayer;
	// The current round number
	private int roundNumber = 0;
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
		
		for(int i = 0; i < 5; i++) {
			
			Player p = new Player("Player" + (i + 1));
			
			players.add(p);
			if(humanPlayer == null) {
				
				humanPlayer = p;
			}
		}
		
		int rand = (int)(Math.floor(Math.random() * 5));
		
		activePlayer = players.get(rand);
		
		//dealCards(gameDeck);
	}
	
	/**
	 * Deals out the cards to all Players
	 * @param Deck d
	 */
	private static void dealCards(Deck d) {
		
		
	}
	
	/**
	 * Calculates and returns the player with the highest attribute value
	 * @return Player round winner
	 */
	public Player getWinningPlayer() {
		
		int highestVal = 0;
		Player winner = null;
		
		for(int i = 0; i < players.size(); i++) {
			
			int n = players.get(i).getCard().getValue(chosenAttribute);
			
			highestVal = Math.max(highestVal, n);
			
			if(n == highestVal) {
				
				winner = players.get(i);
			}
		}
		
		return winner;
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
	public void changeActivePlayer(Player p) {
		
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
	
	/*
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
}
