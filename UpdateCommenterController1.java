package karimfx;

import helpers.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import objects.Commenter;

public class UpdateCommenterController1 {

    @FXML
    private TextField comment;
    
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;
    int id=31;
    

    @FXML
    void Updatebtn(MouseEvent event) {
        try{
            System.out.println(comment.getText());
            query = "UPDATE Commentaire set content=? WHERE id="+id;
            connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,comment.getText());
        preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        

    }

    void setDatas(int id, String content) {
            this.id = id;
            comment.setText(content);
            
       
    }
    
}
    


