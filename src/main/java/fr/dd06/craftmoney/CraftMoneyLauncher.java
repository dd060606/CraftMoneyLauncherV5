package fr.dd06.craftmoney;

import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;
import fr.dd06.apis.javautils.java.util.directory.ProgramDir;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.LauncherStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CraftMoneyLauncher extends Application {

    private final File CRAFTMONEY_PROGRAM_DIR = ProgramDir.createProgramDir("CraftMoney");
    private final File ACCOUNT_DATA = new File(CRAFTMONEY_PROGRAM_DIR, "account_data.json");
    private JSONConfiguration accountDataConfig = new JSONConfiguration(ACCOUNT_DATA);
    private final File LAUNCHER_SETTINGS = new File(CRAFTMONEY_PROGRAM_DIR, "launcher_settings.json");
    private JSONConfiguration launcherSettingsConfig = new JSONConfiguration(LAUNCHER_SETTINGS);




    public static void main(String[] args) {

        launch(args);
    }

    private void initLauncher() {

        CRAFTMONEY_PROGRAM_DIR.mkdirs();
        CraftMoneyGame.CRAFTMONEY_GAME_DIR.mkdirs();
        CraftMoneyGame.CRAFTMONEY_LOG_DIR.mkdirs();

        if(!ACCOUNT_DATA.exists()) {
            try {
                ACCOUNT_DATA.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!LAUNCHER_SETTINGS.exists()) {
            try {
                LAUNCHER_SETTINGS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        launcherSettingsConfig.reloadConfiguration();
        launcherSettingsConfig.getConfiguration().putIfAbsent("autoAuth", false);
        launcherSettingsConfig.getConfiguration().putIfAbsent("lowRam", false);

        launcherSettingsConfig.saveConfiguration();

    }

    @Override
    public void start(Stage primaryStage) {
        initLauncher();
        LauncherStage launcherStage = new LauncherStage(primaryStage, this);

    }

    public JSONConfiguration getAccountDataConfig() {
        return accountDataConfig;
    }

    public JSONConfiguration getLauncherSettingsConfig() {
        return launcherSettingsConfig;
    }


}
