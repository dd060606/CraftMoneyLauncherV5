package fr.dd06.craftmoney.launcher.home.settings.controller.categories.launcher;

import com.jfoenix.controls.JFXToggleButton;
import fr.dd06.apis.javautils.java.util.file.analyse.FileAnalyzer;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.dd06.craftmoney.launcher.home.settings.SettingsStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsLauncherController {
    @FXML
    private GridPane settingsAuthGridPane;
    @FXML
    private GridPane settingsLowRamGridPane;
    private JFXToggleButton authToggleButton;
    private JFXToggleButton lowramToggleButton;
    private CraftMoneyLauncher main;
    @FXML
    private Button updateButton;
    
    public void init(SettingsStage settingsStage,   CraftMoneyLauncher main) {
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        main.getLauncherSettingsConfig().reloadConfiguration();
        authToggleButton = new JFXToggleButton();
        main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("autoAuth", true);
        authToggleButton.setSelected(Boolean.parseBoolean(main.getLauncherSettingsConfig().getConfiguration().get("autoAuth").toString()));
        settingsAuthGridPane.add(authToggleButton, 0, 0);

        lowramToggleButton = new JFXToggleButton();
        main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("lowRam", false);
        lowramToggleButton.setSelected(Boolean.parseBoolean(main.getLauncherSettingsConfig().getConfiguration().get("lowRam").toString()));
        settingsLowRamGridPane.add(lowramToggleButton, 0, 0);

        main.getLauncherSettingsConfig().saveConfiguration();
        initEvents();
    }

    private void initEvents() {

        authToggleButton.setOnMouseClicked(event -> {
            main.getLauncherSettingsConfig().reloadConfiguration();
            main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("autoAuth", true);
            main.getLauncherSettingsConfig().getConfiguration().put("autoAuth", authToggleButton.isSelected());

            main.getLauncherSettingsConfig().saveConfiguration();
        });
        lowramToggleButton.setOnMouseClicked(event -> {
            main.getLauncherSettingsConfig().reloadConfiguration();
            main.getLauncherSettingsConfig().getConfiguration().putIfAbsent("lowRam", false);
            main.getLauncherSettingsConfig().getConfiguration().put("lowRam", lowramToggleButton.isSelected());

            main.getLauncherSettingsConfig().saveConfiguration();
        });


    }
    @FXML
    private void updateLauncher() {
        updateButton.setDisable(true);
        updateButton.setText("Mise à jour ...");
        File launcherUpdaterFile = new File(CraftMoneyLauncher.CRAFTMONEY_PROGRAM_DIR, "CraftMoney Launcher.exe");
        if(launcherUpdaterFile.exists()) {
            Thread updateThread = new Thread(() -> {
                try {
                    InputStream is = new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/launcher.txt").openStream();
                    BufferedReader reader  = new BufferedReader(new InputStreamReader(is));
                    for (int i = 0; i < 1; i++)
                        reader.readLine();
                    String line = reader.readLine();
                    reader.close();
                    is.close();
                    FileAnalyzer analyzer = new FileAnalyzer();
                    if (analyzer.getFileMD5(launcherUpdaterFile).equals(line)) {
                        launchLauncherUpdater(launcherUpdaterFile);
                    }
                    else {
                        downloadLauncherUpdater(new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/CraftMoney Launcher.exe"));
                        launchLauncherUpdater(launcherUpdaterFile);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            updateThread.start();


        }
        else {
            Thread downloadUpdaterThread = new Thread(() -> {
                try {
                    downloadLauncherUpdater(new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/CraftMoney%20Launcher.exe"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                launchLauncherUpdater(launcherUpdaterFile);
            });
            downloadUpdaterThread.start();

        }
    }
    private void downloadLauncherUpdater(URL url) throws IOException {
        File updaterFile = new File(CraftMoneyLauncher.CRAFTMONEY_PROGRAM_DIR, "CraftMoney Launcher.exe");
        updaterFile.getParentFile().mkdirs();
        Files.copy(url.openStream(), updaterFile.toPath());


    }
    private void launchLauncherUpdater(File launcherUpdater) {
        try {
            Process process = Runtime.getRuntime().exec(launcherUpdater.getAbsolutePath());

        } catch (IOException e) {
            Platform.runLater(() -> {
                ErrorStage errorStage = new ErrorStage(main.getLauncherStage(), main);
                errorStage.getController().setErrorType("Impossible de lancer le launcher !");

            });
            return;
        }
        Timer exitTimer = new Timer();
        exitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 400);
    }
}
