/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karimfx;

import helpers.DbConnect;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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


public class CommenterExpController  {

    /**
     * Initializes the controller class.
     */
    int idSession = 1;
    @FXML
    private VBox ContainerPost1;

    @FXML
    private VBox CommenterContainer;

           @FXML
    private TextField comment;
       
     @FXML
    void Post(MouseEvent event) {
        
    }
    

    
    Post post=new Post();
    List<Commenter> commenters;

    private int id_exp;
     String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
    ObservableList<Commenter>  CommenterList = FXCollections.observableArrayList();
    ObservableList<Experience>  ExperienceList = FXCollections.observableArrayList();

    public int getId() {
        return id_exp;
    }

    public void setId(int id) {
        this.id_exp = id;
    }
   
   public void setDa(int id){
       setId(id);
       //System.out.println(id);
       
       setId(id);
       
       
   }
    
        
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////
   @FXML
    void Send(MouseEvent event) {
        String comm = comment.getText();
        System.out.println(comm);
        if (!comm.equals("")){
            try{
            query = "INSERT INTO commentaire (user,comment,blog_id) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idSession);
            preparedStatement.setString(2,comm);
            preparedStatement.setInt(3,getId());

            preparedStatement.executeUpdate();
            comment.setText("");
            
            
            
            
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
        }
        ContainerPost1.getChildren().clear();
        CommenterContainer.getChildren().clear();
        setDataPage();
        
        
    }
   
   
   
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setDataPage(){
        // TODO
        
        //ContainerPost1.getChildren().clear();
        
        try {  
            FXMLLoader fx = new FXMLLoader();
            
            
            
           
           
           
           //post = getPostss(a)  ;
           fx.setLocation(getClass().getResource("/karimfx/post.fxml"));
           VBox pub = fx.load();
           //System.out.println(post);
           PostController postController = fx.getController();
           
           postController.setData(getPostss(getId()));
           //System.out.println(b);
          /* if (a>0) 
           
            {
                //postController.setData(getPostss(8));
                /*
               System.out.println("hanny");
               int a = Integer.parseInt(b);
               
               System.out.println(a);
                
                
               
               postController.setData(getPostss(a));}
                
                               
            */
           
        //mController.setData(post);
       // mController.getPost(getId());
        //System.out.println(id);
        commenters = new ArrayList<>(getCommenters(getId()));
        //CommenterContainer.getChildren().clear();
                for (Commenter commenter:commenters){
                    
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("/karimfx/Commenter.fxml"));
                HBox cm = f.load();
                CommenterController commenterController = f.getController();
                String username = getnameuser(commenter.getId_user());
                
                //System.out.println(commenter.getContent());
                commenterController.setData(commenter,username);
                
        CommenterContainer.getChildren().add(cm);
                }
        //ContainerPost1.getChildren().clear();
        ContainerPost1.getChildren().add(pub);
            
        
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }/*
         commenters = new ArrayList<>(getCommenters());
        for(Commenter commenter:commenters){
            try{
           FXMLLoader fx = new FXMLLoader();
           
           
           fx.setLocation(getClass().getResource("/fxml/Commenter.fxml"));
           HBox pub = fx.load();
           
           //System.out.println(post);
           CommenterController commenterController = fx.getController();
           
           commenterController.setData(commenter);
           //PostController postController = new PostController();
           
           //postController.setData(post);
           CommenterContainer.getChildren().add(pub);
           //ExperiencesContainer.getChildren().clear();
        
       }catch (IOException e){
           e.printStackTrace();
           }
        } 
        */
        
        
        
       
    }
     public List<Post> getPosts(){
        List<Post> ls = new ArrayList<>();
        Post post;
         try {
            ExperienceList.clear();
            
            query = "SELECT * FROM `actualite`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
          post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        account.setName("me7rez");
        account.setProfileImg("/img/yossri.jpg");
        account.setVerified(true);
        post.setAccount(account);
        
        post.setDate(resultSet.getString("date"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("titre"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbComments(getnbComments (resultSet.getInt("id")));
        post.setNbShares(0);
        ls.add(post);
        }
         }catch(SQLException ex){
             
         }
        return ls;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    public int getnbComments (int id){
        int i =0;
     try {
         
            query = "SELECT * FROM `Commentaire` WHERE blog_id="+id;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                i++;
                
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return i;
        
    }
    public int getnbReact (int id){
        int i =0;
     try {
         
            query = "SELECT * FROM `react_exp` WHERE id_exp="+id;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                i++;
                
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return i;
        
    }
    
    
    
    
    public Post getPostss(int id){
         try {
             
            //System.out.println(id);
            query = "SELECT * FROM `actualite`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                if (id == resultSet.getInt("id") ){
                    
                     post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        account.setName("me7rez");
        account.setProfileImg("/img/yossri.jpg");
        account.setVerified(true);
        post.setAccount(account);
        
        post.setDate(resultSet.getString("date"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("titre"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbComments(getnbComments (resultSet.getInt("id")));
        post.setNbShares(0);
        }
            }
         }catch(SQLException ex){
             
         }
         return post;
    }
    public String getnameuser(int id)
    {
        try {
            query = "SELECT * FROM `user`";
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                
                if (resultSet1.getInt("id") == id){
                    //System.out.println(resultSet.getString("nom"));
                    
                   return resultSet1.getString("nom");
                    
                }
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return "";
        
    }
    
    
    
    
    
    
    
    
    
    public List <Commenter> getCommenters (int id){
        List<Commenter> ls = new ArrayList<>();
        Commenter commenter;
         try {
            CommenterList.clear();
            
            query = "SELECT * FROM `commentaire` WHERE blog_id="+id;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            
            while (resultSet1.next()){
                
                commenter = new Commenter();
                
                int user=resultSet1.getInt("user");
                
                commenter.setId(resultSet1.getInt("id"));
                commenter.setId_user(user);
                commenter.setId_exp(resultSet1.getInt("blog_id"));
                commenter.setContent(resultSet1.getString("comment"));
                  
        ls.add(commenter);
        }
         }catch(SQLException ex){
             
         }
        return ls;
        
        
    }


    
    
}
        
        
        
    
