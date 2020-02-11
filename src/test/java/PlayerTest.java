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

public class PlayerTest {
    Deck deck = new Deck();
    ArrayList<PlayerHand> hands = deck.deal();
    PlayerHand hand = hands.get(0);
    Player player = new Player("John", hand);



    @Test
    public void testGetHandSize() {

        assertEquals(8, player.getHandSize());
    }

    @Test
    public void testDrawNewCard() {

        player.drawNewCard();
        assertNotNull(player.getCard());
    }

    @Test 
    public void testGetCard() {

        player.drawNewCard();
        assertEquals(player.currentCard, player.getCard());
    }

    @Test
    public void testGetName() {

        assertEquals("John", player.getName());
    }
    
    @Test 
    public void testGetHighestAttri() {

        player.drawNewCard();
        assertEquals(2, player.getHighestAttribute());
    }

    @Test 
    public void testAddCardAtBottom() {

        player.addCardAtBottom(hands.get(1).getHand());
        assertEquals(16, player.getHandSize());
    }

}