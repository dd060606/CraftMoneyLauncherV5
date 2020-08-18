package fr.dd06.craftmoney.launcher.home.mods;

import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.home.mods.controllers.ModsBorderPaneController;
import fr.dd06.craftmoney.launcher.home.mods.controllers.ModsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ModsStage extends Stage {


    private CraftMoneyLauncher main;
    private LauncherStage launcherStage;
    private BorderPane modsContainer;
    private AnchorPane modsPane;


    public ModsStage(CraftMoneyLauncher main, LauncherStage launcherStage) {

        this.main = main;
        this.launcherStage = launcherStage;


        this.setTitle("CraftMoney Launcher | Mods");
        this.setWidth(910);
        this.setHeight(710);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initOwner(launcherStage.getStage());
        this.initModality(Modality.WINDOW_MODAL);
        initModsBorderPane();
        initModsAnchorPane();
        StageFX.setMovableWithBorder(this, modsContainer, true, 50, 0, 0, 0);

        this.show();
        this.centerOnScreen();
    }

    private void initModsAnchorPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/mods/ModsPaneView.fxml"));

        try {
            modsPane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModsController controller = loader.getController();
        controller.init(main, this);
        modsContainer.setCenter(modsPane);
    }

    private void initModsBorderPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/mods/ModsBorderPaneView.fxml"));

        try {
            modsContainer = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModsBorderPaneController controller = loader.getController();
        controller.init(this);
        Scene scene = new Scene(modsContainer);
        this.setScene(scene);

    }

    public Stage getModsStage() {
        return this;
    }

    public BorderPane getModsContainer() {
        return modsContainer;
    }
    public AnchorPane getModsAnchorPane() {
        return modsPane;
    }

    public void setModsAnchorPane(AnchorPane ac) {
        this.modsPane = ac;
    }
}
