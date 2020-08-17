package fr.dd06.craftmoney.launcher.update.controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class UpdateController {

    @FXML
    private GridPane updateGridPane;
    @FXML
    private Label updateLabel;

    private JFXProgressBar progressBar ;

    public void init() {

        progressBar = new JFXProgressBar();
        progressBar.setMaxWidth(650);
        progressBar.setMaxHeight(35);
        progressBar.setProgress(0.0);

        updateGridPane.add(progressBar, 0, 2);
    }

    public JFXProgressBar getProgressBar() {
        return progressBar;
    }
    public Label getUpdateLabel() {
        return updateLabel;
    }
}
