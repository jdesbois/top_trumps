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

public class CardTest {
    
    String[] attri = {"Size", "Speed", "Range", "Firepower", "Cargo"};
    int[] values = {1,9,2,3,0};
    String desc = "350r";
    Card card = new Card(desc, attri, values);
    

    @Test
    public void testGetDesc() {

        assertEquals("350r", card.getDesc());
    }

    @Test
    public void testGetPair() {

        assertEquals("Size : 1", card.getPair(0)); 
    }

    @Test
    public void testGetAttri() {

        assertNotNull(card.getAttri());
    }

    @Test
    public void testGetValues() {

        assertEquals(values, card.getValues());
    }

    @Test
    public void testGetValue() {

        assertEquals(9, card.getValue(1));
    }

    @Test
    public void testPrintWin() {

        assertEquals("\tDescription: "+desc+"\n" + "\t1. Size: 1\n\t2. Speed: 9 <------- \n\t3. Range: 2\n\t4. Firepower: 3\n\t5. Cargo: 0\n", card.printWin(1));
    }

    @Test
    public void testToString() {

        assertEquals("\tDescription: "+desc+"\n" + "\t1. Size: 1\n\t2. Speed: 9\n\t3. Range: 2\n\t4. Firepower: 3\n\t5. Cargo: 0\n", card.toString());
    }
}