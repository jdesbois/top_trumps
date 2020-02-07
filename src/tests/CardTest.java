package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import commandline.*;

public class CardTest {
    
    String[] attri = {"Size", "Speed", "Range", "Firepower", "Cargo"};
    int[] values = {1,9,2,3,0};
    String desc = "350r";
    Card card = new Card(desc, attri, values);
    

    @Test
    public void testGetDesc() {
        System.out.println("Testing getDesc method");
        assertEquals("350r", card.getDesc());
    }

    @Test
    public void testGetPair() {
        System.out.println("Testing getPair method"); 
        assertEquals("Size : 1", card.getPair(0)); 
    }

    @Test
    public void testGetAttri() {
        System.out.println("Testing getAttri method");
        assertEquals(attri, card.getAttri());
    }

    @Test
    public void testGetValues() {
        System.out.println("Testing getValues method");
        assertEquals(values, card.getValues());
    }

    @Test
    public void testGetValue() {
        System.out.println("Testing getValue method");
        assertEquals(9, card.getValue(1));
    }

    @Test
    public void testPrintWin() {
        System.out.println("Testing printWin method");
        assertEquals("Description: "+desc+"\n" + "Size: 1\nSpeed: 9 <------- \nRange: 2\nFirepower: 3\nCargo: 0\n", card.printWin(1));
    }

    @Test
    public void testToString() {
        System.out.println("Testing toString method");
        assertEquals("Description: "+desc+"\n" + "Size: 1\nSpeed: 9\nRange: 2\nFirepower: 3\nCargo: 0\n", card.toString());
    }
}