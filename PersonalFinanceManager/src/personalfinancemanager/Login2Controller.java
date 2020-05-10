/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author zach
 */
public class Login2Controller implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    
    Stage enclosingStage;
    Stage parentStage;
    Session currentSession = new Session();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void submit(ActionEvent event) {
        String unInput, pwInput, username, password;
        
        if ((userNameField.getText() != null && !userNameField.getText().isEmpty()) && 
            (passwordField.getText() != null && !passwordField.getText().isEmpty())) {
                unInput = userNameField.getText();
                pwInput = passwordField.getText();
                username = unInput.trim();
                password = pwInput.trim();
        
                if (!username.matches("[\\w*\\s*[!@#$%^&\\*()]*]*")) {
                	Main.showAlert(DialogTypes.INVALIDCHARACTERS,null);
                } else {
                	// Set session
                	DAO.shared.login(username,password);
                	try {
        	            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        	            Scene scene = new Scene(loader.load(), 480, 320);
        	            Stage mainMenuStage = new Stage();
        	            mainMenuStage.setTitle("Personal Finance Manager");
        	            mainMenuStage.setScene(scene);
        	            mainMenuStage.show();
        	            // After we successfully show the mainMenu stage after good login, hide current one
        	            final Node source = (Node) event.getSource();
        	            final Stage loginStage = (Stage) source.getScene().getWindow();
        	            loginStage.close();
        	        } catch (IOException ex) {
        	            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        	        }
                }
        } else Main.showAlert(DialogTypes.INVALIDFIELD,null);
    }
    
    @FXML
    private void createUser(ActionEvent event) {
    	        try {
    	            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
    	            Scene scene = new Scene(loader.load(), 720, 320);
    	            CreateUser createUserController = loader.getController();
    	            Stage createUserStage = new Stage();
    	            createUserStage.setTitle("Create New User");
    	            createUserStage.setScene(scene);
    	            createUserStage.setAlwaysOnTop(true);
    	            createUserController.enclosingStage = createUserStage;

    	            createUserStage.show();
    	        } catch (IOException ex) {
    	            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
    	        }
    	        
    	    }
    
    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
        System.out.println("Login exit");
        Platform.exit();
    }
    
    
}
    

