package fr.dd06.craftmoney;

import fr.dd06.apis.javautils.java.util.directory.ProgramDir;
import fr.dd06.craftmoney.launcher.LauncherStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class CraftMoneyLauncher extends Application {

    private File CRAFTMONEY_PROGRAM_DIR = ProgramDir.createProgramDir("CraftMoney");




    public static void main(String[] args) {

        launch(args);
    }

    private void initLauncher() {

        CRAFTMONEY_PROGRAM_DIR.mkdirs();



    }

    @Override
    public void start(Stage primaryStage) {
        initLauncher();
        LauncherStage launcherStage = new LauncherStage(primaryStage);

    }
}
