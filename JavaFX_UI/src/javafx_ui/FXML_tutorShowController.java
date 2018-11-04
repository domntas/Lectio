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
    private Label slot6;

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

    private int id;

    private String studentemail;
    private String studentname;

    ObservableList<String> list = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    private String[] allinformation = new String[5]; // store all the informations of the tutor

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.setItems(list);

        // TODO
    }

    public void myFunction(String username, String subject, String email, String studentemail, String studentname) {
        System.out.println(studentname);
        findTutorDetails(username, subject, email);
        setTutorDetails();
        this.studentemail = studentemail;
        this.studentname = studentname;
    }

    public String[] findTutorDetails(String username, String subject, String email) {

        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FULLNAME,BOROUGH,PRICE,SUBJECT, DETAILS, tutor.ID FROM TUTOR inner join users on users.FULLNAME = '" + username + "' and tutor.subject= '" + subject + "' and users.email= '" + email + "'");
            rs.next();

            id = rs.getInt("ID");

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
        Parent homepage_parent = (Parent) loader.load();
        Scene homepage_scene = new Scene(homepage_parent);
        FXMLStudentPageController setController = loader.getController();
        //System.out.println("YOUR NAME IS" + studentname);
        setController.myFunction(studentname, studentemail);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }

    public void nextPage(MouseEvent event, String time) throws IOException {
        FXMLLoader loader;
        System.out.println("hi");
        loader = new FXMLLoader(getClass().getResource("FXMLPayment.fxml"));
        System.out.println("hi");
        Parent homepage_parent = (Parent) loader.load();
        System.out.println("hi");
        FXMLPaymentController setController = loader.getController();
        System.out.println("hi");
        setController.myFunction(allinformation[2], studentemail, id, time, combobox.getValue());
        System.out.println("hi");
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }

    public void slotClicked9(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            String toastMsg = "some text...";
//                    int toastMsgTime = 3500; //3.5 seconds
//                    int fadeInTime = 500; //0.5 seconds
//                    int fadeOutTime= 500; //0.5 seconds
//                    
//                    Toast.makeText(stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00'");
                String avail = rs.getString("AVAILABILITY");
                System.out.println(avail);
                rs.close();
                System.out.println(combobox.getValue());
                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '09:00' ";
                    // rs.close();
                    stmt.executeUpdate(sql);

                    nextPage(event, "09:00");
                } else {
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

    public void slotClicked11(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '11:00'");
                String avail = rs.getString("AVAILABILITY");
                rs.close();

                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '11:00' ";
                    stmt.executeUpdate(sql);
                    nextPage(event, "11:00");
                } else {
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

    public void slotClicked13(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '13:00'");
                String avail = rs.getString("AVAILABILITY");
                rs.close();

                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '13:00' ";
                    stmt.executeUpdate(sql);
                    nextPage(event, "13:00");
                } else {
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

    public void slotClicked15(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '15:00'");
                String avail = rs.getString("AVAILABILITY");
                rs.close();

                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '15:00' ";
                    stmt.executeUpdate(sql);
                    nextPage(event, "15:00");
                } else {
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

    public void slotClicked17(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '17:00'");
                String avail = rs.getString("AVAILABILITY");
                rs.close();

                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '17:00' ";
                    stmt.executeUpdate(sql);
                    nextPage(event, "17:00");
                } else {
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

    public void slotClicked19(MouseEvent event) throws IOException {
        if (combobox.getSelectionModel().isEmpty()) {
            invalid3_label.setText("Please select day");

        } else {
            Connection c = null;
            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '19:00'");
                String avail = rs.getString("AVAILABILITY");
                rs.close();

                if (avail.equals("True")) {
                    String sql = "UPDATE Slots SET Availability='false' WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + combobox.getValue() + "'" + "AND TIMESLOT = '19:00' ";
                    stmt.executeUpdate(sql);
                    nextPage(event, "19:00");
                } else {
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

    public void daySelected() {
        System.out.println("day selected");
        String day = combobox.getValue();
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            System.out.println(id);
            ResultSet rs = stmt.executeQuery("SELECT TIMESLOT FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + day + "' and AVAILABILITY= 'True'");
            System.out.println(day);
            int size = 0;

            while (rs.next()) {
                size++;
            }

            System.out.println(size);
            rs = stmt.executeQuery("SELECT TIMESLOT FROM Slots WHERE ID = " + "'" + id + "'" + "AND DAY = " + "'" + day + "' and AVAILABILITY= 'True'");

            rs.next();
            System.out.println(rs.getString("Timeslot"));
            if (rs.getString("Timeslot").equals("09:00")) {
                slot1.setText(rs.getString("Timeslot"));
                size--;
                if (size != 0) {
                    rs.next();

                }
            } else {
                slot1.setText("booked");

            }

            if (rs.getString("Timeslot").equals("11:00")) {

                slot2.setText(rs.getString("Timeslot"));
                size--;
                if (size != 0) {
                    rs.next();

                }
            } else {
                slot2.setText("booked");
            }
            if (rs.getString("Timeslot").equals("13:00")) {

                slot3.setText(rs.getString("Timeslot"));
                size--;
                if (size != 0) {
                    rs.next();

                }
            } else {

                slot3.setText("booked");

            }
            if (rs.getString("Timeslot").equals("15:00")) {

                slot4.setText(rs.getString("Timeslot"));
                size--;
                if (size != 0) {
                    rs.next();

                }
            } else {

                slot4.setText("booked");

            }
            if (rs.getString("Timeslot").equals("17:00")) {
                System.out.println(rs.getString("Timeslot"));
                slot5.setText(rs.getString("Timeslot"));
                size--;
                if (size != 0) {
                    System.out.println(size);
                    rs.next();

                }
            } else {
                System.out.println(rs.getString("Timeslot"));
                slot5.setText("booked");

            }

            if (rs.getString("Timeslot").equals("19:00")) {
                System.out.println(rs.getString("Timeslot"));
                slot6.setText(rs.getString("Timeslot"));
            } else {
                slot6.setText("booked");
            }

            System.out.println("day selected");
            invalid3_label.setText("Day updated");

            stmt.close();

            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");

    }
}
