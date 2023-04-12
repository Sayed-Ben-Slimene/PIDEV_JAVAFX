/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.User;
import utils.MyDB;

/**
 *
 * @author Andrew
 */
public class ServiceUser  implements IService<User>{
    
    Connection con ; 
    Statement ste;
     
    
    
    
    
    
    public ServiceUser() {
        
        con = MyDB.createorgetInstance().getCon();
        
    }

    @Override
    public void Ajouter(User t) {
        
        try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
            String req = "INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdress()+"','"+t.getEmail()+"','"+t.getPassword()+"','"+t.getRoles()+"','"+t.getTel()+"');";
            
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void Ajouter2(User t) {
        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`) VALUES (?,?,?,?,?,?,?);");
            
            pre.setString(1, t.getNom());
            pre.setString(2, t.getPrenom());
            pre.setString(3, t.getAdress());
            pre.setString(4, t.getEmail());
            pre.setString(5, t.getPassword());
            pre.setString(6, t.getRoles());
            pre.setInt(7, t.getTel());
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(User t) {
     /*   try {
            String querry= "UPDATE `user` SET `email`='"+t.getEmail()+"',`nom`='"+t.getNom()+"',`role`='"+t.getRoles()+"',`password`='"+t.getPassword()+"' WHERE email='"+t2+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
            if(stm.executeUpdate(querry)==1){
                System.out.print("user modifier");
            }

            } catch (SQLException ex) {
                System.out.println("service classe modif methode  ");
                System.out.println(ex.getMessage());

            }*/
    }

    @Override
    public void Supprimer(User t) {
    /*try {
            String querry= "DELETE FROM `User` WHERE email ='"+t+"'";
            Statement stm = cnx.createStatement();

            stm.executeUpdate(querry);
            if(stm.executeUpdate(querry)==1){
                System.out.print("user supprimer");
            }
            } catch (SQLException ex) {
                System.out.println("service classe supprimer methode  ");
                System.out.println(ex.getMessage());

            }    
    */
    }
    
    

    @Override
    public ArrayList<User> Afficher() {
        ArrayList<User> pers = new ArrayList<>();
        try {
            ste =con.createStatement();
            String req = "SELECT * FROM `user`";
            ResultSet res =ste.executeQuery(req);
            
            while(res.next()){
                int id = res.getInt("id");
                String nom = res.getString(2);
                String prenom =res.getString("prenom");
                int tel = res.getInt(4);
                String email =res.getString("email");                
                String password =res.getString("password");                
                String adress =res.getString("adress");
                String roles =res.getString("roles");

                User p = new User(id, nom, prenom,adress, email,password,roles,tel);
                pers.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  pers;
       
    }
    
}
