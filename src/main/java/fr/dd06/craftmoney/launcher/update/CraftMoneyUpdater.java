package fr.dd06.craftmoney.launcher.update;

import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.utils.BuilderArgumentException;
import fr.flowarg.flowupdater.versions.IVanillaVersion;
import fr.flowarg.flowupdater.versions.NewForgeVersion;
import fr.flowarg.flowupdater.versions.VersionType;
import fr.flowarg.flowupdater.versions.download.IProgressCallback;
import fr.flowarg.flowupdater.versions.download.Mod;
import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CraftMoneyUpdater {
    private final IVanillaVersion.Builder versionBuilder;
    private final IVanillaVersion version ;
    private final CraftMoneyLauncher main;
    private FlowUpdater updater;
    private LauncherStage launcherStage;


    private Logger updateLogger = new Logger("[CraftMoney]", new File(CraftMoneyGame.CRAFTMONEY_LOG_DIR, "last-launcher-update.log"));

    public CraftMoneyUpdater(LauncherStage launcherStage, CraftMoneyLauncher main) {
        this.launcherStage = launcherStage;
        this.main = main;

       versionBuilder = new IVanillaVersion.Builder("1.12.2");
       version = versionBuilder.build(false, VersionType.FORGE);



    }

    public void update(IProgressCallback callback) throws IOException {


            List<Mod> listMods = Mod.getModsFromJson("https://drive.google.com/u/0/uc?id=1uvezMIhvoz1I3DfOCAHKziNcg5wb7cpZ&export=download");

        try {
            updater = new FlowUpdater.FlowUpdaterBuilder().withVersion(version).withSilentUpdate(true).withLogger(updateLogger).withProgressCallback(callback).withForgeVersion(new NewForgeVersion("1.12.2-14.23.5.2854", version, updateLogger, callback, listMods, true).enableModFileDeleter()).build();

        } catch (BuilderArgumentException e) {
            e.printStackTrace();
        }



        try {

            updater.update(CraftMoneyGame.CRAFTMONEY_GAME_DIR, false);
        } catch (Exception e) {
            Platform.runLater(() -> {
                ErrorStage errorStage = new ErrorStage(launcherStage, main);
                errorStage.getController().setErrorType("Impossible de mettre à jour le jeu !");
            });
            e.printStackTrace();
        }







    }

    public IVanillaVersion.Builder getVersionBuilder() {
        return versionBuilder;
    }

    public IVanillaVersion getVersion() {
        return version;
    }

    public FlowUpdater getUpdater() {
        return updater;
    }
    public Logger getUpdateLogger() {
        return updateLogger;
    }

}
