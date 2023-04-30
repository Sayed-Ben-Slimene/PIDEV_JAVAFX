/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Dashboard;

import GUI.Dashboard.UsersDash.ShowUsersController;
import entities.User;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.LoginSession;
import services.ServiceUser;
import services.UserMailing;
import services.ServiceNotification;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class DashboardController implements Initializable {


    @FXML
    private TableColumn<User, String> culumnUsernom;
    @FXML
    private TableColumn<User, String> culumnUserId;
    @FXML
    private TableColumn<User, String> culumnUserPrenom;
    @FXML
    private TableColumn<User, String> culumnUserEmail;
    @FXML
    private TableColumn<User, String> culumnUserPassword;
    @FXML
    private TableColumn<User, String> culumnUserAdress;
    @FXML
    private TableColumn<User, String> culumnUserRoles;
        @FXML
    private TableColumn<User, String> culumnUserIsactive;
    @FXML
    private TableColumn<User, Integer> culumnUserTel;
    @FXML
    private TableView<User> usersTable;
    
    
    //ObservableList<User> listUsers;

   // ObservableList<User>  listUsers = FXCollections.observableArrayList();

  //  ObservableList<User> listUsers;
    
    ServiceUser serviceUser =new ServiceUser();

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_roles;

        @FXML
    private Label nom;
    @FXML
    private Label prenom;
    
    
        private Stage stage; 
    private Scene scene;
    private Parent root;
        @FXML
    private ChoiceBox<String> ChoiceBox;
    @FXML
    private TextField setPromptText;
    
    
        @FXML
    private Button handleImageViewClick;
            @FXML
    private Button oncancel;
    

    ServiceUser su = new ServiceUser();
    int index =-1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
        prenom.setText(LoginSession.prenom +" "+ LoginSession.nom);
       
        
        tf_nom.setText(LoginSession.nom);
        tf_prenom.setText(LoginSession.prenom);
        tf_roles.setText(LoginSession.roles);
        tf_adresse.setText(LoginSession.adress);
        tf_tel.setText(String.valueOf(LoginSession.tel));
                tf_email.setText(LoginSession.email);
           // tf_tel.setText(String.valueOf(LoginSession.tel));

    /*
        listUsers= serviceUser.afficher();
        culumnUserId.setCellValueFactory(new PropertyValueFactory<User,String> ("id"));
        culumnUsernom.setCellValueFactory(new PropertyValueFactory<User,String> ("nom"));
        culumnUserPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        culumnUserEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        culumnUserPassword.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        culumnUserRoles.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
        culumnUserTel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
        culumnUserAdress.setCellValueFactory(new PropertyValueFactory<User,String>("adress"));

        usersTable.setItems(listUsers);
*/
        
        
//        usersTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    try {
//                        selectionnerunuser(newValue);
//                    } catch (MalformedURLException ex) {
//                        Logger.getLogger(ShowUsersController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });

        ChoiceBox.getItems().addAll("Nom", "Email", "Adress", "Prenom","Roles","isactive");
        ChoiceBox.setValue("Nom");
        setPromptText.setPromptText("Rechercher ");


        afficheTableView();
        filteredSearch();
        
        
        
        
    }
    
    
    /*
    
@FXML
    public void modifierUtilisateur() throws IOException {

        User user = usersTable.getSelectionModel().getSelectedItem();

        if (user != null) {

            boolean buttonConfimClicked = showAjouterPersonneFXML(user);//cette qui va ouvrir cette page (dmender cete methode) 
            if (buttonConfimClicked) {

                myServices.modifierUtilisateurs(user);
                ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getEmail() + " avec sucées");
                afficheTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("S'il vous plait selectionner un User");
            alert.show();
        }

    }
*/
private void afficheTableView() {
    int id;
    List<User> users = su.afficherUtilisateurs();

    ObservableList<User> list = FXCollections.observableArrayList(users);
    culumnUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
    culumnUsernom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    culumnUserPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    culumnUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    culumnUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    culumnUserTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    culumnUserAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
    culumnUserIsactive.setCellValueFactory(new PropertyValueFactory<>("isactive"));
    
    
    
    culumnUserRoles.setCellValueFactory(new PropertyValueFactory<>("roles"));
            /**
         * *********************************************************
         */
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactoryRoles
                = //
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            if (item.equals("[\"ROLE_USER\"]")) {
                                setText("USER");

                            }
                            if (item.equals("[\"ROLE_ORGANISATEUR\"]")) {
                                setText("ORGANISATEUR");
                            }
                                                        if (item.equals("[\"ROLE_ADMIN\"]")) {
                                setText("ADMIN");
                            }
                        }
                    }
                };
                return cell;
            }
        };

        culumnUserRoles.setCellFactory(cellFactoryRoles);
        /**
         * *********************************************************
         */

    usersTable.setItems(list);

    usersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            tf_nom.setText(newValue.getNom());
            tf_prenom.setText(newValue.getPrenom());
          
            tf_adresse.setText(newValue.getAdress());
            tf_email.setText(newValue.getEmail());

            tf_tel.setText(String.valueOf(newValue.getTel()));  
            
            
            
            tf_roles.setText(newValue.getRoles());
        
        
        
        
        }
    });
}

