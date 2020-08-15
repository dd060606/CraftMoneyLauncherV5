package fr.dd06.craftmoney.launcher.home.settings;

import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.home.settings.controller.SettingsBorderPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SettingsStage extends Stage {
    private LauncherStage launcherStage;
    private AnchorPane settingsPane;
    private BorderPane settingsContainer;


    public SettingsStage(LauncherStage launcherStage) {
        this.launcherStage = launcherStage;
        initStage();

        initBorderPane();
        this.show();
        this.centerOnScreen();
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
        this.setWidth(640);
        this.setHeight(710);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initOwner(launcherStage.getStage());
        this.initModality(Modality.WINDOW_MODAL);
    }

}
