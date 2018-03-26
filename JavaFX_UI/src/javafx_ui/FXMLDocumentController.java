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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author filippopiggici
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField username_box;
    @FXML
    private TextField password_box;
    @FXML
    private Label invalid_label;

    private String username;

    private String name;

    private String usertype;

    private int id;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void registrationClicked(MouseEvent event) throws IOException {
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXML_Registration.fxml"));
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();

    }

    public void loginClicked(MouseEvent event) throws IOException {

        if (isValidCredentials()) {
            try {
                FXMLLoader loader;
                if (usertype.equals("Student")) {
                    loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
                    Parent homepage_parent = (Parent) loader.load();
                    FXMLStudentPageController setController = loader.getController();
                    System.out.println("YOUR NAME IS" + name);
                    setController.myFunction(name, username);
                    Scene homepage_scene = new Scene(homepage_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                } else {
                    loader = new FXMLLoader(getClass().getResource("FXML_Homepage.fxml"));
                    Parent homepage_parent = (Parent) loader.load();
                    FXML_HomepageController setController = loader.getController();
                    System.out.println("YOUR NAME IS" + name);
                    System.out.println(id);
                    setController.myFunction(name, id);
                    Scene homepage_scene = new Scene(homepage_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                }

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("Wrong Username or Password");
        }
    }

    private boolean isValidCredentials() {

        boolean let_in = false;
        System.out.println("SELECT * FROM Users WHERE EMAIL= " + "'" + username_box.getText() + "'" + " AND PASSWORD= " + "'" + password_box.getText() + "'");

        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");
            c.setAutoCommit(false);

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE EMAIL= " + "'" + username_box.getText() + "'" + " AND PASSWORD= " + "'" + password_box.getText() + "'");

            while (rs.next()) {
                if (rs.getString("EMAIL") != null && rs.getString("PASSWORD") != null) {
                    username = rs.getString("EMAIL");
                    System.out.println("EMAIL = " + username);
                    String password = rs.getString("PASSWORD");
                    System.out.println("PASSWORD = " + password);
                    name = rs.getString("FULLNAME");
                    usertype = rs.getString("usertype");
                    System.out.println(usertype);
                    id = rs.getInt("ID");
                    let_in = true;

                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done succesfully");
        return let_in;
    }

    public String getName() {
        return name;
    }

    public void onEnter(ActionEvent event) throws IOException {
        if (isValidCredentials()) {
            try {
                FXMLLoader loader;
                if (usertype.equals("Student")) {
                    loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
                    Parent homepage_parent = (Parent) loader.load();
                    FXMLStudentPageController setController = loader.getController();
                    System.out.println("YOUR NAME IS" + name);
                    setController.myFunction(name, username);
                    Scene homepage_scene = new Scene(homepage_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                } else {
                    loader = new FXMLLoader(getClass().getResource("FXML_Homepage.fxml"));
                    Parent homepage_parent = (Parent) loader.load();
                    FXML_HomepageController setController = loader.getController();
                    System.out.println("YOUR NAME IS" + name);
                    System.out.println(id);
                    setController.myFunction(name, id);
                    Scene homepage_scene = new Scene(homepage_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.hide();
                    app_stage.setScene(homepage_scene);
                    app_stage.show();
                }

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            username_box.clear();
            password_box.clear();
            invalid_label.setText("Wrong Username or Password");
        }

    }
}
