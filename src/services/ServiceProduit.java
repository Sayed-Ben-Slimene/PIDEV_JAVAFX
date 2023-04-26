/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categorie;
import entities.Produits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class ServiceProduit implements IService<Produits> {
    Connection con ; 
    Statement ste;
    
     
    
    
    
    
    
    public ServiceProduit() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }
    public void Ajouter(Produits t) {
         try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
          //  String req = "INSERT INTO `sportify`.`produits` (`category_id`,`title`,`description`,`published`,`prix`,`photo`) VALUES ('"+t.getCategorie().getId()+"','"+t.getTitle()+"','"+t.getDescription()+"','"+t.getPublished()+"','"+t.getPrix()+"','"+t.getPhoto()+"');";
            
          //  ste.executeUpdate(req);
          String req = "INSERT INTO `sportify`.`produits` (`category_id`,`title`,`description`,`published`,`prix`,`photo`) VALUES (?,?,?,?,?,?)";
PreparedStatement ps = con.prepareStatement(req);
ps.setInt(1, t.getCategorie().getId());
ps.setString(2, t.getTitle());
ps.setString(3, t.getDescription());
ps.setInt(4, t.getPublished());
ps.setDouble(5, t.getPrix());
ps.setString(6, t.getPhoto());
ps.executeUpdate();

           
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println(ex.getMessage());
        }
        
         ServiceProduit sp = new ServiceProduit();
         //sp.Afficher();
    }
  
   

    @Override
    public void Ajouter2(Produits t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Modifier(Produits t) {
        try {
            String querry= "UPDATE `produits` SET `title`='"+t.getTitle()+"',`description`='"+t.getDescription()+"',`published`='"+t.getPublished()+"',`prix`='"+t.getPrix()+"',`photo`='"+t.getPhoto()+"',`category_id`='"+t.getCategorie().getId()+"' where `title`='"+t.getTitle()+"'";
            Statement ste = con.createStatement();

            ste.executeUpdate(querry);
            if(ste.executeUpdate(querry)==1){
                System.out.print("categorie modifier");
            }

            } catch (SQLException ex) {
                System.out.println("service classe modif methode  ");
                System.out.println(ex.getMessage());

            }
        
        

        }

    @Override
    public void Supprimer(Produits t) {
        try {
            String req = "DELETE FROM produits WHERE title=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, t.getTitle());
            pre.executeUpdate();
            System.out.println("produit supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
   public ArrayList<Produits> Afficher() {
    ArrayList<Produits> pro = new ArrayList<>();
    try {
        ste = con.createStatement();
        String req = "SELECT p.*, c.label FROM produits p JOIN category c ON p.category_id = c.id ORDER BY p.title ASC";
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt("p.id");
            String title = res.getString("p.title");
            String description = res.getString("p.description");
            float prix = res.getFloat("p.prix");
            String photo = res.getString("p.photo");
            int published = res.getInt("p.published");
            //int categorieId = res.getInt("c.id");
            String categorieLabel = res.getString("c.label");

            Categorie categorie = new Categorie( categorieLabel);
            Produits p = new Produits(id, title, description, prix, photo, published, categorie);

            pro.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return pro;
}
   public ArrayList<Produits> rechercherParCategorie(String categorie) {
    ArrayList<Produits> produits = new ArrayList<>();
    try {
        String requete = "SELECT p.*, c.label FROM produits p JOIN category c ON p.category_id = c.id WHERE c.label=?";
        PreparedStatement ps = con.prepareStatement(requete);
        ps.setString(1, categorie);
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            int id = res.getInt("p.id");
            String title = res.getString("p.title");
            String description = res.getString("p.description");
            float prix = res.getFloat("p.prix");
            String photo = res.getString("p.photo");
            int published = res.getInt("p.published");
            String categorieLabel = res.getString("c.label");

            Categorie c = new Categorie(categorieLabel);
            Produits p = new Produits(id, title, description, prix, photo, published, c);

            produits.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return produits;
}



}
  
    
   

    


   

    
   
    
    

   


   

   
   
    

