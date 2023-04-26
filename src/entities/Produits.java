/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author LENOVO
 */
public class Produits {
     int id ;
     String title ,description;
     float prix;
     String photo;
     int published;
     private Categorie categorie;

    public Produits() {
    }

    public Produits(int id, String title, String description, float prix, String photo, int published, Categorie categorie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
        this.published = published;
        this.categorie = categorie;
    }

    public Produits(String title, String description, float prix, String photo, int published, Categorie categorie) {
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
        this.published = published;
        this.categorie = categorie;
    }

    public Produits(int id, String title, String description, float prix, Image image, int published, Categorie categorie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Produits(String produitTitle, float prix) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Produits(String produitTitle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Produits(int produitId, String produitTitle, String produitDescription, Categorie categorie, boolean produitPublished, float produitPrix, String produitPhoto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

  

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

  

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

   public Categorie getCategorie() {
    return categorie;
}

    public ProduitPanier addToCart(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}