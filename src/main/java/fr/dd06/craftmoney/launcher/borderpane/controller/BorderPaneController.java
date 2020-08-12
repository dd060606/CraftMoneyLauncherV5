package fr.dd06.craftmoney.launcher.borderpane.controller;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.apis.javautils.javafx.util.StageFX;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class BorderPaneController {
    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void exit() {
        AnimatorFX.fadeOutFrameFX(stage, AnimatorFX.FAST);
    }
    @FXML
    private void minimize() {
        AnimatorFX.inconify(stage);
    }
}
