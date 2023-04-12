/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Categorie;

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
public class ServiceCategorie implements IService<Categorie> {
    
    Connection con ; 
    Statement ste;
    public ServiceCategorie() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }
@Override
    public ArrayList<Categorie> Afficher() {
        ArrayList<Categorie> cat = new ArrayList<>();
        try {
            ste =con.createStatement();
            String req = "SELECT * FROM `category`";
            ResultSet res =ste.executeQuery(req);
            
            while(res.next()){
                int id = res.getInt("id");
                String label = res.getString(2);
                

                Categorie c = new Categorie(id,label);
                cat.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  cat;
       
    }

    @Override
    public void Ajouter(Categorie t) {
         try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
            String req = "INSERT INTO `sportify`.`category` (`label`) VALUES ('"+t.getLabel()+"');";
            
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println(ex.getMessage());
        }
        
         ServiceCategorie sc = new ServiceCategorie();
         sc.Afficher();
    }

    @Override
    public void Ajouter2(Categorie t) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `sportify`.`category` (`label`) VALUES (?);");
            
            pre.setString(1, t.getLabel());
            
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Categorie t) {
        try {
            String querry= "UPDATE `category` SET `label`='"+t.getLabel()+"'";
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
    public void Supprimer(Categorie t) {
        try {
            String req = "DELETE FROM category WHERE label=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, t.getLabel());
            pre.executeUpdate();
            System.out.println("Catégorie supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
