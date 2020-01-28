package commandline;

import java.util.ArrayList;

public class PlayerHand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public PlayerHand() {}

    public void add(Card c) {
        hand.add(c);
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public Card getCard(int i) {
        return this.hand.get(i);
    }
    public String toString() {
        String output = "";
        for (Card c  : hand) {
            output += c + " ";
        }
        output += "END OF HAND!!!!! ======";
        return output;
    }

}