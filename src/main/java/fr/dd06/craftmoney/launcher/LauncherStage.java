package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.javautils.javafx.animation.AnimatorFX;
import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.craftmoney.CraftMoneyLauncher;

import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.auth.controller.AuthController;
import fr.dd06.craftmoney.launcher.borderpane.controller.BorderPaneController;
import fr.dd06.craftmoney.launcher.home.controller.LauncherController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LauncherStage {
    private Stage stage;
    private BorderPane container;
    private AnchorPane anchorPane;
    private CraftMoneyLauncher main;

    public LauncherStage(Stage stage, CraftMoneyLauncher main) {
        this.stage = stage;
        this.main = main;
        initStage();

    }

    private void initStage() {
        stage.setTitle("CraftMoney Launcher V5");
        stage.setResizable(false);
        stage.setWidth(1280);
        stage.setHeight(810);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/logo/icon.png").toString()));


        initBorderPane();

        initAuth();

        StageFX.setMovableWithBorder(stage, container, true, 70, 0, 0, 0);
        stage.show();
        AnimatorFX.fadeInFrameFX(stage, AnimatorFX.FAST);
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

    private void initAuth() {
        main.getAccountDataConfig().reloadConfiguration();
        main.getLauncherSettingsConfig().reloadConfiguration();
        FXMLLoader loader = new FXMLLoader();
        if (main.getLauncherSettingsConfig().getConfiguration().get("autoAuth") != null && main.getAccountDataConfig().getConfiguration().get("token") != null) {

            if (main.getAccountDataConfig().getConfiguration().get("token").toString().equals("failed")) {
                initAuthPane();

            } else {

                if (!Boolean.parseBoolean(main.getLauncherSettingsConfig().getConfiguration().get("autoAuth").toString())){
                    initAuthPane();
                } else {
                    loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AutoAuthPaneView.fxml"));
                    try {
                        anchorPane = (AnchorPane) loader.load();
                        container.setCenter(anchorPane);

                        Thread thread = new Thread(() -> {
                            try {

                                Authentication.authWithMojang(main.getAccountDataConfig().getConfiguration().get("token").toString(), main);
                            } catch (AuthenticationException e) {

                                initAuthPane();
                                Platform.runLater(() -> {
                                    container.setCenter(anchorPane);
                                });

                                main.getAccountDataConfig().getConfiguration().put("token", "failed");
                                main.getAccountDataConfig().saveConfiguration();
                                System.out.println("Invalid token!");


                            }
                            Platform.runLater(() -> {
                                if (Authentication.getAccount().getUUID() != null) {

                                    System.out.println("Connecté en tant que " + Authentication.getAccount().getUsername());

                                    FXMLLoader loader3 = new FXMLLoader();
                                    loader3.setLocation(getClass().getClassLoader().getResource("fxml/launcher/LauncherPaneView.fxml"));
                                    try {
                                        anchorPane = (AnchorPane) loader3.load();
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }

                                    LauncherController controller = loader3.getController();
                                    controller.init(main, this);
                                    container.setCenter(anchorPane);

                                }
                            });

                        });
                        thread.start();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        } else {
           initAuthPane();
        }


    }
    private void initAuthPane() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AuthPaneView.fxml"));
        try {
            anchorPane = (AnchorPane) loader.load();

            AuthController controller = loader.getController();
            controller.init(main, this);
            container.setCenter(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getStage() {
        return stage;
    }
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    public void setAnchorPane(AnchorPane pane) {
        this.anchorPane = pane;
    }
    public BorderPane getContainer() {
        return container;
    }
}
