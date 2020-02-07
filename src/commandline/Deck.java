package commandline;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import online.configuration.TopTrumpsJSONConfiguration;

import java.io.*;

/*
The deck class will read in the cards from a .txt file
Create the card objects
Store them in an array list
*/
public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<PlayerHand> hands = new ArrayList<PlayerHand>();
    TopTrumpsJSONConfiguration conf = new TopTrumpsJSONConfiguration();

    public Deck() {
        //Creates deck by using the import deck function
        try {
            importDeck();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //getters/setters
    public ArrayList<Card> getDeck() {
        return deck;
    }
    //Returns the card using the toString() method of the card object
    public Card getCard(int i) {
        return deck.get(i);
    }
    /*
    This is the toString() method which iterates over the deck, adds each card to the String on a new line
    Returns the completed String as a whole.
    */
    public String toString() {
        String output = "";
        for (int i = 0; i < deck.size(); i++) {
            output += deck.get(i) + "\n";
        }    
        return output;    
    }

    //Method to shuffle deck 
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }
    /*
    Method to take call card objects in deck and deal them to 5 players (can be modified for specific number of players later)
    Instaniates 5 player hand objects, adds these playerhand objects to a Hands arraylist
    While the deck is not equal to 0, method iterates over player hands, taking 0th index item, removing it from deck and placing it into a player hand. 
    */
    public ArrayList<PlayerHand> deal() {
        //Create 5 Player hand objects (will later be modified to accept int and create only those about of hands)
        PlayerHand hand1 = new PlayerHand();
        PlayerHand hand2 = new PlayerHand();
        PlayerHand hand3 = new PlayerHand();
        PlayerHand hand4 = new PlayerHand();
        PlayerHand hand5 = new PlayerHand();
        //Adds the PlayerHand objects to the Hands ArrayList
        hands.add(hand1);hands.add(hand2);hands.add(hand3);hands.add(hand4);hands.add(hand5);
        //Will perform until deck reaches 0
        while (deck.size() != 0) {
            //iterates over the hands arraylist adding top card from deck to each hand, until deck is empty
            for (int i=0; i<hands.size(); i++) {
                hands.get(i).add(deck.remove(0));
            }
        }
        return hands;
    }

    public void importDeck() throws Exception {
        //Creates file object
        File file = new File("StarCitizenDeck.txt");
        //Retrieves file absolute path
        String path = file.getAbsolutePath();
        //Creates file reader and loads in file
        FileReader fr = new FileReader(path);
        //Passes file contents to scanner
        Scanner myScanner = new Scanner(fr);
        //Creates attributes array to be passed to Card Constructor
        String[] attributes = new String[5];
        /*
        These lines strip the first line from the file
        Split the first line into an array
        Iterates over the split array and puts the attribute headers into the attribues array
        */
        String firstLine = myScanner.nextLine();
        String[] firstLineSplit = firstLine.split(" ");
        for (int i=1; i < firstLineSplit.length; i++) {
            attributes[i-1] = firstLineSplit[i];
        }
        
        /*
        Iterates over rest of file in scanner line by line
        Takes a line and splits its contents
        Iterates over line from position 1, to avoid taking in the string, and puts values into values array
        Then creates a card object for each line in file, passing Description name, attributes headers and values to constructor
        */
        while (myScanner.hasNext()) {
            int[] values = new int[5];
            String[] split = myScanner.nextLine().split(" ");
            for (int i=1; i < split.length; i++) {
                int value = Integer.parseInt(split[i]);
                values[i-1] = value;
            }
            deck.add(new Card(split[0], attributes, values));
        }
        myScanner.close();
    }
}
