/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sportify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.security.Provider;
import entities.User;
import services.ServiceUser;
import utils.MyDB;
import javafx.application.Application;
import java.io.IOException;


/**
 *
 * @author Andrew
 */
public class Sportify extends Application  {

    public static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        this.stg = primaryStage;
        FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ForgotPassword/ForgotPassword.fxml"));
        Parent root= loader.load();
        Scene scene= new Scene(root);
        
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println(System.getProperties());
        launch(args);
    }
    
    
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
       A a1 = A.getInstance();
       A a2 = A.getInstance();
       
       
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
      
      
        MyDB.createorgetInstance();
        
        User p1 =new User("Kalbousi", "Yassine","nabeul","sayedjavassssssss@gmail.com","sayed12345","[\"ROLE_ADMIN\"]", 25365241);
        System.out.println("user ajouter");
        
        
        ServiceUser sp = new ServiceUser();
        
        
        sp.Ajouter(p1);
       // sp.Ajouter2(p2);
        System.out.println(sp.Afficher());


    }*/
    
}
