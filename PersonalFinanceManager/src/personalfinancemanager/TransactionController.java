/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author marcusmaibach
 */
public class TransactionController implements Initializable {

    @FXML
    private ComboBox<String> transactionBox;
    @FXML
    private TextField amountField;
    @FXML
    private TextField commentField;
    @FXML
    private ComboBox<String> reasonBox;
    @FXML
    private ComboBox<String> recipientBox;
    @FXML
    private ComboBox<String> sourceBox;
    
    Stage enclosingStage;
    //AccountManager manager;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Deposit","Withdrawal","Transfer");
        transactionBox.setItems(list);
        ObservableList<String> accountList = FXCollections.observableArrayList(DAO.shared.getAccounts(Session.shared.getUID()));
        recipientBox.setItems(accountList);
        sourceBox.setItems(accountList);
        sourceBox.setDisable(true);
        recipientBox.setDisable(true); 

    }    

    @FXML
    private void transactionDidChange(ActionEvent event){
        switch (transactionBox.getValue()) {
            case "Transfer":
                sourceBox.setDisable(false);
                recipientBox.setDisable(false);
                break;
            case "Deposit":
                sourceBox.setDisable(true);
                recipientBox.setDisable(false);
                break;
            case "Withdrawal":
                sourceBox.setDisable(false); 
                recipientBox.setDisable(true);
                break;
        }
    }
    @FXML
    private void submit(ActionEvent event) {
        //long accountFrom = 0;
        //long accountTo = 0;
        
        String accountFrom, accountTo;
        
        double amount = 0;
        String transactionType = transactionBox.getValue();
        String comment = commentField.getText();
        if(transactionBox.getSelectionModel().isEmpty()){
            Main.showAlert(DialogTypes.COMBOBOX,"Transaction");
           return;
        }
        try{
            amount = Double.parseDouble(amountField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN,"Amount");
            return;
        }
        if(transactionType.equals("Deposit")){
            /*
            try{
                accountTo = Long.parseLong(recipientBox.getValue());
            }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Must choose an account");
                return;
            } //*/
            
            accountTo = recipientBox.getValue();
            
            DAO.shared.insertDeposits(accountTo, amount);
        }
        else if(transactionType.equals("Withdrawal")){
            /*
            try{
                accountFrom = Long.parseLong(sourceBox.getValue());
            }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Must choose an account");
                return;
            } //*/
            
            accountFrom = sourceBox.getValue();
            
            DAO.shared.insertWithdrawals(accountFrom, amount);
        }
        else if(transactionType.equals("Transfer")){
            /*
            try{
                accountTo = Long.parseLong(recipientBox.getValue());
            }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Must choose an account");
                return;
            }
            try{
                accountFrom = Long.parseLong(sourceBox.getValue());
            }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Must choose an account");
                return;
            } //*/
            
            accountTo = recipientBox.getValue();
            accountFrom = sourceBox.getValue();
            
            DAO.shared.insertTransfers(accountTo, accountFrom, amount);

            //Main.showAlert(DialogTypes.INVALIDFIELD,null);
        }
        enclosingStage.close();
        //manager.transaction(transactionType, Long.parseLong(sourceBox.getValue()), Long.parseLong(recipientBox.getValue()), amount);
        
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
