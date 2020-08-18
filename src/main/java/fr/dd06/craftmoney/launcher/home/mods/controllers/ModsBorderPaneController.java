package fr.dd06.craftmoney.launcher.home.mods.controllers;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.craftmoney.launcher.home.mods.ModsStage;
import javafx.fxml.FXML;

public class ModsBorderPaneController {
    private ModsStage modsStage;

    public void init(ModsStage modsStage) {
        this.modsStage = modsStage;
    }

    @FXML
    private void exit() {
        AnimatorFX.fadeOutFrameFX(modsStage.getModsStage(), AnimatorFX.FAST, false);

    }
}
