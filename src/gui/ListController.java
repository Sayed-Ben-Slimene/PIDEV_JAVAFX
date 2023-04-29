package gui;

import entities.ProduitPanier;
import entities.Produits;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServicePanier;
import services.ServiceProduit;

public class ListController implements Initializable {

    @FXML
    private GridPane produitsContainer;
    private ServicePanier sp = new ServicePanier();
    private ServiceProduit p = new ServiceProduit();
    @FXML
    private Stage stage; 
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
private ComboBox<String> categoriesComboBox;
    @FXML
private TextField categorieField;
   
  @FXML
    private GridPane 
          gridPane1;
@FXML
private ComboBox<Double> prixMinComboBox;
float prixProduitSelectionne = 0;

   
 ServiceProduit serviceProduit = new ServiceProduit();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProduits();
    }
 
   @FXML
     private void loadPanierView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Commande.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void afficherProduits() {
         
       ArrayList<Produits> produits = serviceProduit.Afficher();

        produitsContainer.getChildren().clear();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        int column = 0;
        int row = 0;

        for (Produits produit : produits) {
            VBox produitBox = new VBox();
            ImageView imageView = new ImageView(new Image(new File(produit.getPhoto()).toURI().toString()));
            imageView.setFitWidth(220);
            imageView.setFitHeight(220);
            Label titreLabel = new Label(produit.getTitle());
            Label categorieLabel = new Label(produit.getCategorie().getLabel());
            Label prixLabel = new Label(String.valueOf(produit.getPrix()));
            Button addToCartButton = new Button("Acheter");

            addToCartButton.setOnAction(event -> {
                
               loadPanierView();
            });
            
            produitBox.getChildren().addAll(imageView, titreLabel, categorieLabel, prixLabel, addToCartButton);

            gridPane.add(produitBox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        produitsContainer.getChildren().add(gridPane);
    }
    






@FXML
void rechercher(ActionEvent event) {
    String categorie = categorieField.getText();
    

    ArrayList<Produits> produits = serviceProduit.rechercherParCategorieEtPrix(categorie);

    // Effacer toutes les cases de la grille avant d'ajouter les nouvelles
    produitsContainer.getChildren().clear();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);

    

    int column = 0;
    int row = 0;

    // Parcourir les produits et créer une case pour chacun
    for (Produits produit : produits) {
        // Créer une case pour le produit
         VBox produitBox = new VBox();
            ImageView imageView = new ImageView(new Image(new File(produit.getPhoto()).toURI().toString()));
            imageView.setFitWidth(220);
            imageView.setFitHeight(220);
            Label titreLabel = new Label(produit.getTitle());
            Label categorieLabel = new Label(produit.getCategorie().getLabel());
            Label prixLabel = new Label(String.valueOf(produit.getPrix()));
            Button addToCart = new Button("Acheter");

            addToCart.setOnAction(event1 -> {
                
               loadPanierView();
            });

           
            
            produitBox.getChildren().addAll(imageView, titreLabel, categorieLabel, prixLabel, addToCart);

            gridPane.add(produitBox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
           
    }
    produitsContainer.getChildren().add(gridPane);
}
}










