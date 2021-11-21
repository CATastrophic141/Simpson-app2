/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowControllerTest {
    //File generation tests
    @Test
    void testTXTFileGeneration() throws IOException {
        String testFilePath = "./docs/testing/TSVGenTestFile.txt";
        File testFile = new File(testFilePath);
        FileWriter fileWriterTest = new FileWriter(testFile);
        BufferedWriter buffWriterTest = new BufferedWriter(fileWriterTest);
        buffWriterTest.write("This is a test file");
        buffWriterTest.close();
        assertTrue(testFile.exists());
    }
    @Test
    void testHMTLFileGeneration() throws IOException {
        String testFilePath = "./docs/testing/HTMLGenTestFile.html";
        File testFile = new File(testFilePath);
        FileWriter fileWriterTest = new FileWriter(testFile);
        BufferedWriter buffWriterTest = new BufferedWriter(fileWriterTest);
        buffWriterTest.write("<body>This is a test file</body>");
        buffWriterTest.close();
        assertTrue(testFile.exists());
    }
    @Test
    void testJSONFileGeneration() throws IOException {
        String testFilePath = "./docs/testing/JSONGenTestFile.json";
        File testFile = new File(testFilePath);
        FileWriter fileWriterTest = new FileWriter(testFile);
        BufferedWriter buffWriterTest = new BufferedWriter(fileWriterTest);
        JSONObject jObjTest = new JSONObject();
        jObjTest.put("TestString", "This is a test");
        buffWriterTest.write(jObjTest.toString());
        buffWriterTest.close();
        assertTrue(testFile.exists());
    }

    //File reading tests
    @Test
    void testTSVFileReading() {
        String testFilePath = "./docs/testing/TestTSVFile.txt";
        File testFile = new File(testFilePath);
        try (Scanner fileIn = new Scanner(new FileInputStream(testFile))) {
            String testLine = fileIn.nextLine();
            String[] testData = testLine.split("\t");
            assertEquals("Item 1", testData[0]);
            assertEquals("a-reg-itm-001", testData[1]);
            assertEquals("100", testData[2]);
            assertEquals("-428160654", testData[3]);
        } catch (FileNotFoundException e) {
            fail("File does not exist");
        }
    }
    @Test
    void testHTMLFileReading() {
        String testFilePath = "./docs/testing/TestHTMLFile.html";
        File testFile = new File(testFilePath);
        try (Scanner fileIn = new Scanner(new FileInputStream(testFile))) {
            StringBuilder fileStringTest = new StringBuilder();
            while (fileIn.hasNextLine()) {
                fileStringTest.append(fileIn.nextLine());
            }
            String[] fileStringArr = fileStringTest.toString().split("</tr>", Integer.MAX_VALUE);
            ArrayList<String> fileDataListTest = new ArrayList<>(Arrays.asList(fileStringArr));
            fileDataListTest.remove(0);
            fileDataListTest.remove(fileDataListTest.size() - 1);

            String[] testData = fileDataListTest.get(0).split("</td>", 4);
            for (int i = 0; i<4; i++) {
            testData[i] = testData[i].replace("<tr>", "");
            testData[i] = testData[i].replace("</tr>", "");
            testData[i] = testData[i].replace("<td>", "");
            testData[i] = testData[i].replace("</td>", "");
            }
            testData[0] = testData[0].replace("<br>", "");

            assertEquals("Item 1", testData[0]);
            assertEquals("a-reg-itm-001", testData[1]);
            assertEquals("100", testData[2]);
            assertEquals("-428160654", testData[3]);
        } catch (FileNotFoundException e) {
            fail("File does not exist");
        }
    }
    @Test
    void testJSONFileReading() {
        String testFilePath = "./docs/testing/TestJSONFile.json";
        File testFile = new File(testFilePath);
        try (Scanner fileIn = new Scanner(new FileInputStream(testFile))) {
            String jsonStringTest = fileIn.nextLine();
            JSONObject jObjTest = new JSONObject(jsonStringTest);
            String nameTest = jObjTest.getString("Name");
            String codeTest = jObjTest.getString("Code");
            String valueTest = jObjTest.getString("Value");
            int idTest = jObjTest.getInt("ID");
            assertEquals("Item 1", nameTest);
            assertEquals("a-reg-itm-001", codeTest);
            assertEquals("100", valueTest);
            assertEquals(-428160654, idTest);
        } catch (FileNotFoundException e) {
            fail("File does not exist");
        }
    }

    //Data invalidation tests
    @Test
    void testInvalidateItemName() {
        int lengthTest = 15;
        assertFalse(invalidateItemName(lengthTest));
        lengthTest = 280;
        assertTrue(invalidateItemName(lengthTest));
    }

    @Test
    void testInvalidateItemCode() {
        String testCode = "A-123-Tst-cde";
        assertFalse(invalidateItemCode(testCode));
        testCode = "C-cheese-10.99";
        assertTrue(invalidateItemCode(testCode));
    }
    @Test
    void testInvalidateItemValue() {
        String testVal = "125.99";
        assertFalse(invalidateItemValue(testVal));
        testVal = "12.00.01";
        assertTrue(invalidateItemValue(testVal));
    }

    //Test index retrieval methods
    @Test
    void testIDIndexRetrieval() {
        Item testItem1 = new Item("I1", "a-111-111-111", "1", 101);
        Item testItem2 = new Item("I2", "b-222-222-222", "2", 202);
        Item testItem3 = new Item("I3", "c-333-333-333", "3", 303);
        allItems.add(testItem1);
        allItems.add(testItem2);
        allItems.add(testItem3);
        assertEquals(0, getIndexOfID(101));
        assertEquals(1, getIndexOfID(202));
        assertEquals(2, getIndexOfID(303));
        assertEquals(-1, getIndexOfID(404));
    }
    @Test
    void testCODEIndexRetrieval() {
        usedCodes.add("a-111-111-111");
        usedCodes.add("b-222-222-222");
        usedCodes.add("c-333-333-333");
        assertEquals(0, getIndexOfCode("a-111-111-111"));
        assertEquals(1, getIndexOfCode("b-222-222-222"));
        assertEquals(2, getIndexOfCode("c-333-333-333"));
        assertEquals(-1, getIndexOfCode("d-444-444-444"));
    }

    //Item getter tests
    @Test
    void testItemNameGetter() {
        Item testItem = new Item("Test item", "T-tst-itm-001", "101.69", 123123);
        assertEquals("Test item", testItem.getItemName());
    }
    @Test
    void testItemCodeGetter() {
        Item testItem = new Item("Test item", "T-tst-itm-001", "101.69", 123123);
        assertEquals("T-tst-itm-001", testItem.getItemCode());
    }
    @Test
    void testItemValueGetter() {
        Item testItem = new Item("Test item", "T-tst-itm-001", "101.69", 123123);
        assertEquals("101.69", testItem.getItemValue());
    }
    @Test
    void testItemIDGetter() {
        Item testItem = new Item("Test item", "T-tst-itm-001", "101.69", 123123);
        assertEquals(123123, testItem.getItemID());
    }

    List<Item> allItems = new ArrayList<>();
    ArrayList<String> usedCodes = new ArrayList<>();

    public boolean invalidateItemName(int nameLength) {
        //Return boolean result resulting from check if length is between 2 and 256
        return nameLength < 2 || nameLength > 256;
    }

    public boolean invalidateItemCode(String code) {
        //Define code regex
        String codeRegex = "^([a-zA-Z](-[a-zA-Z0-9]+){3})$";
        //Check if code does not match regex, return result
        return !code.matches(codeRegex);
    }

    public boolean invalidateItemValue(String value) {
        //Define value regex
        String valueRegex = "^([0-9]{1,3},([0-9]{3},)*[0-9]{3}|[0-9]+)(.[0-9][0-9])?$";
        //Check if value does not match regex, return result
        return !value.matches(valueRegex);
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
}