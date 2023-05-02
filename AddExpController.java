/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karimfx;


import helpers.DbConnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import objects.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author Th3_D0cTor
 */
public class AddExpController  {
      /**
     * Initializes the controller class.
     */

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private Button upBtn;


        @FXML
    private Label path;
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
        
        destinationFile = new File("C:/Users/youssef/Downloads/karimFX/src"+newPath+"/"+nameimg+"."+extension);
        Files.copy(sourceFile.toPath(),destinationFile.toPath());
        System.out.println(sourceFile.toPath());
        System.out.println(destinationFile.toPath());
        path.setText(newPath+"/"+nameimg+"."+extension);
        
        
    } 
     @FXML
    void Post(MouseEvent event) {
        String titl = title.getText();
        String descr = description.getText();
        String img = path.getText();
        System.out.println(descr);
        if (!titl.equals("")&& !descr.equals("")){
            
        
        try{
            query = "INSERT INTO actualite (titre,description,img) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,titl);
            preparedStatement.setString(2,descr);
            preparedStatement.setString(3,img);
            preparedStatement.executeUpdate();
            title.setText("");
            description.setText("");
            
        }catch (SQLException ex) {
            
            System.err.println(ex.getMessage());
        }
        JavaMailUtil ml = new JavaMailUtil();
        ml.message("karim.benyoussef@esprit.tn", "new Post added ");
        }
        
        
    }
    
  
   

    
    
}
