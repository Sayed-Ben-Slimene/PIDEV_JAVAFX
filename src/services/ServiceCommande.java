/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class ServiceCommande implements IService<commande>{

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    Connection con ; 
    Statement ste;
    
     
    
    
    
    
    
    public ServiceCommande() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }

    @Override
    public void Ajouter(commande t) {
         try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
          //  String req = "INSERT INTO `sportify`.`produits` (`category_id`,`title`,`description`,`published`,`prix`,`photo`) VALUES ('"+t.getCategorie().getId()+"','"+t.getTitle()+"','"+t.getDescription()+"','"+t.getPublished()+"','"+t.getPrix()+"','"+t.getPhoto()+"');";
            
          //  ste.executeUpdate(req);
          String req = "INSERT INTO `sportify`.`commande` (`nom`,`prenom`,`num_tel`,`adresse`,`email`,`prix_total`,`mode_paiement`) VALUES (?,?,?,?,?,?,?)";
PreparedStatement ps = con.prepareStatement(req);

ps.setString(1, t.getNom());
ps.setString(2, t.getPrenom());
ps.setInt(3, t. getNum_tel());
ps.setString(4, t.getAdresse());
ps.setString(5, t.getEmail());
ps.setFloat(6, t.getPrix_total());
ps.setInt(7, t. getMode_paiement());
ps.executeUpdate();

           
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println(ex.getMessage());
        }
        
         ServiceCommande sc = new ServiceCommande();
        
    }

    @Override
    public void Ajouter2(commande t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Modifier(commande t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Supprimer(commande t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<commande> Afficher() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
