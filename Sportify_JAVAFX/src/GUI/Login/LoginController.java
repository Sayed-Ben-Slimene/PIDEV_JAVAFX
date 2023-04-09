package GUI.Login;
import services.LoginSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class LoginController implements Initializable {

    private Stage stage; 
    private Scene scene;
    private Parent root;
    @FXML
    private Hyperlink signuplink;
    @FXML
    private TextField logemail;
    @FXML
    private PasswordField logpassword;
    
    @FXML
    private Label oncanceled;
    
    @FXML
    private Button oncancel;

    @FXML
    public void onLogin(ActionEvent event) throws Exception{
        
      //  if(logemail.getText().isEmpty()==false || logpassword.getText().isEmpty()==false ) {
              
            if(logemail.getText().isEmpty()){
                    oncanceled.setText("please enter your Email !");
                    
                }else if(logpassword.getText().isEmpty()){
                  oncanceled.setText("please enter your Password !");
                }else {
            oncanceled.setText("You try to Login ! ");
          // JOptionPane.showMessageDialog(null, "Enter your Email and Password !");

        }
        
        String email=logemail.getText();
        String password=logpassword.getText();
        ServiceUser sp = new ServiceUser();
        
        if((sp.login(email, password)==true)&&(email!="")&&(password!="") ){
              oncanceled.setText("Login Success ! ");
            root = FXMLLoader.load(getClass().getResource("/GUI/Home/Home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();

        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login/Login.fxml"));             
                Parent root = loader.load();
                LoginController mdc = loader.getController();
                mdc.test(logemail.getText(),logpassword.getText());
                 Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
                 
                 JOptionPane.showMessageDialog(null, "Connection Failed");
                 
        }catch(IOException ex){
            System.out.println("problem !");
        
        }
        }   
    }  

    
    public void oncancelpress(ActionEvent e){
        Stage stage =(Stage) oncancel.getScene().getWindow();
        stage.close();
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  BufferedImage i = captcha.getImage();
   //     Image ii = SwingFXUtils.toFXImage(i, null);
//        ImageView ll = new ImageView(ii);
    //    icaptcha.setImage(ii);
    }

    private void test(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
    
    }
    /*
        public void test(String mail,String pass){
        logemail.setText(mail);
        logpassword.setText(pass);
    }*/
   

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/Register/Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
                
    }  
  
    
    
    
}


