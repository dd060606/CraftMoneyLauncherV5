package fr.dd06.craftmoney.launcher.update;

import fr.dd06.apis.javautils.java.util.file.analyse.FileAnalyzer;
import fr.dd06.craftmoney.CraftMoneyLauncher;
import fr.dd06.craftmoney.launcher.CraftMoneyGame;
import fr.dd06.craftmoney.launcher.LauncherStage;
import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.download.IProgressCallback;

import fr.flowarg.flowupdater.download.json.Mod;
import fr.flowarg.flowupdater.utils.UpdaterOptions;

import fr.flowarg.flowupdater.utils.builderapi.BuilderArgumentException;
import fr.flowarg.flowupdater.versions.NewForgeVersion;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.flowarg.flowupdater.versions.VersionType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
            VanillaVersion version = new VanillaVersion.VanillaVersionBuilder().withName("1.12.2").withSnapshot(false).withVersionType(VersionType.FORGE).build();


            FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder().withVersion(version).withUpdaterOptions(new UpdaterOptions(true, false)).withProgressCallback(callback).withLogger(updateLogger).withForgeVersion(new NewForgeVersion("14.23.5.2854", version, updateLogger, callback, modsList, false)).build();


            updater.update(dir, false);

        } catch (BuilderArgumentException e) {
            e.printStackTrace();
        }




    }

    public static void analyzeMods(File dir) {
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

    public Logger getUpdateLogger() {
        return updateLogger;
    }


}
