/*
This class defines the card, its attribues and values
Arrays are used for Attributes and Values 
Description is kept in string format
*/
/**
 * Card class for Top Trumps game
 * @author John Desbois
 * <br><br>
 * 
 * Constructor:<br>
 * 	Card(String, String[], int[])<br><br>
 * 
 * Public methods:<br>
 * 	public String getDesc()<br>
 * 	public String getPair()<br>
 *  public String[] getAttri()<br>
 *  public int[] getValues()<br>
 *  public int getValue(int)<br>
 *  public String printWin(int)<br>
 *  public String toString()
 * 
 */


public class Card {
    String description;
    String[] attributes = new String[5];
    int[] values = new int[5];

 /**
  * Constructor <br>
  * @param String Name of card
  * @param String[] Array of Attribute names as Strings
  * @param int[] Array of attribute values as ints
  */

    public Card(String desc, String[] attributes, int[] values) {
        this.description = desc;
        this.attributes = attributes;
        this.values = values;
    }
    //getters/setters
    /**
     * Getter Method to return description as string
     * @return String Desc
     */
    public String getDesc() {
        return description;
    }
    /**
     * Getter method to return an Attribute (String) and Value (int) pairs a String
     * @param int index of Attribute/Value to return
     * @return String of Attribute and Value concatenated with spaces and :
     */
    public String getPair(int i) {
        return attributes[i] + " : " + values[i];
    }
    /**
     * Getter method to return the String Array of attributes for this card
     * @return String[] of Attribute names
     */
    public String[] getAttri() {
        return attributes;
    }
    /**
     * Getter method to return int Array of values for this card
     * @return int[] of Values
     */
    public int[] getValues() {
        return values;
    }
    /**
     * Getter method to return value at specified array index
     * @param int index of Value to return
     * @return int value at specified index
     */
    public int getValue(int i) {
        return values[i];
    }
    /**
     * Secondary card print method to indicate winning value of winning card
     * @param int index of winning value for this card
     * @return String of card printed with arrow (<-----) pointing to selected value
     */
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
    //This is the toString method, puts description then each header/value in a new line and returns the complete string. Also adds number for user to select correct attribute.
    /**
     * toString method to return a printed version of card object. Indicating Attribute/Value pairs within a numbered list
     * @return String with card description and attribute/value pairs printed in numbered list
     */
    public String toString() {
        String output = "";
        output += "\tDescription: " + description +"\n";
        for (int i =0; i < attributes.length; i++) {
            output += "\t" + (i+1) + ". " + attributes[i] + ": " + values[i] + "\n";
        }
        return output;
    }
}
