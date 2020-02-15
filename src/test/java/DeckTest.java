package test.java;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import commandline.*;

import org.junit.jupiter.api.Test;

public class DeckTest {
    Deck deck = new Deck();
    Deck deck2 = new Deck();
    String[] attri = {"Size", "Speed", "Range", "Firepower", "Cargo"};
    int[] values = {1,9,2,3,0};
    String desc = "350r";
    Card card = new Card(desc, attri, values);

    @Test
    public void testGetDeck() {

        //testing to making sure we have two separate decks from the different objects
        assertNotSame(deck.getDeck(), deck2.getDeck());
    }

    @Test 
    public void testGetCard() {

        assertEquals(deck2.getCard(0).toString(), deck.getCard(0).toString());
    }

    @Test
    public void testShuffleDeck() {
        deck.shuffleDeck();
        int index = 0;
        int count = 0;
        assertNotEquals(deck, deck2);
        // for (Card card : deck2.getDeck()) {
        //     if (count == 5) {
        //         return;
        //     }
        //     assertNotEquals(card, deck.getDeck().get(index));
        //     index++;
        //     count++;
        // }        
    }

    @Test 
    public void testDeal() {

        assertNotEquals(deck.deal(), deck2.deal());
    }

}