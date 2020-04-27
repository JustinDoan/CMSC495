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
public class CardController implements Initializable {

    @FXML
    private TextField cardNumberField;
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
        long account_num = 0;
        long card_num = 0;
        double balance = 0;
        
        if(!accountNumberField.getText().equals("")){
            account_num = Long.parseLong(accountNumberField.getText());
        }
        if(!cardNumberField.getText().equals("")){
            card_num = Long.parseLong(cardNumberField.getText());
        }
        if(!balanceField.getText().equals("")){
            balance = Double.parseDouble(balanceField.getText());
        }
        
        dao.insertCards(account_num, card_num, balance);
    }

    @FXML
    private void exit(ActionEvent event) {
        dao.closeDB();
        enclosingStage.close();
    }
    
}
