/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Dashboard.UsersDash;

import java.awt.*;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import services.ServiceUser;



/**
 * FXML Controller class
 *
 * @author sayed
 */
public class ShowUsersController implements Initializable {
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
     public void GetUser(MouseEvent event){
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
/*
    @FXML
    public void supp(MouseEvent event){

        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation supp");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete ?");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get()==ButtonType.OK){
            User user= new User();
            user=usersTable.getSelectionModel().getSelectedItem();
            serviceUser.supprimer(user);
            listUsers.removeAll(listUsers);
            listUsers=serviceUser.afficher();
            culumnUserId.setCellValueFactory(new PropertyValueFactory<User,String> ("id"));
            culumnUsernom.setCellValueFactory(new PropertyValueFactory<User,String> ("nom"));
            culumnUserPrenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
            culumnUserEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
            culumnUserPassword.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            culumnUserRoles.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
            culumnUserTel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
            culumnUserAdress.setCellValueFactory(new PropertyValueFactory<User,String>("adress"));
            usersTable.setItems(listUsers);
        }
    }
*/
}
