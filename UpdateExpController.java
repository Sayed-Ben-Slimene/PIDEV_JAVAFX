/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karimfx;


import helpers.DbConnect;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import objects.Post;

/**
 * FXML Controller class
 *
 * @author Th3_D0cTor
 */
public class UpdateExpController  {
   
    

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private Button upBtn;

    @FXML
    private Label path;
    private Post post=new Post();
    int id;
    
    
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;
    
     public String randomname ()
    {
	String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String randomString = "";
	int length = 5;

	Random rand = new Random();

	char[] text = new char[length];
	for(int i=0;i<length; i++){
		text[i] = characters.charAt(rand.nextInt(characters.length()));

	}

	for(int i=0;i<length; i++){
		randomString += text[i];
	}


	return randomString;
    }
     @FXML
    void loadimg(MouseEvent event) throws IOException {
        
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File f=fc.getSelectedFile();
        String filename = f.getAbsolutePath();
        filename.replaceAll("\\\\", "/");
        path.setText(filename);
        String newPath = "/img";
        File directory = new File(newPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        File sourceFile = null;
        File destinationFile = null;
        String extension = filename.substring(filename.lastIndexOf('.')+1);
        sourceFile = new File(filename);
        String nameimg=randomname();
        destinationFile = new File("C:/Users/Th3_D0cTor/OneDrive/Documents/NetBeansProjects/backup/New folder/karimFX/src"+newPath+"/"+nameimg+"."+extension);
        Files.copy(sourceFile.toPath(),destinationFile.toPath());
        System.out.println(sourceFile.toPath());
        System.out.println(destinationFile.toPath());
        path.setText(newPath+"/"+nameimg+"."+extension);
        
        
    } 
    @FXML
    void Post(MouseEvent event) {
        try{
        query = "UPDATE actualite set titre=?,description=?  , img=? WHERE id="+id;
        connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,title.getText());
        preparedStatement.setString(2,description.getText());
        preparedStatement.setString(3,path.getText());
        preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        

    }


    
    /**
     * Initializes the controller class.
     */
 
    
    public void setDatas(int id,String tit,String descr){
        title.setText(tit);
        description.setText(descr);
        this.id= id;
        
    }

}
