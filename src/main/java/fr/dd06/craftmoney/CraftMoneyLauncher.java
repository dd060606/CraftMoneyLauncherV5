package fr.dd06.craftmoney;

import fr.dd06.craftmoney.launcher.LauncherStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class CraftMoneyLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LauncherStage launcherStage = new LauncherStage(primaryStage);

    }
}
