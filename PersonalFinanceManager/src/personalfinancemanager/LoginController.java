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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omt
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField pwField;
    @FXML
    private Button newUserButton;
    @FXML
    private Button loginButton;
    
    Stage enclosingStage;
    
    Session currentSession = new Session();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createUser(ActionEvent event) {
    }

    @FXML
    private void submit(ActionEvent event) {
    }
    
}
