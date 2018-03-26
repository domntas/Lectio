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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_StudentDetailsController implements Initializable {

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private Button register;

    @FXML
    private Label invalid_label;

    private String name;

    private String email;

    ObservableList<String> list = FXCollections.observableArrayList("Camden", "Greenwich", "Hackney", "Hammersmith", "Islington", "Kensington and Chelsea", "Lambeth", "Lewisham", "Southwark", "Tower Hamlets", "Wandsworth", "Westminster", "Barking", "Barnet", "Bexley", "Brent", "Bromley", "Croydon", "Ealing", "Enfield", "Haringey", "Harrow", "Havering", "Hillingdon", "Hounslow", "Kingston upon Thames", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Sutton", "Waltham Forest");

    ;
    

    
    
    
    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBox1.setItems(list);

        //comboBox2.getItems().addAll("Math", "History");
    }

    public void registrationClicked(MouseEvent event) throws IOException {

        if (isValid2()) {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXMLStudentPageController setController = loader.getController();
            System.out.println("YOUR NAME IS" + name);
            setController.myFunction(name, email);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();

            app_stage.show();
        } else {

            invalid_label.setText("Select every field please");
        }
    }

    public boolean isValid2() {

        if (comboBox1.getSelectionModel().isEmpty()) {
            System.out.println("heyyyyyyyyy1");
            return false;
        } else {
            System.out.println("heyyyyyyyyy");
            Connection c = null;
            java.sql.Statement stmt = null;

            try {
                c = DriverManager.getConnection("jdbc:sqlite:users.db");

                System.out.println("Opened database succesfully");
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT ID,FULLNAME, EMAIL FROM Users ORDER BY ID DESC LIMIT 1");
                int id = rs.getInt(1);
                name = rs.getString(2);
                email = rs.getString(3);
                String sql = "INSERT INTO student (ID, BOROUGH) VALUES (?,?)";
                // rs.close();

                try (
                        PreparedStatement pstmt = c.prepareStatement(sql)) {
                    System.out.println("inserting IN THE STUDENT");
                    pstmt.setInt(1, id);
                    pstmt.setString(2, comboBox1.getValue());
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("Operation done succesfully");

        }
        return true;
    }

}
