package commandline;
import java.util.*;

public class Player {
	public String name;
	
	public String[] computerPlayers = {"Alphabet", "GoogleAssistant", "Siri", "Cortana", "Jarvis", "Pablo"};
		
	public ArrayList<Card> deckOfCards;
	
	public Player() {
		
		this.name = "";
		deckOfCards = new ArrayList<Card>();
	}
	
	// Human players would have thier names set
	public Player(String name) {
		this.name = name;
		deckOfCards = new ArrayList<Card>();
	}
	
	public void addCard(Card card)
	{
		deckOfCards.add(card);
	}
	
	public void addCardAtBottom(Card card)
	{		
		deckOfCards.add(0, card);
	}
	
	public void printCards()
	{
		for (int i= 0 ; i < deckOfCards.size(); i++)
		{
			Card c = deckOfCards.get(i);
			System.out.print(c.toString());
		}
	}
	
	public Card getTopCard()
	{
		Card c  = deckOfCards.get(deckOfCards.size()-1);
		deckOfCards.remove(deckOfCards.size()-1);
		return c;
	}
	
	
	
}
