package fr.dd06.craftmoney.launcher.home.mods;

import fr.flowarg.flowupdater.versions.download.Mod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class CraftMoneyOptionalMods {
    public static void downloadOptionalMod(String nameOfMod, URL urlOfMod, File gameDir) throws IOException {

        gameDir.getParentFile().mkdirs();
        File mod = new File(gameDir,"/mods/" + nameOfMod + ".jar");
        mod.getParentFile().mkdirs();
        Files.copy(urlOfMod.openStream(), mod.toPath());

    }

}
