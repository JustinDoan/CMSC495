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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author omt
 */
public class WarningController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private Label detailsLabel;
    @FXML
    private HBox actionParent;
    @FXML
    private Button actionButton;
    @FXML
    private Button cancelButton;
    @FXML
    private HBox okParent;
    @FXML
    private Button okButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setMsg(String m, String d) {
        this.messageLabel.setText(m);
        this.detailsLabel.setText(d);
    }

    @FXML
    private void submit(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void action(ActionEvent event) {
    }    
}
