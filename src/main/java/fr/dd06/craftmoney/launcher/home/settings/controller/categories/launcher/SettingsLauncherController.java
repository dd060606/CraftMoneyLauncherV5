package fr.dd06.craftmoney.launcher.home.settings.controller.categories.launcher;

import com.jfoenix.controls.JFXToggleButton;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsLauncherController {
    @FXML
    private GridPane settingsAuthGridPane;
    @FXML
    private GridPane settingsLowRamGridPane;
    private JFXToggleButton authToggleButton;
    private JFXToggleButton lowramToggleButton;
    private CraftMoneyLauncher main;
    
    public void init(SettingsStage settingsStage,   CraftMoneyLauncher main) {
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        main.getLauncherSettingsConfig().reloadConfiguration();
        authToggleButton = new JFXToggleButton();
        main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("autoAuth", true);
        authToggleButton.setSelected(Boolean.parseBoolean(main.getLauncherSettingsConfig().getConfiguration().get("autoAuth").toString()));
        settingsAuthGridPane.add(authToggleButton, 0, 0);

        lowramToggleButton = new JFXToggleButton();
        main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("lowRam", false);
        lowramToggleButton.setSelected(Boolean.parseBoolean(main.getLauncherSettingsConfig().getConfiguration().get("lowRam").toString()));
        settingsLowRamGridPane.add(lowramToggleButton, 0, 0);

        main.getLauncherSettingsConfig().saveConfiguration();
        initEvents();
    }

    private void initEvents() {

        authToggleButton.setOnMouseClicked(event -> {
            main.getLauncherSettingsConfig().reloadConfiguration();
            main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("autoAuth", true);
            main.getLauncherSettingsConfig().getConfiguration().put("autoAuth", authToggleButton.isSelected());

            main.getLauncherSettingsConfig().saveConfiguration();
        });
        lowramToggleButton.setOnMouseClicked(event -> {
            main.getLauncherSettingsConfig().reloadConfiguration();
            main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("lowRam", false);
            main.getLauncherSettingsConfig().getConfiguration().put("lowRam", lowramToggleButton.isSelected());

            main.getLauncherSettingsConfig().saveConfiguration();
        });


    }
    @FXML
    private void updateLauncher() {
        File launcherUpdaterFile = new File(CraftMoneyLauncher.CRAFTMONEY_PROGRAM_DIR, "CraftMoney Launcher.exe");
        if(launcherUpdaterFile.exists()) {
            try {
                Process process = Runtime.getRuntime().exec(launcherUpdaterFile.getAbsolutePath());

            } catch (IOException e) {
                ErrorStage errorStage = new ErrorStage(main.getLauncherStage(), main);
                errorStage.getController().setErrorType("Impossible de mettre à jour le launcher !");
            }
            Timer exitTimer = new Timer();
            exitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 400);

        }
        else {
            ErrorStage errorStage = new ErrorStage(main.getLauncherStage(), main);
            errorStage.getController().setErrorType("Impossible de mettre à jour le launcher : \nUpdater introuvable !");
        }
    }
}
