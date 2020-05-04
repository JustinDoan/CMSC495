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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDateTime;

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
    private ComboBox<?> accountBox;
    @FXML
    private ComboBox<?> cardBox;
    @FXML
    private TextField subtotalField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField cashField;
    @FXML
    private TextField changeField;
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
        String card_id = null;
        long card_num = 0;
        double sub_total = 0;
        double sales_tax = 0;
        double total = 0;
        double discount = 0;
        double cash_paid = 0;
        LocalDateTime time = LocalDateTime.now();
        
        if(cardBox.getValue().toString() != ""){
            card_num = Long.parseLong(cardBox.getValue().toString());
        }
        if(subtotalField.getText() != ""){
            sub_total = Double.parseDouble(subtotalField.getText());
        }
         if(totalField.getText() != ""){
            total = Double.parseDouble(totalField.getText());
        }
          if(taxField.getText() != ""){
            sales_tax = Double.parseDouble(taxField.getText());
        }
         if(discountField.getText() != ""){
            discount = Double.parseDouble(discountField.getText());
        }
        if(cashField.getText() != ""){
            cash_paid = Double.parseDouble(cashField.getText());
        }
        
        dao.insertReceipts(card_num, sub_total, total, sales_tax, discount,
                cash_paid, time);
    }

    @FXML
    private void exit(ActionEvent event) {
        dao.closeDB();
        enclosingStage.close();
    }
    
}
