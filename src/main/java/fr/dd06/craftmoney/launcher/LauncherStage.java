package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.craftmoney.CraftMoneyLauncher;

import fr.dd06.craftmoney.launcher.borderpane.controller.BorderPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LauncherStage {
    private Stage stage;
    private BorderPane container;
    private AnchorPane authPane;

    public LauncherStage(Stage stage) {
        this.stage = stage;

        initStage();

    }

    private void initStage() {
        stage.setTitle("CraftMoney Launcher V5");
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(810);
        stage.initStyle(StageStyle.UNDECORATED);


        initBorderPane();

        initAuthPane();

        StageFX.setMovableWithBorder(stage, container, true, 70, 0, 0, 0);
        stage.show();
        stage.centerOnScreen();

    }

    private void initBorderPane() {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/BorderPaneView.fxml"));


        try {

            container = (BorderPane) loader.load();

            BorderPaneController controller = loader.getController();
            controller.init(stage);
            Scene scene = new Scene(container);

            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAuthPane() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AuthPaneView.fxml"));

        try {
            authPane = (AnchorPane) loader.load();
            container.setCenter(authPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public Stage getStage() {
        return stage;
    }
}
