/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.AcceuilFront;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author sayed
 */
public class AcceuilFrontController implements Initializable {
    private Stage stage; 
    private Scene scene;
    private Parent root;
    
        public Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
            @FXML
    public void switchToProfile(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/ProfileUser/ProfileUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();

    }
            @FXML
        public void logout(ActionEvent actionEvent) {
        logout.getScene().getWindow().hide();
    }
}
