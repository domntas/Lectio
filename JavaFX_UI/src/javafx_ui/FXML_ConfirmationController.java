/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_ConfirmationController implements Initializable {
     @FXML
    private Label invalid2_label;

    @FXML
    private Label subject;

    @FXML
    private Label name;

    @FXML
    private Label welcome_label;

    @FXML
    private AnchorPane studentbox1;

    @FXML
    private Label location;

    @FXML
    private Label timeslot;

    @FXML
    private Label day;

    @FXML
    private Label confirmation1;
    
    private String studentemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void myFunction(String name, String day, String timeslot, String borough) {
        //subject.setText(text);
        this.name.setText(name);
        this.day.setText(day);
        this.timeslot.setText(timeslot);
        location.setText(borough);
        
        //studentemail = email;
    }
    
}
