/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sportifyfx;

import com.mycompany.sportifyfx.Model.Match;
import com.mycompany.sportifyfx.Model.UMatch;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mycompany.sportifyfx.data.AppQuery;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author fekih
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private ImageView MatchImg;

    @FXML
    private Pane back;

    @FXML
    private ImageView facebook;

    @FXML
    private ImageView instagram;

    @FXML
    private ImageView linidin;

    @FXML
    private Label resultLabel;

    @FXML
    private TableView<Match> resultTable;

    @FXML
    private TableColumn<Match, String> score1;

    @FXML
    private TableColumn<Match, String> score2;

    @FXML
    private ImageView sportifyImg;

    @FXML
    private TableView<UMatch> resultTable2;

    @FXML
    private TableColumn<UMatch, String> team11;

    @FXML
    private TableColumn<UMatch, String> team22;

    @FXML
    private TableColumn<Match, String> team1;
    
    @FXML
    private TableColumn<Match, String> team2;
    @FXML
    
    private ImageView twitter;

    @FXML
    private ImageView youtube;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnAdmin;

    @FXML
    private ImageView Image1;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image3;

    @FXML
    private ImageView Image4;
    @FXML
    private TextField textTeam1;

    @FXML
    private TextField textTeam2;

    @FXML
    private TextField textscore1;

    @FXML
    private TextField textscore2;
    @FXML
    private VBox most_inner_pane;

    private int currentIndex = 0;
    /**
     * Initializes the controller class.
     */

    public void slider(){
        new Thread(){

            public void run() {
                int count = 0;
                try {
                while (true) {
                    switch (count) {
                        case 0:

                            Thread.sleep(5000);

                            TranslateTransition slider1 = new TranslateTransition();
                            slider1.setNode(Image1);
                            slider1.setDuration(Duration.seconds(3));
                            slider1.setToX(0);
                            slider1.play();

                            TranslateTransition slider2 = new TranslateTransition();
                            slider1.setNode(Image2);
                            slider1.setDuration(Duration.seconds(3));
                            slider1.setToX(0);
                            slider1.play();

                            TranslateTransition slider3 = new TranslateTransition();
                            slider1.setNode(Image3);
                            slider1.setDuration(Duration.seconds(3));
                            slider1.setToX(-1322);
                            slider1.play();

                            TranslateTransition slider4 = new TranslateTransition();
                            slider1.setNode(Image4);
                            slider1.setDuration(Duration.seconds(3));
                            slider1.setToX(-2644);
                            slider1.play();

                            count = 1;
                            break;
                        case 1:

                            Thread.sleep(5000);

                            TranslateTransition slider5 = new TranslateTransition();
                            slider5.setNode(Image1);
                            slider5.setDuration(Duration.seconds(3));
                            slider5.setToX(1322);
                            slider5.play();

                            TranslateTransition slider6 = new TranslateTransition();
                            slider6.setNode(Image2);
                            slider6.setDuration(Duration.seconds(3));
                            slider6.setToX(1322);
                            slider6.play();

                            TranslateTransition slider7 = new TranslateTransition();
                            slider7.setNode(Image3);
                            slider7.setDuration(Duration.seconds(3));
                            slider7.setToX(0);
                            slider7.play();

                            TranslateTransition slider8 = new TranslateTransition();
                            slider8.setNode(Image4);
                            slider8.setDuration(Duration.seconds(3));
                            slider8.setToX(-1322);
                            slider8.play();

                            count = 2;
                            break;

                        case 2:

                            Thread.sleep(5000);

                            TranslateTransition slider9 = new TranslateTransition();
                            slider9.setNode(Image1);
                            slider9.setDuration(Duration.seconds(3));
                            slider9.setToX(2644);
                            slider9.play();

                            TranslateTransition slider10 = new TranslateTransition();
                            slider10.setNode(Image2);
                            slider10.setDuration(Duration.seconds(3));
                            slider10.setToX(2644);
                            slider10.play();

                            TranslateTransition slider11 = new TranslateTransition();
                            slider11.setNode(Image3);
                            slider11.setDuration(Duration.seconds(3));
                            slider11.setToX(1322);
                            slider11.play();

                            TranslateTransition slider12 = new TranslateTransition();
                            slider12.setNode(Image4);
                            slider12.setDuration(Duration.seconds(3));
                            slider12.setToX(-1322);
                            slider12.play();

                            count = 0;
                            break;
                    }
                }
                }catch (Exception e){e.printStackTrace();}
            }
        }.start();
    }
    private UMatch umatch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider();
        showMatch();
        showUMatch();
        }


    @FXML
    public void mouseClick(MouseEvent e){
        try{
            UMatch umatch =resultTable2.getSelectionModel().getSelectedItem();
            umatch =new UMatch(umatch.getTeam1(),umatch.getTeam2());
            this.umatch=umatch;
            textTeam1.setText(umatch.getTeam1());
            textTeam2.setText(umatch.getTeam2());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void showMatch(){
        com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
        ObservableList<Match> List =query.getMatchList();
        team1.setCellValueFactory(new PropertyValueFactory<Match, String>("team1"));
        team2.setCellValueFactory(new PropertyValueFactory<Match, String>("team2"));
        score1.setCellValueFactory(new PropertyValueFactory<Match, String>("score1"));
        score2.setCellValueFactory(new PropertyValueFactory<Match, String>("score2"));
        resultTable.setItems(List);
    }

    @FXML
    private void showUMatch(){
        com.mycompany.sportifyfx.data.AppQuery query = new com.mycompany.sportifyfx.data.AppQuery();
        ObservableList<UMatch> List =query.getUMatchList();
        team11.setCellValueFactory(new PropertyValueFactory<UMatch, String>("team1"));
        team22.setCellValueFactory(new PropertyValueFactory<UMatch, String>("team2"));
        resultTable2.setItems(List);
    }

    @FXML
    private void addVote() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation d'AJOUT");
        dialog.setHeaderText("êtes-vous sûr d'enregistrer ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label ("team1: "+textTeam1.getText()+"| team2: "+textTeam2.getText()+"| score1 : "+textscore1.getText()+"| score2 : "+textscore2.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == okButton){
            com.mycompany.sportifyfx.Model.Vote vote = new com.mycompany.sportifyfx.Model.Vote(textTeam1.getText(), textTeam2.getText(), textscore1.getText(), textscore2.getText());
            com.mycompany.sportifyfx.data.AppQuery query = new AppQuery();
            query.addVote(vote);
            clearFields();
            showUMatch();
        }
    }
    @FXML
    private void clearFields(){
        textTeam1.setText("");
        textTeam2.setText("");
        textscore1.setText("");
        textscore2.setText("");
    }
    @FXML
    void pageAdmin(ActionEvent event) throws IOException {
        Parent nextPageParent = FXMLLoader.load(getClass().getResource("Match.fxml"));
        Scene nextPageScene = new Scene(nextPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextPageScene);
        window.show();
    }

   


}