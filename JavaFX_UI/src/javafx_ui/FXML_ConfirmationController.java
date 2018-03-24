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
import javafx.scene.text.Text;

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

    @FXML
    private Text final_label;

    private String studentemail;
    
     private int tutorid;

    private int studentid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myFunction(String name, String day, String timeslot, String borough, int tutorid, int studentid) {
        //subject.setText(text);
        this.name.setText(name);
        this.day.setText(day);
        this.timeslot.setText(timeslot);
        location.setText(borough);
        this.tutorid=tutorid;
        this.studentid=studentid;
        final_label.setText("READY TO TEACH ON " + this.day.getText().toUpperCase() + "?");
        update();
        //studentemail = email;
    }

    public void update() {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            
            System.out.println(studentid);
            System.out.println(tutorid);
            System.out.println(timeslot);
            String sql = "UPDATE Requests SET Status='confirmed' WHERE STUDENTID = '" + studentid + "'" + " AND TUTORID = " + "'" + tutorid + "'" + " AND TIMESLOT = '"+timeslot.getText()+"'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
