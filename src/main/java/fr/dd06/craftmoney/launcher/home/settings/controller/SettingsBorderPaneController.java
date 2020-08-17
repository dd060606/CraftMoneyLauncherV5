package fr.dd06.craftmoney.launcher.home.settings.controller;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import fr.dd06.craftmoney.launcher.home.settings.controller.categories.game.SettingsGameController;
import fr.dd06.craftmoney.launcher.home.settings.controller.categories.launcher.SettingsLauncherController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsBorderPaneController {
    private  SettingsStage settingsStage;
    public void init(SettingsStage settingsStage) {
        this.settingsStage = settingsStage;
    }

    @FXML
    private void exit() {
        AnimatorFX.fadeOutFrameFX(settingsStage, AnimatorFX.FAST);

    }
    @FXML
    private void selectGameSettings() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/settings/categories/game/SettingsGameViewPane.fxml"));

        try {
            settingsStage.setSettingsAnchorPane((AnchorPane) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsGameController controller = loader.getController();
        controller.init(settingsStage, settingsStage.getMain());

        settingsStage.getSettingsContainer().setCenter(settingsStage.getSettingsAnchorPane());
    }
    @FXML
    private void selectLauncherSettings() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/settings/categories/launcher/SettingsLauncherViewPane.fxml"));

        try {
            settingsStage.setSettingsAnchorPane((AnchorPane) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsLauncherController controller = loader.getController();
        controller.init(settingsStage, settingsStage.getMain());

        settingsStage.getSettingsContainer().setCenter(settingsStage.getSettingsAnchorPane());
    }
    @FXML
    private void selectInfosSettings() {

    }
}
