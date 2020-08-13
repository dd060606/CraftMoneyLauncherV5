package fr.dd06.craftmoney;

import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;
import fr.dd06.apis.javautils.java.util.directory.ProgramDir;
import fr.dd06.craftmoney.launcher.LauncherStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CraftMoneyLauncher extends Application {

    private final File CRAFTMONEY_PROGRAM_DIR = ProgramDir.createProgramDir("CraftMoney");
    private final File ACCOUNT_DATA = new File(CRAFTMONEY_PROGRAM_DIR, "account_data.json");
    private JSONConfiguration accountDataConfig = new JSONConfiguration(ACCOUNT_DATA);




    public static void main(String[] args) {

        launch(args);
    }

    private void initLauncher() {

        CRAFTMONEY_PROGRAM_DIR.mkdirs();

        if(!ACCOUNT_DATA.exists()) {
            try {
                ACCOUNT_DATA.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void start(Stage primaryStage) {
        initLauncher();
        LauncherStage launcherStage = new LauncherStage(primaryStage, this);

    }

    public JSONConfiguration getAccountDataConfig() {
        return accountDataConfig;
    }
}
