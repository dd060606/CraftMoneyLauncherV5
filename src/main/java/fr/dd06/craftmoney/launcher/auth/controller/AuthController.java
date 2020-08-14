package fr.dd06.craftmoney.launcher.auth.controller;

import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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


    private boolean mojangAuth = true;
    private CraftMoneyLauncher main;

    private void selectMojangAuth() {
        authSelectorButton.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 15px;");
        authSelectorButton2.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 0;" +
                "-fx-border-radius: 15px;");
        mojangAuth = true;
    }

    private void selectCraftMoneyAuth() {
        authSelectorButton2.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 15px;");
        authSelectorButton.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 0;" +
                "-fx-border-radius: 15px;");
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

    public void init(CraftMoneyLauncher main) {

        this.main = main;


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
                        main.getAccountDataConfig().getConfiguration().put("token", Authentication.getAccount().getAccessToken());
                        main.getAccountDataConfig().saveConfiguration();
                    } else {
                        main.getAccountDataConfig().reloadConfiguration();
                        if (main.getAccountDataConfig().getConfiguration().get("token") != null) {
                            main.getAccountDataConfig().getConfiguration().put("token", "failed");
                            main.getAccountDataConfig().saveConfiguration();
                        }
                    }
                }
            });

        });

        thread.start();



    }

    private void authWithCraftMoney() {
        Authentication.authWithCraftMoney(emailField.getText(), passwordField.getText());
    }
}
