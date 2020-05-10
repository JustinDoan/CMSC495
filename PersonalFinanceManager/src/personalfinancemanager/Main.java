/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 *
 * @author marcusmaibach
 */


public class Main extends Application {
    
    public static void main(String[] args) {
        DAO.shared.connect();
        DAO.shared.closeDB();
        Application.launch(Main.class, (java.lang.String[])null);
        
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(loader.load(), 480, 320);
            
            primaryStage.setTitle("Personal Finance Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public static void showAlert(DialogTypes type){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        switch(type){
            case EMPTYFIELD:
                alert.setTitle("Empty Login Fields");
                alert.setContentText("Please enter user name and password.");
                break;
            case USERNAME:
                alert.setTitle("Empty Username Field");
                alert.setContentText("Please provide a valid user name.");
                break;
            case INVALIDCHARACTERS:
                alert.setTitle("Invalid Characters");
                alert.setContentText("The only acceptable characters are letters, numbers and symbols.");
                break;
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Authentication Successful");
                alert.setContentText("Welcome to Personal Finance Manager!");
                break;
            case FAILURE:
                alert.setTitle("Authentication Failed");
                alert.setContentText("Invalid username or password. Please try again.");
                break;
            case DBERROR:
                alert.setTitle("Database Connection Error");
                alert.setContentText("There is a problem with the database. Please contact your administrator.");
                break;
        }
        alert.showAndWait();
    }
}
//("The only acceptable characters are letters, numbers and symbols.")("Please enter user name and password.");
enum DialogTypes{
        EMPTYFIELD,
        USERNAME,
        INVALIDCHARACTERS,
        SUCCESS,
        FAILURE,
        DBERROR
    };
