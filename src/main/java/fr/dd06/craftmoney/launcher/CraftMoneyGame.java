package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.mclauncher.LaunchException;
import fr.dd06.apis.mclauncher.external.ExternalLaunchProfile;
import fr.dd06.apis.mclauncher.external.ExternalLauncher;
import fr.dd06.apis.mclauncher.minecraft.*;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.auth.Authentication;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.dd06.craftmoney.launcher.update.CraftMoneyUpdater;
import fr.dd06.craftmoney.launcher.update.controller.UpdateController;
import fr.dd06.craftmoney.launcher.utils.CrashLogger;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.download.IProgressCallback;
import fr.flowarg.flowupdater.download.Step;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class CraftMoneyGame {


    private static final GameVersion GAME_VERSION = new GameVersion("1.12", GameType.V1_8_HIGHER);
    private static final GameInfos GAME_INFOS = new GameInfos("CraftMoney", GAME_VERSION, new GameTweak[]{
            GameTweak.FORGE
    });
    private LauncherStage stage;
    private CraftMoneyLauncher main;

    public static File CRAFTMONEY_GAME_DIR = GAME_INFOS.getGameDir();
    public static File CRAFTMONEY_LOG_DIR = new File(CRAFTMONEY_GAME_DIR, "logs/");

    private UpdateController controller;
    private boolean gameLaunched = false;
    private boolean downloadingMods = false;

    public CraftMoneyGame(LauncherStage stage, CraftMoneyLauncher main) {
        this.stage = stage;
        this.main = main;
    }

    public void start() {
        initUpdatePane();
        update();

    }

    private void update() {

        CraftMoneyUpdater craftMoneyUpdater = new CraftMoneyUpdater(stage, main);

        Platform.runLater(() -> {
            controller.getUpdateLabel().setText("Recherche de mises à jours . . .");
        });
        Thread thread = new Thread(() -> {

            try {

                craftMoneyUpdater.update(CRAFTMONEY_GAME_DIR, new IProgressCallback() {



                    @Override
                    public void init(ILogger logger) {
                        Platform.runLater(() -> {
                            controller.getUpdateLabel().setText("Initialisation de la mise à jour . . .");
                        });
                    }

                    @Override
                    public void step(Step step) {
                        if (step == Step.DL_ASSETS) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Installation des assets . . .");

                            });
                        }
                        if (step == Step.DL_LIBS) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Installation des libraries . . .");

                            });
                        }

                        if (step == Step.FORGE) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Installation de forge . . .");
                                controller.getProgressBar().setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                            });
                        }
                        if (step == Step.MODS) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Installation des Mods . . .");

                                downloadingMods = true;
                            });
                        }
                        if (step == Step.READ) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Lecture des fichiers . . .");
                            });
                        }
                        if (step == Step.EXTERNAL_FILES || step == Step.EXTRACT_NATIVES) {
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Extraction des fichiers . . .");
                            });
                        }

                        if (step == Step.END) {
                            downloadingMods = false;
                            Platform.runLater(() -> {
                                controller.getUpdateLabel().setText("Lancement du jeu . . .");
                                controller.getProgressBar().setProgress(1);

                                String oldJavaHome = System.getProperty("java.home");
                                try {
                                    System.setProperty("java.home", new File(CraftMoneyLauncher.CRAFTMONEY_PROGRAM_DIR, "/jre/").toPath().toString());

                                    launchGame();
                                } catch (LaunchException e) {

                                    ErrorStage error = new ErrorStage(stage, main);
                                    error.getController().setErrorType("Impossible de lancer le jeu !");
                                    CrashLogger crashLogger = new CrashLogger();
                                    crashLogger.createCrashLog(e);

                                    System.setProperty("java.home", oldJavaHome);
                                    System.out.println("[CraftMoney] : Impossible de lancer le jeu !");
                                    e.printStackTrace();
                                    gameLaunched = false;

                                }
                                if (gameLaunched) {
                                    Timer timer = new Timer();
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            System.exit(0);
                                        }
                                    }, 8500);
                                }

                            });
                        }

                    }

                    @Override
                    public void update(int downloaded, int max) {

                            Platform.runLater(() -> {
                                if (!downloadingMods) {

                                    int updatePercentage = (100 * downloaded) / max;

                                    controller.getUpdateLabel().setText("Mise à jour en cours (" + updatePercentage + "%)");
                                    controller.getProgressBar().setProgress((double) updatePercentage / 100);
                                } else {
                                    int updatePercentage = (100 * downloaded) / max;

                                    controller.getUpdateLabel().setText("Téléchargement des mods (" + updatePercentage + "%)");
                                    controller.getProgressBar().setProgress((double) updatePercentage / 100);
                                }

                            });


                    }


                });
            } catch (Exception e) {


                Platform.runLater(() -> {
                    ErrorStage error = new ErrorStage(stage, main);
                    error.getController().setErrorType("Impossible de mettre à jour le jeu !");
                    CrashLogger crashLogger = new CrashLogger();
                    crashLogger.createCrashLog(e);

                });
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
        if (main.getLauncherSettingsConfig().getConfiguration().get("ram") == null) {
            main.getLauncherSettingsConfig().getConfiguration().put("ram", 1);
        }
        main.getLauncherSettingsConfig().saveConfiguration();
        double allocatedRam = Double.parseDouble(main.getLauncherSettingsConfig().getConfiguration().get("ram").toString());
        int allocatedRamInMb = (int) (allocatedRam * 1000);
        profile.getVmArgs().addAll(Arrays.asList("-Xms1000m", "-Xmx" + allocatedRamInMb + "m"));
        //profile.getArgs().addAll(Arrays.asList(new String[]{"--server=play.craftmoney.fr", "--port=25565"}));

        ExternalLauncher launcher = new ExternalLauncher(profile);

        Process process = launcher.launch();
        this.gameLaunched = true;


    }

}
