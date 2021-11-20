/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private static final String VALUE_ERROR_MSG = "Please enter a valid value for the item (0 or greater)";

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemSearchField;

    @FXML
    private TextField itemValueField;

    @FXML
    private Label searchErrorMsg;

    @FXML
    private Label nameErrorMsg;

    @FXML
    private Label codeErrorMsg;

    @FXML
    private Label valueErrorMsg;

    @FXML
    private Label selectErrorMsg;

    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> codeColumn;
    @FXML
    private TableColumn<Item, Float> valueColumn;

    //Storage lists
    List<Item> allItems = new ArrayList<>();
    ObservableList<Item> viewedItems = FXCollections.observableArrayList();
    ArrayList<String> usedCodes = new ArrayList<>();

    /** Consider using setOnCommits with conditions **/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up columns for use
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));

        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        itemTable.setItems(getViewedItemList());
    }

    //Retrieve the observable list to be used by the table
    private ObservableList<Item> getViewedItemList() {
        return viewedItems;
    }

    @FXML
    private void saveFileAsTSV() {
        String filePath;
        //Open file chooser, save variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Tab-Separated File", ".txt"));
            File file = fileChooser.showSaveDialog(null);
            //Call TSV file generation method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                generateTSVFile(filePath);
            }
        }
        catch (Exception e){
            System.out.printf("Failed to save TSV file%n");
        }
    }

    @FXML
    private void saveFileAsHTML() {
        String filePath;
        //Open file chooser, save variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML File", ".html"));
            File file = fileChooser.showSaveDialog(null);
            //Call HTML file generation method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                generateHTMLFile(filePath);
            }
        }
        catch (Exception e){
            System.out.printf("Failed to save HTML file%n");
        }
    }

    @FXML
    private void saveFileAsJSON() {
        String filePath;
        //Open file chooser, save variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON File", ".json"));
            File file = fileChooser.showSaveDialog(null);
            //Call JSON file generation method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                generateJSONFile(filePath);
            }
        }
        catch (Exception e){
            System.out.printf("Failed to save JSON file%n");
        }
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
    private void uploadFileAsTSV() {
        String filePath;
        //Open file chooser, upload variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            //Call TSV file parsing method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                parseTSVFile(filePath);
            }
        }
        catch (Exception e) {
            //Print error message
            System.out.printf("Failed to upload as TSV file%n");
        }
    }

    @FXML
    private void uploadFileAsHTML() {
        String filePath;
        //Open file chooser, upload variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            //Call HTML file parsing method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                parseHTMLFile(filePath);
            }
        }
        catch (Exception e) {
            //Print error message
            System.out.printf("Failed to upload as HTML file%n");
        }
    }

    @FXML
    private void uploadFileAsJSON() {
        String filePath;
        //Open file chooser, upload variant
        try {
            //Get file path from chooser
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            //Call JSON file parsing method
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                parseJSONFile(filePath);
            }
        }
        catch (Exception e) {
            //Print error message
            System.out.printf("Failed to upload as JSON file%n");
        }
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

    private boolean invalidateItemName(int nameLength) {
        //Return boolean result resulting from check if length is between 2 and 256
        return nameLength < 2 || nameLength > 256;
    }

    private boolean invalidateItemCode(String code) {
        //Define code regex
        String codeRegex = "^([a-zA-Z](-[a-zA-Z0-9]+){3})$";
        //Check if code does not match regex, return result
        return !code.matches(codeRegex);
    }

    private boolean invalidateItemValue(float value) {
        //Return boolean result resulting from check is value is greater than or equal to 0
            //Returns reverse boolean, checks if value is negative
        return value < 0;
    }

    private void resetInformationFields() {
        //Reset all item information entry fields to their default values
        itemNameField.clear();
        itemCodeField.clear();
        itemValueField.clear();
        selectErrorMsg.setText(" ");
        searchErrorMsg.setText(" ");
        nameErrorMsg.setText(" ");
        codeErrorMsg.setText(" ");
        valueErrorMsg.setText(" ");
    }

    private int getIndexOfID(int id) {
        //Loop through items and retrieve index of matching ID
        for(int i = 0; i < allItems.size(); i++) {
            if(allItems.get(i).getItemID() == id){
                return i;
            }
        }
        //Return invalid counter if item is not found
        return -1;
    }
    private int getIndexOfCode(String code){
        //Loop through items and retrieve the index of the matching code
        for(int i = 0; i < usedCodes.size(); i++) {
            if(usedCodes.get(i).matches(code)){
                return i;
            }
        }
        //Return invalid counter if item is not found
        return -1;
    }

    @FXML
    private void selectItemFromList() {
        //Retrieve the index of the selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //If no row is selected, print error message and end method
        if (selectionIndex == -1) {
            selectErrorMsg.setText("Please select an item");
            return;
        }

        //Get item from selected row
        Item selectedItem = itemTable.getItems().get(selectionIndex);
        //Get information from selected row
        String name = selectedItem.getItemName();
        String code = selectedItem.getItemCode();
        float value = selectedItem.getItemValue();
        String valueString = String.valueOf(value);
        //Set information boxes to display relevant info
        itemNameField.setText(name);
        itemCodeField.setText(code);
        itemValueField.setText(valueString);
    }

    @FXML
    private void addItemToList() {
        //Initialize storage variables
        String name = "";
        String code = "";
        float value = -1;
        int errorFlag = 0;

        //Save name if it is valid
        if (invalidateItemName(itemNameField.getText().length())) {
            //If not, change corresponding label to error message
            nameErrorMsg.setText("Please enter a valid name for the item (2-256 characters)");
            errorFlag = 1;
        } else {
            name = itemNameField.getText();
        }
        //Save code if it is valid
        if (invalidateItemCode(itemCodeField.getText())) {
            //If not, change corresponding label to error message
            codeErrorMsg.setText("Please enter a valid code for the item (A-XXX-XXX-XXX)");
            errorFlag = 1;
        }
        else if (usedCodes.contains(itemCodeField.getText())) {
            //If code exists, change corresponding label to error message
            codeErrorMsg.setText("Please enter a unique item code");
            errorFlag = 1;
        } else {
            code = itemCodeField.getText();
        }
        //Save value if it is valid
        try {
            if (itemValueField.getText().length() == 0 || invalidateItemValue(Float.parseFloat(itemValueField.getText()))) {
                //If not, change corresponding label to error message
                valueErrorMsg.setText(VALUE_ERROR_MSG);
                errorFlag = 1;
            } else {
                value = Float.parseFloat(itemValueField.getText());
            }
        }
        catch (Exception e){
            valueErrorMsg.setText(VALUE_ERROR_MSG);
        }

        //If any value storage attempts fail, end function
        if (errorFlag != 0){
            return;
        }

        //Create Item
        Item newItem = new Item(name, code, value);
        //Add item to lists
        allItems.add(newItem);
        viewedItems.add(newItem);
        usedCodes.add(code);
        //Reset info fields
        resetInformationFields();
        itemTable.refresh();
    }

    @FXML
    private void editItemFromList() {
        //Get index for selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //If no row is selected, print error message and end method
        if (selectionIndex == -1) {
            selectErrorMsg.setText("Please select and item");
            return;
        }

        //Retrieve Item
        Item oldItem = itemTable.getItems().get(selectionIndex);
        //Retrieve index of Item and existing code
        int itemIDIndex = getIndexOfID(oldItem.getItemID());
        int codeIndex = getIndexOfCode(oldItem.getItemCode());
        //Initialize storage variables
        String name = "N/A";
        String code = "N/A";
        float value = 0;
        int id;
        int errorFlag = 0;
        //Save name if it is valid
        if (invalidateItemName(itemNameField.getText().length())) {
            //If not, change corresponding label to error message
            nameErrorMsg.setText("Please enter a valid name for the item (2-256 characters)");
            errorFlag = 1;
        } else {
            name = itemNameField.getText();
        }
        //Save code if it is valid
        if (invalidateItemCode(itemCodeField.getText())) {
            //If not, change corresponding label to error message
            codeErrorMsg.setText("Please enter a valid and unique code for the item (A-XXX-XXX-XXX)");
            errorFlag = 1;
        }
        else if (usedCodes.contains(itemCodeField.getText())) {
            //If code exists, change corresponding label to error message
            codeErrorMsg.setText("Please enter a unique item code");
            errorFlag = 1;
        } else {
            code = itemCodeField.getText();
        }
        //Save value if it is valid
        try {
            if (invalidateItemValue(Float.parseFloat(itemValueField.getText()))) {
                //If not, change corresponding label to error message
                valueErrorMsg.setText(VALUE_ERROR_MSG);
                errorFlag = 1;
            } else {
                value = Float.parseFloat(itemValueField.getText());
            }
        }
        catch (Exception e) {
            valueErrorMsg.setText(VALUE_ERROR_MSG);
        }

        //If any value storage attempts fail, end function
        if (errorFlag != 0){
            return;
        }

        id = oldItem.getItemID();

        //Create new Item version
        Item editedItem = new Item(name, code, value, id);
        //Remove old duplicate item from lists
        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
        usedCodes.remove(codeIndex);
        //Add new item to lists
        viewedItems.add(editedItem);
        allItems.add(editedItem);
        usedCodes.add(code);
        //Reset info fields
        resetInformationFields();
        itemTable.refresh();
    }

    @FXML
    private void deleteItemFromList() {
        //Get index for selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //If no row is selected, print error message and end method
        if (selectionIndex == -1) {
            selectErrorMsg.setText("Please select an item to remove");
            return;
        }

        //Retrieve selected item
        Item rmItem = itemTable.getItems().get(selectionIndex);
        //Retrieve index of item from total list and used code list
        int itemIDIndex = getIndexOfID(rmItem.getItemID());
        int usedCodeIndex = getIndexOfCode((rmItem.getItemCode()));
        //Remove item from both lists
        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
        usedCodes.remove(usedCodeIndex);
        selectErrorMsg.setText(" ");
        itemTable.refresh();
    }

    @FXML
    private void clearList() {
        //Clear all items from both lists
        itemTable.getItems().clear();
        allItems.clear();
        viewedItems.clear();
        itemTable.refresh();
        usedCodes.clear();
    }

    @FXML
    void searchForItem() {
        //Cancel search if search field is empty
        if (itemSearchField.getText().isBlank()) {
            searchErrorMsg.setText("Please enter a name or code");
            return;
        }

        //Clear observable list
        viewedItems.clear();
        //Get search string
        String searchText = itemSearchField.getText();
        //For each item in the total list, check if a name or code matches the string
        //If so, add item to the observable list
        for (Item item : allItems) {
            if (item.getItemName().contains(searchText)) {
                viewedItems.add(item);
            }
            if (item.getItemCode().contains(searchText)) {
                viewedItems.add(item);
            }
        }
        //Refresh table and reset error msg and search field
        itemTable.refresh();
        searchErrorMsg.setText(" ");
        itemSearchField.setText("");
    }

    @FXML
    void resetSearch() {
        //Clear the list
        viewedItems.clear();
        //Add all items to the observable list
        viewedItems.addAll(allItems);
        //Refresh the table
        itemTable.refresh();
        searchErrorMsg.setText(" ");
        itemSearchField.setText("");
    }
}
