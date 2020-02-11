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

        assertEquals(card.toString(), deck.getCard(0).toString());
    }

    @Test
    public void testShuffleDeck() {

        Card firstCard = deck.getCard(0);
        deck.shuffleDeck();
        assertNotEquals(firstCard, deck.getCard(0));
    }

    @Test 
    public void testDeal() {

        assertNotEquals(deck.deal(), deck2.deal());
    }

}