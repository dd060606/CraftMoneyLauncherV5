package fr.dd06.craftmoney.launcher.update;

import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.utils.BuilderArgumentException;
import fr.flowarg.flowupdater.versions.IVanillaVersion;
import fr.flowarg.flowupdater.versions.VersionType;
import fr.flowarg.flowupdater.versions.download.IProgressCallback;

import java.io.File;
import java.io.IOException;

public class CraftMoneyUpdater {
    private final IVanillaVersion.Builder versionBuilder;
    private final IVanillaVersion version ;
    private FlowUpdater updater;



    private Logger updateLogger = new Logger("[CraftMoney]", new File(CraftMoneyGame.CRAFTMONEY_LOG_DIR, "last-launcher.log"));

    public CraftMoneyUpdater() {

       versionBuilder = new IVanillaVersion.Builder("1.12.2");
       version = versionBuilder.build(false, VersionType.VANILLA);



    }

    public void update(File dir, IProgressCallback callback) throws IOException {

        try {
            updater = new FlowUpdater.FlowUpdaterBuilder().withVersion(version).withLogger(updateLogger).withSilentUpdate(true).withProgressCallback(callback).build();
        } catch (BuilderArgumentException e) {
            e.printStackTrace();
        }

        try {
            updater.update(CraftMoneyGame.CRAFTMONEY_GAME_DIR, false);
        } catch (IOException e) {
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
