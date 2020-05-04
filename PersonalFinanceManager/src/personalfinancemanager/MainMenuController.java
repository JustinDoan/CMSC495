/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marcusmaibach
 */
public class MainMenuController implements Initializable, AccountCreator, AccountManager {

    @FXML
    private Text infoText;
    
    User currentUser = new User();
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
       //Placeholder accounts
       Account a = new Account(0, 1010, 0);
       currentUser.addAccount(a);
       Account b = new Account(141, 12123, 10);
       currentUser.addAccount(b);
    }    

    @FXML
    private void newReceipt(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
            Scene scene = new Scene(loader.load(), 720, 320);
            
            ReceiptController receiptController = loader.getController();
            
            Stage receiptStage = new Stage();
            receiptStage.setTitle("New Receipt");
            receiptStage.setScene(scene);
            receiptController.enclosingStage = receiptStage;
            receiptStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void report(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reports.fxml"));
            Scene scene = new Scene(loader.load(), 580, 403);
            
            ReportsController reportsController = loader.getController();
            
            Stage reportsStage = new Stage();
            reportsStage.setTitle("Reports");
            reportsStage.setScene(scene);
            reportsStage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void newTransaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
            Scene scene = new Scene(loader.load(), 720, 320);
            
            TransactionController transactionController = loader.getController();
            
            Stage transactionStage = new Stage();
            transactionStage.setTitle("New Transaction");
            transactionStage.setScene(scene);
            transactionController.enclosingStage = transactionStage;
            transactionStage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void newAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
            Scene scene = new Scene(loader.load(), 720, 320);
            
            AccountController accountController = loader.getController();
            
            Stage accountStage = new Stage();
            accountStage.setTitle("New Account");
            accountStage.setScene(scene);
            accountController.enclosingStage = accountStage;
            accountStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newCard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Card.fxml"));
            Scene scene = new Scene(loader.load(), 720, 320);
            
            CardController cardController = loader.getController();
            
            Stage cardStage = new Stage();
            cardStage.setTitle("New Card");
            cardStage.setScene(scene);
            cardController.enclosingStage = cardStage;
            cardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @Override
    public void createAccount(long accountNumber, long routingNumber, double balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getAccounts() {
        String[] accountNumbers = new String[currentUser.accounts.size()];
        for(int i = 0; i < accountNumbers.length;i++){
           accountNumbers[i] = "" + currentUser.accounts.get(i).accountNumber;
        }
        return accountNumbers;
    }
    
    public void transaction(String transactionType, long sourceAccountNumber, long destinationAccountNumber, double amount){
        Account source = currentUser.getAccount(sourceAccountNumber);
        Account destination = currentUser.getAccount(destinationAccountNumber);
        switch(transactionType){
            case "Deposit":
                source.deposit(amount);
                break;
            case "Withdrawal":
                source.withdraw(amount);
                break;
            case "Transfer":
                source.transfer(amount, destination);
                break;
            default:
                break;
        }
    }


    
    
}
