package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.mclauncher.minecraft.GameInfos;
import fr.dd06.apis.mclauncher.minecraft.GameTweak;
import fr.dd06.apis.mclauncher.minecraft.GameType;
import fr.dd06.apis.mclauncher.minecraft.GameVersion;
import fr.dd06.craftmoney.CraftMoneyLauncher;
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


import java.io.File;
import java.io.IOException;
import java.util.List;


public class CraftMoneyGame {


    private static final GameVersion GAME_VERSION = new GameVersion("1.12", GameType.V1_8_HIGHER);
    private static final GameInfos GAME_INFOS = new GameInfos("CraftMoney", GAME_VERSION, new GameTweak[]{
            GameTweak.FORGE
    });

    public static File CRAFTMONEY_GAME_DIR = GAME_INFOS.getGameDir();
    public static File CRAFTMONEY_LOG_DIR = new File(CRAFTMONEY_GAME_DIR, "logs/");
    private final IVanillaVersion.Builder versionBuilder = new IVanillaVersion.Builder("1.12.2");
    private final IVanillaVersion version = versionBuilder.build(false, VersionType.FORGE);

    private FlowUpdater updater;

    private void update(File dir, IProgressCallback callback) throws IOException {

        List<Mod> mods = Mod.getModsFromJson("");
        Logger logger = new Logger("[CraftMoney]", new File(CRAFTMONEY_LOG_DIR, "last-launcher.log"));
        try {
            updater = new FlowUpdater.FlowUpdaterBuilder().withVersion(version).withLogger(logger).withSilentUpdate(true).withProgressCallback(callback).build();
        } catch (BuilderArgumentException e) {
            e.printStackTrace();
        }


        updater.setForgeVersion(new NewForgeVersion("1.12.2-14.23.5.2854", version, logger, callback, mods, false));

        updater.update(CRAFTMONEY_GAME_DIR , false);
    }

    public void start() {
        initUpdatePane();
    }

    private void initUpdatePane() {

    }
}