public void filteredSearch() {

        List<User> list_user = su.afficherUtilisateurs();
        ObservableList<User> list = FXCollections.observableArrayList(list_user);
        FilteredList<User> fluser = new FilteredList(list, p -> true);
        setPromptText.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (ChoiceBox.getValue()) {
                case "Nom":
                    fluser.setPredicate(p -> p.getNom().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Email":
                    fluser.setPredicate(p -> p.getEmail().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Adress":
                    fluser.setPredicate(p -> p.getAdress().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Prenom":
                    fluser.setPredicate(p -> p.getPrenom().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;            
                 case "Roles":
                    fluser.setPredicate(p -> p.getRoles().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                 case "isactive":
                    fluser.setPredicate(p -> p.getIsactive().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;            
            }

        });
        usersTable.setItems(fluser);
        usersTable.refresh(); // Refresh the TableView after setting the data

        
        
        ChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                setPromptText.setText("");
            }
        });

    }
    
@FXML
    private void changeToUSER(ActionEvent event) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Warning");
            al.setContentText("Selctionner un ustilisateur");
            //ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getEmail() + " avec sucées");

            al.setHeaderText(null);
            al.show();
        } if (user.getRoles().equals("[\"ROLE_ORGANISATEUR\"]")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sure de Changer le Roles de ORGANISATEUR To USER ");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                su.RolesUserToUSER(user.getId());
             
                try {               
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
                  m.setSubject("[ Sportify ]  change Role a USER de Votre compte");
                  m.setText("Votre compte a  change Role a USER Vous pouvez vous connecter");
                  ServiceNotification.showNotif("Felicitaion ", "Vous Avez RolesUserToUSER ce membre" + "" + user.getEmail() + " avec sucées");

                  //envoyer le message
                  Transport.send(m);
                  System.out.println("Email modifier Activity envoyé avec succès");
                  
                } catch (MessagingException e) {
                  e.printStackTrace();
                }
                    
                    
                    
                                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                
                
              
                   
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("L Modification to USER a été effecuter avec sucess. Un mail a été envoye a " + user.getNom());
                
                resAlert.showAndWait();
            } else {

                alert.close();
            }

        }else {
                        Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("User " +user.getEmail()+ "  est Deja Simple User");
                resAlert.showAndWait();
        
        }

            afficheTableView();
    usersTable.refresh(); // Refresh the TableView after setting the data

    }

