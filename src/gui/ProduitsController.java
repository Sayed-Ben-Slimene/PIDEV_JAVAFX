/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import entities.Categorie;
import entities.Produits;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.ServiceCategorie;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ProduitsController implements Initializable {
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tafile;
    @FXML
    private TextField textFieldCategorie;
    @FXML
    private ComboBox<Categorie> cbCat;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextField tfpho;
    @FXML
    private TextField tfprix;
    @FXML
    private CheckBox ckpub;
    @FXML
    private Label cs1;
    private Label produit;
     @FXML
    private TableView <Produits> tbProduit;
    @FXML
    private TableColumn <Produits,String> cTitle;
    @FXML
    private TableColumn <Produits,String> cdes;
    @FXML
    private TableColumn <Produits,Float> cprix;
    @FXML
    private TableColumn <Produits,String> cato;
    @FXML
    private TableColumn <Produits,ImageView> pho;
    @FXML
    private TableColumn <Produits,Integer> publ;
    @FXML
        private Button btn;
        private
        boolean verification = false;
        private ServiceProduit sp = new ServiceProduit();
        final FileChooser fc = new FileChooser();
         private ServiceCategorie sc = new ServiceCategorie();
 @FXML
    private Stage stage; 
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ObservableList<Categorie> categories = FXCollections.observableArrayList(sc.Afficher());

cbCat.setItems(categories);
cbCat.setConverter(new StringConverter<Categorie>() {
    @Override
    public String toString(Categorie categorie) {
        return categorie.getLabel();
    }

    @Override
    public Categorie fromString(String nomCategorie) {
        return categories.stream()
                .filter(categorie -> categorie.getLabel().equals(nomCategorie))
                .findFirst()
                .orElse(null);
    }
});
    cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    cdes.setCellValueFactory(new PropertyValueFactory<>("description"));
    cato.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().getLabel()));
    pho.setCellValueFactory(cellData -> {
    Produits produit = cellData.getValue();
    Image image = new Image(new File(produit.getPhoto()).toURI().toString());
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(70);
    imageView.setFitHeight(70);
    return new SimpleObjectProperty<>(imageView);
});
    cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    publ.setCellValueFactory(new PropertyValueFactory<>("published"));
    Afficher();
    
    
    
        } 
    @FXML
    private void uploadImage(ActionEvent event){
            fc.setTitle("My File chooser");
            //fc.setInitialDirectory(new File(System.getProperty("users.home")));
            File file =fc.showOpenDialog(null);
            if(file !=null){
            tafile.appendText(file.getAbsolutePath());
            }
            else {
                System.out.println("le champ est invalid");
            }
    }
    private void verifTitle(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i <  tftitle.getText().trim().length(); i++) {
            char ch = tftitle.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 &&  tftitle.getText().trim().length() >=3) {
            
            cs1.setText ("champ valide ");
            cs1.setTextFill(Color.GREEN);


             verification = true;
        } else {
           
            cs1.setText ("le champ est vide ");

//                erreur = erreur + ("Pas de caractere permit dans le telephone\n")
    }
    }
     @FXML
    public void ListProduits(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/List.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Produits");
        stage.setScene(scene);
        stage.show();
                
    }
    
    @FXML
    private void ajouterAction(ActionEvent event) {
    Categorie categorie = cbCat.getValue();
    
    int publishedValue = ckpub.isSelected() ? 1 : 0; // détermine la valeur à enregistrer en fonction de l'état de la case à cocher

    Produits produit = new Produits(tftitle.getText(), tfdesc.getText(),Float.parseFloat(tfprix.getText()),tafile.getText(), publishedValue, categorie);
    sp.Ajouter(produit);
    Afficher();
    }
    @FXML
    private void Afficher(){
        ObservableList<Produits> list = FXCollections.observableArrayList(sp.Afficher());

    tbProduit.setItems(list);
    }
     //Connection con ;
            
 @FXML
private void modifierProduit() {
    // Récupérer le produit sélectionné
    Produits produitSelectionne = tbProduit.getSelectionModel().getSelectedItem();
      ServiceProduit sp = new ServiceProduit();
    if (produitSelectionne != null) {
        // Afficher les informations du produit sélectionné dans les champs d'édition
        tftitle.setText(produitSelectionne.getTitle());
        tfdesc.setText(produitSelectionne.getDescription());
        tfprix.setText(String.valueOf(produitSelectionne.getPrix()));
        tafile.setText(produitSelectionne.getPhoto());
        ckpub.setText(String.valueOf(produitSelectionne.getPublished()));
        cbCat.getSelectionModel().select(produitSelectionne.getCategorie().getId());

        // Attendre que l'utilisateur modifie les champs d'édition et valide les modifications
        String title = tftitle.getText();
        String description = tfdesc.getText();
        float prix = Float.parseFloat(tfprix.getText());
        String photo = tafile.getText();
        boolean published = Boolean.parseBoolean(ckpub.getText());
        Categorie categorie = cbCat.getSelectionModel().getSelectedItem();

        // Afficher une boîte de dialogue de confirmation pour enregistrer les modifications
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Enregistrer les modifications");
        alert.setHeaderText("Voulez-vous enregistrer les modifications?");
        alert.setContentText("Cliquez sur OK pour enregistrer les modifications ou Annuler pour les annuler.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
         sp.Modifier( produitSelectionne );
        }
        
    }
}
@FXML
private void supprimerAction(ActionEvent event) {
    // Récupérer la catégorie sélectionnée
    Produits cat = tbProduit.getSelectionModel().getSelectedItem();
    
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
    confirmation.setHeaderText("Supprimer la catégorie " + cat.getTitle() + " ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer la catégorie " + cat.getTitle() + " ?");
    Optional<ButtonType> resultat = confirmation.showAndWait();
    
    // Supprimer la catégorie si l'utilisateur a confirmé
    if(resultat.get() == ButtonType.OK) {
        sp.Supprimer(cat);
        // Effacer la catégorie de la table
      Afficher();
    }}
    @FXML
private void rechercherProduit(ActionEvent event) {
    String categorie = textFieldCategorie.getText();

    // Appeler la méthode de recherche
    ArrayList<Produits> produits = sp.rechercherParCategorie(categorie);

    // Mettre à jour l'interface utilisateur avec les résultats de la recherche
     tbProduit.getItems().setAll(produits);
}
    
}

    
    
    

   







  
 
