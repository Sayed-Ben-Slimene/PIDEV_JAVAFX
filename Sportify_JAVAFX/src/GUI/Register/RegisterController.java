package GUI.Register;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//nom
    @FXML
    private ImageView nomCheck;
    @FXML
    private Label labelnom;
//prenom
    @FXML
    private ImageView prenomCheck;
    @FXML
    private Label labelprenom;
//adress
    @FXML
    private ImageView adresscheck;
    @FXML
    private Label labeladress;
//telephone
    @FXML
    private ImageView telcheck;
    @FXML
    private Label labeltel;
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


    //les verfication de la mot de passe
    boolean containsDigit = false;
    boolean containsLowerCaseLetter = false;
    boolean containsUpperCaseLetter = false;
    boolean containsSpecialCharacter = false;
    boolean length = false;
    private boolean verificationUserpasword;


    private ServiceUser myServices = new ServiceUser();

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
    private void verifNom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nom.getText().trim().length() >=3) {
            nomCheck.setImage(new Image ("@../../Images/checkMark.png"));
            labelnom.setText ("nom valide ");
            labelnom.setTextFill(Color.GREEN);


            // verificationUserNom = true;
        } else {
            nomCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelnom.setText ("Check nom !!! ");

//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
           // verificationUserNom = false;

        }
    }
//prenom
    @FXML
    private void verifPrenom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < prenom.getText().trim().length(); i++) {
            char ch = prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom.getText().trim().length() >=3) {
            prenomCheck.setImage(new Image("@../../Images/checkMark.png"));
            labelprenom.setText ("prennom valide ");
            labelprenom.setTextFill(Color.GREEN);


            // verificationUserPrenom = true;
        } else {
            prenomCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelprenom.setText ("Check prenom !!! ");

//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
           // verificationUserPrenom = false;

        }
    }
//adresse
    @FXML
    private void verifAdress(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < adress.getText().trim().length(); i++) {
            char ch = adress.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && adress.getText().trim().length() >=3) {
            adresscheck.setImage(new Image ("@../../Images/checkMark.png"));
            labeladress.setText ("Adress valide ");
            labeladress.setTextFill(Color.GREEN);


            // verificationUserNom = true;
        } else {
            adresscheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labeladress.setText ("Check your Adress !!! ");

//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            // verificationUserNom = false;

        }
    }
//telephone
    @FXML
    private void veriftel(KeyEvent event) {
    if (tel.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < tel.getText().trim().length(); i++) {
            char ch = tel.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
            System.out.println(nbChar);
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
            labeltel.setTextFill(Color.GREEN);

            telcheck.setImage(new Image("@../../Images/checkMark.png"));
          //  verificationUserPhone = true;
        } else {telcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labeltel.setText("invalide number \n"
                    + " Il exist des char");
           // verificationUserPhone = false;

        }

    } else {telcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
        labeltel.setText("Il faut 8 chiffres");
       // verificationUserPhone = false;
    }
}
//email = verification
    @FXML
    private void verifEmail(KeyEvent event) {

    if (myServices.chercherUtilisateurByEmail(email.getText().trim()) == true) {
        labelemail.setText("Email Existe déja");
       // verificationUserEmail = false;
    }
    if (myServices.chercherUtilisateurByEmail(email.getText().trim()) == false) {//alphanumerique@alphanumerique.com
        //{ici longeur  }
        //debut ^
        //fin $
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(email.getText().trim());

        if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
            labelemail.setVisible(false);
            labelemail.setText("Email valide !");
            labelemail.setTextFill(Color.GREEN);

            emailcheck.setImage(new Image("@../../Images/checkMark.png"));
           // verificationUserEmail = true;

        } else {
            labelemail.setVisible(true);
            emailcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelemail.setText("Email Format invalide !");
            // JOptionPane.showMessageDialog(null, "Email Format invalide");
            //verificationUserEmail = false;

        }
    }

}
//mot de passe
    @FXML
    private void verifMps(KeyEvent event) {

    String PAS = password.getText().trim();

    if (PAS.length() >= 8) {// Check for Digits in password
//•	Contains at least 1 numeric digit
        labelpassword.setText("Longeur juste");
        labelpassword.setTextFill(Color.GREEN);

        //verificationUserConfirmpasword = true;

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
        if (password.getText().trim().equals(confirmpassword.getText().trim())) {
            labelconfirmpassword.setText("Mot de passe Conformer :) ");
            labelconfirmpassword.setTextFill(Color.GREEN);

            labelpassword.setText("");
            confirmpasswordcheck.setImage(new Image("@../../Images/checkMark.png"));
            verificationUserConfirmpasword = true;
        } else {  confirmpasswordcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelconfirmpassword.setText("Verifier votre mot de passe");
            verificationUserConfirmpasword = false;
        }

    }


    public static Stage stg;










    @FXML
    private void Login(ActionEvent event) throws IOException {
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

         root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
        
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
