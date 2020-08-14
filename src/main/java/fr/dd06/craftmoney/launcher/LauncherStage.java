package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.javautils.javafx.util.StageFX;
import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.craftmoney.CraftMoneyLauncher;

import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.auth.controller.AuthController;
import fr.dd06.craftmoney.launcher.borderpane.controller.BorderPaneController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;

public class LauncherStage {
    private Stage stage;
    private BorderPane container;
    private AnchorPane authPane;
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
        main.getAccountDataConfig().reloadConfiguration();
        FXMLLoader loader = new FXMLLoader();
        if (main.getAccountDataConfig().getConfiguration().get("token") != null) {

            loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AutoAuthPaneView.fxml"));
            try {
                authPane = (AnchorPane) loader.load();


                container.setCenter(authPane);
                    Thread thread = new Thread(() -> {
                        try {

                            Authentication.authWithMojang(main.getAccountDataConfig().getConfiguration().get("token").toString(), main);
                        } catch (AuthenticationException e) {

                            FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(getClass().getClassLoader().getResource("fxml/auth/AuthPaneView.fxml"));

                            try {
                                authPane = (AnchorPane) loader2.load();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                            AuthController controller = loader2.getController();
                            controller.init(main);
                            Platform.runLater(() -> {
                                container.setCenter(authPane);
                            });

                            main.getAccountDataConfig().getConfiguration().put("token", "failed");
                            main.getAccountDataConfig().saveConfiguration();
                            System.out.println("Invalid token!");



                        }
                        Platform.runLater(() -> {
                            if(Authentication.getAccount().getUUID() != null) {
                                System.out.println("Connecté en tant que " + Authentication.getAccount().getUsername());
                            }
                        });

                    });
                    thread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AuthPaneView.fxml"));
            try {
                authPane = (AnchorPane) loader.load();

                AuthController controller = loader.getController();
                controller.init(main);
                container.setCenter(authPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public Stage getStage() {
        return stage;
    }
}
