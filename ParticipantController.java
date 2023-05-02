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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.Account;
import objects.Commenter;
import objects.Experience;
import objects.Post;
import objects.PostAudience;

/**
 * FXML Controller class
 *
 * @author Th3_D0cTor
 */
public class ParticipantController  {

    int idSession=2;

    /**
     * Initializes the controller class.
     */
   
    

      @FXML
    private ImageView imgProfile;

    @FXML
    public Label username;

    @FXML
    public Label content;
       @FXML
    private Label error1;
       
       @FXML
    private Label nbp;

    @FXML
    private Label email;

    @FXML
    private Label dt;
    
    private Commenter commenter;
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;


    public void setData(Commenter commenter,String name) { 
        this.commenter = commenter;
        username.setText(name);
        content.setText(commenter.getContent());
        nbp.setText(String.valueOf(commenter.getNb_place()));
    }
     @FXML
    void UpdateBtn(MouseEvent event) throws SQLException {
        if (idSession == commenter.getId_user()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/garafx/UpdateCommenter.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                UpdateCommenterController uptexpController = fxmlLoader.getController();
                System.out.println(commenter.getContent());
                uptexpController.setDatas(commenter.getId(),String.valueOf(commenter.getNb_place()));
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
            System.out.println("Cant load Exp Upadate page");
            
        }
        
        }else {
            error1.setText("Error,it s not urs !!");
        }
    }
       @FXML
    void DeleteBtn(MouseEvent event) throws SQLException {
        
        if (idSession == commenter.getId_user()) {
        query = "DELETE FROM `reservation` WHERE id  ="+commenter.getId();
        connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        }else {
            error1.setText("Error,it s not urs !!");
        }
    }
    
    
  
    
    

    
}
