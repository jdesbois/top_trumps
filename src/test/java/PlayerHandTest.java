package test.java;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import commandline.*;

import org.junit.jupiter.api.Test;

public class PlayerHandTest {
    //Creationg of objects/variables used to assert methods inside are working
    Deck deck = new Deck();
    ArrayList<PlayerHand> hands = deck.deal();
    PlayerHand hand = hands.get(0);
    Player player = new Player("John", hand);
    Card card = player.getCard();

    @Test 
    public void testAdd() {

        player.drawNewCard(); 
        hand.add(card);
        assertEquals(8, hand.getHand().size()); //testing for total of 8 as initial draw card removes top card from hand
    }

    @Test
    public void testAddMultipleCards() {

        hand.addMultipleCards(hands.get(1).getHand());
        assertEquals(16, hand.getHand().size()); //testing size of hand after adding another hand to it, each hand contains 8 cards
    }

    @Test 
    public void testGetHand() {

        assertNotNull(hand.getHand()); //testing to make sure that the returned object is nut null
    }

    @Test
    public void testGetTopCard() {

        assertNotNull(hand.newTopCard()); //testing to make sure the method doesn't not return a null object
    }

}