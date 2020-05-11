/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author marcusmaibach
 */
public class CreateUser implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    

    Stage enclosingStage;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void cancel(ActionEvent event) {
        enclosingStage.close();
        // need to reopen the login window
    }
    
    @FXML
    private void createNewUser(ActionEvent event) {
    	
    	if ((userNameField.getText() != null && !userNameField.getText().isEmpty()) && 
                (passwordField.getText() != null && !passwordField.getText().isEmpty())) {
                    String unInput = userNameField.getText();
                    String pwInput = passwordField.getText();
                    String username = unInput.trim();
                    String password = pwInput.trim();
                    long date = System.currentTimeMillis();
                    String stringDate = Long.toString(date);
                    DAO.shared.insertUser(stringDate, username, password);
                    enclosingStage.close();
    	}
    	
    	
    	
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
        // need to reopen login window
    }
    
}
