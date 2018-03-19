/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_tutorShowController implements Initializable {

   
    @FXML
    private Label borough;

    @FXML
    private Label invalid2_label;

    @FXML
    private Label rate;

    @FXML
    private Label subject;

    @FXML
    private Label nametutor;

    @FXML
    private Label description;

    private String[] allinformation = new String[5]; // store all the informations of the tutor
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myFunction(String username, String subject) {
        findTutorDetails(username, subject);
        setTutorDetails();
    }

    public String[] findTutorDetails(String username, String subject) {

        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FULLNAME,BOROUGH,PRICE,SUBJECT, DETAILS FROM TUTOR inner join users on users.FULLNAME = '" + username + "' and tutor.subject= '" + subject + "'");
            rs.next();

            for (int x = 0; x < allinformation.length; x++) {     // insert all the information in the array that iwill return at the end of the function
                allinformation[x] = rs.getString(x+1);
            }
            rs.close();
            stmt.close();
            c.close();
            return allinformation;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.exit(0);
        return allinformation;
    }
    
    
    public void setTutorDetails() {
        nametutor.setText(allinformation[0]);
        borough.setText(allinformation[1]);
        rate.setText(allinformation[2] +"$/h");
        subject.setText(allinformation[3]);
        description.setText(allinformation[4]);
    }
    
     public void backClicked(MouseEvent event)  throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLStudentPage.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }
}
