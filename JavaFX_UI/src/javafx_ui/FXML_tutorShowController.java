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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

    @FXML
    private Label slot5;

    @FXML
    private Label slot4;

    @FXML
    private Label slot3;

    @FXML
    private Label slot2;

    @FXML
    private Label slot1;

    @FXML
    private Label invalid3_label;
    
    @FXML
    private ComboBox<String> combobox;

    private String id="";

    ObservableList<String> list3 = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    private String[] allinformation = new String[5]; // store all the informations of the tutor

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.setItems(list3);
        // TODO
    }

    public void myFunction(String username, String subject, String email) {
        findTutorDetails(username, subject, email);
        setTutorDetails();
    }

    public String[] findTutorDetails(String username, String subject, String email) {

        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FULLNAME,BOROUGH,PRICE,SUBJECT, DETAILS FROM TUTOR inner join users on users.FULLNAME = '" + username + "' and tutor.subject= '" + subject + "' and users.email= '" + email + "'");
            rs.next();
            id = rs.getString("ID");

            for (int x = 0; x < allinformation.length; x++) {     // insert all the information in the array that iwill return at the end of the function
                allinformation[x] = rs.getString(x + 1);
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
        rate.setText(allinformation[2] + "£/h");
        subject.setText(allinformation[3]);
        description.setText(allinformation[4]);
    }

    public void backClicked(MouseEvent event) throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLStudentPage.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }

    public void slotClicked9(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + id +  " AND DAY = " + "'" + combobox.getValue() + "'" + " AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    public void slotClicked11(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    public void slotClicked13(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    public void slotClicked15(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    public void slotClicked17(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    public void slotClicked19(MouseEvent event) throws IOException {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
            String avail = rs.getString("AVAILABILITY");
            rs.close();

            if (avail.equals("True")) {
                
                Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLPayment.fxml"));
                Scene homepage_scene = new Scene(homepage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(homepage_scene);
                app_stage.show();
            } else {
                slot1.setText("BOOKED");
                invalid3_label.setText("This slot has already been booked");
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
    
    
}
