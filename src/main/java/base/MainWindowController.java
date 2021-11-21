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
import java.io.*;
import java.net.URL;
import java.util.*;
import org.json.JSONObject;

public class MainWindowController implements Initializable {

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
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text TSV File", ".txt"));
            File file = fileChooser.showSaveDialog(null);
            //Call TSV file generation method
            if (file != null) {
                filePath = file.getPath();
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
                generateJSONFile(filePath);
            }
        }
        //Broad exception catching
        catch (Exception e){
            System.out.printf("------Failed to open JSON file for saving------%n");
            e.printStackTrace();
        }
    }

    private void generateTSVFile(String filePath) {
        //Setup print path to generated file
        File fileOut = new File(filePath);
        Formatter saveStream;
        try {
            saveStream = new Formatter(new FileOutputStream(fileOut));
        } catch (FileNotFoundException e) {
            System.out.printf("------Failed to open TSV save  stream------%n");
            return;
        }
        //For every item within the list, print out the details in tab-separated format
        for (Item item : allItems) {
            saveStream.format("%s\t%s\t%s\t%d%n",
                    item.getItemName(), item.getItemCode(), item.getItemValue(), item.getItemID());
        }
        saveStream.close();
    }

    private void generateHTMLFile(String filePath) {
        //Setup print path to generated file
        File fileOut = new File(filePath);
        Formatter saveStream;
        try {
            saveStream = new Formatter(new FileOutputStream(fileOut));
        } catch (FileNotFoundException e) {
            System.out.printf("------Failed to open HTML save stream------%n");
            return;
        }
        String header = "<html>\n\t<body>\n<style>table{border-spacing: 15px;}</style>\n\t\t<pre><table>\n<th><u>Name</u></th><th><u>Code</u></th><th><u>Value</u></th><th><u>ID</u></th><br></tr>\n";
        //Generate heading
        saveStream.format(header);
        //For every item within the list, print out the details in a html-group format
            // >> Save group in tabular format
        for (Item item : allItems) {
           saveStream.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%d</td></tr><br>%n"
                   ,item.getItemName(),item.getItemCode(),item.getItemValue(),item.getItemID());
        }
        saveStream.format("\t\t\t</table>%n\t\t</pre>%n\t</body>%n</html>%n");
        saveStream.close();
    }

    private void generateJSONFile(String filePath) {
        //Setup print path to generated file
        try (FileWriter saveStream = new FileWriter(filePath)) {
            //For every item within the list, print a JSON object, details included
            for (Item item : allItems) {
                JSONObject jObj = new JSONObject();
                String name = item.getItemName();
                String code = item.getItemCode();
                String value = item.getItemValue();
                int id = item.getItemID();
                jObj.put("Name", name);
                jObj.put("Code", code);
                jObj.put("Value", value);
                jObj.put("ID", id);
                saveStream.write(jObj.toString());
                saveStream.write("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.printf("------Failed to open JSON save stream------%n");
        } catch (Exception e) {
            System.out.printf("------JSON save stream failure------%n");
        }
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
        allItems.clear();
        viewedItems.clear();
        usedCodes.clear();
        //Access file for parsing
        try (Scanner fileIn = new Scanner(new FileInputStream(filePath))) {
            String readLine;
            String[] data;
            int id;
            //While the file has lines
            while (fileIn.hasNextLine()) {
                //Read the line
                readLine = fileIn.nextLine();
                //Split data by \t
                data = readLine.split("\t");
                //Create item with stored data
                id = Integer.parseInt(data[3]);
                Item newItem = new Item(data[0], data[1], data[2], id);
                //Add item to lists
                allItems.add(newItem);
                viewedItems.add(newItem);
                usedCodes.add(data[1]);
            }
        } catch (FileNotFoundException e) {
            //Print error message
            System.out.printf("%nError parsing TSV file%n");
        }
        //Refresh tableview
        itemTable.refresh();
    }

    private void parseHTMLFile(String filePath) {
        //Clear stored lists
        allItems.clear();
        viewedItems.clear();
        usedCodes.clear();
        //Access file for parsing
        try (Scanner fileIn = new Scanner(new FileInputStream(filePath))) {
            StringBuilder fileString = new StringBuilder();
            //While the file has a next input
            while (fileIn.hasNextLine()) {
                //Add line to file
                fileString.append(fileIn.nextLine());
            }
            //Take the file as a string
            String[] fileStringArr = fileString.toString().split("</tr>", Integer.MAX_VALUE);
            ArrayList<String> fileDataList = new ArrayList<>(Arrays.asList(fileStringArr));
            //Remove the beginning and ending tags
            fileDataList.remove(0);
            fileDataList.remove(fileDataList.size()-1);
            //For the remaining array strings
            for (String strings : fileDataList) {
                //Separate the line as each of the four data pieces
                String[] data = strings.split("</td>", 4);
                for (int i = 0; i<4; i++) {
                    //Remove table tags
                    data[i] = data[i].replace("<tr>", "");
                    data[i] = data[i].replace("</tr>", "");
                    data[i] = data[i].replace("<td>", "");
                    data[i] = data[i].replace("</td>", "");
                }
                //Remove html new line tag
                data[0] = data[0].replace("<br>", "");

                //Create item with stored data
                Item newItem = new Item(data[0], data[1], data[2], Integer.parseInt(data[3]));
                //Add item to lists
                allItems.add(newItem);
                viewedItems.add(newItem);
                usedCodes.add(data[1]);
            }
        } catch (FileNotFoundException e) {
            //Print error message
            System.out.printf("%nError reading HTML file%n");
        } catch (Exception e) {
            //Print error message
            System.out.printf("%nError parsing HTML file%n");
            e.printStackTrace();
        }
        //Refresh tableview
        itemTable.refresh();
    }

    private void parseJSONFile(String filePath) {
        //Clear stored lists
        allItems.clear();
        viewedItems.clear();
        usedCodes.clear();
        //Access file for parsing
        try (Scanner fileIn = new Scanner(new FileInputStream(filePath))) {
            //While a next JSON object exists
            while (fileIn.hasNextLine()) {
                //Get each detail from JSON object
                JSONObject jObj = new JSONObject(fileIn.nextLine());
                String name = jObj.getString("Name");
                String code = jObj.getString("Code");
                String val = jObj.getString("Value");
                int id = jObj.getInt("ID");
                //Create item with stored data
                Item newItem = new Item(name, code, val, id);
                //Add item to lists
                allItems.add(newItem);
                viewedItems.add(newItem);
                usedCodes.add(code);
            }
        } catch (FileNotFoundException e) {
            //Print error message
            System.out.printf("%nError parsing JSON file%n");
        }
        //Refresh tableview
        itemTable.refresh();
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

    private boolean invalidateItemValue(String value) {
        //Define value regex
        String valueRegex = "^([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(.[0-9][0-9])?$";
        //Check if value does not match regex, return result
        return !value.matches(valueRegex);
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
    private int getIndexOfCode(String code) {
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
        String value = selectedItem.getItemValue();
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
        String value = "";
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
            codeErrorMsg.setText("Please enter a unique item code. Consider using \"Edit\"");
            errorFlag = 1;
        } else {
            code = itemCodeField.getText();
        }
        //Save value if it is valid
        if (invalidateItemValue(itemValueField.getText())) {
            //If not, change corresponding label to error message
            valueErrorMsg.setText("Please enter a valid monetary value for the item (Greater than or equal to 0. Do not include \"$\")");
            errorFlag = 1;
        } else {
            value = itemValueField.getText();
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
        String name = "";
        String code = "";
        String value = "";
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
        //Check if code is not unique and is not the same as the last code
        else if (usedCodes.contains(itemCodeField.getText()) && !usedCodes.get(codeIndex).matches(itemCodeField.getText())) {
            //If code exists, change corresponding label to error message
            codeErrorMsg.setText("Please enter a unique item code");
            errorFlag = 1;
        } else {
            code = itemCodeField.getText();
        }
        //Save value if it is valid
        if (invalidateItemValue(itemValueField.getText())) {
            //If not, change corresponding label to error message
            valueErrorMsg.setText("Please enter a valid monetary value for the item (Greater than or equal to $0)");
            errorFlag = 1;
        } else {
            value = itemValueField.getText();
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
    private void searchForItem() {
        //Cancel search if search field is empty
        if (itemSearchField.getText().isBlank()) {
            searchErrorMsg.setText("Please enter a name or code");
            return;
        }

        //Clear observable list
        viewedItems.clear();
        //Get search string
        String searchText = itemSearchField.getText();
        //For each item in the total list, check if a name or code matches the string and does not already exist
        //If so, add item to the observable list
        for (Item item : allItems) {
            if (item.getItemName().toLowerCase(Locale.ROOT).contains(searchText.toLowerCase(Locale.ROOT)) && !viewedItems.contains(item)) {
                viewedItems.add(item);
            }
            if (item.getItemCode().toLowerCase(Locale.ROOT).contains(searchText.toLowerCase(Locale.ROOT)) && !viewedItems.contains(item)) {
                viewedItems.add(item);
            }
        }
        //Refresh table and reset error msg and search field
        itemTable.refresh();
        searchErrorMsg.setText(" ");
        itemSearchField.setText("");
    }

    @FXML
    private void resetSearch() {
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