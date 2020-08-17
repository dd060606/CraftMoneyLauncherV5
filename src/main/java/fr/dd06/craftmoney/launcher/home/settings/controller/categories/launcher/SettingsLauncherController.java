package fr.dd06.craftmoney.launcher.home.settings.controller.categories.launcher;

import com.jfoenix.controls.JFXToggleButton;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

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
}
