package commandline;
import java.util.*;
/**
 * 
 * @author Mike
 * matric 2205885M
 * <br><br>
 * constructor: <br>
 * String name, PlayerHand gameHand <br><br>
 * 
 * Public methods: <br>
 * int getHandSize() <br>
 * void drawNewCard() <br>
 * Card getCard() <br>
 * int getHighestAtribute <br>
 * void addCardsAtBottom() <br>
 * void printCards() <br>
 * String getName() <br>
 *
 */
public class Player {
	public String name;
	private PlayerHand gameHand;
	private Card currentCard;
	
	
	public Player(String name, PlayerHand gameHand ) {
		this.name = name;
		this.gameHand = gameHand;
	} 
	/**
	 * calculate size of the players array list gameHand. 
	 * @return int size of array
	 */
	public int getHandSize()
	{
		int handSize = gameHand.getHand().size();
		return handSize;
	}
	/**
	 * calls method of gameHand to retrieve the top card from the deck and sets as 
	 * player's currentCard
	 */
	public void drawNewCard() 
	{ 
		currentCard = gameHand.getTopCard();
	}
	
	/**
	 * method simply retrieves the currentCardAtribute
	 * @return currentCard card object to be accessed elsewhere in the project
	 */
	public Card getCard()
	{
		return currentCard;
	}
	
	/**
	 * Create tempArray to store a Player's currentCard values 
	 * store initial value in maxValue and compare with all other values.
	 * if the value is greater than initial it is stored and its index stored in "index"
	 * 
	 * @return index of the largest value in values array returned to be used as 
	 * chosen attribute
	 */
	
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
	
	/**
	 * Access gameHand method addMultiplecards
	 * @param winPool array list from each round is added to the winners gameHand.
	 */
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
	 * access players gameHand
	 * @return gameHand
	 */
	public PlayerHand getPlayerHand() {
		
		return gameHand;
	}
	
	/**Simply returns name of each player
	 * @return String name
	 */
	public String getName() {
		
		return name;
	}
}




