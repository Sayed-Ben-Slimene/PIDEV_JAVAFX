/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author sayed
 * @param <T>
 */
public interface IService <T> {
    
    public boolean  Ajouter(T t);
    public void  Ajouter2(T t);
    public List<T> afficher();
    public void  modifier (String t,T a);
    public void supprimer (T t);
    //public void TruncateTable();
}
