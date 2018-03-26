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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_RegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField username2_box;
    @FXML
    private TextField password2_box;
    @FXML
    private TextField password3_box;
    @FXML
    private TextField email_box;
    @FXML
    private RadioButton student_radio;
    @FXML
    private RadioButton tutor_radio;
    @FXML
    private Label invalid2_label;

    private String message = "";

    //Global variable
    private String type = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void backClicked(MouseEvent event) throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }

    public void registratationClicked(MouseEvent event) throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXML_StudentDetails.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();

        FXML_RegistrationController ctrl1 = (FXML_RegistrationController) fxmlLoader.getController();
        if (isValidCredentials()) {
            if (!username2_box.getText().isEmpty() && !password2_box.getText().isEmpty() && !email_box.getText().isEmpty()) {
                if (type == "Student") {
                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                } else {
                    System.out.println("the end");
                    homepage_parent = FXMLLoader.load(getClass().getResource("FXML_TutorDetails.fxml"));
                    homepage_scene = new Scene(homepage_parent);
                    app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                }

            }
        } else {
            username2_box.clear();
            password2_box.clear();
            password3_box.clear();
            email_box.clear();
            invalid2_label.setText(message);

        }
    }

    private boolean isValidCredentials() {

        boolean let_in = true;
        System.out.println("SELECT * FROM Users WHERE EMAIL= " + "'" + username2_box.getText() + "'" + " AND PASSWORD= " + "'" + password2_box.getText() + "'");

        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE EMAIL= " + "'" + email_box.getText() + "'");
            if (rs.next()) {
                message = "Email already taken";
                let_in = false;
            }
            rs.close();
            if (let_in) {
                if ((!student_radio.isSelected() && !tutor_radio.isSelected()) || username2_box.getText().isEmpty() || password2_box.getText().isEmpty() || password3_box.getText().isEmpty() || email_box.getText().isEmpty()) {
                    message = "Please full missing fields";
                    return false;
                } else {
                    if (student_radio.isSelected()) {
                        type = "Student";
                    }
                    if (tutor_radio.isSelected()) {
                        System.out.println("tutor");
                        type = "Tutor";
                    }

                    if (!(password2_box.getText().equals(password3_box.getText()))) {
                        System.out.println(password2_box.getText());
                        System.out.println(password3_box.getText());
                        message = "Passwords do not match";
                        return false;
                    }
                    String sql = "INSERT INTO Users (FULLNAME, EMAIL, PASSWORD,USERTYPE) VALUES (?,?,?,?)";

                    try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                        System.out.println("inserting");
                        pstmt.setString(1, username2_box.getText());
                        pstmt.setString(2, email_box.getText());
                        pstmt.setString(3, password2_box.getText());
                        pstmt.setString(4, type);
                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                }

            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println(let_in);
        System.out.println("Operation done succesfully");
        return let_in;
    }

}
