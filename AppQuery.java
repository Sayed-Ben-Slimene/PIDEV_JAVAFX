/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sportifyfx.data;

import java.sql.ResultSet;
import java.sql.Statement;

import com.mycompany.sportifyfx.Model.Vote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fekih
 */
public class AppQuery {
    
    private DBConnection c = new DBConnection();
    
    
    
    public void addTeam(com.mycompany.sportifyfx.Model.Team team){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("insert into team(nom,players)values(?,?)");
            ps.setString(1, team.getNom());
            ps.setString(2, team.getPlayers());
            ps.execute();
            ps.close();
            DBConnection.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------
    
    public void addMatch(com.mycompany.sportifyfx.Model.Match match){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("INSERT INTO `match`(`team1`, `team2`, `score1`, `score2`) VALUES (?,?,?,?)");
            ps.setString(1, match.getTeam1());
            ps.setString(2, match.getTeam2());
            ps.setString(3, match.getScore1());
            ps.setString(4, match.getScore2());
            ps.execute();
            ps.close();
            DBConnection.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------


    public void addVote(com.mycompany.sportifyfx.Model.Vote vote){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("INSERT INTO `vote`(`team1`, `team2`, `score1`, `score2`) VALUES (?,?,?,?)");
            ps.setString(1, vote.getTeam1());
            ps.setString(2, vote.getTeam2());
            ps.setString(3, vote.getScore1());
            ps.setString(4, vote.getScore2());
            ps.execute();
            ps.close();
            DBConnection.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------
    
    public void addUMatch(com.mycompany.sportifyfx.Model.UMatch umatch){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("INSERT INTO `umatch`(`team1`, `team2`) VALUES (?,?)");
            ps.setString(1, umatch.getTeam1());
            ps.setString(2, umatch.getTeam2());
            ps.execute();
            ps.close();
            DBConnection.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------
    
    public ObservableList<com.mycompany.sportifyfx.Model.Team> getTeamList(){
        ObservableList<com.mycompany.sportifyfx.Model.Team> teamList = FXCollections.observableArrayList();
        try{
            String query ="select id, nom, players from team order by nom asc";
            c.getDBConn();
            Statement st =c.getCon().createStatement();
            ResultSet rs =st.executeQuery(query);
            com.mycompany.sportifyfx.Model.Team s;
            while(rs.next()){
                s =new com.mycompany.sportifyfx.Model.Team(rs.getInt("id"), rs.getString("nom"), rs.getString("players"));
                teamList.add(s);
            }   
            rs.close();
            st.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return teamList;
    }
    
    
    //---------------------------------------------------------
    
    public ObservableList<com.mycompany.sportifyfx.Model.Match> getMatchList(){
        ObservableList<com.mycompany.sportifyfx.Model.Match> matchList = FXCollections.observableArrayList();
        try{
            String query ="SELECT `id`, `team1`, `team2`, `score1`, `score2` FROM `match` ORDER BY `team1` ASC";
            c.getDBConn();
            Statement st =c.getCon().createStatement();
            ResultSet rs =st.executeQuery(query);
            com.mycompany.sportifyfx.Model.Match s;
            while(rs.next()){
                s =new com.mycompany.sportifyfx.Model.Match(rs.getInt("id"),rs.getString("team1"),rs.getString("team2"), rs.getString("score1"), rs.getString("score2"));
                matchList.add(s);
            }   
            rs.close();
            st.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return matchList;
    }
    
    //---------------------------------------------------------
    
    public ObservableList<com.mycompany.sportifyfx.Model.UMatch> getUMatchList(){
        ObservableList<com.mycompany.sportifyfx.Model.UMatch> umatchList = FXCollections.observableArrayList();
        try{
            String query ="SELECT `id`, `team1`, `team2` FROM `umatch` ORDER BY `team1` ASC";
            c.getDBConn();
            Statement st =c.getCon().createStatement();
            ResultSet rs =st.executeQuery(query);
            com.mycompany.sportifyfx.Model.UMatch s;
            while(rs.next()){
                s =new com.mycompany.sportifyfx.Model.UMatch(rs.getInt("id"),rs.getString("team1"),rs.getString("team2"));
                umatchList.add(s);
            }   
            rs.close();
            st.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return umatchList;
    }
    
    //---------------------------------------------------------
    //---------------------------------------------------------

    public ObservableList<Vote> getVoteList(){
        ObservableList<com.mycompany.sportifyfx.Model.Vote> voteList = FXCollections.observableArrayList();
        try{
            String query ="SELECT `id`, `team1`, `team2`, `score1`, `score2` FROM `vote` ORDER BY `team1` ASC";
            c.getDBConn();
            Statement st =c.getCon().createStatement();
            ResultSet rs =st.executeQuery(query);
            com.mycompany.sportifyfx.Model.Vote s;
            while(rs.next()){
                s =new com.mycompany.sportifyfx.Model.Vote(rs.getInt("id"),rs.getString("team1"),rs.getString("team2"), rs.getString("score1"), rs.getString("score2"));
                voteList.add(s);
            }
            rs.close();
            st.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return voteList;
    }
    public void updateTeam(com.mycompany.sportifyfx.Model.Team team){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("UPDATE `team` SET `nom` = ?,`players` = ? WHERE `id` = ?  ");
            ps.setString(1, team.getNom());
            ps.setString(2, team.getPlayers());
            ps.setInt(3, team.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------
    
    public void updateMatch(com.mycompany.sportifyfx.Model.Match match){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("UPDATE `match` SET `team1` = ?,`team2` = ?,`score1` = ?,`score2` = ? WHERE `id` = ?  ");
            ps.setString(1, match.getTeam1());
            ps.setString(2, match.getTeam2());
            ps.setString(3, match.getScore1());
            ps.setString(4, match.getScore2());
            ps.setInt(5, match.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------
    
    
    public void updateUMatch(com.mycompany.sportifyfx.Model.UMatch umatch){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("UPDATE `umatch` SET `team1` = ?,`team2` = ? WHERE `id` = ?  ");
            ps.setString(1, umatch.getTeam1());
            ps.setString(2, umatch.getTeam2());
            ps.setInt(3, umatch.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------

    public void deleteTeam(com.mycompany.sportifyfx.Model.Team team){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM `team` WHERE `id`= ?");
            ps.setInt(1, team.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    //---------------------------------------------------------
    
    
    public void deleteMatch(com.mycompany.sportifyfx.Model.Match match){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM `match` WHERE `id`= ?");
            ps.setInt(1, match.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //---------------------------------------------------------


    public void deleteVote(com.mycompany.sportifyfx.Model.Vote vote){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM `vote` WHERE `id`= ?");
            ps.setInt(1, vote.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //---------------------------------------------------------
    
    
    public void deleteUMatch(com.mycompany.sportifyfx.Model.UMatch umatch){
        try{
            c.getDBConn();
            java.sql.PreparedStatement ps = c.getCon().prepareStatement("DELETE FROM `umatch` WHERE `id`= ?");
            ps.setInt(1, umatch.getId());
            ps.execute();
            ps.close();
            c.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}



















