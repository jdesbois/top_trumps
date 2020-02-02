package commandline;
/*
This class defines the card, its attribues and values
Arrays are used for Attributes and Values 
Description is kept in string format
*/
public class Card {
    String description;
    String[] attributes = new String[5];
    int[] values = new int[5];

    public Card(String desc, String[] attributes, int[] values) {
        this.description = desc;
        this.attributes = attributes;
        this.values = values;
    }
    //getters/setters
    public String getDesc() {
        return description;
    }
    public String getPair(int i) {
        return attributes[i] + " : " + values[i];
    }
    public String[] getAttri() {
        return attributes;
    }
    public int[] getValues() {
        return values;
    }
    public int getValue(int i) {
        return values[i];
    }
    public String printWin(int index) {
        String output = "";
        output += "\tDescription: " + description +"\n";
        for (int i =0; i < attributes.length; i++) {
            if (index == i) {
                output += "\t" + (i+1) + ". " + attributes[i] + ": " + values[i] + " <------- \n";
                continue;
            }
            output += "\t" + (i+1) + ". " + attributes[i] + ": " + values[i] + "\n";
        }
        return output;
    }
    //This is the toString method, puts description then each header/value in a new line and returns the complete string
    public String toString() {
        String output = "";
        output += "\tDescription: " + description +"\n";
        for (int i =0; i < attributes.length; i++) {
            output += "\t" + (i+1) + ". " + attributes[i] + ": " + values[i] + "\n";
        }
        return output;
    }
}
