/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController {

    //Storage lists
    List<Item> allItems = new ArrayList<>();
    ObservableList<Item> viewedItems = FXCollections.observableArrayList();

    @FXML
    private Button addButton;

    @FXML
    private Button clearAllButton;

    @FXML
    private Button editButton;

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemSearchField;

    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> codeColumn;
    @FXML
    private TableColumn<Item, Double> valueColumn;

    @FXML
    private TextField itemValueField;

    @FXML
    private Button removeButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button selectButton;

    @FXML
    private MenuItem uploadButton;

    @FXML
    private MenuItem saveTSVButton;

    @FXML
    private MenuItem saveHTMLButton;

    @FXML
    private MenuItem saveJSONButton;

    @FXML
    private void saveFileAsTSV(ActionEvent event) {
        //Open file chooser, save variant
            //Get file path from chooser
        //Call TSV file generation method
    }

    @FXML
    private void saveFileAsHTML(ActionEvent event) {
        //Open file chooser, save variant
            //Get file path from chooser
        //Call HTML file generation method
    }

    @FXML
    private void saveFileAsJSON(ActionEvent event) {
        //Open file chooser, save variant
            //Get file path from chooser
        //Call JSON file generation method
    }

    private void generateTSVFile(String filePath) {
        //Setup print path to generated file
        //Specify number of items in the list
        //For every item within the list, print out the details in tab-separated format
        //Set print back to console
    }

    private void generateHTMLFile(String filePath) {
        //Setup print path to generated file
        //Specify number of items in the list
        //For every item within the list, print out the details in a html-group format
            // >> Save group in tabular format
        //Set print back to console
    }

    private void generateJSONFile(String filePath) {
        //Setup print path to generated file
        //For every item within the list, print a JSON object, details included
        //Set print back to console
    }

    @FXML
    private void uploadFileAsTSV(ActionEvent event) {
        //Open file chooser, upload variant
            //Get file path from chooser
        //Call TSV file parsing method
    }

    @FXML
    private void uploadFileAsHTML(ActionEvent event) {
        //Open file chooser, upload variant
            //HTML file parsing method
    }

    @FXML
    private void uploadFileAsJSON(ActionEvent event) {
        //Open file chooser, upload variant
            //Get file path from chooser
        //Call JSON file parsing method
    }

    private void parseTSVFile(String filePath) {
        //Clear stored lists
        //Access file for parsing
            //Get the number of items to parse
            //Initialize storage variables
            //For the number of items
                //Save each next string/double to variables
                //Create item with stored data
                //Add item to lists
        //Refresh tableview
    }

    /** RESEARCH HTML PARSING **/
    private void parseHTMLFile(String filePath) {
        //Clear stored lists
        //Access file for parsing
            //Get the number of items to parse
            //Initialize storage variables
            //For the number of items
                //Save each next string/double to variables
                //Create item with stored data
                //Add item to lists
        //Refresh tableview
    }

    private void parseJSONFile(String filePath) {
        //Clear stored lists
        //Access file for parsing
            //While a next JSON object exists
                //Initialize storage variables
                //Get each detail from JSON object
                //Create item with stored data
                //Add item to lists
        //Refresh tableview
    }

    /**@Override**/
    public void initialize(URL url, ResourceBundle rb) {
        //Set up columns for use
            /** Consider using setOnCommits with conditions **/
    }


    private boolean validateItemName(int nameLength) {
        //Return boolean result resulting from check if length is between 2 and 256
    }

    private boolean validateItemCode(int code) {
        //Define code regex
        //Check if code matches regex, return result
    }

    private boolean validateItemValue(double value) {
        //Return boolean result resulting from check is value is greater than or equal to 0
    }

    private void resetInformationEntryFields() {
        //Reset all item information entry fields to their default values
    }

    private int getIndexOfID(int id) {
        //Loop through items and retrieve index of matching ID

        //Return invalid counter if item is not found
    }

    @FXML
    private void selectItemFromList(ActionEvent event) {
        //Retrieve the index of the selected row

        //If no row is selected, print error message and end method

        //Get item from selected row
        //Get information from selected row
        //Set information boxes to display relevant info
    }

    @FXML
    private void addItemToList(ActionEvent event) {
        //Initialize storage variables
        //Save name if it is valid
            //If not, change corresponding label to error message
        //Save code if it is valid
            //If not, change corresponding label to error message
        //Save value if it is valid
            //If not, change corresponding label to error message

        //If any value storage attempts fail, end function

        //Create Item
        //Add item to lists
        //Reset info fields
    }

    @FXML
    private void editItemFromList(ActionEvent event) {
        //Get index for selected row

        //If no row is selected, print error message and end method

        //Retrieve Item
        //Retrieve index of Item
        //Initialize storage variables
        //Save name if it is valid
        //If not, change corresponding label to error message
        //Save code if it is valid
        //If not, change corresponding label to error message
        //Save value if it is valid
        //If not, change corresponding label to error message

        //If any value storage attempts fail, end function

        //Create new Item version
        //Remove old duplicate item from lists
        //Add new item to lists
        //Reset info fields
    }

    @FXML
    private void deleteItemFromList(ActionEvent event) {
        //Get index for selected row

        //If no row is selected, print error message and end method

        //Retrieve selected item
        //Retrieve index of item from total list
        //Remove item from both lists
    }

    @FXML
    private void clearList(ActionEvent event) {
        //Clear all items from both lists
    }
}
