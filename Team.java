package com.mycompany.sportifyfx.Model;

public class Team {
    private Integer id;
    private String nom;
    private String players;

    public Team(Integer id, String nom, String players) {
        this.id = id;
        this.nom = nom;
        this.players = players;
    }

    public Team(String nom, String players) {
        this.nom = nom;
        this.players = players;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }
    
    
}
