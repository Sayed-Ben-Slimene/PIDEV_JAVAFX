
package garafx;

import helpers.DbConnect;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import objects.Account;
import objects.Post;
import objects.PostAudience;
import objects.Reactions;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objects.Experience;

public class PostController   {
    @FXML
    private ImageView imgProfile;

    @FXML
    private Label username;
    
    @FXML
    private Label nbplace;

    @FXML
    private ImageView imgVerified;

    @FXML
    private Label date;

    @FXML
    private ImageView audience;
    
     @FXML
    private Label title;

    @FXML
    private Label caption;

    @FXML
    private ImageView imgPost;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbShares;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    
    @FXML
    private HBox likeContainer;

    @FXML
    private ImageView imgReaction;

    @FXML
    private Label reactionName;
      @FXML
    private Label error1;
       @FXML
    private HBox commentContainer;

    
    
    
  
    
    int idSession = 2;
    private long startTime = 0;
    private Reactions currentReaction;
    private Post post= new Post();
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
    Experience student = null ;
    ObservableList<Experience>  ExperienceList = FXCollections.observableArrayList();
    /*
    @FXML
    void CommentClick(MouseEvent event) throws IOException {
        
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CommenterExp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                //**CommenterExpController uptexpController = fxmlLoader.getController();
                //System.out.println(post.getId());
                uptexpController.setDa(post.getId());
                uptexpController.setDataPage();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
            System.out.println("Cant load Exp Upadate page");
            
        }/*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/CommenterExp.fxml"));
        Parent root =  loader.load();
        
        Scene CommenterExp = new Scene(root);
        CommenterExpController CExpController = loader.getController();
        CExpController.setDa(post.getId());
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(CommenterExp);
        window.show();
        
        
        
       
    
        

    }*/
    
    @FXML
    void participer(MouseEvent event) {
        /*try{
            query = "INSERT INTO participant (user_id, event_id) SELECT ?, ? FROM DUAL WHERE NOT EXISTS (SELECT * FROM participant WHERE user_id=? AND event_id=?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,String.valueOf(idSession));
            preparedStatement.setString(2,String.valueOf(post.getId()));
            preparedStatement.setString(3,String.valueOf(idSession));
             preparedStatement.setString(4,String.valueOf(post.getId()));
            preparedStatement.executeUpdate();
            
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
        
          try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/garafx/addres.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                AddresController uptexpController = fxmlLoader.getController();
                uptexpController.setDatas(post.getId());
                //System.out.println(post.getId());
                
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
            System.out.println("Cant load Exp Upadate page");
            
        }
          

        

    }

    
    
    
       @FXML
    void UpdateBtn(MouseEvent event) throws SQLException {
       if (idSession == post.getUser_id()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/garafx/UpdateExp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                UpdateExpController uptexpController = fxmlLoader.getController();
                uptexpController.setDatas(post.getId(),post.getTitle(),post.getCaption(),post.getImage(),post.getCap());
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
        
        System.out.println(post.getUser_id());
        if (idSession == post.getUser_id()) {
            
        query = "DELETE FROM `evenement` WHERE id  ="+post.getId();
        connection = DbConnect.getConnect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        }else {
            error1.setText("Error,it s not urs !!");
        }
    }
    @FXML
    public void onLikeContainerPressed(MouseEvent me){
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me){
        if(System.currentTimeMillis() - startTime > 500){
            reactionsContainer.setVisible(true);
        }else {
            if(reactionsContainer.isVisible()){
                reactionsContainer.setVisible(false);
            }
            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed(MouseEvent me) throws SQLException{
        
        switch (((ImageView) me.getSource()).getId()){
            case "imgLove":
                setReaction(Reactions.GOING);
                query = "INSERT INTO `react_event`( `id_user`, `id_evt`, `react`) VALUES ("+idSession+","+post.getId()+",'Going')";
                break;
            case "imgCare":
                setReaction(Reactions.INTERESTED);
                query = "INSERT INTO `react_event`( `id_user`, `id_evt`, `react`) VALUES ("+idSession+","+post.getId()+",'Interested')";
                
                break;
            case "imgHaha":
                setReaction(Reactions.NO);
                query = "INSERT INTO `react_event`( `id_user`, `id_evt`, `react`) VALUES ("+idSession+","+post.getId()+",'No')";
                break;
           
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        
        reactionsContainer.setVisible(false);
    
    }

    public void setReaction(Reactions reaction) {
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
            post.setTotalReactions(post.getTotalReactions() - 1);
            try{
            query = "DELETE FROM `react_event` WHERE `id_user`="+idSession+" AND `id_evt`="+post.getId();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
    }

    public  void setData(Post post){
        
        this.post = post;
        this.post.setId(post.getId());
        Image img;
        
        
        
        img = new Image(getClass().getResourceAsStream(post.getAccount().getProfileImg()));
        imgProfile.setImage(img);
        username.setText(post.getAccount().getName());
        if(post.getAccount().isVerified()){
            imgVerified.setVisible(true);
        }else{
            imgVerified.setVisible(false);
        }

        date.setText(post.getDate());
        if(post.getAudience() == PostAudience.PUBLIC){
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);
        
        if(post.getTitle() != null && !post.getTitle().isEmpty()){
            title.setText(post.getTitle());
        }else{
            title.setManaged(false);
        }
        
        if(post.getCaption() != null && !post.getCaption().isEmpty()){
            caption.setText(post.getCaption());
        }else{
            caption.setManaged(false);
        }

        if(post.getImage() != null && !post.getImage().isEmpty()){
            img = new Image(getClass().getResourceAsStream(post.getImage()));
            imgPost.setImage(img);
        }else{
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText(post.getNbComments() + " Reservations");   
        
        nbplace.setText(post.getCapacite() + " capacite");

        currentReaction = Reactions.NON;
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
        int user=resultSet.getInt("user_id");
        post.setCap(resultSet.getInt("user_id"));
        post.setUser_id(user);
        account.setName(getnameuser(user));
        account.setProfileImg("/img/youssef.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setDate(resultSet.getString("date_evenement"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("nom_evenement"));
        post.setCaption(resultSet.getString("description"));
        post.setImage(resultSet.getString("img"));
        post.setTotalReactions(getnbReact (resultSet.getInt("id")));
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
         
            query = "SELECT * FROM `reservation` WHERE id_event="+id;
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
    
    
    public boolean isreact(int id,int id_exp){
        try {
            query = "SELECT * FROM `react_event` WHERE id_evt ="+id_exp;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                
                if (resultSet1.getInt("id_user") == id){
                    //System.out.println(resultSet.getString("nom"));
                    
                   return true;
                    
                }
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return false;
        
        
        
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
    
      @FXML
    void mail(MouseEvent event) {
        

    }
    public boolean issaved(int id,int id_exp){
        try {
            query = "SELECT * FROM `fvt_evt` WHERE id_evt ="+id_exp;
            preparedStatement = connection.prepareStatement(query);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                
                if (resultSet1.getInt("id_user") == id){
                    //System.out.println(resultSet.getString("nom"));
                    
                   return true;
                    
                }
                   
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        //System.out.println(resultSet);
        return false;
        
        
        
    }
        
    
     @FXML
    void Saved(MouseEvent event) throws SQLException {
         if (issaved(idSession,post.getId())){
            query = "DELETE FROM `fvt_evt` WHERE `id_user`="+idSession+" AND `id_evt`="+post.getId();
            
        }else{
            
            query = "INSERT INTO `fvt_evt`( `id_user`, `id_evt`) VALUES ("+idSession+","+post.getId()+")";
            
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    
        
        
    
/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData(getPost());
    }
*/
   
}
