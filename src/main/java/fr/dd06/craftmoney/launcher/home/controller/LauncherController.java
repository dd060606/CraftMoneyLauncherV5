package fr.dd06.craftmoney.launcher.home.controller;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.auth.controller.AuthController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LauncherController {

    @FXML
    private ImageView playerHead;

    @FXML
    private Label playerName;

    @FXML
    private Label playerRank;

    @FXML
    private Label shopPoints;

    @FXML
    private Text infoText1;
    @FXML
    private Text infoText2;
    @FXML
    private Text infoText3;
    @FXML
    private Button settingsButton;


    private CraftMoneyLauncher main;
    private LauncherStage stage;

    public void init(CraftMoneyLauncher main, LauncherStage stage) {
        this.main = main;
        this.stage = stage;

        playerHead.setImage(new Image("https://minotar.net/avatar/" + Authentication.getAccount().getUsername() + "/75.png"));
        playerName.setText("Pseudo: " + Authentication.getAccount().getUsername());
        playerRank.setText("Grade: " + "Joueur");
        shopPoints.setText("Points boutiques: " + "0");

        if (playerName.getText().length() > 19) {
            playerName.setFont(new Font("Arial Black", 12));
        }
        initInformations();
        initEvents();
    }

    private void initEvents() {
        settingsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RotateTransition rt = new RotateTransition(Duration.millis(3000), imageView);
                rt.setByAngle(360);
                rt.setCycleCount(Animation.INDEFINITE);
                rt.setInterpolator(Interpolator.LINEAR);
                rt.play();

            }
        });
    }

    private void initInformations() {
        infoText1.setText("Aucune informations à afficher !");
        infoText2.setText("");
        infoText3.setText("");
    }

    @FXML
    private void logout() {

        main.getAccountDataConfig().reloadConfiguration();
        if (main.getAccountDataConfig().getConfiguration().get("token") != null) {
            main.getAccountDataConfig().getConfiguration().put("token", "failed");
        }
        main.getAccountDataConfig().saveConfiguration();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/auth/AuthPaneView.fxml"));

        try {
            stage.setAnchorPane((AnchorPane) loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AuthController controller = loader.getController();
        controller.init(main, stage);
        stage.getContainer().setCenter(stage.getAnchorPane());
    }

    @FXML
    private void play() {

    }


    @FXML
    private void openCraftMoneyWebsite() {
        try {
            Desktop.getDesktop().browse(new URL("https://craftmoney.fr").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCraftMoneyDiscord() {
        try {
            Desktop.getDesktop().browse(new URL("https://craftmoney.fr/discord").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCraftMoneyYoutube() {
        try {
            Desktop.getDesktop().browse(new URL(" https://www.youtube.com/channel/UC4h1OOZUUkRLWJnzejpUt3g?reload=9"
            ).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openModsMenu() {

    }
    @FXML
    private void openSettings() {

    }
}
