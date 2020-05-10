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
import javax.swing.JOptionPane;

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

    Stage enclosingStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void submit(ActionEvent event) {
        /*
        long account_num = 0;
        long card_num = 0;
        
        try{
            account_num = Long.parseLong(accountNumberField.getText());
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Account Number must be a number");
        }
        try{
            card_num = Long.parseLong(cardNumberField.getText());
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Card Number must be a number");
        } //*/
        
        String account_num = accountNumberField.getText();
        String card_num = cardNumberField.getText();
        double balance = 0D;
        
        try{
            balance = Double.parseDouble(balanceField.getText());
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Balance must be a number");
        }
        
        DAO.shared.insertCards(account_num, card_num, balance);
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
