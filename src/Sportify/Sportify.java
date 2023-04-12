/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sportify;

import entities.Categorie;
import entities.Produits;
import java.security.Provider;
import entities.User;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceCategorie;
import services.ServiceProduit;
import workshop3a31.test.A;
import utils.MyDB;

/**
 *
 * @author Andrew
 */
public class Sportify extends Application{

    /**
     * @param args the command line arguments
     */
     public static Stage stg;
     public void start(Stage primaryStage) throws IOException
    {
        this.stg = primaryStage;
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/CategorieFXML.fxml"));
        Parent root= loader.load();
        Scene scene= new Scene(root);
        
        primaryStage.setTitle("Category");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
         System.out.println(System.getProperties());
        launch(args);
      /* A a1 = A.getInstance();
       A a2 = A.getInstance();
       
       
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());*/
      
      
        //MyDB.createorgetInstance();
        
       /* User p1 =new User("Kalbousi", "Yassine","nabeul","sayedjava@gmail.com","sayed12345","[\"ROLE_ADMIN\"]", 25365241);
        System.out.println("user ajouter");
        ServiceUser sp = new ServiceUser();
        */
      //  Categorie c =new Categorie("css");
       // Categorie c1 =new Categorie("chaussures");
        
      //  ServiceCategorie sc = new ServiceCategorie();
      // sc.Ajouter(c);
       // sc.Ajouter(c1);
        //sc.Supprimer(c);
       // sc.Afficher();
       
       //Produits p1 = new Produits("maillot","hihtniktnn tjk g",145,15,"hi ijt .",true);
       // ServiceProduit sp = new ServiceProduit();
        //sp.Ajouter(p1);
        //sp.Supprimer(p1);
        //System.out.println(sc.Afficher());
        
        
        
       
       // sp.Ajouter2(p2);
        //System.out.println(sp.Afficher());
    }
    
}
