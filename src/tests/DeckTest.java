package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import commandline.*;

public class DeckTest {
    Deck deck = new Deck();
    Deck deck2 = new Deck();
    String[] attri = {"Size", "Speed", "Range", "Firepower", "Cargo"};
    int[] values = {1,9,2,3,0};
    String desc = "350r";
    Card card = new Card(desc, attri, values);

    @Test
    public void testGetDeck() {
        System.out.println("Testing the getDeck method");
        //testing to making sure we have two separate decks from the different objects
        assertNotSame(deck.getDeck(), deck2.getDeck());
    }

    @Test 
    public void testGetCard() {
        System.out.println("Testing the getCard method");
        assertEquals(card.toString(), deck.getCard(0).toString());
    }

    @Test
    public void testShuffleDeck() {
        System.out.println("Testing shuffle method");
        Card firstCard = deck.getCard(0);
        deck.shuffleDeck();
        assertNotEquals(firstCard, deck.getCard(0));
    }

    @Test 
    public void testDeal() {
        System.out.println("Testing deal method");
        assertNotEquals(deck.deal(), deck2.deal());
    }

}