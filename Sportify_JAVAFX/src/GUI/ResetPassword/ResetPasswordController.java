/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.ResetPassword;

import GUI.Login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class ResetPasswordController implements Initializable {
    public static String mail="a";
    
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Hyperlink signuplink;
    @FXML
    private TextField mps;
    
    @FXML
    private TextField password;
    @FXML
    private TextField confirmpassword;
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
    
    @FXML
    private Label labelpasswordvalide;
//confirm password
    @FXML
    private ImageView confirmpasswordcheck;
    @FXML
    private Label labelconfirmpassword;
    private boolean verificationUserConfirmpasword;
        private boolean verificationUserpasword;
    private boolean verificationUserConfirmPassword;

        //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    //mot de passe
    @FXML
    private void verifMps(KeyEvent event) {

    String PAS = mps.getText().trim();

    if (PAS.length() >= 8) {// Check for Digits in password
//•	Contains at least 1 numeric digit
        labelpassword.setText("Longeur juste");
        labelpassword.setTextFill(Color.GREEN);

        verificationUserpasword = true;

        for (int i = 0; i < PAS.length(); i++) {
            char ch = PAS.charAt(i);

            if (Character.isDigit(ch)) {// Check for Digits in password
//•	Contains at least 1 numeric digit
              //labelpassword.setText("Contient un nombre");
                 containsDigit = true;
            }

            if (Character.isLetter(ch) && Character.isLowerCase(ch)) {// Check for Letters in password
//•	Contains at least 1 lower letter character
             // labelpassword.setText("lettre minuscule");
              containsLowerCaseLetter = true;

            }

            if (Character.isLetter(ch) && Character.isUpperCase(ch)) {// Check for Letters in password
//•	Contains at least 1 upper letter character
               // labelpassword.setText("Lettre majuscule");
                containsUpperCaseLetter = true;

            }
            if (ch == '{' || ch == '(' || ch == '|' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '-' ||
                    ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*') {
//•	Contains at least 1 special character from the set: !@#$%^&*
              //  labelpassword.setText("Lettre sepcial");
                containsSpecialCharacter = true;

            }
            if (containsUpperCaseLetter && containsLowerCaseLetter && containsDigit && containsSpecialCharacter) {
                labelpasswordvalide.setText("Mot de passe valide!");
                labelpasswordvalide.setTextFill(Color.GREEN);

                passwordCheck.setImage(new Image("@../../Images/checkMark.png"));
                verificationUserpasword = true;
            }

        }
    } else{
        passwordCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
        labelpassword.setText("Au moins 8 caractères");
        labelpasswordvalide.setText("Mot de passe invalide!");
        labelpasswordvalide.setTextFill(Color.RED);

        
        length = false;
        verificationUserpasword = false;
        containsSpecialCharacter = false;
        containsUpperCaseLetter = false;
        containsLowerCaseLetter = false;
        containsDigit = false;
    }

}


    @FXML
    private void ConfirmMDP(KeyEvent event) {
        if (confirmpassword.getText().trim().equals(mps.getText().trim())) {
            labelconfirmpassword.setText("Mot de passe Conformer :) ");
            labelconfirmpassword.setTextFill(Color.GREEN);
            confirmpasswordcheck.setImage(new Image("@../../Images/checkMark.png"));
            verificationUserConfirmpasword = true;
        } else { 
    
            confirmpasswordcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelconfirmpassword.setText("Verifier votre mot de passe");
            labelconfirmpassword.setTextFill(Color.RED);

            verificationUserConfirmpasword = false;
        }

    }




    
    @FXML
    private void Envoyer(ActionEvent event) throws IOException {
        if(mps.getText().isEmpty())
        {  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Champs vide !!!");
            alert.showAndWait();
        }
        else
        { String newPass = mps.getText();
        ServiceUser sc = new ServiceUser();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Login/Login.fxml"));
        LoginController ircc = loader.getController();
        mail=ircc.username;
        int id = sc.getIdbymail(ircc.username);
       
        sc.setNewMotPass(id, newPass);
        FXMLLoader loaderr = new FXMLLoader();
        mps.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loaderr.setLocation(getClass().getResource("/GUI/Login/Login.fxml"));
        Parent root = loaderr.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);

        prStage.show();
        }
    }

}
