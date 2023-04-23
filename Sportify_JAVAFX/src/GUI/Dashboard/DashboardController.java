/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Dashboard;

import GUI.Dashboard.UsersDash.ShowUsersController;
import entities.User;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ServiceUser;

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
    private TableColumn<User, Integer> culumnUserTel;
    @FXML
    private TableView<User> usersTable;
    //ObservableList<User> listUsers;

    ObservableList<User>  listUsers = FXCollections.observableArrayList();

    ServiceUser serviceUser =new ServiceUser();

    @FXML
    private TextField iduser;
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenomuser;
    @FXML
    private TextField emailuser;
    @FXML
    private TextField passworduser;
    @FXML
    private TextField adressuser;

    @FXML
    private TextField teluser;
    @FXML
    private TextField roleuser;

    int index =-1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

//        usersTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> {
//                    try {
//                        selectionnerunuser(newValue);
//                    } catch (MalformedURLException ex) {
//                        Logger.getLogger(ShowUsersController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
    }


    @FXML
     public void getuser(MouseEvent event){
      index = usersTable.getSelectionModel ().getFocusedIndex ();
      if(index <=-1){
          return;

      }
        iduser.setText(culumnUserId.getCellData(index).toString());
        nomuser.setText(culumnUsernom.getCellData(index).toString());
        prenomuser.setText(culumnUserPrenom.getCellData(index).toString());
        teluser.setText(culumnUserTel.getCellData(index).toString());
        roleuser.setText(culumnUserRoles.getCellData(index).toString());
        adressuser.setText(culumnUserAdress.getCellData(index).toString());

        passworduser.setText(culumnUserPassword.getCellData(index).toString());
        emailuser.setText(culumnUserEmail.getCellData(index).toString());

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

//    @FXML
//    private void modifierUser(ActionEvent event)
//    {
//        User user= new User();
//
//
//                    User E = usersTable.getSelectionModel().getSelectedItem();
//
//        int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
//
//
//        User e = usersTable.getSelectionModel().getSelectedItem();
//
//
//
//        String nom = e.getNom();
//        String prenom = e.getPrenom();
//        String email = e.getEmail();
//        int tel = e.getTel();
//        String password = e.getPassword();
//        String adress = e.getAdress();
//        String roles = e.getRoles();
//
//
//       E=e;
//
//        try {
//
//            Parent page1= FXMLLoader.load(getClass().getResource("../GUI/Dashboard/Dashboard.fxml"));
//            Scene scene = new Scene(page1);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException ex) {
//            System.out.println("Erreur\n");
//            Logger.getLogger(ShowUsersController.class.getName()).log(Level.SEVERE, null, ex);
//
//        }

//   }
    /*
    private void update_button(ActionEvent event) throws IOException {
        ServiceUser uc = new ServiceUser();
        User u = uc.getByID(DashboardController.userc);
        u.setFirstname(firstname_update.getText());
        u.setLastname(lastname_update.getText());
        u.setUsername(username_update.getText());
        u.setEmail(u.getEmail());
        uc.Modifier(u);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profil updated !");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load()));
        stage.show();
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.hide();
    }
    
    
/*
    @FXML
    private void Modifier() {
        // Vérifier si une catégorie est sélectionnée
        User cat = usersTable.getSelectionModel().getSelectedItem();
        if (cat == null) {
            System.out.println("Veuillez sélectionner une catégorie");
            return;
        }

        // Ouvrir une boîte de dialogue pour modifier la catégorie
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Modifier une catégorie");

        // Créer les champs de saisie pour le formulaire de modification
        TextField labelnom = new TextField();
        labelnom.setText(cat.getNom());

        TextField labelprenom = new TextField();
        labelprenom.setText(cat.getPrenom());

        TextField labelRole = new TextField();
        labelRole.setText(cat.getRoles ());

        TextField labelEmail = new TextField();
        labelEmail.setText(cat.getEmail());

        TextField labelPassword = new TextField();
        labelPassword.setText(cat.getPassword ());

        TextField labelAdress = new TextField();
        labelAdress.setText(cat.getAdress());

        // Ajouter les champs de saisie à la boîte de dialogue
        GridPane grid = new GridPane();
        grid.add(new Label("Nom:"), 0, 0);
        grid.add (labelAdress.getText (),1,0);



        dialog.getDialogPane().setContent(grid);

        // Ajouter les boutons de validation et d'annulation à la boîte de dialogue
        ButtonType validerButtonType = new ButtonType("Valider", ButtonData.OK_DONE);
        ButtonType annulerButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, annulerButtonType);

        // Convertir le résultat de la boîte de dialogue en objet Categorie
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == validerButtonType) {
                User newCat = new User(cat.getId(), labelField.getText());
                return newCat;
            }
            return null;
        });


    }

 */
}