package com.mycompany.sportifyfx;

import com.mycompany.sportifyfx.Model.UMatch;
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
public class UMatchController implements Initializable{

    private static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showUMatch();
        btupdate.setDisable(true);
        btdelete.setDisable(true);
        

    }   
    
    
    
    @FXML
    private TableView<UMatch> MatchTable;

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
    private TableColumn<UMatch, Integer> culumnMatchId;
    
    @FXML
    private TableColumn<UMatch,String > culumnTeam1;

    @FXML
    private TableColumn<UMatch,String> culumnTeam2;

    
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
    private ImageView adminImg;

    @FXML
    private ImageView sportifyImg;
     
    @FXML
    private ImageView regImg;

  private UMatch umatch;

    @FXML
    void PageTeams(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Team.fxml"));
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
    void PageHome(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

    @FXML
    void PageMatch(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Match.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
}
    
    @FXML
    private void addUMatch() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation d'AJOUT");
        dialog.setHeaderText("êtes-vous sûr d'enregistrer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.Model.UMatch umatch = new com.mycompany.sportifyfx.Model.UMatch(textTeam1.getText(), textTeam2.getText());
            com.mycompany.sportifyfx.data.AppQuery query = new AppQuery();
            query.addUMatch(umatch);
            showUMatch();
        }
    }
    
    
    @FXML
    private void updateUMatch(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de MODIFIER");
        dialog.setHeaderText("êtes-vous sûr de changer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == okButton){
                com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
                com.mycompany.sportifyfx.Model.UMatch umatch = new com.mycompany.sportifyfx.Model.UMatch(this.umatch.getId(), textTeam1.getText(), textTeam2.getText());
                query.updateUMatch(umatch);
                showUMatch();
                clearFields();
                btupdate.setDisable(true);
                btdelete.setDisable(true);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    @FXML
    private void deleteUMatch(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de SUPRIMER");
        dialog.setHeaderText("êtes-vous sûr de supprimer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText());        
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
            com.mycompany.sportifyfx.Model.UMatch umatch = new com.mycompany.sportifyfx.Model.UMatch(this.umatch.getId(), textTeam1.getText(), textTeam2.getText());
            query.deleteUMatch(umatch);
            showUMatch();
            clearFields();
            btupdate.setDisable(true);
            btdelete.setDisable(true);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void showUMatch(){
        com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
        ObservableList<UMatch> List =query.getUMatchList();
        culumnMatchId.setCellValueFactory(new PropertyValueFactory<UMatch, Integer>("id"));
        culumnTeam1.setCellValueFactory(new PropertyValueFactory<UMatch, String>("team1"));
        culumnTeam2.setCellValueFactory(new PropertyValueFactory<UMatch, String>("team2"));
        MatchTable.setItems(List);    
    }
    
    
    @FXML
    public void mouseClick(MouseEvent e){
        try{
            UMatch umatch =MatchTable.getSelectionModel().getSelectedItem();
            umatch =new UMatch(umatch.getId(),umatch.getTeam1(),umatch.getTeam2());
            this.umatch=umatch;
            textTeam1.setText(umatch.getTeam1());
            textTeam2.setText(umatch.getTeam2());
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
    }
    
    /**
     * Initializes the controller class.
     */
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        UMatchController.stage = stage;
    }
    
}
