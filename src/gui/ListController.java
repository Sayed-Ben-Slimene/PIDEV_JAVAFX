package gui;

import entities.ProduitPanier;
import entities.Produits;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServicePanier;
import services.ServiceProduit;

public class ListController implements Initializable {

    @FXML
    private GridPane produitsContainer;
    ServicePanier sp= new ServicePanier();
     private void loadPanierView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProduits();
    }

    @FXML
    private void afficherProduits() {
        ServiceProduit serviceProduit = new ServiceProduit();
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
            Button addToCartButton = new Button("Ajouter au panier");

            addToCartButton.setOnAction(event -> {
                ProduitPanier produitPanier = new ProduitPanier(produit, 1, produit.getPrix());
                sp.Ajouter(produitPanier);
                sp.Afficher();
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

}
