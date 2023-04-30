package GUI.Login;
import GUI.ResetPassword.ResetPasswordController;
import entities.User;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import services.LoginSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

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
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import services.ServiceUser;
import nl.captcha.Captcha;
import static services.LoginSession.password;
import services.ServiceNotification;
import static services.ServiceUser.user;
import services.UserMailing;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class LoginController implements Initializable {

    private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
   Captcha captcha = new Captcha.Builder(200, 50)
           .addText()
           .addBackground()
           .addNoise()
           .addBorder()
           .build();
        @FXML
    private TextField tcaptcha;
    @FXML
    private ImageView icaptcha;
    
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
    
        private boolean verificationUserEmail;
    
    private boolean verificationUserpasword;
        public static String username;
    public static String motpass;
    
    public static int  codem;
      //  public static User userc;
    private ServiceUser myServices = new ServiceUser();

                ServiceUser ps = new ServiceUser();
//            User userc = ps.findUserByLogin(logemail.getText(),logpassword.getText());
            
        @Override
    public void initialize(URL url, ResourceBundle rb) {

        
       BufferedImage i = captcha.getImage();
        Image ii = SwingFXUtils.toFXImage(i, null);
        ImageView ll = new ImageView(ii);
        icaptcha.setImage(ii);
        
        
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/ResetPassword/ResetPassword.fxml"));
        ResetPasswordController ircc = loader.getController();
        String s="a";
        if (!ircc.mail.equals(s)){
            logemail.setText(ircc.mail);
        }
        
    }

    private void test(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
    
    }
    
    @FXML
    private void verifEmail(KeyEvent event) {

//        if (myServices.chercherUtilisateurByEmail(logemail.getText().trim()) == true) {
//            labelemail.setText("Email Existe déja");
//            // verificationUserEmail = false;
//        }
//        if (myServices.chercherUtilisateurByEmail(logemail.getText().trim()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(logemail.getText().trim());

            if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
                //Label labelemail = new Label("Email Format valide :)");
                labelemail.setText("Email Format valide !");
                labelemail.setTextFill(Color.GREEN);
                emailcheck.setImage(new Image("@../../Images/checkMark.png"));
                 verificationUserEmail = true;

            } else {
                labelemail.setVisible(true);
                emailcheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
                labelemail.setText("Email Format invalide !");
                labelemail.setTextFill(Color.RED);
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    //mot de passe
    @FXML
    private void verifMps(KeyEvent event) {

        String PAS = logpassword.getText().trim();

        if (PAS.length() >= 8) {// Check for Digits in password
//•	Contains at least 1 numeric digit
            labelpassword.setText("Longeur juste :)");
            labelpassword.setTextFill(Color.GREEN);
            passwordCheck.setImage(new Image("@../../Images/checkMark.png"));
                    verificationUserpasword = true;


        } else{
            passwordCheck.setImage(new Image("@../../Images/erreurCheckMark.png"));
            labelpassword.setText("Au moins 8 caractères !");
         //   labelpassword.setText("Mot de passe invalide!");
                 verificationUserpasword = false;


        }

    }

    
    
        //verification 1
    private boolean login() {
 
        if ( verificationUserEmail && verificationUserpasword ) {
               return true;
        } else {

            return false;
        }

    }
    
    
 @FXML
    public void onLogin(ActionEvent event) throws Exception {
        
    if (logemail.getText().isEmpty()) {
        oncanceled.setText("Please enter your Email!");
                    oncanceled.setTextFill(Color.RED);

        showAlert("Please enter your email :(");
    } else if (logpassword.getText().isEmpty()) {
        oncanceled.setText("Please enter your Password!");
                            oncanceled.setTextFill(Color.RED);

        showAlert("Please enter your password :(");
    } else if (!captcha.isCorrect(tcaptcha.getText()) || tcaptcha.getText().isEmpty()) {
        oncanceled.setText("Please enter the CAPTCHA code!");
                            oncanceled.setTextFill(Color.RED);

        showAlert("Please enter the CAPTCHA code :(");
    } else {

        String email = logemail.getText();
        String password = logpassword.getText();
        ServiceUser sp = new ServiceUser();
        String encPass = sp.encrypt(password);
        if (sp.login(email, encPass) &&(captcha.isCorrect(tcaptcha.getText()))) {

            System.out.println(encPass);
                if (LoginSession.isactive.equals("desactive")) {
                            oncanceled.setText("Votre compte est suspendu !");
                            oncanceled.setTextFill(Color.RED);
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Alert");
                al.setContentText("Votre compte est suspendu. Veuillez contacter l'administrateur.");
                al.setHeaderText(null);
                al.show();
            } else {
                    
            
            ServiceNotification.showNotif("Felicitaion ", "Vous Avez Login  avec sucées");
            oncanceled.setText("Login Successful!");
            oncanceled.setTextFill(Color.GREEN);
                 System.out.println(LoginSession.roles);
    
                switch (LoginSession.roles) {
                    case "[\"ROLE_USER\"]":
                        // go to user
                        root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/AcceuilFront.fxml"));
                        break;
                    case "[\"ROLE_ORGANISATEUR\"]":
                        // go to admin
                        root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard/Dashboard.fxml"));
                        break;
                     case "[\"ROLE_ADMIN\"]":
                        // go to admin
                        root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard/Dashboard.fxml"));
                        break;
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Welcome");
                stage.setScene(scene);
                stage.show();
            }
        } else {
            oncanceled.setText("Failed to Login!");
                                oncanceled.setTextFill(Color.RED);

            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Invalid email or password.");
            al.setHeaderText(null);
            al.show();
        }
    }
}

    
    /*
    @FXML
    public void onLogin(ActionEvent event) throws Exception{
        
      //  if(logemail.getText().isEmpty()==false || logpassword.getText().isEmpty()==false ) {
               if(logemail.getText().isEmpty()){
                        oncanceled.setText("please enter your Email !");
                        showAlert("please enter your email :( ");

                }else if(logpassword.getText().isEmpty()){
                        oncanceled.setText("please enter your Password !");
                        showAlert("please enter your password :( ");
                } else if((!captcha.isCorrect(tcaptcha.getText())) || tcaptcha.getText().isEmpty() ){
                          oncanceled.setText("Please enter Verif the CAPTCHA  !");
                        showAlert("¨Please enter Verif the CAPTCHA :( ");
                } else if (login()){
                    
                        oncanceled.setText("You try to Login ! ");
                        String email=logemail.getText();
                        String password=logpassword.getText();
                        ServiceUser sp = new ServiceUser();
                        
                         String encPass = sp.encrypt(logpassword.getText());

                        if((sp.login(email, encPass)==true)&&(email!="")&&(password!="")&&(captcha.isCorrect(tcaptcha.getText())))
                        {
                        ServiceNotification.showNotif("Felicitaion ", "Vous Avez Login  avec sucées");
                                    oncanceled.setText("Login Successful! ");
                                    oncanceled.setTextFill(Color.GREEN);
System.out.println(encPass);
                        if (null != user.getRoles()) {
                                        if (user.getIsactive().equals("desactive")) {
                                            Alert al = new Alert(Alert.AlertType.ERROR);
                                            al.setTitle("Alert");
                                            al.setContentText("Votre account est suspendue, Veuillez contacter l admininstrateur");
                                            al.setHeaderText(null);
                                            al.show();
                                        } else {
                                            switch (user.getRoles()) {
                                                case "[\"ROLE_USER\"]":
                                                    // go to user
                                root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/AcceuilFrontController/AcceuilFrontController.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setTitle("Sign Up");
                                stage.setScene(scene);
                                stage.show();                            
                                System.out.println("user");
                                                    break;
                                                     case "[\"ROLE_ADMIN\"]":
                                                    // go to admin
                                root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard/Dashboard.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setTitle("Sign Up");
                                stage.setScene(scene);
                                stage.show(); 
                                                    System.out.println("admin");
                                                    break;

                                                default:
                                                    break;
                                            }
                                        }
                                    }

                                    } else {
                                        oncanceled.setText("Failed to Login ! ");

                                        Alert al = new Alert(Alert.AlertType.ERROR);
                                        al.setTitle("Alert");
                                        al.setContentText("invalid login or mot de passe");
                                        al.setHeaderText(null);
                                        al.show();

                                    }

                                            }else {         
                                        Alert al = new Alert(Alert.AlertType.ERROR);
                                        al.setHeaderText(null);
                                        al.setContentText("Veuillez remplir les champs vides ! ");
                                        al.showAndWait();

                                    }
    
    }  
*/
        @FXML
    private void refCaptcha(MouseEvent event) {
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login/Login.fxml"));
                 Parent root = loader.load();
                 LoginController mdc = loader.getController();
                 mdc.test(logemail.getText(),logpassword.getText());
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public void oncancelpress(ActionEvent e){
        Stage stage =(Stage) oncancel.getScene().getWindow();
        stage.close();
        
    }

     private void showAlert(String message) 
    {
                 Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Warning");
                 al.setHeaderText(null);
                 al.setContentText(message);
                 al.showAndWait();
            

       
    }
    
      public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
             Scene scene = new Scene(parent);
        
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }  

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/Register/Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
                
    }  
  
    /*
        @FXML
    public void switchToForgot(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/ForgotPassword/ForgotPassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ForgotPass");
        stage.setScene(scene);
        stage.show();
                
    }  */
    /*
          @FXML
    private void switchToForgot(ActionEvent event) {
         oncanceled.getScene().getWindow().hide();
            loadWindow(getClass().getResource("/GUI/ForgotPassword/ForgotPassword.fxml"), "ForgotPass", null);
  
            
    }*/
    
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    @FXML
    private void MotpassOubliee(ActionEvent event) throws MessagingException, IOException {
        username = logemail.getText();
        ServiceUser sc = new ServiceUser();
   		
        Random r = new Random ();
      codem =r.nextInt(9999-1000+1);
                System.out.println(codem);
                //sc.setCodepass(sc.getIdbymail(t1.getText()), codem);

        //System.out.println(sc.getPassbyId(sc.getIdbymail(t1.getText())));
        if(isValidEmailAddress(logemail.getText())){
   //     SendMail.send(t1.getText(), sc.getPassbyId(sc.getIdbymail(t1.getText())));
            UserMailing.send(logemail.getText(), codem);
            FXMLLoader loader = new FXMLLoader();
        oncanceled.getScene().getWindow().hide();
        Stage prStage = new Stage();
      oncanceled.getScene().getWindow().hide();
          //  loadWindow(getClass().getResource("/GUI/ForgotPassword/ForgotPassword.fxml"), "ForgotPass", null);
             loader.setLocation(getClass().getResource("/GUI/ForgotPassword/ForgotPassword.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Adresse Email Non Valide !!!");
            alert.showAndWait();
        }
       
    }
   
        // check if the user is banned
   public Boolean Active_account() {
        Boolean verif = true;
        List<User> list_user = myServices.afficherUtilisateurs();
        for (int i = 0; i < list_user.size(); i++) {
            if (list_user.get(i).getEmail().equals(logemail.getText())) {
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

    
}


