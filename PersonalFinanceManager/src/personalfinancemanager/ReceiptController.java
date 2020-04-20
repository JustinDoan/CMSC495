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

    Stage enclosingStage;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        enclosingStage.close();
    }
    
}
