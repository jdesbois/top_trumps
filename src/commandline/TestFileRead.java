package commandline;

import java.io.*;

public class TestFileRead {

    public static void main(String args[]) {
        Deck deck = new Deck();
        try {
            deck.importDeck();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
