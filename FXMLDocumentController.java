package garafx;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import objects.Account;
import objects.Commenter;
import objects.Experience;
import objects.Post;
import objects.PostAudience;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class FXMLDocumentController {
    
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    int idSession = 2;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
    Experience student = null ;
    ObservableList<Experience>  ExperienceList = FXCollections.observableArrayList();
        Post post;
        List<Post> posts;
     
        
        List<Commenter> participants;
    

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
    
    
     ////////////////////////////////////////////////////////////////////////////
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
    
    @FXML
    void EventMur(ActionEvent event) throws IOException {
        usernamelabel.setText(getnameuser(idSession));
    posts = new ArrayList<>(getPosts());
   FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("addevent.fxml"));
    AddEventController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    
    
    
    
       
       posts = new ArrayList<>(getPosts());
       
       
        for(Post post:posts){
            participants= new ArrayList<>(getParticipant(post.getId()));
            
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
           
           for(Commenter part:participants){
                FXMLLoader fx1 = new FXMLLoader();
           
           
                fx1.setLocation(getClass().getResource("participant.fxml"));
                HBox p = fx1.load();
           
           //System.out.println(post);
                ParticipantController participantController = fx1.getController();
           
                participantController.setData(part,getnameuser(part.getId_user()));
           //PostController postController = new PostController();
           
           //postController.setData(post);
                ExperiencesContainer.getChildren().add(p);
               
           }
           
          
           
        
       }catch (IOException e){
           e.printStackTrace();
           }}
    }
     public int getnbpart (int id){
        int i =0;
     try {
         
            query = "SELECT * FROM `reservation` WHERE event_id="+id;
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
     
   
    
    public List<Commenter> getParticipant(int eventid){
        List<Commenter> ls = new ArrayList<>();
        Commenter commenter;
         try {
            ExperienceList.clear();
            
            query = "SELECT * FROM `reservation` WHERE event_id="+eventid;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                commenter = new Commenter();
                commenter.setId(resultSet.getInt("id"));
                commenter.setId_exp(resultSet.getInt("event_id"));
                commenter.setId_user(resultSet.getInt("user_id"));
                commenter.setContent(resultSet.getString("date_res"));
                commenter.setNb_place(resultSet.getInt("nb_place"));
                
                ls.add(commenter);
            
            }
            
            
            
            
            
         
         }catch(SQLException ex){
             
         }
            
            
            
            return ls;
        
    }
    
    
     public List<Post> getPosts(){
        List<Post> ls = new ArrayList<>();
        Post post;
         try {
            ExperienceList.clear();
            
            query = "SELECT * FROM `evenement`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
          post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        int user=resultSet.getInt("user_id");
        post.setCap(resultSet.getInt("user_id"));
        post.setUser_id(user);
        account.setName(getnameuser(resultSet.getInt("user_id")));
        account.setProfileImg("/img/youssef.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setCapacite(resultSet.getInt("capacite"));
        post.setDate(resultSet.getString("date_evenement"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("nom_evenement"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbComments(getnbpart (resultSet.getInt("id")));
        post.setNbShares(getnbshares(resultSet.getInt("id")));
        ls.add(post);
        }
         }catch(SQLException ex){
             
         }
        return ls;
    }
     
      public List<Post> getPostssaved(){
          List<Post> ls = new ArrayList<>();
        Post post;
         try {
            ExperienceList.clear();
            
            query = "SELECT * FROM `fvt_evt` WHERE `id_user` ="+idSession;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                    post = getPost(resultSet.getInt("id_evt"));
                    ls.add(post);
                
        }
         }catch(SQLException ex){
             
         }
        return ls;
    }
    
     public int getnbshares (int id){
        int i =0;
     try {
         
            query = "SELECT * FROM `fvt_evt` WHERE id_evt="+id;
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
         
            query = "SELECT * FROM `react_event` WHERE id_evt="+id;
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
     public Post getPost(int id){
         try {
             
            //System.out.println(id);
            query = "SELECT * FROM `evenement`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                if (id == resultSet.getInt("id") ){
                    
                       post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        int user=resultSet.getInt("user_id");
        post.setCap(resultSet.getInt("user_id"));
        post.setUser_id(user);
        account.setName(getnameuser(resultSet.getInt("user_id")));
        account.setProfileImg("/img/yossri.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setCapacite(resultSet.getInt("capacite"));
        post.setDate(resultSet.getString("date_evenement"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("nom_evenement"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbShares(getnbshares(resultSet.getInt("id")));
        }
            }
         }catch(SQLException ex){
             
         }
         return post;
    }
     
     @FXML
    void savedlist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("addevent.fxml"));
    AddEventController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(getPostssaved());
       for(Post post:posts){
            participants= new ArrayList<>(getParticipant(post.getId()));
            
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
           
           for(Commenter part:participants){
                FXMLLoader fx1 = new FXMLLoader();
           
           
                fx1.setLocation(getClass().getResource("participant.fxml"));
                HBox p = fx1.load();
           
           //System.out.println(post);
                ParticipantController participantController = fx1.getController();
           
                participantController.setData(part,getnameuser(part.getId_user()));
           //PostController postController = new PostController();
           
           //postController.setData(post);
                ExperiencesContainer.getChildren().add(p);
               
           }
           
          
       }catch (IOException e){
           e.printStackTrace();
           }}

    }
     public List<Post> searchh( String ch) {
    List<Post> ls = new ArrayList<>();
    Post post;
    try {
        // Clear any previous items in the list
        ls.clear();

        // Construct the SQL query using the given search criteria
        String query = "SELECT * FROM `evenement` WHERE nom_evenement LIKE '%" + ch + "%'";

        // Prepare the statement and execute the query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate over the result set and create Post objects for each row
         while (resultSet.next()){
          post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        int user=resultSet.getInt("user_id");
        post.setCap(resultSet.getInt("user_id"));
        post.setUser_id(user);
        account.setName(getnameuser(resultSet.getInt("user_id")));
        account.setProfileImg("/img/youssef.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setCapacite(resultSet.getInt("capacite"));
        post.setDate(resultSet.getString("date_evenement"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("nom_evenement"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbComments(getnbpart (resultSet.getInt("id")));
        post.setNbShares(getnbshares(resultSet.getInt("id")));
        ls.add(post);
        }
         }catch(SQLException ex){
             
         }
        return ls;
}

     
      @FXML
    void rech(ActionEvent event) throws IOException {
        
   FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/garafx/addevent.fxml"));
    AddEventController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(searchh(ch));
        for(Post post:posts){
            participants= new ArrayList<>(getParticipant(post.getId()));
            
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
           
           for(Commenter part:participants){
                FXMLLoader fx1 = new FXMLLoader();
           
           
                fx1.setLocation(getClass().getResource("participant.fxml"));
                HBox p = fx1.load();
           
           //System.out.println(post);
                ParticipantController participantController = fx1.getController();
           
                participantController.setData(part,getnameuser(part.getId_user()));
           //PostController postController = new PostController();
           
           //postController.setData(post);
                ExperiencesContainer.getChildren().add(p);
               
           }
           
          
           
        
       }catch (IOException e){
           e.printStackTrace();
           }}
    }
    
    
    
     public List<Post> tri() {
    List<Post> ls = new ArrayList<>();
    Post post;
    try {
        // Clear any previous items in the list
        ls.clear();

        // Construct the SQL query using the given search criteria
        String query = "SELECT * FROM `evenement` ORDER BY date_evenement ASC";

        // Prepare the statement and execute the query
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate over the result set and create Post objects for each row
         while (resultSet.next()){
          post = new Post();
        Account account = new Account();
        //int user=resultSet.getInt("coach_id");
        //post.setUser_id(user);
        int user=resultSet.getInt("user_id");
        post.setCap(resultSet.getInt("user_id"));
        post.setUser_id(user);
        account.setName(getnameuser(resultSet.getInt("user_id")));
        account.setProfileImg("/img/youssef.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setCapacite(resultSet.getInt("capacite"));
        post.setDate(resultSet.getString("date_evenement"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("nom_evenement"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        //post.setTotalReactions(getnbReact (resultSet.getInt("id")));
        post.setId(resultSet.getInt("id"));
        post.setNbComments(getnbpart (resultSet.getInt("id")));
        post.setNbShares(getnbshares(resultSet.getInt("id")));
        ls.add(post);
        }
         }catch(SQLException ex){
             
         }
        return ls;
}
     
     
      @FXML
    void tripardate(ActionEvent event) throws IOException {
        
   FXMLLoader fxmlLoader1 = new FXMLLoader(); 
    ExperiencesContainer.getChildren().clear();
    VBox addexp = fxmlLoader1.load(getClass().getResource("/garafx/addevent.fxml"));
    AddEventController addexController = fxmlLoader1.getController();
    ExperiencesContainer.getChildren().add(addexp);
    String ch =search.getText();
       
       posts = new ArrayList<>(tri());
        for(Post post:posts){
            participants= new ArrayList<>(getParticipant(post.getId()));
            
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
           
           for(Commenter part:participants){
                FXMLLoader fx1 = new FXMLLoader();
           
           
                fx1.setLocation(getClass().getResource("participant.fxml"));
                HBox p = fx1.load();
           
           //System.out.println(post);
                ParticipantController participantController = fx1.getController();
           
                participantController.setData(part,getnameuser(part.getId_user()));
           //PostController postController = new PostController();
           
           //postController.setData(post);
                ExperiencesContainer.getChildren().add(p);
               
           }
           
          
           
        
       }catch (IOException e){
           e.printStackTrace();
           }}
    }
    
    
    @FXML
    void print(ActionEvent event)throws SQLException, FileNotFoundException, IOException {    

        try {
       com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\youssef\\Desktop\\finalFX\\garafx\\Event.pdf"));  
       doc.open();
       
    doc.add(new Paragraph(" "));
       
      Chunk chunk = new Chunk("liste des Evenements", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, new BaseColor(255, 140, 0)));
Paragraph p = new Paragraph(chunk);
p.setAlignment(Element.ALIGN_CENTER);
doc.add(p);
doc.add(new Paragraph(" "));
doc.add(new Paragraph(" "));

// ...
PdfPTable tabpdf = new PdfPTable(5);
tabpdf.setWidthPercentage(100);

PdfPCell cell;
cell = new PdfPCell(new Phrase("id"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
cell.setBackgroundColor(BaseColor.GRAY);
cell.setPadding(5);
cell.setBorderWidth(1);
cell.setBorderColor(BaseColor.WHITE);
tabpdf.addCell(cell);

cell = new PdfPCell(new Phrase("nom Evenement"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
cell.setBackgroundColor(BaseColor.GRAY);
cell.setPadding(5);
cell.setBorderWidth(1);
cell.setBorderColor(BaseColor.WHITE);
tabpdf.addCell(cell);

cell = new PdfPCell(new Phrase("description"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
cell.setBackgroundColor(BaseColor.GRAY);
cell.setPadding(5);
cell.setBorderWidth(1);
cell.setBorderColor(BaseColor.WHITE);
tabpdf.addCell(cell);

cell = new PdfPCell(new Phrase("date evenement"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
cell.setBackgroundColor(BaseColor.GRAY);
cell.setPadding(5);
cell.setBorderWidth(1);
cell.setBorderColor(BaseColor.WHITE);
tabpdf.addCell(cell);

cell = new PdfPCell(new Phrase("capacite"));
cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
cell.setBackgroundColor(BaseColor.GRAY);
cell.setPadding(5);
cell.setBorderWidth(1);
cell.setBorderColor(BaseColor.WHITE);
tabpdf.addCell(cell);

query = "SELECT * FROM `evenement`";
preparedStatement = connection.prepareStatement(query);
resultSet = preparedStatement.executeQuery();

while (resultSet.next()) {
   cell = new PdfPCell(new Phrase(resultSet.getString("id")));
   cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
   cell.setBackgroundColor(BaseColor.WHITE);
   cell.setPadding(5);
   cell.setBorderWidth(1);
   cell.setBorderColor(BaseColor.GRAY);
   tabpdf.addCell(cell);

   cell = new PdfPCell(new Phrase(resultSet.getString("nom_evenement")));
   cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
   cell.setBackgroundColor(BaseColor.WHITE);
   cell.setPadding(5);
   cell.setBorderWidth(1);
   cell.setBorderColor(BaseColor.GRAY);
   tabpdf.addCell(cell);

   cell = new PdfPCell(new Phrase(resultSet.getString("description")));
   cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
   cell.setBackgroundColor(BaseColor.WHITE);
   cell.setPadding(5);
   cell.setBorderWidth(1);
   cell.setBorderColor(BaseColor.GRAY);
   tabpdf.addCell(cell);

   cell = new PdfPCell(new Phrase(resultSet.getString("date_evenement")));
   cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
   cell.setBackgroundColor(BaseColor.WHITE);
   cell.setPadding(5);
   cell.setBorderWidth(1);
   cell.setBorderColor(BaseColor.GRAY);
   tabpdf.addCell(cell);

   cell = new PdfPCell(new Phrase(resultSet.getString("capacite")));
   cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
   cell.setBackgroundColor(BaseColor.WHITE);
   cell.setPadding(5);
   cell.setBorderWidth(1);
   cell.setBorderColor(BaseColor.GRAY);
   tabpdf.addCell(cell);
}

    doc.add(tabpdf);
// ...

          JOptionPane.showMessageDialog(null, "Votre fichier a ete exporter en pdf avec succes");
          doc.close();
          Desktop.getDesktop().open(new File("C:\\Users\\youssef\\Desktop\\finalFX\\garafx\\Event.pdf"));
       }
 
        catch (DocumentException | HeadlessException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }


} 
   
@FXML
private void excelEvenement(ActionEvent event) {
    try {
        String filename = "C:\\xampp\\htdocs\\fichierExcelJava\\Evenemenssssts.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Liste des événements");

        // Ajout du titre
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Liste des événement:"
                + ""
                + ""
                + ""
                + ""
                + ""
                + "");                                                                                                                                              
               
             
                
   
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4)); // Fusion des cellules pour le titre

        // Création de l'en-tête du tableau
        HSSFRow headerRow = sheet.createRow(1);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nom d'événement");
        headerRow.createCell(2).setCellValue("Description");

        // Agrandissement de la colonne de la date d'événement
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        HSSFDataFormat dateFormat = workbook.createDataFormat();
        dateStyle.setDataFormat(dateFormat.getFormat("dd/mm/yyyy"));
        HSSFCell dateCellHeader = headerRow.createCell(3);
        dateCellHeader.setCellValue("Date d'événement");
        dateCellHeader.setCellStyle(dateStyle);
        sheet.setColumnWidth(3, 256*15); // Largeur de colonne de 15 caractères (256 est la largeur d'un caractère)

        headerRow.createCell(4).setCellValue("Capacité");

        // Remplissage des données du tableau
        int rowNum = 2;
        query = "SELECT * FROM evenement";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(resultSet.getInt("id"));
            row.createCell(1).setCellValue(resultSet.getString("nom_evenement"));
            row.createCell(2).setCellValue(resultSet.getString("description"));
            HSSFCell dateCell = row.createCell(3);
            dateCell.setCellValue(resultSet.getString("date_evenement"));
            dateCell.setCellStyle(dateStyle);
            row.createCell(4).setCellValue(resultSet.getInt("capacite"));
        }

        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Votre fichier Excel a été généré avec succès!");

        File file = new File(filename);
        if (file.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        }
    } catch (Exception ex) {
        System.out.println(ex);
    }
}

   
  

    
}