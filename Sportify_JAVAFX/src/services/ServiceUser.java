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

import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Andrew
 */
public class ServiceUser  implements IService<User>{
    
    static Connection con ;
    static Statement ste;
     
    public static User user;
    
    public ServiceUser() {
        
        con = MyDB.createorgetInstance().getCon();
    }
    
    @Override
    public boolean Ajouter(User t) {
        
        try {
        String querry = "INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdress()+"','"+t.getEmail()+"','"+t.getPassword()+"','"+t.getRoles()+"','"+t.getTel()+"');";
        Statement stm = con.createStatement();
    
        int x = stm.executeUpdate(querry);
        if(x==0){
            return false;
        }else{
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("service classe ajouter methode  ");
        System.out.println(ex.getMessage());
    
    }
     return false;   
    }
    
    public boolean ajouter(User t) {
        
        try {
       
            ste = con.createStatement();
            
            String req = "INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdress()+"','"+t.getEmail()+"','"+t.getPassword()+"','"+t.getRoles()+"','"+t.getTel()+"');";
            
      //      ste.executeUpdate(req);
            
    
        int x = ste.executeUpdate(req);
        if(x==0){
            return false;
        }else{
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("service classe ajouter methode  ");
        System.out.println(ex.getMessage());
    
    }
     return false;   
    }

    
    @Override
    public void Ajouter2(User t) {
        
        try {
            
            //1 creer le statement 
            ste = con.createStatement();
            
            String req = "INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdress()+"','"+t.getEmail()+"','"+t.getPassword()+"','"+t.getRoles()+"','"+t.getTel()+"');";
            
            ste.executeUpdate(req);
            
            
        } catch (SQLException ex) {
            //System.out.println("service classe ajouter methode  ");
            System.out.println("erreur f ajout2");
        }
        
    }
/*
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
*/

    @Override
    public ObservableList<User> afficher () {
        ObservableList<User> users = FXCollections.observableArrayList();

        //ArrayList<User> pers = new ArrayList<>();
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
                users.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  users;
       
    }

    public boolean login(String email, String password) {
try {
           
        String querry ="SELECT * FROM `user` where email ='"+email+"' and password ='"+password+"'";
        Statement stm = con.createStatement();
        ResultSet rs= stm.executeQuery(querry);

        if(!rs.isBeforeFirst()){
            System.out.println("user not found !!!!");
            return false;
        }
        else{
            System.out.println("user is logged");
            while(rs.next()){
                LoginSession.id=rs.getInt("id");
                LoginSession.roles=rs.getString("roles");
                LoginSession.nom=rs.getString("nom");
                LoginSession.prenom=rs.getString("prenom");
                LoginSession.adress=rs.getString("adress");
                
                LoginSession.email=rs.getString("email");
                LoginSession.password=rs.getString("password"); 
                LoginSession.image=rs.getString("image");
            //    LoginSession.IsLogged=true;
            }
            System.out.println(LoginSession.nom+" "+LoginSession.prenom +" is connected" );
            return true;
        }
        } catch (SQLException ex) {
            //System.out.println(ex);
        System.out.println("SQL Error !!!!!!!!!!!");

        }
        return false;
    }

    @Override
    public void supprimer(int id) {
          try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Abonnement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public void setNewMotPass(int idUser ,String pass){
       PreparedStatement st;
        try {
            String req = "UPDATE `user` SET `password` ='" + pass + "' WHERE `user`.`id` = "+idUser;
            st = con.prepareStatement(req);
            st.executeUpdate(req);
             System.out.println(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }    
    
    
    public int getIdbymail(String email) {
    try {
        PreparedStatement st = con.prepareStatement("SELECT id FROM user WHERE email=?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
}
   
    
    

    public boolean chercherUtilisateurByEmail(String s) {
        User user = null;
        String req = "select * from `user` where email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = MyDB.createorgetInstance().getCon().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("adress"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("roles"),
                        resultSet.getInt("tel")
                        );

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
    //            String querry= "UPDATE `user` SET `email`='"+t.getEmail()+"',`nom`='"+t.getNom()+"',`roles`='"+t.getRoles()+"',`password`='"+t.getPassword()+"',`tel`='"+t.getTel()+"',`adress`='"+t.getAdress()+"'  ";

    
    
    
    
}
