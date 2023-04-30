package GUI.Register;

import GUI.Login.LoginController;
import static GUI.Login.LoginController.loadWindow;
import entities.User;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Parent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ServiceNotification;
import services.ServiceUser;

import services.UserMailing;

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

    
    private boolean verificationUserName;
    private boolean verificationPrenom;
    private boolean verificationAdress;
    private boolean verificationUserPhone;

    private boolean verificationUserEmail;
    
    private boolean verificationUserpasword;
    private boolean verificationUserConfirmPassword;


     ServiceUser serviceUser = new ServiceUser();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }
//            nomCheck.setImage(new Image ("@../../Images/checkMark.png"));
    //            nomCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));


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
            labelnom.setText ("nom valide ");
            nomCheck.setImage(new Image("@../../Images/checkMark.png"));
            labelnom.setTextFill(Color.GREEN);


            verificationUserName = true;
        } else {
            labelnom.setText ("Check nom !!! ");
            labelnom.setTextFill(Color.RED);
            nomCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            verificationUserName = false;

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

            verificationPrenom =true;
            // verificationUserPrenom = true;
        } else {
            prenomCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelprenom.setText ("Check prenom !!! ");
            labelprenom.setTextFill(Color.RED);
            verificationPrenom =false;

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


             verificationAdress = true;
        } else {
            adresscheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labeladress.setText ("Check your Adress !!! ");
            labeladress.setTextFill(Color.RED);

             verificationAdress = false;

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
            System.out.println("le nompre invalide");
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
             telcheck.setImage(new Image("@../../Images/checkMark.png"));
            labeltel.setTextFill(Color.GREEN);

           verificationUserPhone = true;
        } else {
            telcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labeltel.setText("invalide number");
            labeltel.setTextFill(Color.RED);
            verificationUserPhone = false;

        }

    } else {
        labeltel.setText("Il faut 8 chiffres");
        labeltel.setTextFill(Color.RED); 
        telcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));

       // verificationUserPhone = false;
    }
}
//email = verification
    @FXML
    private void verifEmail(KeyEvent event) {

    if (serviceUser.chercherUtilisateurByEmail(email.getText().trim()) == true) {
        labelemail.setText("Email Existe déja");
        verificationUserEmail = false;
    }
    if (serviceUser.chercherUtilisateurByEmail(email.getText().trim()) == false) {//alphanumerique@alphanumerique.com
        //{ici longeur  }
        //debut ^
        //fin $
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(email.getText().trim());

        if (((Matcher) matcher).matches()) {       
            
                //if   matcher ne contient pas la format
                labelemail.setVisible(false);
                labelemail.setText("Email Format valide !");
                labelemail.setTextFill(Color.GREEN);
                emailcheck.setImage(new Image("@../../Images/checkMark.png"));
         
                verificationUserEmail = true;

        } else {
            labelemail.setVisible(true);
             emailcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));

            labelemail.setText("Email Format invalide !");
            labelemail.setTextFill(Color.RED);

             verificationUserEmail = false;

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
        if (password.getText().trim().equals(confirmpassword.getText().trim())) {
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


    public static Stage stg;

    
    
    
    //verification 1
    private boolean Signup() {
 

        if ( verificationUserName && verificationUserEmail  && verificationUserPhone
                && verificationUserpasword && verificationUserConfirmpasword && verificationAdress
                && verificationPrenom ) {

               return true;
        } else {

            return false;
        }

    }
     // tester si l email est unique 
    public Boolean CheckLogin() {
        Boolean verif = true;
        List<User> list_user = serviceUser.afficherUtilisateurs();
        
        
        for (int i = 0; i < list_user.size(); i++) {
            if (list_user.get(i).getEmail().equals(email.getText())) {
                verif = false;
                
            }
            
        }
        if (verif == false) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("User login existe déja");
            al.setHeaderText(null);
            al.show();
        }
        
        return verif;
    }

    @FXML
    private void Register(ActionEvent event) throws Exception {
                if(nom.getText().isEmpty()){
                    oncanceled.setText("please enter your nom !");
                    showAlert("please enter your nom :( ");

                }else if(prenom.getText().isEmpty()){
                  oncanceled.setText("please enter your prenom !");
                  showAlert("please enter your prenom  :( ");

                }else if(adress.getText().isEmpty()){
                    oncanceled.setText("please enter your adress !");
                                                        showAlert("please enter your adress :( ");

                }else if(tel.getText().isEmpty()){
                  oncanceled.setText("please enter your telephone !");
                                                      showAlert("please enter your telephone :( ");

                }else if(email.getText().isEmpty()){
                  oncanceled.setText("please enter your email !");
                                                      showAlert("please enter your email :( ");

                }else if ( !CheckLogin() ){
                
                         oncanceled.setText("Email existe deja !");
                                                      showAlert("Email existe deja ! :( ");
                
                
                }else if(password.getText().isEmpty()){
                  oncanceled.setText("please enter your password !");
                                                      showAlert("please enter your password :( ");

                }else if(confirmpassword.getText().isEmpty()){
                  oncanceled.setText("please enter your confirmpassword !");
                                                      showAlert("please enter your confirmpassword :( ");

                }else {
                    oncanceled.setText("You try to Login ! ");
          // JOptionPane.showMessageDialog(null, "Enter your Email and Password !");

        }
                

        if (Signup() && CheckLogin() ) {
                   ServiceUser sp = new ServiceUser();
        //sp.Ajouter2(user);
       //  User user = new User(nom.getText(),prenom.getText(),adress.getText(),email.getText(),password.getText(),"[\"ROLE_USER\"]",Integer.parseInt(tel.getText()),"active");

           User user = new User();
        
                   
            user.setEmail(email.getText());
            user.setPassword(serviceUser.encrypt(password.getText()));
            user.setRoles("[\"ROLE_USER\"]");
            user.setNom(nom.getText());          
            user.setPrenom(prenom.getText());
            
            user.setTel(Integer.parseInt(tel.getText()));
           // user.setPhoto(tf_photo.getText());           
            user.setAdress(adress.getText());                         
            user.setIsactive("active");
          
            sp.AjouterUser(user);
                  oncanceled.setText(" login Successful ");

         //   if(sp.ajouter(user)==true){
                            root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setTitle("Login");
                            stage.setScene(scene);
                            stage.show();
                          ServiceNotification.showNotif("Bienvenu", "Bienvenu dans Sportify Vous Pouvez login maintenant");

                            
         System.out.print("user added with email : "+email.getText());
/*
               UserMailing.send(
                "sportifyapp00@gmail.com",
                "Sp123456789",
                "sayedbenslimane@gmail.com",
                "Bienvenu sur Sportify",
                "Some text <b>bold part</b> ... continue");
            
                 */
                Properties p = new Properties();
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.socketFactory.port", "465");
                p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.port", "465");
                //Session
                Session s = Session.getDefaultInstance(p,
                  new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication("sportifyapp00@gmail.com", "rulrljfrzqctiqcd");
                  }
                });
                //composer le message
                try {
                  MimeMessage m = new MimeMessage(s);
                  m.addRecipient(Message.RecipientType.TO,new InternetAddress("sayedbenslimane@gmail.com"));
                  m.setSubject("Bienvenu sur Sportify");
                  m.setText("mrigel jawek behy");
                  //envoyer le message
                  Transport.send(m);
                  System.out.println("Email envoyé avec succès");
                } catch (MessagingException e) {
                  e.printStackTrace();
                }
         
        }else {
           oncanceled.setText("Veillez remplir tous les champs ! ");

            
            
            
            
        }
    
 }
    
       // tester si l email est unique 
   
    
        @FXML
        public void switchToLogin(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("/GUI/Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
                
    }

     private void showAlert(String message) 
    {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText(message);
            alert.show();
       
    }
  
}
               // int n = rand.nextInt(50);
                /*
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Test");
                alert.setHeaderText("You are succefully sing up.Please verify you email");
                alert.setResizable(false);
                alert.setContentText("Select okay or cancel this alert.");
                alert.showAndWait();
                
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("/GUI/Login/Login.fxml"));
                    Parent root1= (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Login");
                    stage.setScene(new Scene(root1));
                    stage.show();
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Account Doesn't Created");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("make sur for information");
            alert.show();
        }
*/