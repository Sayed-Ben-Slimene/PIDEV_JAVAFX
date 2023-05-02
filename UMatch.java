package com.mycompany.sportifyfx.Model;

public class UMatch {
    private Integer id;
    private String team1;
    private String team2;
    
    
    
    public UMatch(Integer id, String team1, String team2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
    }

    public UMatch( String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

   
    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    
    
    
    
   
}
