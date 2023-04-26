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
    ArrayList<ProduitPanier> panier = new ArrayList<>();
    try {
        ste = con.createStatement();
        String req = "SELECT p.*, c.title,c.prix FROM panier p JOIN produits c ON p.produit_id = c.id ";
        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt("p.id");
            int quantite = res.getInt("p.quantite");
            float total = res.getFloat("p.total");

            int produit_id = res.getInt("p.produit_id");
            String produitTitle = res.getString("c.title");
           
            float produitPrix = res.getFloat("c.prix");
            

           

            Produits produit = new Produits( produitTitle, produitPrix);

            ProduitPanier p = new ProduitPanier(id, quantite, total, produit);

            panier.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return panier;
}

}
