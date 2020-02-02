//package commandline;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import org.junit.jupiter.api.Test;
//
//class GameStateTest {
//
//	//Test GameState Object
//	GameState g = new GameState();
//	
//	@Test
//	void getWinningPlayerTest() {
//
//		GameState g2 = new GameState();
//		
//		g2.drawNewCard();
//		g2.setCurrentAttribute(2);
//		
//		assertEquals("Player5", g2.getWinningPlayer().getName());
//	}
//	
//	@Test
//	void changeActivePlayerTest() {
//		
//		Player p = new Player("Player1");
//		
//		g.changeActivePlayer(p);
//		
//		assertEquals(p, g.getActivePlayer());
//	}
//	
//	@Test
//	void drawNewCardTest() {
//		
//		String[] c1s = {"boop1", "boop2", "boop3", "boop4", "boop5"};
//		int[] c1v = {1, 2, 3, 4, 5};
//		Card c1 = new Card("Boop", c1s, c1v);
//		
//		assertEquals(null, g.getActivePlayer().getCard());
//		
//		g.drawNewCard();
//		
//		assertEquals(c1.getValue(1), g.getActivePlayer().getCard().getValue(1));
//	}
//	
//	@Test
//	void setCurrentAttributeTest() {
//		
//		g.setCurrentAttribute(1);
//		
//		assertEquals(1, g.getCurrentAttribute());
//	}
//	
//	@Test
//	void addCommunalPileTest() {
//		ArrayList<Card> c = new ArrayList<Card>();
//		String[] c1s = {"boop1", "boop2", "boop3", "boop4", "boop5"};
//		int[] c1v = {1, 2, 3, 4, 5};
//		Card c1 = new Card("Boop", c1s, c1v);
//		
//		for(int i = 0; i < 5; i++) {
//			
//			c.add(c1);
//		}
//		
//		g.drawNewCard();
//		g.addCommunalPile();
//		
//		for(int i = 0; i < 5; i++) {
//		
//			assertEquals(c.get(i).getDesc(), g.getCommunalPile().get(i).getDesc());
//		}
//	}
//}
