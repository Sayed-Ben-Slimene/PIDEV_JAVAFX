/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sportify;

import java.security.Provider;
import entities.User;
import services.ServiceUser;
import workshop3a31.test.A;
import utils.MyDB;

/**
 *
 * @author Andrew
 */
public class Sportify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      /* A a1 = A.getInstance();
       A a2 = A.getInstance();
       
       
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());*/
      
      
        MyDB.createorgetInstance();
        
        User p1 =new User("Kalbousi", "Yassine","nabeul","sayedjava@gmail.com","sayed12345","[\"ROLE_ADMIN\"]", 25365241);
        System.out.println("user ajouter");
        
        
        ServiceUser sp = new ServiceUser();
        
        
        sp.Ajouter(p1);
       // sp.Ajouter2(p2);
        //System.out.println(sp.Afficher());
    }
    
}
