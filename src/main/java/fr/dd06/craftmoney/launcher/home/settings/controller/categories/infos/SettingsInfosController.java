package fr.dd06.craftmoney.launcher.home.settings.controller.categories.infos;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class SettingsInfosController {

    @FXML
    private void openOfficialLauncherLink() {
        try {
            Desktop.getDesktop().browse(new URL("https://craftmoney.fr/launcher").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
