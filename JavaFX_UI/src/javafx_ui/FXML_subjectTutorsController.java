/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_ui;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXML_subjectTutorsController implements Initializable {

    @FXML
    private Label nametutor5;

    @FXML
    private Label nametutor4;

    @FXML
    private Label nametutor3;

    @FXML
    private Label nametutor2;

    @FXML
    private Label nametutor1;

    @FXML
    private Label invalid2_label;

    @FXML
    private AnchorPane tutorbox5;

    @FXML
    private Label distance5;

    @FXML
    private AnchorPane tutorbox2;

    @FXML
    private Label distance4;

    @FXML
    private Label rate4;

    @FXML
    private AnchorPane tutorbox1;

    @FXML
    private Label distance3;

    @FXML
    private Label rate5;

    @FXML
    private Label distance2;

    @FXML
    private AnchorPane tutorbox4;

    @FXML
    private Label distance1;

    @FXML
    private AnchorPane tutorbox3;

    @FXML
    private Label subject1;

    @FXML
    private Label rate1;

    @FXML
    private Label subject2;

    @FXML
    private Label rate2;

    @FXML
    private Label subject3;

    @FXML
    private Label rate3;

    @FXML
    private Label subject4;

    @FXML
    private Label subject5;

    @FXML
    private Label welcome_label;

    private String subject;

    private String studentname;

    private String studentemail;

    private int size;

    private ArrayList<Double> order = new ArrayList<Double>();      //distances in miles

    private ArrayList<String> ordername = new ArrayList<String>(); //fullnames

    private ArrayList<Integer> rate = new ArrayList<Integer>();    //rate

    private ArrayList<String> email = new ArrayList<String>();    //rate

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myFunction(String name, String email, String text) {
        subject = text;
        welcome_label.setText(subject);
        studentname = name;
        studentemail = email;
        Distance();
        setEverything();
    }

    public void setEverything() {
        System.out.println("YOU ARE INSIDE SET EVERYTHING");
        sortAll();
        System.out.println(order);
        if (size == 0) {
            tutorbox1.setVisible(false);
            tutorbox2.setVisible(false);
            tutorbox3.setVisible(false);
            tutorbox4.setVisible(false);
            tutorbox5.setVisible(false);
        }
        if (size == 1) {
            nametutor1.setText((String) ordername.get(0));
            distance1.setText(String.valueOf(order.get(0)) + " miles");
            subject1.setText(subject);
            rate1.setText(String.valueOf(rate.get(0)) + "£/h");
            tutorbox2.setVisible(false);
            tutorbox3.setVisible(false);
            tutorbox4.setVisible(false);
            tutorbox5.setVisible(false);
        }
        if (size == 2) {
            nametutor1.setText((String) ordername.get(0));
            distance1.setText(String.valueOf(order.get(0)) + " miles");
            subject1.setText(subject);
            rate1.setText(String.valueOf(rate.get(0)) + "£/h");
            nametutor2.setText((String) ordername.get(1));
            distance2.setText(String.valueOf(order.get(1)) + " miles");
            subject2.setText(subject);
            rate2.setText(String.valueOf(rate.get(1)) + "£/h");
            tutorbox3.setVisible(false);
            tutorbox4.setVisible(false);
            tutorbox5.setVisible(false);
        }
        if (size == 3) {
            nametutor1.setText((String) ordername.get(0));
            distance1.setText(String.valueOf(order.get(0)) + " miles");
            subject1.setText(subject);
            rate1.setText(String.valueOf(rate.get(0)) + "£/h");
            nametutor2.setText((String) ordername.get(1));
            distance2.setText(String.valueOf(order.get(1)) + " miles");
            subject2.setText(subject);
            rate2.setText(String.valueOf(rate.get(2)) + "£/h");
            nametutor3.setText((String) ordername.get(2));
            distance3.setText(String.valueOf(order.get(2)) + " miles");
            subject3.setText(subject);
            rate3.setText(String.valueOf(rate.get(2)) + "£/h");

            tutorbox4.setVisible(false);
            tutorbox5.setVisible(false);
        }
        if (size == 4) {
            nametutor1.setText((String) ordername.get(0));
            distance1.setText(String.valueOf(order.get(0)) + " miles");
            subject1.setText(subject);
            rate1.setText(String.valueOf(rate.get(0)) + "£/h");
            nametutor2.setText((String) ordername.get(1));
            distance2.setText(String.valueOf(order.get(1)) + " miles");
            subject2.setText(subject);
            rate2.setText(String.valueOf(rate.get(1)) + "£/h");
            nametutor3.setText((String) ordername.get(2));
            distance3.setText(String.valueOf(order.get(2)) + " miles");
            subject3.setText(subject);
            rate3.setText(String.valueOf(rate.get(2)) + "£/h");
            nametutor4.setText((String) ordername.get(3));
            distance4.setText(String.valueOf(order.get(3)) + " miles");
            subject4.setText(subject);
            rate4.setText(String.valueOf(rate.get(3)) + "£/h");

            tutorbox5.setVisible(false);
        }
        if (size == 5) {
            nametutor1.setText((String) ordername.get(0));
            distance1.setText(String.valueOf(order.get(0)) + " miles");
            subject1.setText(subject);
            rate1.setText(String.valueOf(rate.get(0)) + "£/h");
            nametutor2.setText((String) ordername.get(1));
            distance2.setText(String.valueOf(order.get(1)) + " miles");
            subject2.setText(subject);
            rate2.setText(String.valueOf(rate.get(1)) + "£/h");
            nametutor3.setText((String) ordername.get(2));
            distance3.setText(String.valueOf(order.get(2)) + " miles");
            subject3.setText(subject);
            rate3.setText(String.valueOf(rate.get(2)) + "£/h");
            nametutor4.setText((String) ordername.get(3));
            distance4.setText(String.valueOf(order.get(3)) + " miles");
            subject4.setText(subject);
            rate4.setText(String.valueOf(rate.get(3)) + "£/h");
            nametutor5.setText((String) ordername.get(4));
            distance5.setText(String.valueOf(order.get(4)) + " miles");
            subject5.setText(subject);
            rate5.setText(String.valueOf(rate.get(4)) + "£/h");

        }

        ///we miis the rate here
    }

    public void sortAll() {
        System.out.println(order);
        int z = order.size();
        int c = 0;
        while (c != z) {
            for (int x = 0 + c; x < z - 1; x++) {
                for (int y = 1 + c; y < z; y++) {
                    System.out.println(order);
                    if (order.get(x) >= order.get(y)) {
                        Collections.swap(order, x, y);
                        Collections.swap(ordername, x, y);
                        Collections.swap(rate, x, y);
                        Collections.swap(email, x, y);
                    }

                }
            }
            c++;
        }
        System.out.println(order);
    }

    public void Distance() {
        Connection c = null;
        java.sql.Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:users.db");

            System.out.println("Opened database succesfully");
            stmt = c.createStatement();
            System.out.println("hey");

            String newname = studentname;
            System.out.println(studentname);
            ResultSet rs = stmt.executeQuery("SELECT BOROUGH FROM STUDENT inner join users on users.id=student.id WHERE users.FULLNAME = '" + newname + "'");
            rs.next();
            String name = rs.getString(1) + " London";
            System.out.println(name);
            rs.close();

            rs = stmt.executeQuery("SELECT BOROUGH,SUBJECT, fullname, price,email FROM TUTOR, users where users.id=tutor.id and tutor.subject= '" + subject + "'");
            String check = "";
            size = 0;
            while (rs.next()) {
                size++;
            }
            System.out.println(size);
            rs = stmt.executeQuery("SELECT BOROUGH,SUBJECT, fullname, price,email FROM TUTOR, users where users.id=tutor.id and tutor.subject= '" + subject + "'");

            System.out.println("ready to go inside");

            check = name;
            String compareLongs[] = getLatLongPositions(check);
            System.out.println("Hey Latitude: " + compareLongs[0] + " and Longitude: " + compareLongs[1]);

            while (rs.next()) {
                check = rs.getString(1) + " London";
                String latLongs[] = getLatLongPositions(check);
                System.out.println("Latitude: " + latLongs[0] + " and Longitude: " + latLongs[1]);
                System.out.println(check);

                System.out.println("Hey Latitude: " + compareLongs[0] + " and Longitude: " + compareLongs[1]);
                double lat1 = Double.parseDouble(latLongs[0]);
                double lat2 = Double.parseDouble(compareLongs[0]);
                double lng1 = Double.parseDouble(latLongs[1]);
                double lng2 = Double.parseDouble(compareLongs[1]);

                double metres = calcDifference(lat1, lat2, lng1, lng2);
                metres = metres * 0.000621371192;
                System.out.println(metres + " metres");
                if (metres > 1000) {
                    metres = metres / 1000 * 2;
                }

                metres = (Math.floor((metres) * 100) / 100);
                System.out.println(metres + " metres");
                order.add(metres);                  // adding all the values in the arraylist ready to be sorted by the distance
                ordername.add(rs.getString(3));
                rate.add(rs.getInt(4));
                email.add(rs.getString(5));

//[7.08, 6.87, 3.75, 1000000.0, 6.87]
            }

            // rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public static double calcDifference(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);

    }

    public static String[] getLatLongPositions(String address) throws Exception {
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
            Document document = builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String) expr.evaluate(document, XPathConstants.STRING);
            if (status.equals("OK")) {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                return new String[]{latitude, longitude};
            } else if (status.equals("OVER_QUERY_LIMIT")) {

                Thread.sleep(10);
                return getLatLongPositions(address);

            } else {
                throw new Exception("Error from the API - response status: " + status);
            }
        }
        return null;
    }

    public void box1Clicked(MouseEvent event) throws IOException {

        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_tutorShow.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXML_tutorShowController setController = loader.getController();
            setController.myFunction(ordername.get(0), subject, email.get(0), studentemail, studentname);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void box2Clicked(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_tutorShow.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXML_tutorShowController setController = loader.getController();
            setController.myFunction(ordername.get(1), subject, email.get(1), studentemail, studentname);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void box3Clicked(MouseEvent event) throws IOException {
        try {

            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_tutorShow.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXML_tutorShowController setController = loader.getController();
            setController.myFunction(ordername.get(2), subject, email.get(2), studentemail, studentname);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void box4Clicked(MouseEvent event) throws IOException {
        try {

            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_tutorShow.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXML_tutorShowController setController = loader.getController();
            setController.myFunction(ordername.get(3), subject, email.get(3), studentemail, studentname);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void box5Clicked(MouseEvent event) throws IOException {
        try {

            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("FXML_tutorShow.fxml"));
            Parent homepage_parent = (Parent) loader.load();
            FXML_tutorShowController setController = loader.getController();
            setController.myFunction(ordername.get(4), subject, email.get(4), studentemail, studentname);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void backClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLStudentPage.fxml"));
        Parent homepage_parent = (Parent) loader.load();
        Scene homepage_scene = new Scene(homepage_parent);
        FXMLStudentPageController setController = loader.getController();
        System.out.println("YOUR NAME IS" + studentname);
        setController.myFunction(studentname, studentemail);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
    }

}
