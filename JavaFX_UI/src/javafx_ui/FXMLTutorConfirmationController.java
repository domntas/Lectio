/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author domantas
 */
public class FXMLTutorConfirmationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label subject;

    @FXML
    private Label name;

    @FXML
    private Label location;

    @FXML
    private Label timeslot;

    @FXML
    private Label day;

    private String studentemail;

    private String studentname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myFunction(String name, String day, String timeslot, String borough, String studentemail, String studentname, String subject) {
        this.subject.setText(subject);
        this.name.setText(name);
        this.day.setText(day);
        this.timeslot.setText(timeslot);
        this.location.setText(borough);
        this.studentemail = studentemail;
        this.studentname = studentname;
        //update();
        //studentemail = email;
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

}
