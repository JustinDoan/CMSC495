/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
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
        
                if (!username.matches("[\\w*\\s*[!@#$%^&\\*()]*]*")) Main.showAlert(DialogTypes.INVALIDCHARACTERS);
                else DAO.shared.login(username,password);
        } else Main.showAlert(DialogTypes.EMPTYFIELD);
    }
    
    @FXML
    private void createUser(ActionEvent event) {
    }    
    
    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
        System.out.println("Login exit");
        Platform.exit();
    }
    
    
}
    
    

    /* 
    public void hideMenu(Stage ps) {
        this.parentStage = ps;
        this.enclosingStage.initModality(Modality.WINDOW_MODAL);
        this.enclosingStage.initOwner(ps);
    } //*/
    

