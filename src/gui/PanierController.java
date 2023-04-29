/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.ProduitPanier;
import entities.Produits;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServicePanier;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PanierController implements Initializable {
     @FXML
    private TableView <ProduitPanier> panier;
    @FXML
    private TableColumn <ProduitPanier,String> tproduit;
    @FXML
    private TableColumn <ProduitPanier,Float> tprix;
    @FXML
    private TableColumn <ProduitPanier,Integer> tquantite;
    @FXML
    private TableColumn <ProduitPanier,Float> ttotal;
    private ServicePanier sp= new ServicePanier();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    
   
    };

  

    @FXML
private void ajouterAuPanier() {
    // récupérer le produit sélectionné dans la TableView
    ProduitPanier produitPanier = panier.getSelectionModel().getSelectedItem();
    
    // créer une instance de ServicePanier
    ServicePanier servicePanier = new ServicePanier();
    
    // appeler la méthode Ajouter avec le produit sélectionné
    servicePanier.Ajouter(produitPanier);
   
}

   

    
}
