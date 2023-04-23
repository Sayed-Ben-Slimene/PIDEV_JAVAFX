package GUI.ForgotPassword;
import GUI.Login.LoginController;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import services.LoginSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import services.UserMailing;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class ForgotPasswordController implements Initializable {

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
    //email
    @FXML
    private ImageView emailcheck;
    @FXML
    private Label labelemail;
    //password
    @FXML
    private ImageView passwordCheck;
    @FXML
    private Label labelpassword;
    
        public int code;
    
    
    private ServiceUser myServices = new ServiceUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/Login/Login.fxml"));
            LoginController ircc = loader.getController();

            code = ircc.codem;

        } catch (Exception ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
/*
@FXML
    private void verifCode(KeyEvent event) {
    if (logemail.getText().trim().length() >= 5) {
        int nbChar = 0;
        for (int i = 1; i < logemail.getText().trim().length(); i++) {
            char ch = logemail.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
            System.out.println("le nombre Code");
        }

        if (nbChar == 0) {
            labelemail.setText("Code valide");
             emailcheck.setImage(new Image("@../../Images/checkMark.png"));
            labelemail.setTextFill(Color.GREEN);

           //verificationUserPhone = true;
        } else {
            emailcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelemail.setText("invalide Code");
            labelemail.setTextFill(Color.RED);
           // verificationUserPhone = false;

        }

    } else {
        labelemail.setText("Il faut 4 chiffres");
        labelemail.setTextFill(Color.RED); 
        emailcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));

       // verificationUserPhone = false;
    }
}*/
    @FXML
    public void resetPasswordButtonAction(ActionEvent event) throws Exception{

        //  if(logemail.getText().isEmpty()==false || logpassword.getText().isEmpty()==false ) {

             if(logemail.getText().isEmpty()){
                    labelemail.setText("please enter your Email !");
                     showAlert("please enter your email :( ");

            }else {
                    oncanceled.setText("You try to RSP ! ");
            // JOptionPane.showMessageDialog(null, "Enter your Email and Password !");


    }
    }
    
    @FXML
    private void Envoyer(ActionEvent event) throws IOException {
        
        int codex = Integer.parseInt(logemail.getText());
        ServiceUser sc = new ServiceUser();
        String x="x";
        if (logemail.getText().toString().equals(x))
        {Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!Veuillez taper le code de Verification !!!");
            alert.showAndWait();
        }
        
        else if (code == codex) {
            
            FXMLLoader loader = new FXMLLoader();
            labelemail.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("/GUI/ResetPassword/ResetPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Code incorrecte !!!");
            alert.showAndWait();
        
    }
    }

    public void oncancelpress(ActionEvent e){
        Stage stage =(Stage) oncancel.getScene().getWindow();
        stage.close();

    }

    

    private void test(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody


    }
    /*
        public void test(String mail,String pass){
        logemail.setText(mail);
        logpassword.setText(pass);
    }*/

    
    
    
     private void showAlert(String message) 
    {
                 Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Warning");
                 al.setHeaderText(null);
                 al.setContentText(message);
                 al.showAndWait();
            

       
    }
    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();

    }




}


