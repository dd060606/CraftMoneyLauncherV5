package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.mclauncher.LaunchException;
import fr.dd06.apis.mclauncher.external.ExternalLaunchProfile;
import fr.dd06.apis.mclauncher.external.ExternalLauncher;
import fr.dd06.apis.mclauncher.minecraft.*;
import fr.dd06.apis.mclauncher.util.ProcessLogManager;
import fr.dd06.apis.mclauncher.util.ramselector.RamSelector;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.update.CraftMoneyUpdater;
import fr.dd06.craftmoney.launcher.update.controller.UpdateController;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.utils.BuilderArgumentException;
import fr.flowarg.flowupdater.versions.IForgeVersion;
import fr.flowarg.flowupdater.versions.IVanillaVersion;
import fr.flowarg.flowupdater.versions.NewForgeVersion;
import fr.flowarg.flowupdater.versions.VersionType;
import fr.flowarg.flowupdater.versions.download.IProgressCallback;
import fr.flowarg.flowupdater.versions.download.Mod;
import fr.flowarg.flowupdater.versions.download.Step;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CraftMoneyGame {


    private static final GameVersion GAME_VERSION = new GameVersion("1.12", GameType.V1_8_HIGHER);
    private static final GameInfos GAME_INFOS = new GameInfos("CraftMoney", GAME_VERSION , new GameTweak[] {});
    private LauncherStage stage;
    private CraftMoneyLauncher main;

    public static File CRAFTMONEY_GAME_DIR = GAME_INFOS.getGameDir();
    public static File CRAFTMONEY_LOG_DIR = new File(CRAFTMONEY_GAME_DIR, "logs/");

    private UpdateController controller;


    public CraftMoneyGame(LauncherStage stage, CraftMoneyLauncher main) {
        this.stage = stage;
        this.main = main;
    }

    public void start() {
        initUpdatePane();
        update();

    }

    private void update() {
        CraftMoneyUpdater craftMoneyUpdater = new CraftMoneyUpdater();
        Thread thread = new Thread(() -> {
            try {
                craftMoneyUpdater.update(CRAFTMONEY_GAME_DIR, new IProgressCallback() {
                    @Override
                    public void init() {
                        Platform.runLater(()-> {
                            controller.getUpdateLabel().setText("Recherche de mises à jours . . .");
                        });

                    }

                    @Override
                    public void step(Step step) {
                        if(step == Step.END) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Lancement du jeu . . .");
                                controller.getProgressBar().setProgress(1);

                                try {
                                    launchGame();
                                } catch (LaunchException e) {
                                    System.out.println("[CraftMoney] : Impossible de lancer le jeu ");
                                    e.printStackTrace();
                                    System.exit(0);
                                }
                            });
                        }
                    }

                    @Override
                    public void update(int downloaded, int max) {
                        Platform.runLater(() -> {
                            int updatePercentage = (100* downloaded) / max;

                            controller.getUpdateLabel().setText("Mise à jour en cours (" + updatePercentage + "%)");
                            controller.getProgressBar().setProgress((double) updatePercentage / 100);
                        });

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }

    private void initUpdatePane() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getClassLoader().getResource("fxml/launcher/update/UpdateViewPane.fxml"));

        try {
            stage.setAnchorPane(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
         controller = loader.getController();
        controller.init();
        stage.getContainer().setCenter(stage.getAnchorPane());
    }
    private void launchGame() throws LaunchException {

        ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(GAME_INFOS, GameFolder.CRAFTMONEY, Authentication.getAccount());
        main.getLauncherSettingsConfig().reloadConfiguration();
        if(main.getLauncherSettingsConfig().getConfiguration().get("ram") == null) {
            main.getLauncherSettingsConfig().getConfiguration().put("ram", 1);
        }
        main.getLauncherSettingsConfig().saveConfiguration();
        double allocatedRam = Double.parseDouble(main.getLauncherSettingsConfig().getConfiguration().get("ram").toString());
        int allocatedRamInMb = (int) (allocatedRam * 1000);
        profile.getVmArgs().addAll(Arrays.asList("-Xms2G", "-Xmx3G"));

        ExternalLauncher launcher = new ExternalLauncher(profile);

        Process process = launcher.launch();

        ProcessLogManager logManager = new ProcessLogManager(process.getInputStream(), new File(CRAFTMONEY_LOG_DIR, "last-game-logs.txt"));
        logManager.start();
        try {
            Thread.sleep(7000L);

            process.waitFor();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

}
