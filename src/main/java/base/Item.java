package base;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Random;

class Item {
    Random rand = new Random();

    //Attributes
    private final int itemID;
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemCode;
    private final SimpleFloatProperty itemValue;

    //Constructors methods
    public Item(String name, String code, float value) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemCode = new SimpleStringProperty(code);
        itemValue = new SimpleFloatProperty(value);
        //Generate a random ID
        itemID = rand.nextInt();
    }

    public Item(String name, String code, float value, int id) {
        //Set instance details to passed arguments
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemCode = new SimpleStringProperty(code);
        itemValue = new SimpleFloatProperty(value);
        //Set a random ID
        itemID = id;
    }

    //Getter methods

    public String getItemName(){
        return itemName.get();
    }

    public String getItemCode(){
        return itemCode.get();
    }

    public float getItemValue(){
        return itemValue.get();
    }

    public int getItemID() {
        return itemID;
    }
}