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
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_TutorDetailsController implements Initializable {

    @FXML
    private ComboBox<String> comboBox1;
    
    @FXML
    private ComboBox<String> comboBox2;
    
    
    
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBox1 =  new ComboBox<String>();
       comboBox1.getItems().addAll("Camden", "Greenwich", "Hackney", "Hammersmith", "Islington", "Kensington and Chelsea", "Lambeth", "Lewisham", "Southwark", "Tower Hamlets", "Wandsworth", "Westminster", "Barking", "Barnet", "Bexley", "Brent", "Bromley", "Croydon", "Ealing", "Enfield", "Haringey", "Harrow", "Havering", "Hillingdon", "Hounslow", "Kingston upon Thames", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Sutton", "Waltham Forest");
       comboBox2 =  new ComboBox<String>();
       comboBox2.getItems().addAll("Math","History");
    }    
    
}
