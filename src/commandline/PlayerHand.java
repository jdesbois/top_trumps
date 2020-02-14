package commandline;

import java.util.ArrayList;
/*
    This class creates and holds an ArrayList of Card objects in reference to a Player holding their hand of cards.
*/
/**
 * PlayerHand class for Top Trumps game
 * @author John Desbois
 * <br><br>
 * 
 * Constructor:<br>
 * 	PlayerHand()<br><br>
 * 
 * Public methods:<br>
 * 	public void addCard(Card) <br>
 *  public void addMultipleCards(ArrayList<Card>)<br>
 *  public ArrayList<Card> getHand() <br>
 *  public Card getTopCard() <br>
 *  public String toString()
 * 
 */
public class PlayerHand {
    private ArrayList<Card> hand = new ArrayList<Card>();
/**
 * Constructor <br>
 */
    public PlayerHand() {}
/**
 * Method to add a card to the hand. 
 * @param Card that is to be added to hand ArrayList
 */
    public void add(Card c) {
        hand.add(c);
    }
/**
 * Method in order to add multiple cards to the array at once
 * @param ArrayList<Card> to be added to hand ArrayList
 */
    public void addMultipleCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.hand.add(card);
        }
    }
/**
 * Method to retrive the player hand 
 * @return ArrayList<Card> of card objects in the current PlayerHand
 */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public Card newTopCard() {
        return this.hand.remove(0);
    }
/**
 * toString method that iterates over hand ArrayList and print each card onto a new line.
 * @return String of all cards in hand ArrayList on new line concatenated together.
 */
    public String toString() {
        String output = "";
        for (Card c  : hand) {
            output += c + " ";
        }
        output += "====================";
        return output;
    }

}