package com.mycompany.sportifyfx;

import com.mycompany.sportifyfx.Model.Team;
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
public class TeamController implements Initializable {

    private static Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showTeam();
        btupdate.setDisable(true);
        btdelete.setDisable(true);
    }    
    
    

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
    private TableView <Team> TeamTable;

    @FXML
    private TableColumn<Team, Integer> culumnTeamId;

    @FXML
    private TableColumn<Team, String> culumnTeamnom;

    @FXML
    private TableColumn<Team, String> culumnTeamplayers;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane most_inner_pane;

    @FXML
    private Pane pane_132;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    private TextField textid;

    @FXML
    private TextField textnom;

    @FXML
    private TextField textplayers;

    private Team team;

    @FXML
    void PageMatch(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Match.fxml"));
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
    void PageVote(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Vote.fxml"));
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
    private void addTeam() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation d'AJOUT");
        dialog.setHeaderText("êtes-vous sûr d'enregistrer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("Nom: "+textnom.getText()+"| Players : "+textplayers.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.Model.Team team = new com.mycompany.sportifyfx.Model.Team(textnom.getText(), textplayers.getText());
            com.mycompany.sportifyfx.data.AppQuery query = new AppQuery();
            query.addTeam(team);
            showTeam();
        }
    }
    
    @FXML
    private void updateTeam(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de MODIFIER");
        dialog.setHeaderText("êtes-vous sûr de changer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("Nom : "+textnom.getText()+"| Players : "+textplayers.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == okButton){
                com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
                com.mycompany.sportifyfx.Model.Team team = new com.mycompany.sportifyfx.Model.Team(this.team.getId(), textnom.getText(), textplayers.getText());
                query.updateTeam(team);
                showTeam();
                clearFields();
                btupdate.setDisable(true);
                btdelete.setDisable(true);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deleteTeam(){
        try{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de SUPRIMER");
        dialog.setHeaderText("êtes-vous sûr de supprimer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label ("Nom: "+textnom.getText()+"| Players :  "+textplayers.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
            com.mycompany.sportifyfx.Model.Team team = new com.mycompany.sportifyfx.Model.Team(this.team.getId(), textnom.getText(), textplayers.getText());
            query.deleteTeam(team);
            showTeam();
            clearFields();
            btupdate.setDisable(true);
            btdelete.setDisable(true);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void showTeam(){
        com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
        ObservableList<Team> List =query.getTeamList();
        culumnTeamId.setCellValueFactory(new PropertyValueFactory<Team, Integer>("id"));
        culumnTeamnom.setCellValueFactory(new PropertyValueFactory<Team, String>("nom"));
        culumnTeamplayers.setCellValueFactory(new PropertyValueFactory<Team, String>("players"));
        TeamTable.setItems(List);
    
    }

    @FXML
    public void mouseClick(MouseEvent e){
        try{
            Team team =TeamTable.getSelectionModel().getSelectedItem();
            team =new Team(team.getId(),team.getNom(),team.getPlayers());
            this.team=team;
            textnom.setText(team.getNom());
            textplayers.setText(team.getPlayers());
            btupdate.setDisable(false);
            btdelete.setDisable(false);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    private void clearFields(){
        textid.setText("");
        textnom.setText("");
        textplayers.setText("");
    }
    
    /**
     * Initializes the controller class.
     */
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TeamController.stage = stage;
    }

}

