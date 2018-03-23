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
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_HomepageController implements Initializable {

    @FXML
    private Label welcome_label;

    @FXML
    private Label invalid2_label;

    @FXML
    private Label day1;

    @FXML
    private Label day11;

    @FXML
    private Label details1;

    @FXML
    private Label details11;

    @FXML
    private Label name1;

    @FXML
    private Label name11;

    @FXML
    private Label borough1;

    @FXML
    private Label borough11;

    @FXML
    private AnchorPane studentbox1;

    @FXML
    private AnchorPane studentbox11;

    @FXML
    private Label timeslot1;

    @FXML
    private Label timeslot11;

    @FXML
    private Label request_label;

    @FXML
    private ImageView arrow;

    @FXML
    private ImageView accept1;

    @FXML
    private ImageView deny1;

    @FXML
    private Label confirmation1;

    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //welcome_label.setText("hello" + new FXMLDocumentController().getName());

    }

    public void myFunction(String text, int tutorid) {
        confirmation1.setVisible(false);

        System.out.println("hellooo");
        System.out.println(text);
        String firstName[] = text.split(" ");
        welcome_label.setText("Hello, " + firstName[0]);
        id = tutorid;
        System.out.println(welcome_label.getText());
        findRequests();

    }

    public void findRequests() {
        Connection c = null;
        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully homepage fot tutor");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT fullname, day, timeslot, details from users inner join requests on users.id = requests.studentid  where requests.tutorid = " + id + " ");
            System.out.println("executed");
            System.out.println(id);
            int size = 0;
            while (rs.next()) {
                size++;
            }
            System.out.println(size);
            rs = stmt.executeQuery("SELECT fullname, day, timeslot, details, borough from users inner join requests on users.id = requests.studentid  inner join student on users.id=student.id where requests.tutorid = " + id + " ");

            if (size == 1) {

                System.out.println("hi");
                System.out.println(rs.getString(1));
                name1.setText(rs.getString(1));
                day1.setText(rs.getString(2));
                timeslot1.setText(rs.getString(3));
                details1.setText(rs.getString(4));
                request_label.setText("NEW REQUEST");
                borough1.setText(rs.getString(5));
                studentbox11.setVisible(false);
            }
            if (size >= 2) {
                rs.next();
                System.out.println("hi");
                name1.setText(rs.getString(1));
                day1.setText(rs.getString(2));
                timeslot1.setText(rs.getString(3));
                details1.setText(rs.getString(4));
                borough1.setText(rs.getString(5));
                rs.next();
                name11.setText(rs.getString(1));
                day11.setText(rs.getString(2));
                timeslot11.setText(rs.getString(3));
                details11.setText(rs.getString(4));
                borough11.setText(rs.getString(5));
            }

            if (size == 0) {
                request_label.setText("NO REQUESTS YET");
                arrow.setVisible(false);
                studentbox1.setVisible(false);
                studentbox11.setVisible(false);
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");
    }

    public void acceptedFirst(MouseEvent event) throws IOException, InterruptedException {
        accept1.setVisible(false);
        deny1.setVisible(false);
        confirmation1.setVisible(true);
        confirmation1.setText("Accepted");
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_ConfirmationController.fxml"));
            System.out.println(loader);
            Parent homepage_parent = (Parent) loader.load();
            FXML_ConfirmationController setController = loader.getController();
            setController.myFunction(name1.getText(), day1.getText(), timeslot1.getText(), borough1.getText());
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void deniedFirst(MouseEvent event) throws IOException, InterruptedException {
        accept1.setVisible(false);
        deny1.setVisible(false);
        confirmation1.setVisible(true);
       confirmation1.setText("Denied");


    }
}
