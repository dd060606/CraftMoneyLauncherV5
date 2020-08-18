package fr.dd06.craftmoney.launcher.update;

import fr.dd06.apis.javautils.java.util.file.hash.FileAnalyzer;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.utils.BuilderArgumentException;
import fr.flowarg.flowupdater.versions.IVanillaVersion;
import fr.flowarg.flowupdater.versions.NewForgeVersion;
import fr.flowarg.flowupdater.versions.VersionType;
import fr.flowarg.flowupdater.versions.download.IProgressCallback;
import fr.flowarg.flowupdater.versions.download.Mod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CraftMoneyUpdater {
    private final IVanillaVersion.Builder versionBuilder;
    private final IVanillaVersion version ;
    private FlowUpdater updater;



    private Logger updateLogger = new Logger("[CraftMoney]", new File(CraftMoneyGame.CRAFTMONEY_LOG_DIR, "last-launcher.log"));

    public CraftMoneyUpdater() {

       versionBuilder = new IVanillaVersion.Builder("1.16.1");
       version = versionBuilder.build(false, VersionType.VANILLA);



    }

    public void update(File dir, IProgressCallback callback) throws IOException {


        List<Mod> listMods = Mod.getModsFromJson("https://www.dropbox.com/s/y0297a0auwlufre/mods.json?dl=1");
        try {
            FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder().withVersion(version).withLogger(new Logger("[CraftMoney]", new File(CraftMoneyGame.CRAFTMONEY_LOG_DIR, "last-launcher-update.log"))).withSilentUpdate(true).withProgressCallback(callback).build();
            updater.update(dir, false);
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
