/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author marcusmaibach
 */
public class ReportsController implements Initializable {

    @FXML
    private ComboBox<String> accountBox;
    @FXML
    private ComboBox<String> dataBox;
    @FXML
    private TableView<Comparable> tableView;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //TODO: Replace with actual values
        String[] accounts = {"1","2","3"};
        accountBox.setItems(FXCollections.observableArrayList(accounts));
        dataBox.setItems(FXCollections.observableArrayList(accounts));
    }    
    public void accountChoiceDidChange(){
        System.out.println(accountBox.getValue());
    }
    public void dataChoiceDidChange(){
        System.out.println(dataBox.getValue());
    }
    
}