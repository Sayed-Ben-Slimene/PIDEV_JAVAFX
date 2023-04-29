/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class commande {
    int id ;
    String nom,prenom;
    int num_tel;
    String adresse;
    float prix_total;
    String email;
    int mode_paiement;

    public commande(int id, String nom, String prenom, int num_tel, String adresse, float prix_total, String email, int mode_paiement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.prix_total = prix_total;
        this.email = email;
        this.mode_paiement = mode_paiement;
    }

    public commande() {
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

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(int mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public commande(String nom, String prenom,int num_tel, String adresse, String email,float prix_total, int mode_paiement) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.prix_total = prix_total;
        this.email = email;
        this.mode_paiement = mode_paiement;
    }
    
}
