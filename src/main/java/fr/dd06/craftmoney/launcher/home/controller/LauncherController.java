package fr.dd06.craftmoney.launcher.home.controller;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.auth.controller.AuthController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;

public class LauncherController {

    @FXML
    private ImageView playerHead;

    @FXML
    private Label playerName;

    @FXML
    private Label playerRank;

    @FXML
    private Label shopPoints;


    private CraftMoneyLauncher main;
    private LauncherStage stage;

    public void init(CraftMoneyLauncher main, LauncherStage stage) {
        this.main = main;
        this.stage = stage;

        playerHead.setImage(new Image("https://minotar.net/avatar/"+ Authentication.getAccount().getUsername() +"/75.png"));
        playerName.setText("Pseudo: "+ Authentication.getAccount().getUsername());
        playerRank.setText("Grade: " + "Joueur");
        shopPoints.setText("Points boutiques: " + "0");

        if(playerName.getText().length() > 19) {
            playerName.setFont(new Font("Arial Black", 12));
        }
    }
    
    @FXML
    private void logout() {

        main.getAccountDataConfig().reloadConfiguration();
        if(main.getAccountDataConfig().getConfiguration().get("token") != null) {
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
}
