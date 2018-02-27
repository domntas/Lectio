# Lectio

Document --> Login
Homepage -> Homepage
Registration -> Registration.
Every single one has its own controller where the functions are defined.


Example of how to change a page based on a click of a mouse on a button

public void registrationClicked(MouseEvent event)  throws IOException
{
        Parent homepage_parent = FXMLLoader.load(getClass().getResource("FXML_Registration.fxml"));  /
        Scene homepage_scene = new Scene(homepage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homepage_scene);
        app_stage.show();
      
}

