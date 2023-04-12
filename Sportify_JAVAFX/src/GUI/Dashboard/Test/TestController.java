/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Dashboard.Test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sayed
 */
public class TestController implements Initializable {
    /*
   @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;

       @FXML
    private TextField password;
           @FXML
    private TextField tel;
               @FXML
    private TextField adress;

    
  
  

    @FXML
    private void modiferAbonnementBack(ActionEvent event) 
    {
         
        AbonnementCRUD inter = new Abonnement1CRUD();
        
        String nom = nomAbonnementModifierfx.getText();
        String prenom = prenomAbonnementModifierfx.getText();
        int numero = Integer.valueOf(numeroAbonnementModifierfx.getText());
        String email = emailAbonnementModifierfx.getText();
        String type = typeAbonnementModifierfx.getValue();
        
        
        System.out.println(AfficherAbonnementBackController.E.getId());
        Abonnement even = new Abonnement(AfficherAbonnementBackController.E.getId(), numero, nom, prenom, email, type );
      
        inter.modifierAbonnement(even);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

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
 */   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

       
    }   
}
