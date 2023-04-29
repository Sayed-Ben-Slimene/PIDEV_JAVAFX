/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import entities.Categorie;
import entities.Produits;
import entities.commande;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCommande;
import services.ServiceProduit;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CommandeController implements Initializable {
      @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField temail;
   
    @FXML
    private TextField tnum;
    @FXML
    private TextField tadresse;
    @FXML
    private TextField tprix;
    @FXML
    private CheckBox ckpub;
     ServiceCommande sc = new ServiceCommande();
     float prixProduitSelectionne = 0;
     ServiceProduit serviceProduit = new ServiceProduit();
     @FXML
private TableView<Produits> tableProduits;
     @FXML
    private Stage stage; 
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    
    
    private final String TO_NUMBER = "+15074484539";
    public static final String ACCOUNT_SID = "AC571ca3659d5ac729764975c897129e09";
  public static final String AUTH_TOKEN = "3c8a90aece4c3641f0c3490ec5077a45";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
   } 
    @FXML
    public void List(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/gui/List.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Categorie");
        stage.setScene(scene);
        stage.show();
                
    }
     @FXML
     private void loadPanierView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/List.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  // Envoyer le message WhatsApp avec Twilio
                
    @FXML
private void ajouterAction(ActionEvent event) throws StripeException {
    int num = Integer.parseInt(tnum.getText());
    
    int publishedValue = ckpub.isSelected() ? 1 : 0;
    // détermine la valeur à enregistrer en fonction de l'état de la case à cocher
    commande c = new commande(tnom.getText(), tprenom.getText(),num,tadresse.getText(),temail.getText(),Float.parseFloat(tprix.getText()), publishedValue);
    sc.Ajouter(c);
    
    
    float prixCommande = Float.parseFloat(tprix.getText());

    // créer un objet Session pour Stripe Checkout
    Stripe.apiKey = "sk_test_51N1xYXErs4CGHvjuMSNl54CaBbapdZXJ63oxt3w0AG6FxgA7tZWyzm7lYKiIio527VMnytultn5Jn072N78ta5be00yt9q0H4q";
    SessionCreateParams params =
        SessionCreateParams.builder()
          .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
          .setMode(SessionCreateParams.Mode.PAYMENT)
          .setSuccessUrl("https://votre-site.com/paiement-reussi")
          .setCancelUrl("https://votre-site.com/paiement-annule")
          .addLineItem(
            SessionCreateParams.LineItem.builder()
              .setPriceData(
                SessionCreateParams.LineItem.PriceData.builder()
                  .setCurrency("eur")
                  .setUnitAmount((long) (prixCommande * 100))
                  .setProductData(
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                      .setName("Commande")
                      .build())
                  .build())
              .setQuantity(1L)
              .build())
          .build();
    Session session = null;
    session = Session.create(params);

    // rediriger l'utilisateur vers la page de paiement de Stripe Checkout
    String url = session.getUrl();
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // attendre la fin du paiement
    while (true) {
        try {
            session = Session.retrieve(session.getId());
        } catch (StripeException e) {
            System.out.println(e.getMessage());
            break;
        }
        if ("paid".equals(session.getPaymentStatus())) {
            // mettre à jour la commande dans la base de données
            //c.setPayee(true);
            //sc.Modifier(c);
            Twilio.init("AC571ca3659d5ac729764975c897129e09", "3c8a90aece4c3641f0c3490ec5077a45");
    Message message = Message.creator(
            new PhoneNumber("+21652492642"), // numéro de téléphone de destination
            new PhoneNumber("+15074484539"), // numéro de téléphone Twilio
            "une commande ajoute avec succee"
                    + ""
                    + "!").create();
    System.out.println(message.getSid());
            
            break;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

  
    
}
