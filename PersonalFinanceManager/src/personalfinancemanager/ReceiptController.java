/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
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
public class ReceiptController implements Initializable {

    @FXML
    private TextField taxField;
    @FXML
    private TextField discountField;
    @FXML
    private ComboBox<Integer> accountBox;
    @FXML
    private ComboBox<Integer> cardBox;
    @FXML
    private TextField subtotalField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField cashField;
    @FXML
    private TextField dateField;
    private DAO dao;

    Stage enclosingStage;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO();
        dao.connect();
        
        ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3);
        accountBox.setItems(list);
        cardBox.setItems(list);
    }    

    @FXML
    private void submit(ActionEvent event) {
        String card_id = null;
        long card_num = 0;
        double sub_total = 0;
        double sales_tax = 0;
        double total = 0;
        double discount = 0;
        double cash_paid = 0;
        String date = null;
        
        
        
        if(!cardBox.getValue().toString().equals("")){
            card_num = Long.parseLong(cardBox.getValue().toString());
        }
        if(!subtotalField.getText().equals("")){
            sub_total = Double.parseDouble(subtotalField.getText());
        }
         if(!totalField.getText().equals("")){
            total = Double.parseDouble(totalField.getText());
        }
        if(!taxField.getText().equals("")){
            sales_tax = Double.parseDouble(taxField.getText());
        }
         if(!discountField.getText().equals("")){
            discount = Double.parseDouble(discountField.getText());
        }
        if(!cashField.getText().equals("")){
            cash_paid = Double.parseDouble(cashField.getText());
        }
        if(!dateField.getText().equals("")){
            date = dateField.getText();
        }
        
        dao.insertReceipts(card_num, sub_total, total, sales_tax, discount, cash_paid, date);
    }

    @FXML
    private void exit(ActionEvent event) {
        dao.closeDB();
        enclosingStage.close();
    }
    
}
