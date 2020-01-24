package commandline;

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


}