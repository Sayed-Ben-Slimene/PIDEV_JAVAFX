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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static services.LoginSession.id;
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
    public void AjouterUser(User t) {
        try {
            String req = "INSERT INTO user (nom , prenom , adress , email , password , roles , tel , isactive ) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(req);
       ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
                        ps.setString(3, t.getAdress());

            ps.setString(4, t.getEmail());

            ps.setString(5, t.getPassword());
                 ps.setString(6, t.getRoles());
            ps.setInt(7, t.getTel());
            ps.setString(8, t.getIsactive());
            

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess ");
            }

        } catch (Exception ex) {
        System.out.println("service classe ajouter methode  ");
        }

    }
    
    
    
    
    
    
    
          public String Gettype(String s) {
        String s1 = "";
        PreparedStatement preparedStatement;
        try {
            
          PreparedStatement st = con.prepareStatement("SELECT roles FROM `user` where id=?");

           
            st.setString(1, s);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString("roles");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s1;
    }
     
    public boolean ajouter(User t) {
        
        try {
       
            ste = con.createStatement();
            
            String req = "INSERT INTO `sportify_pidev`.`user` (`nom`,`prenom`,`adress`,`email`,`password`,`roles`,`tel`,`isactive`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdress()+"','"+t.getEmail()+"','"+t.getPassword()+"','"+t.getRoles()+"','"+t.getTel()+",'"+t.getIsactive()+"');";
            
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

        public void logout(){
        LoginSession.IsLogged=false;
    }
        
        public String getPhotobyId(int id) {
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM `user` where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("image");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }    
    
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
                String isactive =res.getString("isactive");

                User p = new User(id, nom, prenom,adress, email,password,roles,tel,isactive);
                users.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return  users;
       
    }
    
    public List<User> getNutriById(){
        
        List<User> list = new ArrayList<User>();
        int count =0;
        
        String requete="select * from user ";
         try{
            Statement st =con.createStatement();
            ResultSet res = st.executeQuery(requete);
            
            while (res.next()){
                
                int id = res.getInt("id");
                String nom = res.getString(2);
                String prenom =res.getString("prenom");
                int tel = res.getInt(4);
                String email =res.getString("email");                
                String password =res.getString("password");                
                String adress =res.getString("adress");
                String roles =res.getString("roles");
                String isactive =res.getString("isactive");
                User p = new User(id, nom, prenom,adress, email,password,roles,tel,isactive);

                list.add(p);
                
                count++;
            }
            if(count == 0){
                return null;
           }else
            {
               return list;
            }
         }
        catch (SQLException ex) {
System.out.println(ex.getMessage());
return null;
        }
     }
    
    
    
    
    @Override
    public List<User> afficherUtilisateurs() {
        
        
        List<User> list_Utilisateur = new ArrayList<>();

        try {
            String req = "select * from user";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString("email"));
                user.setRoles(rs.getString("roles"));
                user.setPassword(rs.getString("password"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdress(rs.getString("adress"));
                user.setTel(rs.getInt("tel"));

                                user.setIsactive(rs.getString("isactive"));


                list_Utilisateur.add(user);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list_Utilisateur;

    }
    
        
    @Override
    public void modifierUtilisateur(User t, int id) {

        try {
            String req = "UPDATE user set email = ? , nom = ? ,prenom = ?, adress = ?, tel = ? , image= ? WHERE id =" + id;
            PreparedStatement ps = con.prepareStatement(req);

            ps.setString(1, t.getEmail());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getAdress());
            ps.setInt(5, t.getTel());
            ps.setString(6, t.getImage());




            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la modification de l utilisateur :" + t.getNom() + " " + t.getPrenom() + " a ete effectuer avec sucess");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    @Override
    public void modifier(String t2,User t) {
       
        try {
        String querry= "UPDATE `User` SET `email`='"+t.getEmail()+"',`nom`='"+t.getNom()+"',`prenom`='"+t.getPrenom()+"' ,`adress`='"+t.getAdress()+"',`tel`='"+t.getTel()+"',`roles`='"+t.getRoles()+"',`password`='"+t.getPassword()+"' WHERE email='"+t2+"'";
        Statement stm = con.createStatement();
    
        stm.executeUpdate(querry);
        if(stm.executeUpdate(querry)==1){
            System.out.print("user modifier");
        }
    
        } catch (SQLException ex) {
            System.out.println("service classe modif methode  ");
            System.out.println(ex.getMessage());

        }
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
                
                                LoginSession.isactive=rs.getString("isactive");

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
            System.out.println("User deleted !");
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
                        resultSet.getInt("tel"),
                        
                        resultSet.getString("isactive")

                        
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
         public String encrypt(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decrypt(String password) {

        return new String(Base64.getMimeDecoder().decode(password));
    }
        public void RolesUserToORGANISATEUR(int id) {
        try {
            String req = "UPDATE `user` SET `roles` = '[\"ROLE_ORGANISATEUR\"]' WHERE id =" + id;
            PreparedStatement ps = con.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la Rloles de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void RolesUserToUSER(int id) {
        try {
            String req = "UPDATE `user` SET `roles` = '[\"ROLE_USER\"]' WHERE id =" + id;
            PreparedStatement ps = con.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la Rloles user de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    public void DescativerUser(int id) {
        try {
            String req = "Update user set isactive = 'desactive' where id =" + id;
            PreparedStatement ps = con.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la desactivation de l utilisateur   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActiverUser(int id) {
        try {
            String req = "Update user set isactive = 'active' where id =" + id;
            PreparedStatement ps = con.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
            System.out.println(" la activation de l utilisateur   a ete effectuer avec sucess");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    //            String querry= "UPDATE `user` SET `email`='"+t.getEmail()+"',`nom`='"+t.getNom()+"',`roles`='"+t.getRoles()+"',`password`='"+t.getPassword()+"',`tel`='"+t.getTel()+"',`adress`='"+t.getAdress()+"'  ";

    public User findUserByLogin(String email, String password) {
        User u = null;

        try {
            String sql = "select * from `user` where email =? and password=?";
            PreparedStatement ste=con.prepareStatement(sql);
            ste.setString(1,email);
            ste.setString(2,password);
            ResultSet s = ste.executeQuery();
            if (s.next()) {

                u = new User(s.getInt(1),
                            s.getString("nom"),
                        s.getString("prenom"),
                            s.getString("email"),
                        s.getString("password"),
                        s.getString("adress"),
                         s.getString("roles"),
                        s.getInt("tel"), s.getString("isactive"));

                }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    
    public ObservableList<User> rechercherUser(String n){
        
        //List<User> users = new ArrayList();
        ObservableList<User> users =FXCollections.observableArrayList();
        try {
        String querry ="SELECT id,nom,prenom,tel,adress,email,roles,password FROM `user` where email like '%"+n+"%' or nom like '%"+n+"%' or prenom like '%"+n+"%' or roles like '%"+n+"%'";
        Statement stm = con.createStatement();
        ResultSet rs= stm.executeQuery(querry);
        System.out.println(querry);
        while (rs.next()){
            User p = new User();
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setPrenom(rs.getString(3));
            
                        p.setTel(rs.getInt(4));
                        p.setAdress(rs.getString(5));
            
        
            p.setEmail(rs.getString(6));
            p.setRoles(rs.getString(8));
            p.setPassword(rs.getString(7));
            users.add(p);
        }
        
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return users;
    }

    @Override
    public boolean Ajouter(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
