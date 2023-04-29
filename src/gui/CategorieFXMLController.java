/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServiceCategorie;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CategorieFXMLController implements Initializable {
    
    @FXML
    private TextField categorie;
    @FXML
    private TableView <Categorie> tbCategorie;
    @FXML
    private TableColumn <Categorie,Integer> colId;
    @FXML
    private Label clabel;
    @FXML
    private TableColumn <Categorie,String> colLabel;
     Connection con=null ; 
    PreparedStatement st=null;
    ResultSet rs=null;
    boolean verification = false;
    @FXML
    private Stage stage; 
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    
    
    private ServiceCategorie sc = new ServiceCategorie();
   
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
    Afficher();
     
        
       
    } 
    @FXML
    private void verifNom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < categorie.getText().trim().length(); i++) {
            char ch = categorie.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && categorie.getText().trim().length() >=3) {
            
            clabel.setText ("champ valide ");
            clabel.setTextFill(Color.GREEN);


             verification = true;
        } else {
           
            clabel.setText ("Verifier le champ !!! ");

//                erreur = erreur + ("Pas de caractere permit dans le telephone\n")
    }
    }
    @FXML
    public void Retour(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/dashbord.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Produits");
        stage.setScene(scene);
        stage.show();
                
    }
    @FXML
    private void ajouterAction(ActionEvent event) {
        
        // String Label =label.getText();
        if(categorie.getText().isEmpty()){
                clabel.setText("entrer une categorie !");}
        if( verification==true){
        
       
        
      //  ServiceCategorie sc = new ServiceCategorie();
        Categorie cat = new Categorie(categorie.getText());
        
        
        sc.Ajouter(cat);
        Afficher();
        System.out.print("categorie ajoutee : "+categorie.getText());
        }
       
        
        
    }
   
   
      
  
    @FXML
    private void Afficher(){
        ObservableList<Categorie> list = FXCollections.observableArrayList(sc.Afficher());

    tbCategorie.setItems(list);
    }
            
    
   
    @FXML
private void supprimerAction(ActionEvent event) {
    // Récupérer la catégorie sélectionnée
    Categorie cat = tbCategorie.getSelectionModel().getSelectedItem();
    
    // Vérifier que la catégorie sélectionnée n'est pas nulle
    if(cat == null) {
        // Afficher un message d'erreur si aucune catégorie n'a été sélectionnée
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune catégorie sélectionnée");
        alert.setContentText("Veuillez sélectionner une catégorie à supprimer");
        alert.showAndWait();
        return;
    }
    
    // Demander confirmation à l'utilisateur avant de supprimer la catégorie
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Supprimer la catégorie " + cat.getLabel() + " ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer la catégorie " + cat.getLabel() + " ?");
    Optional<ButtonType> resultat = confirmation.showAndWait();
    
    // Supprimer la catégorie si l'utilisateur a confirmé
    if(resultat.get() == ButtonType.OK) {
        sc.Supprimer(cat);
        // Effacer la catégorie de la table
      Afficher();
    }
    
}
@FXML
private void Modifier() {
    // Vérifier si une catégorie est sélectionnée
    Categorie cat = tbCategorie.getSelectionModel().getSelectedItem();
    if (cat == null) {
        System.out.println("Veuillez sélectionner une catégorie");
        return;
    }

    // Ouvrir une boîte de dialogue pour modifier la catégorie
    Dialog<Categorie> dialog = new Dialog<>();
    dialog.setTitle("Modifier une catégorie");

    // Créer les champs de saisie pour le formulaire de modification
    TextField labelField = new TextField();
    labelField.setEditable(true);
    labelField.setText(cat.getLabel());

    // Ajouter les champs de saisie à la boîte de dialogue
    GridPane grid = new GridPane();
    grid.add(new Label("Label :"), 0, 0);
    grid.add(labelField, 1, 0);
    dialog.getDialogPane().setContent(grid);

    // Ajouter les boutons de validation et d'annulation à la boîte de dialogue
    ButtonType validerButtonType = new ButtonType("Valider", ButtonData.OK_DONE);
    ButtonType annulerButtonType = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, annulerButtonType);

    // Convertir le résultat de la boîte de dialogue en objet Categorie
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == validerButtonType) {
            return cat;
        }
        return null;
    });

    // Afficher la boîte de dialogue et récupérer la nouvelle catégorie
    Optional<Categorie> result = dialog.showAndWait();
    if (result.isPresent()) {
        // Mettre à jour la catégorie dans la base de données
        Categorie newCat = result.get();
        sc.Modifier(newCat);

        // Mettre à jour la TableView
        ObservableList<Categorie> list = tbCategorie.getItems();
        int index = list.indexOf(cat);
        list.set(index, newCat);
        tbCategorie.refresh();

    }
}

    

    
    
    


    
     
    

}