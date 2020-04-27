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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marcusmaibach
 */
public class AccountController implements Initializable {

    @FXML
    private TextField routingNumberField;
    @FXML
    private TextField accountNumberField;
    @FXML
    private TextField balanceField;
    private DAO dao;
    
    Stage enclosingStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO();
        dao.connect();
    }

    @FXML
    private void submit(ActionEvent event) {
        long accountNumber = 0;
        long routingNumber = 0;
        double balance = 0;
        
        //TODO: Add proper validation
        if(!accountNumberField.getText().equals("")){
            accountNumber = Long.parseLong(accountNumberField.getText());
        }
        if(!routingNumberField.getText().equals("")){
            routingNumber = Long.parseLong(routingNumberField.getText());
        }
        if(!balanceField.getText().equals("")){
            balance = Double.parseDouble(balanceField.getText());
        }
        
        dao.insertAccount(accountNumber, routingNumber, balance);
    }

    @FXML
    private void exit(ActionEvent event) {
        dao.closeDB();
        enclosingStage.close();
    }
    
}
