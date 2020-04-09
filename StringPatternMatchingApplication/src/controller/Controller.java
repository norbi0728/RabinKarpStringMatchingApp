package controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import logic.RabinKarpStringPatternMatching;
import utility.FileReaderUtility;

import javafx.event.ActionEvent;
import java.io.File;

import java.util.List;

public class Controller {
    @FXML
    private TextFlow resultTextFlow;
    @FXML
    private TextArea patternTextArea;
    @FXML
    private TextArea userTextArea;
    @FXML
    private BorderPane resultPane;
    @FXML
    private BorderPane mainWindow;
    @FXML
    private Label statusLabelMain;
    @FXML
    private Label statusLabelResult;
    @FXML
    private AnchorPane helpPane;

    public void openTextFileButtonAction(ActionEvent e){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            userTextArea.setText(new FileReaderUtility(selectedFile).readFile());
            statusLabelMain.setText("Szövegfájl beolvasása befejeződött");
        }
    }

    public void openPatternFileButtonAction(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            statusLabelMain.setText("Minta beolvasása befejeződött");
            String file = new FileReaderUtility(selectedFile).readFile();
            patternTextArea.setText(file);
            ObservableList l = resultTextFlow.getChildren();
            List<Integer> matchingIndices = RabinKarpStringPatternMatching
                            .stringPatternMatching(
                            userTextArea.getText(),
                            patternTextArea.getText(), 10, 13);
            for (int i = 0; i < userTextArea.getText().length(); i++){
                if (matchingIndices.contains(i)){
                    Text t = new Text(userTextArea.getText().charAt(i) + "");
                    t.setFill(Color.RED);
                    l.add(t);
                }
                else
                    l.add(new Text(userTextArea.getText().charAt(i) + ""));
            }
            resultPane.setVisible(true);
            statusLabelResult.setText("Az eredmény megjelenítése befejeződött, találatok száma: " + matchingIndices.size());
            mainWindow.setVisible(false);
        }
    }

    public void onBackButtonAction(ActionEvent actionEvent) {
        resultTextFlow.getChildren().clear();
        resultPane.setVisible(false);
        mainWindow.setVisible(true);
    }

    public void onExitButtonAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void onSearchButtonAction(ActionEvent actionEvent) {
        ObservableList l = resultTextFlow.getChildren();
        List<Integer> matchingIndices = RabinKarpStringPatternMatching
                .stringPatternMatching(
                        userTextArea.getText(),
                        patternTextArea.getText(), 10, 13);
        for (int i = 0; i < userTextArea.getText().length(); i++){
            if (matchingIndices.contains(i)){
                Text t = new Text(userTextArea.getText().charAt(i) + "");
                t.setFill(Color.RED);
                l.add(t);
            }
            else
                l.add(new Text(userTextArea.getText().charAt(i) + ""));
        }
        resultPane.setVisible(true);
        statusLabelResult.setText("Az eredmény megjelenítése befejeződött, találatok száma: " + matchingIndices.size());
        mainWindow.setVisible(false);
    }

    public void clearContent(ActionEvent actionEvent) {
        userTextArea.clear();
        patternTextArea.clear();
    }

    public void onHelpAction(ActionEvent actionEvent) {
        mainWindow.setVisible(false);
        helpPane.setVisible(true);
    }

    public void onOkayButtonAction(ActionEvent actionEvent) {
        helpPane.setVisible(false);
        mainWindow.setVisible(true);
    }
}
