package karimfx;

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
    private ImageView imgWow;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgAngry;

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

    
    
    
  
    
    int idSession = 1;
    private long startTime = 0;
    private Reactions currentReaction;
    private Post post= new Post();
    String query = null;
    Connection connection = DbConnect.getConnect() ;
    
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet,resultSet1 = null ;
    Experience student = null ;
    ObservableList<Experience>  ExperienceList = FXCollections.observableArrayList();
    
    @FXML
    void CommentClick(MouseEvent event) throws IOException {
        
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/karimfx/CommenterExp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                CommenterExpController uptexpController = fxmlLoader.getController();
                //System.out.println(post.getId());
                uptexpController.setDa(post.getId());
                uptexpController.setDataPage();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
            System.out.println("Cant load Exp Upadate page");
            
        }
    }
       @FXML
    void UpdateBtn(MouseEvent event) throws SQLException {
        //if (idSession == post.getUser_id()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/karimfx/UpdateExp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                UpdateExpController uptexpController = fxmlLoader.getController();
                uptexpController.setDatas(post.getId(),post.getTitle(),post.getCaption());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception ex) {
            System.out.println("Cant load Exp Upadate page");
            
        }
        
        /*}else {
            error1.setText("Error,it s not urs !!");
        }*/
    }
       @FXML
    void DeleteBtn(MouseEvent event) throws SQLException {
        System.out.println(post.getId());
        
        if (idSession == post.getUser_id()) {
        query = "DELETE FROM `actualite` WHERE id  ="+post.getId();
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
                setReaction(Reactions.LOVE);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",Love)";
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'Care')";
                
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'HAHA')";
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                query = "INSERT INTO `react_act`( `id_user_id`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'WOW')";
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'Sad')";
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'Angry')";
                break;
            default:
                setReaction(Reactions.LIKE);
                query = "INSERT INTO `react_act`( `id_user`, `id_act`, `react`) VALUES ("+idSession+","+post.getId()+",'Like')";
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
            query = "DELETE FROM `react_act` WHERE `id_user`="+idSession+" AND `id_act`="+post.getId();
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
        nbComments.setText(post.getNbComments() + " comments");
        nbShares.setText(post.getNbShares() + " Shares");
        currentReaction = Reactions.NON;
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
        int user=resultSet.getInt("id_user");
        post.setUser_id(user);
        account.setName(getnameuser(user));
        account.setProfileImg("/img/yossri.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setDate(resultSet.getString("dt"));
        post.setAudience(PostAudience.PUBLIC);
        post.setTitle(resultSet.getString("title"));
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
    
    
    public boolean isreact(int id,int id_exp){
        try {
            query = "SELECT * FROM `react_act` WHERE id_act ="+id_exp;
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
        public boolean issaved(int id,int id_exp){
        try {
            query = "SELECT * FROM `fvt_act` WHERE id_act ="+id_exp;
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
    void save(MouseEvent event) throws SQLException {
        
        if (issaved(idSession,post.getId())){
            query = "DELETE FROM `fvt_act` WHERE `id_user`="+idSession+" AND `id_act`="+post.getId();
            
        }else{
            
            query = "INSERT INTO `fvt_act`( `id_user`, `id_act`) VALUES ("+idSession+","+post.getId()+")";
            
        }
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    
    
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
                    
                   return resultSet1.getString("username");
                    
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
    
        
        
    
/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData(getPost());
    }
*/
   
}
