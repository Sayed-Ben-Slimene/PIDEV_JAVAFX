/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.AcceuilFront.ProfileUser;

import GUI.Login.LoginController;
import GUI.Register.RegisterController;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.LoginSession;
import static services.LoginSession.image;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class ProfileUserController implements Initializable {

            public Button logout;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelemail;
    @FXML
    private Label labelpassword;
    @FXML
    private Label labeltel;    
    @FXML
    private Label labeladress;
        @FXML
    private ImageView img;
            @FXML
    private Button choisirimg;
    File file;
        ServiceUser serviceUser = new ServiceUser() ;

        private Stage stage; 
    private Scene scene;
    private Parent root;
        //image
    @FXML
    private ImageView selectedImage;
    @FXML
    private Button selectionnerimagebutton;
    
        @FXML
    private HBox pane;
    
    
    
     private File selectedImageFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

     
       
  //  ServiceUser uc = new ServiceUser() ;
      //  User u = LoginController.userc;        
        labelnom.setText(LoginSession.nom);
        labelemail.setText(LoginSession.email);
       // labelpassword.setText(LoginSession.password);
        labeladress.setText((LoginSession.adress));
        labeltel.setText(String.valueOf(LoginSession.tel));
        labelprenom.setText((LoginSession.prenom));
        
        /*
                File filex = new File(serviceUser.getPhotobyId(LoginSession.id));
            Image image = new Image(filex.toString());
            selectedImage.setImage(image);
     
     */
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
        

    
        public void switchProfileEdit(ActionEvent event) throws IOException{
        /*
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("/GUI/AcceuilFront/EditProfileUser/EditProfileUser.fxml"));
        Parent root1= (Parent) fxmlloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Profile");
        stage.setScene(new Scene(root1));
        stage.show();
        */
        
                root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/EditProfileUser/EditProfileUser.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Profile Edit");
        stage.setScene(scene);
        stage.show();
        

    }  
/*
        public void handleChooseImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image");
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
        Image selectedImage = new Image(selectedFile.toURI().toString());
        imgprof.setImage(selectedImage);
    }
}
*/
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
