package fr.dd06.craftmoney.launcher.auth.controller;

import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.craftmoney.launcher.auth.Authentication;
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

    private void selectMojangAuth()  {
        authSelectorButton.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 15px;");
        authSelectorButton2.setStyle("-fx-border-color: #0C85E7 ;" +
                "-fx-border-width: 0;" +
                "-fx-border-radius: 15px;");
        mojangAuth = true;
    }

    private void selectCraftMoneyAuth()  {
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
        if(mojangAuth) {
            try {
                Desktop.getDesktop().browse(new URL("https://www.minecraft.net/fr-fr/password/forgot/").toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Desktop.getDesktop().browse(new URL("https://craftmoney.fr/password/forgot").toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {

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


        if(mojangAuth) {
            authErrorLabel.setText("");
            loginButton.setText("Connexion ...");
            authWithMojang();

        }
        else {
            authErrorLabel.setText("");
            loginButton.setText("Connexion ...");
            authWithCraftMoney();

        }

    }

    private void authWithMojang() {

        try {
            Authentication.authWithMojang(emailField.getText(), passwordField.getText());
        } catch (AuthenticationException e) {
            authErrorLabel.setText("Connexion impossible : Adresse E-mail ou mot de passe incorrect !");
            loginButton.setText("Connexion");
            Authentication.getAccount().disconnect();
            return;
        }
        authErrorLabel.setText("");
        loginButton.setText("Connected");
        loginButton.setDisable(true);
        if(rememberMe.isSelected()) {
            
        }

    }
    private void authWithCraftMoney() {
        Authentication.authWithCraftMoney(emailField.getText(), passwordField.getText());
    }
}
