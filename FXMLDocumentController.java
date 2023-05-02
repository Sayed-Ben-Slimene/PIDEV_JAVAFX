package karimfx;

import helpers.DbConnect;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import objects.Account;
import objects.Experience;
import objects.Post;
import objects.PostAudience;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class FXMLDocumentController {
    
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    int idSession = 1;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
    Experience student = null ;
    ObservableList<Experience>  ExperienceList = FXCollections.observableArrayList();
        List<Post> posts;

    @FXML
    private Button experiences;

    @FXML
    private Button mailer;

    @FXML
    private VBox LeftContainer;

    @FXML
    private Label usernamelabel;

    @FXML
    private VBox ExperiencesContainer;
    
    @FXML
    private TextField search;
    private Post post;

    
    @FXML
    void EventMur(ActionEvent event) throws IOException {
    posts = new ArrayList<>(getPosts());
   FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/karimfx/addExp.fxml"));
    AddExpController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    
    
       
       posts = new ArrayList<>(getPosts());
        for(Post post:posts){
            try{
           FXMLLoader fx = new FXMLLoader();
           
           
           fx.setLocation(getClass().getResource("post.fxml"));
           VBox pub = fx.load();
           
           //System.out.println(post);
           PostController postController = fx.getController();
           
           postController.setData(post);
           //PostController postController = new PostController();
           
           //postController.setData(post);
           ExperiencesContainer.getChildren().add(pub);
           //ExperiencesContainer.getChildren().clear();
        
       }catch (IOException e){
           e.printStackTrace();
           }}
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
        account.setName("karim");
        account.setProfileImg("/img/yossri.jpg");
        account.setVerified(true);
        post.setAccount(account);
        
        post.setDate(resultSet.getString("date"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("titre"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        post.setTotalReactions(getnbReact (resultSet.getInt("id")));
         post.setNbComments(getnbComments (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
       
         post.setNbShares(getnbshares(resultSet.getInt("id")));
        ls.add(post);
        }
         }catch(SQLException ex){
             
         }
        return ls;
    }
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
         
            query = "SELECT * FROM `react_act` WHERE id_act="+id;
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
     
    public List<Post> searchh(String critere, String ch) {
    List<Post> ls = new ArrayList<>();
    Post post;
    try {
        // Clear any previous items in the list
        ls.clear();

        // Construct the SQL query using the given search criteria
        String query = "SELECT * FROM `actualite` WHERE " + critere + " LIKE '%" + ch + "%'";

        // Prepare the statement and execute the query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate over the result set and create Post objects for each row
        while (resultSet.next()) {
            post = new Post();
            Account account = new Account();

            // Set account details (e.g. name, profile image, verified status)
            account.setName("karim");
            account.setProfileImg("/img/yossri.jpg");
            account.setVerified(true);

            // Set post details (e.g. date, audience, title, caption, image, ID, number of shares)
            post.setAccount(account);
            post.setDate(resultSet.getString("date"));
            post.setAudience(PostAudience.PUBLIC);
            post.setTitle(resultSet.getString("titre"));
            post.setCaption(resultSet.getString("description"));
            post.setImage(resultSet.getString("img"));
            post.setId(resultSet.getInt("id"));
             post.setTotalReactions(getnbReact (resultSet.getInt("id")));
         post.setNbComments(getnbComments (resultSet.getInt("id")));
             post.setNbShares(getnbshares(resultSet.getInt("id")));

            // Add the completed Post object to the list of results
            ls.add(post);
        }
    } catch (SQLException ex) {
        // Handle any SQLException that may occur (e.g. logging the error)
        ex.printStackTrace();
    }
    // Return the list of results
    return ls;
}

     
      @FXML
    void rech(ActionEvent event) throws IOException {
        
   FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/karimfx/addExp.fxml"));
    AddExpController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(searchh("titre",ch));
        for(Post post:posts){
                System.out.println(post.getId());

            try{
           FXMLLoader fx = new FXMLLoader();
           
           
           fx.setLocation(getClass().getResource("post.fxml"));
           VBox pub = fx.load();
           
           //System.out.println(post);
           PostController postController = fx.getController();
           
           postController.setData(post);
           //PostController postController = new PostController();
           
           //postController.setData(post);
           ExperiencesContainer.getChildren().add(pub);
           //ExperiencesContainer.getChildren().clear();
        
       }catch (IOException e){
           e.printStackTrace();
           }}
    }
    
    public List<Post> tri() {
    List<Post> ls = new ArrayList<>();
    Post post;
    try {
        // Construct the SQL query to sort the 'actualite' table by date in ascending order
        String query = "SELECT * FROM `actualite` ORDER BY date ASC";

        // Prepare the statement and execute the query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate over the result set and create Post objects for each row
        while (resultSet.next()) {
            post = new Post();
            Account account = new Account();

            // Set account details (e.g. name, profile image, verified status)
            account.setName("karim");
            account.setProfileImg("/img/yossri.jpg");
            account.setVerified(true);

            // Set post details (e.g. date, audience, title, caption, image, ID, number of shares)
            post.setAccount(account);
            post.setDate(resultSet.getString("date"));
            post.setAudience(PostAudience.PUBLIC);
            post.setTitle(resultSet.getString("titre"));
            post.setCaption(resultSet.getString("description"));
            post.setImage(resultSet.getString("img"));
            post.setId(resultSet.getInt("id"));
             post.setTotalReactions(getnbReact (resultSet.getInt("id")));
         post.setNbComments(getnbComments (resultSet.getInt("id")));
            post.setNbShares(getnbshares(resultSet.getInt("id"))); // The number of shares is not retrieved in this code snippet

            // Add the completed Post object to the list of results
            ls.add(post);
        }
    } catch (SQLException ex) {
        // Handle any SQLException that may occur (e.g. logging the error)
        ex.printStackTrace();
    }
    // Return the list of results
    return ls;
}
    @FXML
    void tridate(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/karimfx/addExp.fxml"));
    AddExpController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(tri());
        for(Post post:posts){
                System.out.println(post.getId());

            try{
           FXMLLoader fx = new FXMLLoader();
           
           
           fx.setLocation(getClass().getResource("post.fxml"));
           VBox pub = fx.load();
           
           //System.out.println(post);
           PostController postController = fx.getController();
           
           postController.setData(post);
           //PostController postController = new PostController();
           
           //postController.setData(post);
           ExperiencesContainer.getChildren().add(pub);
           //ExperiencesContainer.getChildren().clear();
        
       }catch (IOException e){
           e.printStackTrace();
           }}
        
        

    }
    
    
    
    
     public int getnbshares (int id){
        int i =0;
     try {
         
            query = "SELECT * FROM `fvt_act` WHERE id_act="+id;
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
     
      @FXML
    void fav(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/karimfx/addExp.fxml"));
    AddExpController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(getPostssaved());
        for(Post post:posts){
                System.out.println(post.getId());

            try{
           FXMLLoader fx = new FXMLLoader();
           
           
           fx.setLocation(getClass().getResource("post.fxml"));
           VBox pub = fx.load();
           
           //System.out.println(post);
           PostController postController = fx.getController();
           
           postController.setData(post);
           //PostController postController = new PostController();
           
           //postController.setData(post);
           ExperiencesContainer.getChildren().add(pub);
           //ExperiencesContainer.getChildren().clear();
        
       }catch (IOException e){
           e.printStackTrace();
           }}
        
        }
    
    
    
    
    public List<Post> getPostssaved(){
        List<Post> ls = new ArrayList<>();
        Post post;
         try {
            ExperienceList.clear();
            
            query = "SELECT * FROM `fvt_act`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                if (resultSet.getInt("id_user") == idSession){
                    post = getPost(resultSet.getInt("id_act"));
                    ls.add(post);
                }
        }
         }catch(SQLException ex){
             
         }
        return ls;
    }
    
    
     public Post getPost(int id){
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
        post.setNbShares(getnbshares(resultSet.getInt("id")));
        }
            }
         }catch(SQLException ex){
             
         }
         return post;
    }
     
    @FXML
    void print(ActionEvent event)throws SQLException, FileNotFoundException, IOException {    

        try {
       com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\youssef\\Downloads\\karimFX\\Publication.pdf"));  
       doc.open();
       
    doc.add(new Paragraph(" "));
       
       Paragraph p = new Paragraph("liste des Actualite  ");
       p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));

       PdfPTable tabpdf = new PdfPTable(5);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("id"));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("titre"));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("description"));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("date"));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       cell = new PdfPCell(new Phrase("img"));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       
       
       query = "SELECT * FROM `actualite`";
         preparedStatement = connection.prepareStatement(query);
         resultSet = preparedStatement.executeQuery();
      
          
      while (resultSet.next()) {
           cell = new PdfPCell(new Phrase(resultSet.getString("id")));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(resultSet.getString("titre")));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           cell = new PdfPCell(new Phrase(resultSet.getString("description")));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           cell = new PdfPCell(new Phrase(resultSet.getString("date")));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           cell = new PdfPCell(new Phrase(resultSet.getString("img")));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
       }
     
   
          doc.add(tabpdf);
          JOptionPane.showMessageDialog(null, "Votre fichier a ete exporter avec succes");
          doc.close();
          Desktop.getDesktop().open(new File("C:\\Users\\youssef\\Downloads\\karimFX\\Publication.pdf"));
       }
 
        catch (DocumentException | HeadlessException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }


} 


}
