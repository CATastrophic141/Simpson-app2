/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.beans.property.SimpleStringProperty;
import java.util.Random;

public class Item {

    //Attributes
    private final int itemID;
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemCode;
    private final SimpleStringProperty itemValue;

    //Constructors methods
    public Item(String name, String code, String value) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemCode = new SimpleStringProperty(code);
        itemValue = new SimpleStringProperty(value);
        //Generate a random ID
        Random rand = new Random();
        itemID = rand.nextInt();
    }

    public Item(String name, String code, String value, int id) {
        //Set instance details to passed arguments
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemCode = new SimpleStringProperty(code);
        itemValue = new SimpleStringProperty(value);
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

    public String getItemValue(){
        return itemValue.get();
    }

    public int getItemID() {
        return itemID;
    }
}