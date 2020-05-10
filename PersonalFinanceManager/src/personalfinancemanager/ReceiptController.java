/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


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
    private DatePicker dateField;

    Stage enclosingStage;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
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
        Date date = null;
        
        try{
            card_num = Long.parseLong(cardBox.getValue().toString());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Card Number");
            return;
        }
        try{
            sub_total = Double.parseDouble(subtotalField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Subtotal");
            return;
        }
        try{
            total = Double.parseDouble(totalField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Total");
            return;
        }
        try{
            sales_tax = Double.parseDouble(taxField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Sales Tax");
            return;
        }
        try{
            discount = Double.parseDouble(discountField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Discount");
            return;
        }
        try{
            cash_paid = Double.parseDouble(cashField.getText());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Cash Paid");
            return;
        }
        try{
            date = Date.valueOf(dateField.getValue());
        }
        catch (NumberFormatException n) {
            Main.showAlert(DialogTypes.NAN, "Date");
            return;
        }
        
        DAO.shared.insertReceipts(card_num, sub_total, total, sales_tax, discount, cash_paid, date);
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
