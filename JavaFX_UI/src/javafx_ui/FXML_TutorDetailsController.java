/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    
    @FXML
    private Button register;

    ObservableList<String> list = FXCollections.observableArrayList("Camden", "Greenwich", "Hackney", "Hammersmith", "Islington", "Kensington and Chelsea", "Lambeth", "Lewisham", "Southwark", "Tower Hamlets", "Wandsworth", "Westminster", "Barking", "Barnet", "Bexley", "Brent", "Bromley", "Croydon", "Ealing", "Enfield", "Haringey", "Harrow", "Havering", "Hillingdon", "Hounslow", "Kingston upon Thames", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Sutton", "Waltham Forest");

    ;
    
    ObservableList<String> list2 = FXCollections.observableArrayList("Art", "Biology", "Business Studies", "Chemistry", "Design and Technology", "Drama", "English Language", "English Literature", "Geography", "History", "ICT", "Latin", "Law", "Maths", "Media Studies", "Music", "Physics", "Psychology", "Science", "Sociology", "Statistics");

    ;
    
    
    
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        comboBox1.setItems(list);
        comboBox2.setItems(list2);

        //comboBox2.getItems().addAll("Math", "History");
    }
    
    
     public void registrationClicked(MouseEvent event)  throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXML_Homepage.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if(isValid())
        {
            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        }
        else
        {
             
             //invalid_label.setText("Wrong Username or Password");
        }
    }
     
     public boolean isValid(){
         
        if(comboBox1.getSelectionModel().isEmpty()){
             return false;
         }
        else{
            
        }
         
     }
    

}
