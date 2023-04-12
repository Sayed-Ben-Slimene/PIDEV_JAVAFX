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
        PreparedStatement pre = con.prepareStatement("INSERT INTO `sportify`.`produits` (`title`, `description`, `prix`, `category_id`, `photo`, `published`) VALUES (?, ?, ?, ?, ?, ?);");
        
        pre.setString(1, t.getTitle());
        pre.setString(2, t.getDescription());
        pre.setFloat(3, t.getPrix());
        pre.setInt(4, t.getCategorieId()); // utiliser la catégorie sélectionnée pour initialiser le champ categorieId
        pre.setString(5, t.getPhoto());
        pre.setBoolean(6, t.isPublished());
        
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
   }

    @Override
    public void Ajouter2(Produits t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Modifier(Produits t) {
        

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
            ste =con.createStatement();
            String req = "SELECT * FROM `produits`";
            ResultSet res =ste.executeQuery(req);
            
            while(res.next()){
                int id = res.getInt("id");
                String title = res.getString(2);
                String description = res.getString(3);
                float prix = res.getFloat(4);
                int categorieId = res.getInt(5);
                String photo =res.getString(6);
                boolean published=res.getBoolean(7);
                

                Produits p = new Produits(id,title,description,prix,categorieId,photo,published);
                pro.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  pro;
        
    }
}
  
    
   

    


   

    
   
    
    

   


   

   
   
    

