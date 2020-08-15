package fr.dd06.craftmoney.launcher.home.settings;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.home.settings.controller.SettingsBorderPaneController;
import fr.dd06.craftmoney.launcher.home.settings.controller.categories.game.SettingsGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SettingsStage extends Stage {
    private CraftMoneyLauncher main;
    private LauncherStage launcherStage;
    private AnchorPane settingsPane;
    private BorderPane settingsContainer;


    public SettingsStage(LauncherStage launcherStage, CraftMoneyLauncher main) {
        this.launcherStage = launcherStage;
        this.main = main;
        initStage();

        initBorderPane();
        initAnchorPane();
        StageFX.setMovableWithBorder(this, settingsContainer, true, 50, 0, 0 , 0);
        this.show();
        AnimatorFX.fadeInFrameFX(this, AnimatorFX.FAST);
        this.centerOnScreen();
    }

    private void initAnchorPane() {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/settings/categories/game/SettingsGameViewPane.fxml"));

        try {
            settingsPane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsGameController controller = loader.getController();
        controller.init(this, main);

        settingsContainer.setCenter(settingsPane);
    }


    private void initBorderPane() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/settings/SettingsViewBorderPane.fxml"));

        try {
            settingsContainer = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SettingsBorderPaneController controller = loader.getController();
        controller.init(this);

        Scene scene = new Scene(settingsContainer);
        this.setScene(scene);
    }

    private void initStage() {
        this.setTitle("CraftMoney Launcher | Paramètres");
        this.setWidth(910);
        this.setHeight(710);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initOwner(launcherStage.getStage());
        this.initModality(Modality.WINDOW_MODAL);
    }

}
