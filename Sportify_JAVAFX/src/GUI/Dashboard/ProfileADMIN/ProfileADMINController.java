/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Dashboard.ProfileADMIN;

import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.LoginSession;
import static services.LoginSession.image;
import static services.LoginSession.password;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class ProfileADMINController implements Initializable {
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
    
    //image
    @FXML
    private ImageView selectedImage;
    @FXML
    private Button selectionnerimagebutton;
    
        @FXML
    private HBox pane;
    
    
    
     private File selectedImageFile;
     
     
    public void ImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg","*jpeg"));

        selectedImageFile = openFile.showOpenDialog(pane.getScene().getWindow());

        if (selectedImageFile != null) {
            selectedImage .setImage(new Image(selectedImageFile.toURI().toString(), 82, 84, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }
        
     //image   
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
          //  profileImage.setImage(imager);
          /*
        File filex = new File(serviceUser.getPhotobyId(LoginSession.id));
            Image image = new Image(filex.toString());
     selectedImage.setImage(image);
*/
    }    
    
    @FXML
    private void modifiermenu(ActionEvent event) 
    {
          
        //User user = new User();
        
        String nom = labelnom.getText();
        String email = labelemail.getText();
        int tel = Integer.valueOf(labeltel.getText());        
        String prenom = labelprenom.getText();
        String adress = labeladress.getText();

      //  boolean prenom = labelprenom.isSelected();
       // String image = modifierimageMenu.getText();
        
 
       
       
        
       // System.out.println(ProfileADMINController.user.getId());
        /*
        String imagePath = selectedImageFile.toString();
        
        
              User user = new User(nom,prenom,adress,email,tel,imagePath);

        serviceUser.modifierUtilisateur(user,LoginSession.id);
        */
                         Notifications notificationBuilder = Notifications.create()
                 .title("succès de modification")
                 .text("le user " +nom+ "a été modifié avec succès !!")
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.CENTER)
                 .graphic(null)
                 .darkStyle();
             notificationBuilder.showInformation();
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/GUI/Dashboard/Dashboard.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreusdddddddddddddddddr\n");

        }
                
  
        
        
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
          
        
        
        
       // user.setImage(image.getText());

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
