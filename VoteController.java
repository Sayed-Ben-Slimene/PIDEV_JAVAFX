package com.mycompany.sportifyfx;

import com.mycompany.sportifyfx.Model.Match;
import com.mycompany.sportifyfx.Model.Vote;
import com.mycompany.sportifyfx.data.AppQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author fekih
 */
public class VoteController implements Initializable{

    private static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showVote();
        

    }   
    
    
    
    @FXML
    private TableView<Vote> MatchTable;

    @FXML
    private Button btadd;

    @FXML
    private Button btdelete;

    @FXML
    private Button btn_Event;

    @FXML
    private Button btn_Shop;

    @FXML
    private Button btn_Team;

    @FXML
    private Button btn_actuality;

    @FXML
    private Button btn_match;

    @FXML
    private Button btn_workbench;

    @FXML
    private Button btupdate;
    
    @FXML
    private TableColumn<Vote, Integer> culumnMatchId;
    
    @FXML
    private TableColumn<Vote,String > culumnTeam1;

    @FXML
    private TableColumn<Vote,String> culumnTeam2;

    @FXML
    private TableColumn<Vote, String> culumnScore1;
    
    @FXML
    private TableColumn<Vote, String> culumnScore2;
    
    @FXML
    private Pane inner_pane;

    @FXML
    private Pane most_inner_pane;

    @FXML
    private Pane pane_132;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    private TextField textTeam1;

    @FXML
    private TextField textTeam2;
    
    @FXML
    private TextField textscore1;

    @FXML
    private TextField textscore2;
    
    @FXML
    private ImageView adminImg;
    
    @FXML
    private ImageView regImg;
    
    @FXML
    private ImageView sportifyImg;
    

    
    private Vote vote;

    @FXML
    void PageTeams(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Team.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    @FXML
    void PageHome(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    @FXML
    void PageUMatch(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("UMatch.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }@FXML

    void PageMatch(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Match.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }
    
    

    

    
    
    @FXML
    private void deleteVote(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de SUPRIMER");
        dialog.setHeaderText("êtes-vous sûr de supprimer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            AppQuery query = new AppQuery();
            Vote vote = new Vote(this.vote.getId(), textTeam1.getText(), textTeam2.getText(), textscore1.getText(), textscore2.getText());
            query.deleteVote(vote);
            showVote();
            clearFields();
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void showVote(){
        AppQuery query = new AppQuery();
        ObservableList<Vote> List =query.getVoteList();
        culumnMatchId.setCellValueFactory(new PropertyValueFactory<Vote, Integer>("id"));
        culumnTeam1.setCellValueFactory(new PropertyValueFactory<Vote, String>("team1"));
        culumnTeam2.setCellValueFactory(new PropertyValueFactory<Vote, String>("team2"));
        culumnScore1.setCellValueFactory(new PropertyValueFactory<Vote, String>("score1"));
        culumnScore2.setCellValueFactory(new PropertyValueFactory<Vote, String>("score2"));
        MatchTable.setItems(List);    
    }
    
    
    @FXML
    public void mouseClick(MouseEvent e){
        try{
            Vote vote =MatchTable.getSelectionModel().getSelectedItem();
            vote = new Vote(vote.getId(),vote.getTeam1(),vote.getTeam2(),vote.getScore1(),vote.getScore2());
            this.vote=vote;

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void clearFields(){
        textTeam1.setText("");
        textTeam2.setText("");
        textscore1.setText("");
        textscore2.setText("");
    }
    
    /**
     * Initializes the controller class.
     */
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        VoteController.stage = stage;
    }


}
