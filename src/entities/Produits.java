/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class Produits {
     int id ;
     String title ,description;
     float prix;
     int categorieId; // champ pour stocker l'ID de la catégorie sélectionnée
     String photo;
     boolean published;

    public Produits() {
    }

    public Produits(int id, String title, String description, float prix, int categorieId, String photo, boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.categorieId = categorieId;
        this.photo = photo;
        this.published = published;
    }

    public Produits(String title, String description, float prix, int categorieId, String photo, boolean published) {
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.categorieId = categorieId;
        this.photo = photo;
        this.published = published;
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

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}