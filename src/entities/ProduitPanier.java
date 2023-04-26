/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class ProduitPanier {
    private int id;
    Produits produit;
    private int quantite;
    private float total;

    public ProduitPanier(int id,Produits produit , int quantite,float total) {
        this.id = id;
       this.produit=produit;
        this.quantite = quantite;
        this.total = total;
    }

    public ProduitPanier() {
    }

    public ProduitPanier(int id, String quantite, float total, Produits produit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ProduitPanier(int id, int quantite, float total, Produits produit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produits getProduit() {
        return produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ProduitPanier(Produits produit, int quantite, float total) {
        this.produit = produit;
        this.quantite = quantite;
        this.total = total;
    }

    

    
}
