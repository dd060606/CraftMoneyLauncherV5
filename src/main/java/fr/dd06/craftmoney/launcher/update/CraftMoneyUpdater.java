package fr.dd06.craftmoney.launcher.update;

import fr.dd06.apis.javautils.java.util.file.analyse.FileAnalyzer;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.dd06.craftmoney.launcher.errors.ErrorStage;
import fr.dd06.craftmoney.launcher.home.mods.CraftMoneyOptionalMods;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CraftMoneyUpdater {

    private final CraftMoneyLauncher main;
    private LauncherStage launcherStage;


    private Logger updateLogger = new Logger("[CraftMoney]", new File(CraftMoneyGame.CRAFTMONEY_LOG_DIR, "last-launcher-update.log"));

    public CraftMoneyUpdater(LauncherStage launcherStage, CraftMoneyLauncher main) {
        this.launcherStage = launcherStage;
        this.main = main;


    }

    public void update(File dir, IProgressCallback callback) throws IOException {
        analyzeMods(dir);
        try {
            List<Mod> modsList = Mod.getModsFromJson(new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/mods/mods.json"));
            final IVanillaVersion.Builder versionBuilder = new IVanillaVersion.Builder("1.12.2");
            final IVanillaVersion version = versionBuilder.build(false, VersionType.FORGE);
            final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder().withForgeVersion(new NewForgeVersion("14.23.5.2854", version, updateLogger, callback, modsList, true)).withVersion(version).withLogger(updateLogger).withSilentUpdate(true).withProgressCallback(callback).build();

            updater.update(dir, false);
        } catch (BuilderArgumentException e) {
            e.printStackTrace();
        }



    }

    private void analyzeMods(File dir) {
        File modsDir = new File(dir, "/mods/");
        modsDir.getParentFile().mkdirs();
        modsDir.mkdirs();
        FileAnalyzer modsAnalyzer = new FileAnalyzer();
        try {
            modsAnalyzer.analyzeFolder(modsDir, new URL("http://dd06dev.planethoster.world/download_center/launchers/craftmoney/mods/modsChecklist.json").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
