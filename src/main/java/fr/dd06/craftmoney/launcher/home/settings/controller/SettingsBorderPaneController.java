package fr.dd06.craftmoney.launcher.home.settings.controller;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.fxml.FXML;

public class SettingsBorderPaneController {
    private  SettingsStage settingsStage;
    public void init(SettingsStage settingsStage) {
        this.settingsStage = settingsStage;
    }

    @FXML
    private void exit() {
        AnimatorFX.fadeOutFrameFX(settingsStage, AnimatorFX.FAST);

    }
}
