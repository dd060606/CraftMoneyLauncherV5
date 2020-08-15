package fr.dd06.craftmoney.launcher.home.settings.controller.categories.game;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SettingsGameController {

    @FXML
    private Label ramLabel;
    private SettingsStage settingsStage;
    private CraftMoneyLauncher main;

    public void init(SettingsStage settingsStage, CraftMoneyLauncher main) {
        this.settingsStage = settingsStage;
        this.main = main;

        main.getLauncherSettingsConfig().reloadConfiguration();

        if(main.getLauncherSettingsConfig().getConfiguration().get("ram") == null) {
            main.getLauncherSettingsConfig().getConfiguration().put("ram", 2000);
        }
        main.getLauncherSettingsConfig().saveConfiguration();

        ramLabel.setText("RAM: " + main.getLauncherSettingsConfig().getConfiguration().get("ram") + "Mb");

    }
}
