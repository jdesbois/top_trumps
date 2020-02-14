package commandline;
import java.util.*;

public class Player {
	public String name;
	private PlayerHand gameHand;
	private Card currentCard;
	
	
	// Human players would have there names set
	public Player(String name, PlayerHand gameHand ) {
		this.name = name;
		this.gameHand = gameHand;
	} 
	// return size of players hand
	public int getHandSize()
	{
		int handSize = gameHand.getHand().size();
		return handSize;
	}
	
	public void drawNewCard() 
	{ //done
		currentCard = gameHand.newTopCard();
	}
	
	public Card getCard()
	{
		return currentCard;
	}
	
	public int getHighestAttribute() 
	{ // fill tempArray with values from current card
		int[] tempArray = new int[5];
		tempArray= currentCard.getValues();
		int maxValue = currentCard.getValues()[0];
		int index = 0;
		// find largest value held in current card
		for (int i = 0; i < currentCard.getValues().length; i++) {
			tempArray[i] = currentCard.getValues()[i];
		if (currentCard.getValues()[i] > maxValue) {
			maxValue = currentCard.getValues()[i];
			index = i;
			
		}
		// return index of highest value as "AI" move selection
		}return index +1;
	}
	
	// method to add winnings to playerHand if needed
	public void addCardAtBottom(ArrayList<Card> winPool)
	{		
		gameHand.addMultipleCards(winPool);
	}
	
	// print contents of playerHand for testing purposes
	public void printCards()
	{
		for (int i= 0 ; i < gameHand.getHand().size(); i++)
		{
			Card c = gameHand.getHand().get(i);
			System.out.print(c.toString());
		}
	}
	
	/**
	 * Returns playerhand
	 * @return PlayerHand
	 */
	public ArrayList<Card> getPlayerHand() {
		
		return gameHand.getHand();
	}
	
	/**
	 * Returns name
	 * @return String
	 */
	public String getName() {
		
		return name;
	}
}




