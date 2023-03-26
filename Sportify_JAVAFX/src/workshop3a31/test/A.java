/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a31.test;

/**
 *
 * @author Andrew
 */
public class A {
    //2 cree une variable static pour stocker l'instance
    public static A instance;
    
    
    
     //1 rendre le contructeur privee
    private A() {
    }
    
    //3  creer une meth static qui renvoie l'instance oubien elle cree une instance
    public static A getInstance(){
        if(instance ==null){
            instance = new A();
        }
       return instance;
    }
    
    
    
}
