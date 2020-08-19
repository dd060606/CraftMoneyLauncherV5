package fr.dd06.craftmoney.launcher.home.mods.controllers;

import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.home.mods.CraftMoneyOptionalMods;
import fr.dd06.craftmoney.launcher.home.mods.ModsStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ModsController {
    private CraftMoneyLauncher main;
    private ModsStage modsStage;
    @FXML
    private HBox modBox;
    @FXML
    private HBox modBox2;
    @FXML
    private HBox modBox3;
    @FXML
    private Button installButton1;
    @FXML
    private Button installButton2;
    @FXML
    private Button installButton3;

    private JSONConfiguration modConfig = new JSONConfiguration(new File(CraftMoneyLauncher.CRAFTMONEY_PROGRAM_DIR, "modConfig.json"));

    private boolean mod1Installed = false;
    public void init(CraftMoneyLauncher main, ModsStage modsStage) {
        this.main = main;
        this.modsStage = modsStage;

        modBox2.setVisible(false);
        modBox3.setVisible(false);
        modConfig.reloadConfiguration();
        modConfig.getConfiguration().putIfAbsent("mod1", false);
        modConfig.saveConfiguration();
        mod1Installed = Boolean.parseBoolean(modConfig.getConfiguration().get("mod1").toString());
        if (mod1Installed) {
            setMod1ButtonUnistall();

        }
        else {
            setMod1Button();
        }
    }

    private void setMod1Button() {


        Platform.runLater(() -> {

            installButton1.setStyle("-fx-background-color: rgba(51, 210, 48, 0.9);");
            installButton1.setText("Installer");

        });


    }
    private void setMod1ButtonUnistall() {
        Platform.runLater(() -> {
            installButton1.setStyle("-fx-background-color: red");
            installButton1.setText("Désintaller");

        });




    }
    @FXML
    private void installMod1() {
        if(mod1Installed) {
            Platform.runLater(() -> {
                installButton1.setText("Desinstallation ...");
                installButton1.setDisable(true);
            });

            File mod1File = new File(CraftMoneyGame.CRAFTMONEY_GAME_DIR, "/mods/journeyMap.jar");
            if(mod1File.exists()) {
                mod1File.delete();
            }


            modConfig.reloadConfiguration();
            modConfig.getConfiguration().put("mod1", false);
            modConfig.saveConfiguration();
            Platform.runLater(() -> {
                installButton1.setDisable(false);
            });
            mod1Installed = Boolean.parseBoolean(modConfig.getConfiguration().get("mod1").toString());
            setMod1Button();
        }
        else {
            Platform.runLater(() -> {
                installButton1.setText("Installation ...");
                Platform.runLater(() -> {
                    installButton1.setDisable(true);
                });
            });
            Thread downloadThread = new Thread(() -> {
                File mod1File = new File(CraftMoneyGame.CRAFTMONEY_GAME_DIR, "/mods/journeyMap.jar");
                if(mod1File.exists()) {
                    mod1File.delete();
                }
                try {
                    CraftMoneyOptionalMods.downloadOptionalMod("journeyMap", new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/mods/journeymap-1.12.2-5.7.1.jar"), CraftMoneyGame.CRAFTMONEY_GAME_DIR);
                } catch (IOException e) {
                    modConfig.reloadConfiguration();
                    modConfig.getConfiguration().put("mod1", false);
                    modConfig.saveConfiguration();
                    mod1Installed = Boolean.parseBoolean(modConfig.getConfiguration().get("mod1").toString());
                    e.printStackTrace();
                    setMod1Button();
                }
                modConfig.reloadConfiguration();
                modConfig.getConfiguration().put("mod1", true);
                modConfig.saveConfiguration();
                Platform.runLater(() -> {
                    installButton1.setDisable(false);
                });
                mod1Installed = Boolean.parseBoolean(modConfig.getConfiguration().get("mod1").toString());
                setMod1ButtonUnistall();
            }) ;
            downloadThread.start();


        }


    }
    @FXML
    private void installMod2() {

    }
    @FXML
    private void installMod3() {

    }
}
