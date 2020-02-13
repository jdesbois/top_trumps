package commandline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Aaron Callaghan
 * <br><br>
 * 
 *Constructor:<br>
 *	TestLog(File f, boolean b)<br><br>
 *
 *Public methods:<br>
 *	public void logDeck(String)<br>
 *	public void logShuffledDeck(String)<br>
 *	public void logStartingHands(ArrayLisy<Player>)<br>
 *	public void logCommunalPile(ArrayList<Card>)<br>
 *	public void logCurrentCards(ArrayList<Player>)<br>
 *	public void logCategoryandValues(String, int[], boolean)<br>
 *	public void logHands(ArrayList<Player>, boolean)<br>
 *	public void logWinner(String)
 */
public class TestLog {

	// File Writer associated with log file
	FileWriter log;
	// BufferedWriter using log
	BufferedWriter w;
	
	// Boolean representing whether logs are written
	boolean shouldWrite;
	
	/**
	 * Constructor
	 */
	public TestLog(File f, boolean b) {
		
		shouldWrite = b;
	
		try {
			
			// Initialises FileWriter
			log = new FileWriter(f);
			// Initialises BufferedWriter
			w = new BufferedWriter(log);
		} catch(IOException e) {
			
			System.err.print("FileWriter initialisation error");
		}
	}
	
	/**
	 * Logs the deck state when it's loaded from file
	 * @param String toString of Deck
	 * @return Boolean if write was successful
	 */
	public void logDeck(String s) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
			
			return;
		}
		
		// Formats log
		String st = new String("======================\n" + "Loaded Deck: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the deck after its been shuffled
	 * @param String toString of Deck after shuffle
	 * @return Boolean if write was successful
	 */
	public void logShuffledDeck(String s) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
				
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + s);
		
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the Player hands at the start of the game
	 * Note: Expects HumanPlayer as the first entry as it's initialised before AI players
	 * @param String toString of playerHands
	 * @return Boolean if write was successful
	 */
	public void logStartingHands(ArrayList<Player> p) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Should not be passed null (as this is game start)
		sb.append("HumanPlayer:\n" + p.get(0).getPlayerHand().toString());
				
		// Loops through all AiPlayers and prints their hand
		for(int i = 1; i < p.size(); i++) {
			
			sb.append("AIPlayer" + (i + 1) + ":\n" + p.get(i).getPlayerHand().toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the communal pile after cards are added or removed
	 * @param String communal pile
	 * @return Boolean if write was successful
	 */
	public void logCommunalPile(ArrayList<Card> cP) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Loops through the communal pile and prints each card
		for(int i = 0; i < cP.size(); i++) {
			
			sb.append(cP.get(i).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Communal Pile Contents: "  + sb);
		
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs cards in play
	 * @param String each players top card 
	 * @return Boolean if write was successful
	 */
	public void logCurrentCards(ArrayList<Player> p) {
		
		// Stores an ArrayList to be manipulated
		ArrayList<Player> list = p;
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Loops through all active cards and prints them
		for(int i = 0; i < p.size(); i++) {
			
			sb.append(list.get(i).getCard().toString() + "\n");
		}
		
		// Formats log
		String st = new String("======================\n" + "Current Cards:\n"  + sb);
	
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the chosen category and each players associated value
	 * @param String Category 
	 * @param int Values
	 * @param boolean representing whether the humanPlayer is active
	 * @return Boolean if write was successful
	 */
	public void logCategoryandValues(String s, int[] n, boolean b) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		StringBuffer sb = new StringBuffer("Player card values for the chosen attribute are:\n");
			
		// Loops through all values and separates human player from ai players
		for(int i = 0; i < n.length; i++) {
				
			if(i == 0 && b) {
					
				sb.append("Human Player value is: " + n[i]);
			} else {
					
				sb.append("\nAI Player values is: " + n[i]);
			}
		}
		
		
		// Formats log
		String st = new String("======================\n" + "Chosen Category Is: "  + s + "\n" + sb);
		
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the hands after every round
	 * Note: Expects HumanPlayer as the first entry as it's initialised before AI players
	 * @param String each player's hand contents
	 * @param Boolean is humanPlayer active
	 * @return Boolean if write was successful
	 */
	public void logHands(ArrayList<Player> p, boolean b) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		// Checks if humanPlayer is null and if not, appends hand
		if(b) {
			
			sb.append("HumanPlayer:\n" + p.get(0).getCard().toString());
		}
				
		// Loops through all AiPlayers and prints their hand
		for(int i = 1; i < p.size(); i++) {
			
			sb.append("AIPlayer" + (i + 1) + ":\n" + p.get(i).getCard().toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the game winner
	 * @param String winner
	 * @return Boolean if write was successful
	 */
	public void logWinner(String s) {
		
		// If the user doesn't want to write to log return
		if(!shouldWrite) {
					
			return;
		}
		
		// Formats log
		String st = new String("======================\n" + "Game Winner: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
			// Closes BufferedWriter
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
