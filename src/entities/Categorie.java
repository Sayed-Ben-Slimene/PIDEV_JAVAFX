/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class Categorie {
    int id;
    String label;

    public Categorie() {
    }

    public Categorie(int id, String label) {
        this.id = id;
        this.label = label;
    }
    public Categorie(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

   
    
    
}
