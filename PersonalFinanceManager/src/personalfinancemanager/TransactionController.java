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
    AccountManager manager;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Deposit","Withdrawal","Transfer");
        transactionBox.setItems(list);
//        ObservableList<String> accountList = FXCollections.observableArrayList(Arrays.asList(manager.getAccounts()));
//        recipientBox.setItems(accountList);
//        sourceBox.setItems(accountList);

    }    

    @FXML
    private void submit(ActionEvent event) {
        double amount = 0;
        String transactionType = transactionBox.getValue();
        if(!amountField.getText().equals("")){
            amount = Double.parseDouble(amountField.getText());
        }
        manager.transaction(transactionType, Long.parseLong(sourceBox.getValue()), Long.parseLong(recipientBox.getValue()), amount);

        
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
