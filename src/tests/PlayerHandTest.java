package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import commandline.*;

public class PlayerHandTest {
    //Creationg of objects/variables used to assert methods inside are working
    Deck deck = new Deck();
    ArrayList<PlayerHand> hands = deck.deal();
    PlayerHand hand = hands.get(0);
    Player player = new Player("John", hand);
    Card card = player.getCard();

    @Test 
    public void testAdd() {
        System.out.println("Testing add method");
        player.drawNewCard(); 
        hand.add(card);
        assertEquals(8, hand.getHand().size()); //testing for total of 8 as initial draw card removes top card from hand
    }

    @Test
    public void testAddMultipleCards() {
        System.out.println("Testing add multiple cards method");
        hand.addMultipleCards(hands.get(1).getHand());
        assertEquals(16, hand.getHand().size()); //testing size of hand after adding another hand to it, each hand contains 8 cards
    }

    @Test 
    public void testGetHand() {
        System.out.println("Testing get hand method");
        assertNotNull(hand.getHand()); //testing to make sure that the returned object is nut null
    }

    @Test
    public void testGetTopCard() {
        System.out.println("Testing get top card method");
        assertNotNull(hand.getTopCard()); //testing to make sure the method doesn't not return a null object
    }

}