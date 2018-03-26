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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author domantas
 */
public class FXMLPaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label price;

    @FXML
    private ImageView confirm;

    @FXML
    private Label emptyLabel;

    @FXML
    private TextArea requestdetails;

    private String studentemail;

    private int id;

    private String day;

    private String timeslot;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //
    public void confirmClicked(MouseEvent event) throws IOException {
        if (!(email.getText().isEmpty() && password.getText().isEmpty())) {
            if (email.getText().contains("@")) {

                Connection c = null;
                java.sql.Statement stmt = null;

                try {
                    c = DriverManager.getConnection("jdbc:sqlite:users.db");

                    System.out.println("Opened database succesfully");
                    stmt = c.createStatement();

                    ResultSet rs = stmt.executeQuery("SELECT student.ID, USERS.FULLNAME, USERS.EMAIL FROM STUDENT INNER JOIN USERS ON student.id=users.id where users.email= '" + studentemail + "'");
                    String studentid = rs.getString("ID");
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    String sql = "INSERT INTO REQUESTS(STUDENTID, TUTORID, DAY, TIMESLOT, DETAILS)  VALUES (?,?,?,?,?)";

                    try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                        System.out.println("inserting");
                        System.out.println(studentid + " " + String.valueOf(id) + " " + day + timeslot);
                        pstmt.setString(1, studentid);
                        pstmt.setString(2, String.valueOf(id));
                        pstmt.setString(3, day);
                        pstmt.setString(4, timeslot);
                        pstmt.setString(5, requestdetails.getText());
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
                    Parent homepage_parent = (Parent) loader.load();
                    FXMLStudentPageController setController = loader.getController();
                    System.out.println("YOUR NAME IS" + name);
                    setController.myFunction(name, email);
                    Scene homepage_scene = new Scene(homepage_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                System.out.println("Operation done succesfully");

            } else {
                if (!email.getText().contains("@")) {
                    emptyLabel.setText("This is not an email");
                } else {
                    emptyLabel.setText("Please fill in all empty fields");
                }
            }
        }
    }
//allinformation[2], studentemail, allinformation[0], id, time, combobox.getValue()  );

    public void myFunction(String text, String studentemail, int id, String time, String day) {
        System.out.println(text);
        if (text.contains(".")) {
            price.setText("£" + text);
        } else {
            price.setText("£" + text + ".00");
        }

        this.studentemail = studentemail;
        timeslot = time;
        this.day = day;
        this.id = id;

    }

}
