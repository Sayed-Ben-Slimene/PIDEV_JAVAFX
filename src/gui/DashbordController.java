/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DashbordController implements Initializable {
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
        // TODO
    }   
    @FXML
    public void GestionProduits(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/Produits.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Produits");
        stage.setScene(scene);
        stage.show();
                
    }
    @FXML
    public void GestionCategorie(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/CategorieFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Categorie");
        stage.setScene(scene);
        stage.show();
                
    }
    @FXML
    public void Home(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/list.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Categorie");
        stage.setScene(scene);
        stage.show();
                
    }
    
}
