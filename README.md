# Cata-log Inventory Manager User Guide

Welcome the Cata-log Inventory Manager program.
This software is a program purpose-built to manage a catalog of items.
This document will demonstrate the basic features of the program.

## Adding items

To add an item, first fill out the information boxes in the lower end of the program screen.
Once the information is filled in, click the "**Add**" button to add the item to the list.

## Editing Items

To edit an item, click on the row of the item to edit.
Once the row is chosen, click the “**Select**” button.
This will bring up the item information in the item information entry fields.
From here, you can edit the information in the fields.
Once the necessary edits have been made, you can either click “**Edit**” to edit the original item, or you can create a duplicate item with the edits by clicking the “**Add**” button.

## Item restrictions

Item names must be between 2 to 256 characters long, inclusive
Item codes must be in the format A-XXX-XXX-XXX, where A is a letter and X is a letter or number character
Item values must be nonegative and must be within two decimals. Do not include the "$" character

## Removing Items and Clearing the List

To remove an item, first click on the row of the item to remove.
Once the row is chosen, click the “**Remove**” button the initiate item removal. 
To clear the list, click on the “**Clear All**” button. 
*Notice: All unsaved items will be lost*

## Searching for items by name or serial code

To search for an item within the list, first enter either a name or a code.
Once the search field has been completed, click the "**Search**" button.
Any item containing the search field entry will be displayed on the table.
To reset the search and return all items to the table, press the "**Reset**" button

## Sorting information on the table

To sort the items based on a specific value, click on the header of the column you want to sort by.
Sorting will be implemented by descending order, ascending order, or by no sorting order.

## Saving a list to a file

To save a list to a file, click on the “**File**” menu button, and then click on the “**Save**” option.
A sub-menu dropdown will appear. Select the format to save by. (TSV, HTML, or JSON).
A file saving window will appear. Navigate to the where the file is intended to be saved, specify a file name, and then click “**Save**.”

## Uploading a list to the program

To upload a list from a CheckBoard file, click on the “**File**” menu button, and then click on the “**Upload**” option.
A sub-menu dropdown will appear. Select the format of file to upload by. (TSV, HTML, or JSON).
A file selection window will appear. Navigate to the where the file is saved, select the item, and then click “**Open**.” File Selection can also be performed by double-clicking the file.