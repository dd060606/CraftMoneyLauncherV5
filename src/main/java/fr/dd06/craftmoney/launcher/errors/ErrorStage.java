package fr.dd06.craftmoney.launcher.errors;

import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.errors.controller.ErrorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ErrorStage extends Stage {


    private LauncherStage launcherStage;
    private ErrorController controller;
    private AnchorPane pane;
    private CraftMoneyLauncher main;

    public ErrorStage(LauncherStage launcherStage, CraftMoneyLauncher main) {

        this.launcherStage = launcherStage;
        this.main = main;

        this.setTitle("CraftMoney Launcher | Erreur");
        this.initOwner(launcherStage.getStage());
        this.initModality(Modality.WINDOW_MODAL);
        this.setWidth(500);
        this.setHeight(350);
        this.initStyle(StageStyle.UNDECORATED);
        this.setResizable(false);

        initPane();
        StageFX.setMovableWithBorder(this, pane, true, 50, 0, 0, 0);
        this.show();
        this.centerOnScreen();
    }

    private void initPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/errors/ErrorViewPane.fxml"));
        try {
             pane = (AnchorPane) loader.load();

            controller = loader.getController();
            controller.init(this, launcherStage, main);
            Scene scene = new Scene(pane);

            this.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ErrorController getController() {
        return controller;
    }

}
