/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.AcceuilFront.EditProfileUser;

import GUI.Register.RegisterController;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.LoginSession;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class EditProfileUserController implements Initializable {
            public Button logout;
    @FXML
    private TextField labelnom;
    @FXML
    private TextField labelprenom;
    @FXML
    private TextField labelemail;
    @FXML
    private TextField labelpassword;
    @FXML
    private TextField labeltel;    
    @FXML
    private TextField labeladress;
    
        private Stage stage; 
    private Scene scene;
    private Parent root;
    
            ServiceUser serviceUser = new ServiceUser() ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        labelnom.setText(LoginSession.nom);
        labelemail.setText(LoginSession.email);
     //   labelpassword.setText(LoginSession.password);
        labeladress.setText((LoginSession.adress));
        labeltel.setText(String.valueOf(LoginSession.tel));
        labelprenom.setText((LoginSession.prenom));    
    
    
    }    
            @FXML
    public void logout(ActionEvent event) throws IOException{
        serviceUser.logout();
        root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();    
    }        
        
        public void editprofile(ActionEvent event) throws IOException{

        User user = new User(labelnom.getText(),labelemail.getText(),labeladress.getText(),labelemail.getText(),labelpassword.getText(),"Role_USER",Integer.parseInt(labeltel.getText()),"active");

     //    ServiceUser.modifier(LoginSession.email,user);
        LoginSession.nom=labelnom.getText();
     //   LoginSession.password=labelpassword.getText();
        LoginSession.email=labelemail.getText();
        LoginSession.tel = Integer.parseInt(labeltel.getText());             
        LoginSession.adress=labeladress.getText();
        LoginSession.prenom=labelprenom.getText();

                                    root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/ProfileUser/ProfileUser.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setTitle("Profile");
                            stage.setScene(scene);
                            stage.show();
        
        
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
    
}
