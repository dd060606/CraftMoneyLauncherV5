package fr.dd06.craftmoney.launcher.auth.controller;

import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.home.controller.LauncherController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class AuthController {

    @FXML
    private HBox authSelectorButton;
    @FXML
    private HBox authSelectorButton2;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label forgotPassword;
    @FXML
    private Label authErrorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private HBox passwordFieldBox;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;

    private boolean mojangAuth = true;
    private CraftMoneyLauncher main;
    private LauncherStage stage;

    private void selectMojangAuth() {
        authSelectorButton.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 15px;");
        authSelectorButton2.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 0;" +
                "-fx-border-radius: 15px;");
        passwordField.setVisible(true);
        passwordFieldBox.setVisible(true);
        passwordLabel.setVisible(true);
        emailLabel.setText("Adresse E-mail");
        emailField.setPromptText("Adresse E-mail");

        mojangAuth = true;
    }

    private void selectCraftMoneyAuth() {

        authSelectorButton2.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 15px;");
        authSelectorButton.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 0;" +
                "-fx-border-radius: 15px;");
        passwordField.setVisible(false);
        passwordFieldBox.setVisible(false);
        passwordLabel.setVisible(false);
        emailLabel.setText("Pseudo");
        emailField.setPromptText("Pseudo");
        mojangAuth = false;
    }

    @FXML
    private void forgotPassword() {
        if (mojangAuth) {
            try {
                Desktop.getDesktop().browse(new URL("https://www.minecraft.net/fr-fr/password/forgot/").toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Desktop.getDesktop().browse(new URL("https://craftmoney.fr/password/forgot").toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void init(CraftMoneyLauncher main, LauncherStage stage) {

        this.main = main;
        this.stage = stage;


        if (mojangAuth) {
            selectMojangAuth();
        }
        authSelectorButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectMojangAuth();
            }
        });
        authSelectorButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectCraftMoneyAuth();
            }
        });

    }

    @FXML
    private void login() {

        disableFields(true);
        if (mojangAuth) {


            authWithMojang();

        } else {


            authWithCraftMoney();

        }

    }

    private void disableFields(boolean enabled) {
        if (enabled) {
            loginButton.setDisable(true);
            emailField.setDisable(true);
            passwordField.setDisable(true);
            rememberMe.setDisable(true);
        } else {
            loginButton.setDisable(false);
            emailField.setDisable(false);
            passwordField.setDisable(false);
            rememberMe.setDisable(false);
        }
    }

    private void authWithMojang() {
        authErrorLabel.setText("");
        loginButton.setText("Connexion ...");


        Thread thread = new Thread(() -> {
            try {

                Authentication.authWithMojang(emailField.getText(), passwordField.getText());
            } catch (AuthenticationException e) {
                Platform.runLater(() -> {
                    authErrorLabel.setText("Connexion impossible : Adresse E-mail ou mot de passe incorrect !");
                    loginButton.setText("Connexion");
                });

                Authentication.getAccount().disconnect();
                disableFields(false);
            }
            Platform.runLater(() -> {
                if (Authentication.getAccount().getUUID() != null) {
                    disableFields(true);
                    authErrorLabel.setText("");
                    loginButton.setText("Connected");

                    if (rememberMe.isSelected()) {
                        main.getAccountDataConfig().reloadConfiguration();
                        main.getLauncherSettingsConfig().reloadConfiguration();
                        main.getAccountDataConfig().getConfiguration().put("token", Authentication.getAccount().getAccessToken());
                        main.getLauncherSettingsConfig().getConfiguration().put("autoAuth", true);
                        main.getLauncherSettingsConfig().saveConfiguration();
                        main.getAccountDataConfig().saveConfiguration();
                    } else {
                        main.getAccountDataConfig().reloadConfiguration();
                        if (main.getAccountDataConfig().getConfiguration().get("token") != null) {
                            main.getAccountDataConfig().getConfiguration().put("token", "failed");
                            main.getAccountDataConfig().saveConfiguration();
                        }
                    }

                    FXMLLoader loader3 = new FXMLLoader();
                    loader3.setLocation(getClass().getClassLoader().getResource("fxml/launcher/LauncherPaneView.fxml"));
                    try {
                        stage.setAnchorPane((AnchorPane) loader3.load());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    LauncherController controller = loader3.getController();
                    controller.init(main, stage);
                    stage.getContainer().setCenter(stage.getAnchorPane());

                }
            });

        });

        thread.start();


    }

    private void authWithCraftMoney() {
        Thread thread = new Thread(() -> {
            Authentication.authWithCraftMoney(emailField.getText());
            disableFields(true);

            if (rememberMe.isSelected()) {
                main.getAccountDataConfig().reloadConfiguration();
                main.getLauncherSettingsConfig().reloadConfiguration();
                main.getAccountDataConfig().getConfiguration().put("token", "pseudo:"+emailField.getText());
                main.getLauncherSettingsConfig().getConfiguration().put("autoAuth", true);
                main.getLauncherSettingsConfig().saveConfiguration();
                main.getAccountDataConfig().saveConfiguration();
            } else {
                main.getAccountDataConfig().reloadConfiguration();
                if (main.getAccountDataConfig().getConfiguration().get("token") != null) {
                    main.getAccountDataConfig().getConfiguration().put("token", "failed");
                    main.getAccountDataConfig().saveConfiguration();
                }
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader loader3 = new FXMLLoader();
                    loader3.setLocation(getClass().getClassLoader().getResource("fxml/launcher/LauncherPaneView.fxml"));
                    try {
                        stage.setAnchorPane((AnchorPane) loader3.load());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    LauncherController controller = loader3.getController();
                    controller.init(main, stage);
                    stage.getContainer().setCenter(stage.getAnchorPane());
                }
            });


        });
        thread.start();

    }
}
