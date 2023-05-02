package com.mycompany.sportifyfx;

import com.mycompany.sportifyfx.Model.Match;
import com.mycompany.sportifyfx.data.AppQuery;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author fekih
 */
public class MatchController implements Initializable{

    private static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMatch();
        btupdate.setDisable(true);
        btdelete.setDisable(true);
        

    }   
    
    
    
    @FXML
    private TableView<Match> MatchTable;

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
    private TableColumn<Match, Integer> culumnMatchId;
    
    @FXML
    private TableColumn<Match,String > culumnTeam1;

    @FXML
    private TableColumn<Match,String> culumnTeam2;

    @FXML
    private TableColumn<Match, String> culumnScore1;
    
    @FXML
    private TableColumn<Match, String> culumnScore2;
    
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
    

    
    private Match match;

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
    }
    @FXML
    void PageVote(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Vote.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }
    
    
    @FXML
    private void addMatch() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation d'AJOUT");
        dialog.setHeaderText("êtes-vous sûr d'enregistrer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText()+"| score1 : "+textscore1.getText()+"| score2 : "+textscore2.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.Model.Match match = new com.mycompany.sportifyfx.Model.Match(textTeam1.getText(), textTeam2.getText(), textscore1.getText(), textscore2.getText());
            com.mycompany.sportifyfx.data.AppQuery query = new AppQuery();
            query.addMatch(match);
            showMatch();
        }
    }
    
    
    @FXML
    private void updateMatch(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de MODIFIER");
        dialog.setHeaderText("êtes-vous sûr de changer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText()+"| score1 : "+textscore1.getText()+"| score2 : "+textscore2.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == okButton){
                com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
                com.mycompany.sportifyfx.Model.Match match = new com.mycompany.sportifyfx.Model.Match(this.match.getId(), textTeam1.getText(), textTeam2.getText(), textscore1.getText(), textscore2.getText());
                query.updateMatch(match);
                showMatch();
                clearFields();
                btupdate.setDisable(true);
                btdelete.setDisable(true);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    @FXML
    private void deleteMatch(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de SUPRIMER");
        dialog.setHeaderText("êtes-vous sûr de supprimer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText()+"| score1 : "+textscore1.getText()+"| score2 : "+textscore2.getText());        
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
            com.mycompany.sportifyfx.Model.Match match = new com.mycompany.sportifyfx.Model.Match(this.match.getId(), textTeam1.getText(), textTeam2.getText(), textscore1.getText(), textscore2.getText());
            query.deleteMatch(match);
            showMatch();
            clearFields();
            btupdate.setDisable(true);
            btdelete.setDisable(true);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void showMatch(){
        com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
        ObservableList<Match> List =query.getMatchList();
        culumnMatchId.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));
        culumnTeam1.setCellValueFactory(new PropertyValueFactory<Match, String>("team1"));
        culumnTeam2.setCellValueFactory(new PropertyValueFactory<Match, String>("team2"));
        culumnScore1.setCellValueFactory(new PropertyValueFactory<Match, String>("score1"));
        culumnScore2.setCellValueFactory(new PropertyValueFactory<Match, String>("score2"));
        MatchTable.setItems(List);    
    }
    
    
    @FXML
    public void mouseClick(MouseEvent e){
        try{
            Match match =MatchTable.getSelectionModel().getSelectedItem();
            match =new Match(match.getId(),match.getTeam1(),match.getTeam2(),match.getScore1(),match.getScore2());
            this.match=match;
            textTeam1.setText(match.getTeam1());
            textTeam2.setText(match.getTeam2());
            textscore1.setText(match.getScore1());
            textscore2.setText(match.getScore2());
            btupdate.setDisable(false);
            btdelete.setDisable(false);
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
        MatchController.stage = stage;
    }
    
}
