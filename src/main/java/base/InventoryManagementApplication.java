/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class InventoryManagementApplication extends javafx.application.Application {
    //Application start method
    @Override
    public void start(Stage stage){
        try {
            //Setup application scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("StyleSheet.css")).toExternalForm());
            stage.setTitle("Inventory Management Application");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("media/DuffleIcon.png"))));
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.print("Could not start application");
        }
    }

    //Launch program
    public static void main(String[] args) {
        launch(args);
    }

    /*NOTES*/
    //Duffle is a phony company, any likeness or use of name from existing company or organization is a coincidence
    //(In regard to the icon image) Protected by fair use laws. Intended use is for educational purposes.
    //          Original image has been altered using image editing software
}
