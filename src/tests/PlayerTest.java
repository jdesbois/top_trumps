package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import commandline.*;

public class PlayerTest {
    Deck deck = new Deck();
    PlayerHand hand = deck.deal().get(0);
    Player player = new Player("John", hand);


    @Test
    public void testGetHandSize() {
        System.out.println("Testing get hand size method");
        assertEquals(8, player.getHandSize());
    }

    @Test
    public void testDrawNewCard() {
        System.out.println("Testing drawNewCard method");
        player.drawNewCard();
        assertNotNull(player.getCard());
    }

    @Test 
    public void testGetCard() {
        System.out.println("Testing get card method");
        player.drawNewCard();
        assertEquals(player.currentCard, player.getCard());
    }

    @Test
    public void testGetName() {
        System.out.println("Testing get name method");
        assertEquals("John", player.getName());
    }
}