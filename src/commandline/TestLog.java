package commandline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestLog {

	FileWriter log;

	/**
	 * Constructor
	 */
	public TestLog(File f) {
		
		try {
			
			log = new FileWriter(f);
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
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Loaded Deck: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Should not be passed null (as this is game start)
		sb.append("HumanPlayer:\n" + p.remove(0).toString());
				
		// Loops through all AiPlayers and prints their hand
		for(int i = 0; i < p.size(); i++) {
			
			sb.append("AIPlayer" + (i + 1) + ":\n" + p.remove(0).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through the communal pile and prints each card
		for(int i = 0; i < cP.size(); i++) {
			
			sb.append(cP.remove(0).toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Communal Pile Contents: "  + sb);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Loops through all active cards and prints them
		for(int i = 0; i < p.size(); i++) {
			
			sb.append(p.remove(0).getCard().toString() + "\n");
		}
		
		// Formats log
		String st = new String("======================\n" + "Current Cards:\n"  + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the chosen category and each players associated value
	 * @param String Category 
	 * @param int Values
	 * @return Boolean if write was successful
	 */
	public void logCategoryandValues(String s, int[] n) {
		
		BufferedWriter w = new //BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Shuffled Deck: "  + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		StringBuffer sb = new StringBuffer();
		
		// Checks if humanPlayer is null and if not, appends hand
		if(b) {
			
			sb.append("HumanPlayer:\n" + p.remove(0).getCard().toString());
		}
				
		// Loops through all AiPlayers and prints their hand
		for(int i = 0; i < p.size(); i++) {
			
			sb.append("AIPlayer" + (i + 1) + ":\n" + p.remove(0).getCard().toString());
		}
		
		// Formats log
		String st = new String("======================\n" + "Starting Player Hands: " + sb);
	
		// Writes to file
		try {
			
			w.write(st);
			w.close();
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
		
		BufferedWriter w = new BufferedWriter(log);
		// Formats log
		String st = new String("======================\n" + "Game Winner: " + s);
		
		// Writes to file
		try {
			
			w.write(st);
			w.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
