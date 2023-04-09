package GUI.Register;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class RegisterController implements Initializable {
   
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adress;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmpassword;
    @FXML
    private Label oncanceled;
    
    private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void Login(ActionEvent event) {
        if(nom.getText().isEmpty()){
                    oncanceled.setText("please enter your nom !");
                    
                }else if(prenom.getText().isEmpty()){
                  oncanceled.setText("please enter your prenom !");
                }else if(adress.getText().isEmpty()){
                    oncanceled.setText("please enter your adress !");
                }else if(tel.getText().isEmpty()){
                  oncanceled.setText("please enter your tel!");
                }else if(email.getText().isEmpty()){
                  oncanceled.setText("please enter your email !");
                }else if(password.getText().isEmpty()){
                  oncanceled.setText("please enter your password !");
                }else if(confirmpassword.getText().isEmpty()){
                  oncanceled.setText("please enter your confirmpassword !");
                }else {
            oncanceled.setText("You try to Login ! ");
          // JOptionPane.showMessageDialog(null, "Enter your Email and Password !");

        }
        
        String email2=email.getText();
        int phoneNumber = Integer.parseInt(tel.getText());
        
        ServiceUser sp = new ServiceUser();
        User user = new User(nom.getText(),prenom.getText(),adress.getText(),email2,password.getText(),"Role_USER",phoneNumber);
        sp.Ajouter2(user);
        System.out.print("user added with email : "+email.getText());
        
    }
    
        @FXML
        public void switchToLogin(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
                
    }

  
}
