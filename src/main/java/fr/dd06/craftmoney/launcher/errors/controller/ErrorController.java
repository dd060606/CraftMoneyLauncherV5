package fr.dd06.craftmoney.launcher.errors.controller;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.dd06.craftmoney.launcher.home.controller.LauncherController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;


import java.awt.*;
import java.io.IOException;

public class ErrorController {


    private ErrorStage errorStage;
    private LauncherStage launcherStage;
    @FXML
    private Label errorType;
    @FXML
    private Button okButton;
    private CraftMoneyLauncher main;


    public void init(ErrorStage errorStage, LauncherStage launcherStage, CraftMoneyLauncher main) {
        this.errorStage = errorStage;
        this.launcherStage = launcherStage;
        this.main = main;
    }
    @FXML
    private void openLogsDir() {
        try {
            Desktop.getDesktop().open(CraftMoneyGame.CRAFTMONEY_LOG_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/LauncherPaneView.fxml"));
        try {
            launcherStage.setAnchorPane(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LauncherController controller = loader.getController();
        controller.init(main, launcherStage);
        launcherStage.getContainer().setCenter(launcherStage.getAnchorPane());
        AnimatorFX.fadeOutFrameFX(errorStage, AnimatorFX.FAST, false);
    }
    public void setErrorType(String error) {
        this.errorType.setText(error);
    }
}
