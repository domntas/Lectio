/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class FXML_HomepageController implements Initializable {

    @FXML
    private Label welcome_label;

    @FXML
    private Label nametutor111;

    @FXML
    private Label distance111;

    @FXML
    private Label invalid2_label;

    @FXML
    private Label subject11;

    @FXML
    private Label day1;

    @FXML
    private Label details1;

    @FXML
    private AnchorPane tutorbox111;

    @FXML
    private Label nametutor11;

    @FXML
    private Label distance11;

    @FXML
    private Label subject111;

    @FXML
    private AnchorPane tutorbox11;

    @FXML
    private Label timeslot1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //welcome_label.setText("hello" + new FXMLDocumentController().getName());
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT fullname, day, timeslot, details from users inner join requests on users.id = requests.id  where users.usertype ='student'");
            rs.next();
                nametutor11.setText(rs.getString(1));
                day1.setText(rs.getString(2));
                timeslot1.setText(rs.getString(3));
                details1.setText(rs.getString(4));
            rs.next();
//                nametutor111.setText(rs.getString(1));
//                day2.setText(rs.getString(2));
//                timeslot2.setText(rs.getString(3));
//                details2.setText(rs.getString(4));
            

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");
    }

}
