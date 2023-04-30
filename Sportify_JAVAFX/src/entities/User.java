/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Andrew
 */
public class User {
    int id ;
    String nom ,prenom,adress,email,password,roles,image,isactive;
    int tel;

    public User() {
    }

    public User(int id, String nom, String prenom, String adress, String email, String password, String roles, int tel,String image,String isactive) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.password = password;
                this.password = password;
        this.roles = roles;

        
       // this.roles = "[\"" + roles.toUpperCase() + "\"]";
        this.tel = tel;
        this.image=image;
        this.isactive = isactive ;

    }
    
        public User( String nom, String prenom, String adress, String email, int tel,String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.tel = tel;
                this.image = image;


    }
    
    
    
    public User(int id, String nom, String prenom, String adress, String email, String password, String roles, int tel,String isactive) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.password = password;
                this.roles = roles;

      //  this.roles = "[\"" + roles.toUpperCase() + "\"]";
        this.tel = tel;
        this.isactive = isactive ;

    }
    /*
    public User(String nom, String prenom, String adress, String email, String password, String roles, int tel,String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.password = password;        
        this.roles = "[\"" + roles.toUpperCase() + "\"]";
        this.tel = tel;
        this.image=image;

    }
*/
        public User(String nom, String prenom, String adress, String email, String password, String roles, int tel,String isactive) {
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.password = password;        
                this.roles = roles;

       // this.roles = "[\"" + roles.toUpperCase() + "\"]";
        this.tel = tel;
        this.isactive = isactive;


    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
      public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adress=" + adress + ", email=" + email + ", password=" + password + ", roles=" + roles + ", tel=" + tel + '}';
    }


    
    // user actuellement connecter
    
            public static User user_connecter;
    
}
