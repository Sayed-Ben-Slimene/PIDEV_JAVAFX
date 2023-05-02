/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garafx;

import helpers.DbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Th3_D0cTor
 */
public class AddresController  {

    /**
     * Initializes the controller class.
     */
   
     @FXML
    private TextField comment;
      String query = null;
    Connection connection = DbConnect.getConnect() ;
    int idSession = 2;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
 
    int idevent;
    int sessionid = 2;
 public int getnbpart (){
        int i =0;
     try {
         
            query = "SELECT * FROM `reservation` WHERE event_id="+idevent;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                i=i+resultSet1.getInt("nb_place");
                
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return i;
        
    }
 
 public int getcap() throws SQLException {
    int capacite = 0;
        query = "SELECT * FROM `evenement` WHERE id=" + idevent;
        preparedStatement = connection.prepareStatement(query);
        resultSet1 = preparedStatement.executeQuery();
        if (resultSet1.next()) {
            capacite = resultSet1.getInt("capacite");
        }
    
    return capacite;
}
 
 
public static boolean estNumerique(String chaine) {
    return chaine.matches("[0-9]+");
}
    @FXML
    void Updatebtn(MouseEvent event) throws SQLException {
        
        System.out.println(getcap());
        System.out.println(getnbpart ());
        if (estNumerique(comment.getText())&& Integer.parseInt(comment.getText()) < getcap() -getnbpart ()){
        try{
            System.out.println(comment.getText());
            query = "INSERT INTO reservation  (nb_place,event_id,user_id) VALUES (?,?,?)";
            connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,Integer.parseInt(comment.getText()));
        preparedStatement.setInt(2,idevent);
        preparedStatement.setInt(3,sessionid);
        preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }else {
            System.err.println("erroooooooooooorrr");
        }
        
        
        

    }
     void setDatas(int id) {
            this.idevent = id;
       
    }
    
    
}
