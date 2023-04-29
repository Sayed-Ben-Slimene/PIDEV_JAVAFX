/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categorie;
import entities.ProduitPanier;
import entities.Produits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class ServicePanier implements IService<ProduitPanier> {
     Connection con ; 
    Statement ste;
     public ServicePanier() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }

    @Override
    public void Ajouter(ProduitPanier t) {
         try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
  
            
          //  ste.executeUpdate(req);
          String req = "INSERT INTO `sportify`.`panier` (`produit_id`,`quantite`,`total`) VALUES (?,?,?)";
          PreparedStatement ps = con.prepareStatement(req);
          ps.setInt(1, t.getProduit().getId());
          ps.setInt(2, t.getQuantite());
          ps.setFloat(3, t.getTotal());

           ps.executeUpdate();

           
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println(ex.getMessage());
        }
        
         ServicePanier sp = new ServicePanier();
    }

    @Override
    public void Ajouter2(ProduitPanier t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Modifier(ProduitPanier t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Supprimer(ProduitPanier t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override

public ArrayList<ProduitPanier> Afficher() {
    ArrayList<ProduitPanier> pro = new ArrayList<>();
    try {
        ste = con.createStatement();
        String req = "SELECT p.*, c.title,c.prix FROM panier p JOIN produits c ON p.produit_id = c.id ";
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            
            
            float total= res.getFloat("p.total");
           
            int quantite = res.getInt("p.quantite");
            //int categorieId = res.getInt("c.id");
            String title = res.getString("c.title");
            float prix= res.getFloat("c.prix");

            Produits produit = new Produits( title,prix);
            ProduitPanier p = new ProduitPanier( total, quantite,produit);

            pro.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return pro;
}
public ObservableList<ProduitPanier> getPanier() {
    ObservableList<ProduitPanier> panier = FXCollections.observableArrayList();
    
    try {
        ste = con.createStatement();
        String query = "SELECT * FROM panier";
        
        ResultSet rs = ste.executeQuery(query);
        
        while (rs.next()) {
            ProduitPanier produitPanier = new ProduitPanier(
                rs.getString("tproduit"),
                rs.getFloat("tprix"),
                rs.getInt("tquantite"),
                rs.getFloat("ttotal")
            );
            panier.add(produitPanier);
        }
        
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return panier;
}



}
