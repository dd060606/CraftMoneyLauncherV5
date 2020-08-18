package fr.dd06.craftmoney.launcher.home.mods.controllers;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.home.mods.ModsStage;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class ModsController {
    private CraftMoneyLauncher main;
    private ModsStage modsStage;
    @FXML
    private HBox modBox;
    @FXML
    private HBox modBox2;
    @FXML
    private HBox modBox3;
    public void init(CraftMoneyLauncher main, ModsStage modsStage) {
        this.main = main;
        this.modsStage = modsStage;

        modBox2.setVisible(false);
        modBox3.setVisible(false);
    }
}
