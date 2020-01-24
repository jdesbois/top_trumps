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
    public String[] getAttri() {
        return attributes;
    }
    public int[] getValues() {
        return values;
    }

    public String toString() {
        String output = "";
        output += "Description : " + description +"\n";
        for (int i =0; i < attributes.length; i++) {
            output += attributes[i] + " : " + values[i] + "\n";
        }
        return output;
    }
}