@FXML
private void changeToORGANISATEUR(ActionEvent event) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Warning");
            al.setContentText("Selctionner un ustilisateur");
            //ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getEmail() + " avec sucées");

            al.setHeaderText(null);
            al.show();
        } if (user.getRoles().equals("[\"ROLE_USER\"]")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sure de Changer le Roles de USER To ORGANISATEUR ");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                su.RolesUserToORGANISATEUR(user.getId());
             
                try {
                    
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
                  m.setSubject("[ Sportify ]  Changer Votre Compte to ORGANISATEUR");
                                    ServiceNotification.showNotif("Felicitaion ", "Changer le Compte to ORGANISATEUR" + "" + user.getEmail() + " avec sucées");

                  m.setText("Votre compte a été Changer to ORGANISATEUR \n Contacter l administrateur pour plus d information");
                  //envoyer le message
                  Transport.send(m);
                  System.out.println("Email modif RolesUserToAdmin envoyé avec succès");
                  
                } catch (MessagingException e) {
                  e.printStackTrace();
                }
                    
                    
                    
                                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("L Modification to ORGANISATEUR a été effecuter avec sucess. Un mail a été envoye a " + user.getEmail());
                resAlert.showAndWait();
            } else {

                alert.close();
            }

        }else {
                        Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("User " +user.getEmail()+ "  est Deja un Organisateur");
                resAlert.showAndWait();      
        }

            afficheTableView();
    usersTable.refresh(); // Refresh the TableView after setting the data

    }











    @FXML
    private void DesactiverUser(ActionEvent event) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Warning");
            al.setContentText("Selctionner un ustilisateur");

            al.setHeaderText(null);
            al.show();
        } else if (user.getIsactive().equals("active")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sure de votre supspension ");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                su.DescativerUser(user.getId());
             
                try {
                    
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
                  m.setSubject("[ Sportify ]  Desactivation de Votre compte");
                                    ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getEmail() + " avec sucées");

                  m.setText("Votre compte a ete desactivee.\n contacter l administrateur pour plus d information");
                  //envoyer le message
                  Transport.send(m);
                  System.out.println("Email modif Activity envoyé avec succès");
                  
                } catch (MessagingException e) {
                  e.printStackTrace();
                }

                                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("La suspension a été effecuter avec sucess. Un mail a ete envoye a " + user.getEmail());
                resAlert.showAndWait();
            } else {

                alert.close();
            }

        } else {
                        Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("User " +user.getEmail()+ "  est Deja Désactiver");
                resAlert.showAndWait();
        
        }


            afficheTableView();
    usersTable.refresh(); // Refresh the TableView after setting the data

    }
    
        @FXML
    private void ActiverUser(ActionEvent event) throws IOException {
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Warning");
            al.setContentText("Selctionner un ustilisateur");

            al.setHeaderText(null);
            al.show();
        } if (user.getIsactive().equals("desactive")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sure de votre activation ");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                su.ActiverUser(user.getId());
             
                try {               
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
                  m.setSubject("[ Sportify ]  Activation de Votre compte");
                  m.setText("Votre compte a ete reactivee.\n Vous pouvez vous connecter");
                  ServiceNotification.showNotif("Felicitaion ", "Vous Avez modifier ce membre" + "" + user.getEmail() + " avec sucées");

                  //envoyer le message
                  Transport.send(m);
                  System.out.println("Email modif Activity envoyé avec succès");
                  
                } catch (MessagingException e) {
                  e.printStackTrace();
                }
                    
                    
                    
                                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                
                
              
                   
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("L activation a été effecuter avec sucess. Un mail a été envoye a " + user.getNom());
                
                resAlert.showAndWait();
            } else {

                alert.close();
            }

        }else{
        
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("User " +user.getEmail()+ " est Deja Activer");
                resAlert.showAndWait();
        
        }


            afficheTableView();
    usersTable.refresh(); // Refresh the TableView after setting the data

    }

        @FXML
    private void suppuser(ActionEvent event) 
    {

        User user= new User();
            user=usersTable.getSelectionModel().getSelectedItem();
        
        int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            User E = usersTable.getSelectionModel().getSelectedItem();
            serviceUser.supprimer(E.getId());
            usersTable.getItems().remove(selectedIndex);
        } else {
            showAlert("Veuillez sélectionner un user à supprimer.");
        }
         
    }

    private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    @FXML
    private void btn_modifier(ActionEvent event) {
        User user = new User();
      //  if (VerifUserChamps() && validateNumberCIN()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sur de votre modification");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {


                            
                user.setNom(tf_nom.getText());
                user.setPrenom(tf_prenom.getText());
                user.setTel(Integer.parseInt(tf_tel.getText()));
                user.setRoles(tf_roles.getText());
                user.setAdress(tf_adresse.getText());
                user.setEmail(tf_email.getText());

                // user.setImage(mewImagePath);
                su.modifierUtilisateur(user, LoginSession.id);

                // String path = "Users/ahmed ah,ed/Desktop/ahmed/src/Ressources/Image/" + mewImagePath;
                // image.setImage(new Image("file:/" + path, 193, 200, false, false));
                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("La modification a été effectuer avec succes");
                resAlert.showAndWait();
            } else {
                // mewImagePath = userConn.getImage();
                //String path = "Users/ahmed ahmde/Desktop/ahmed/src/Ressources/Image/" + mewImagePath;
                user.setNom(tf_nom.getText());
                user.setPrenom(tf_prenom.getText());
                user.setTel(Integer.parseInt(tf_tel.getText()));
                user.setRoles(tf_roles.getText());
                user.setAdress(tf_adresse.getText());
                user.setEmail(tf_email.getText());
                alert.close();
            }

       
            afficheTableView();
    usersTable.refresh(); 

    }
    
    @FXML
    public void handleImageViewClick(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard/Dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
                
    }  
       public void oncancelpress(ActionEvent e){
        Stage stage =(Stage) oncancel.getScene().getWindow();
        stage.close();
        
    }
       
       
           @FXML
    public void GoToProfile(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/Dashboard/ProfileADMIN/ProfileADMIN.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
                
    }
    
               @FXML
    public void GoToHome(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/GUI/AcceuilFront/AcceuilFront.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
                
    }
    
    
    @FXML
    private void exportToCsv(ActionEvent event) {
               FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File outputFile = fileChooser.showSaveDialog(usersTable.getScene().getWindow());
        
        if (outputFile != null) {
            try {
                // Open a FileWriter for the output file
                FileWriter writer = new FileWriter(outputFile);
                
                // Write the header row
                writer.write("Column 1,Column 2,Column 3,Column 4,Column 5\n");
                
                // Write the data rows
                for (User item : usersTable.getItems()) {
                    String row = String.format("%s,%s,%s,%s,%s\n", item.getEmail(), item.getPrenom(), item.getNom(),item.getAdress(),item.getTel());
                    writer.write(row);
                }
                
                // Close the FileWriter
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
}