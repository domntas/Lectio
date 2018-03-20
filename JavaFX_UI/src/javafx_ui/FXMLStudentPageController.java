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
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
import org.w3c.dom.Text;

/**
 * FXML Controller class
 *
 * @author filippopiggici
 */
public class FXMLStudentPageController implements Initializable {

    @FXML
    private Label nametutor111;

    @FXML
    private Label distance1;

    @FXML
    private Label subject1;

    @FXML
    private Label rate1;

    @FXML
    private Label subject11;

    @FXML
    private Label nametutor11;

    @FXML
    private Label welcome_label;

    @FXML
    private Label subject111;

    @FXML
    private Label distance11;

    @FXML
    private Label rate111;

    @FXML
    private Label nametutor1;

    @FXML
    private Label distance111;

    @FXML
    private Label rate11;

    @FXML
    private TextField tutorsearch;

    @FXML
    private AnchorPane tutorbox1;

    @FXML
    private AnchorPane tutorbox11;

    @FXML
    private AnchorPane tutorbox111;

    private String studentname;
    
    private String studentemail;

    private ArrayList<Double> order = new ArrayList<Double>();      //distances in miles

    private ArrayList<String> ordername = new ArrayList<String>(); //fullnames

    private ArrayList<String> subject = new ArrayList<String>();    //subjects

    private ArrayList<Integer> rate = new ArrayList<Integer>();    //rate

    private ArrayList<String> email = new ArrayList<String>();    //rate

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void myFunction(String text, String email) {
        System.out.println("hellooo");
        String firstName[] = text.split(" ");
        welcome_label.setText("Hello, " + firstName[0]);
        studentname = text;
        studentemail = email;
        System.out.println(welcome_label.getText());
        Distance();
        setEverything();
    }

    public void setEverything() {
        System.out.println("YOU ARE INSIDE SET EVERYTHING");
        System.out.println("whyyyyy" + (String) ordername.get(1));
        sortAll();
        System.out.println(order);
        nametutor1.setText((String) ordername.get(0));
        nametutor11.setText((String) ordername.get(1));
        nametutor111.setText((String) ordername.get(2));
        distance1.setText(String.valueOf(order.get(0)) + " miles");
        distance11.setText(String.valueOf(order.get(1)) + " miles");
        distance111.setText(String.valueOf(order.get(2)) + " miles");
        subject1.setText((String) subject.get(0));
        subject11.setText((String) subject.get(1));
        subject111.setText((String) subject.get(2));
        rate1.setText(String.valueOf(rate.get(0)) +"£/h");
        rate11.setText(String.valueOf(rate.get(1))+"£/h");
        rate111.setText(String.valueOf(rate.get(2))+"£/h");
        ///we miis the rate here
    }

    public void sortAll() {
        System.out.println(order);
        int z = order.size();
        while (z > 1) {
            for (int x = 0; x < z - 2; x++) {
                for (int y = 1; y < z - 1; y++) {
                    if (order.get(x) > order.get(y)) {
                        Collections.swap(order, x, y);
                        Collections.swap(ordername, x, y);
                        Collections.swap(subject, x, y);
                        Collections.swap(rate, x, y);
                        Collections.swap(email, x, y);
                    }

                }
            }
            z--;
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
            String name = rs.getString(1);
            System.out.println(name);
            rs.close();

            rs = stmt.executeQuery("SELECT BOROUGH,SUBJECT, fullname, price,email FROM TUTOR,users where  users.id=tutor.id");
            String check = "";

            System.out.println("ready to go inside");

            check = name;
            String compareLongs[] = getLatLongPositions(check);
            System.out.println("Hey Latitude: " + compareLongs[0] + " and Longitude: " + compareLongs[1]);

            while (rs.next()) {
                check = rs.getString(1);
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
                if (metres > 1000) {
                    metres = metres / 1000 * 2;
                }
                metres = (Math.floor((metres) * 100) / 100);
                System.out.println(metres + " metres");
                order.add(metres);                  // adding all the values in the arraylist ready to be sorted by the distance
                subject.add(rs.getString(2));
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
            setController.myFunction(ordername.get(0), subject.get(0), email.get(0), studentemail);
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
            setController.myFunction(ordername.get(1), subject.get(1),email.get(1), studentemail);
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
            setController.myFunction(ordername.get(2), subject.get(2),email.get(2), studentemail);
            Scene homepage_scene = new Scene(homepage_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(homepage_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
