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
public class AccountController implements Initializable {

    @FXML
    private TextField routingNumberField;
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
        long accountNumber = 0;
        long routingNumber = 0;
        
        //TODO: Add proper validation
        try{
            accountNumber = Long.parseLong(accountNumberField.getText());
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Account Number must be a number");
        }
        try{
            routingNumber = Long.parseLong(routingNumberField.getText());
        }
        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null, "Routing Number must be a number");
        }
        //*/
        
        String accountNumber = accountNumberField.getText();
        String routingNumber = routingNumberField.getText();
        double balance = 0;

        try{
            balance = Double.parseDouble(balanceField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Balance");
            return;        
        }
        
        DAO.shared.insertAccount(accountNumber, routingNumber, balance);
        enclosingStage.close();
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
