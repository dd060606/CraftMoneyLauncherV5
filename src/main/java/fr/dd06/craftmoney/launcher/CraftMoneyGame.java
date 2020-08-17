package fr.dd06.craftmoney.launcher;

import fr.dd06.apis.mclauncher.minecraft.GameInfos;
import fr.dd06.apis.mclauncher.minecraft.GameTweak;
import fr.dd06.apis.mclauncher.minecraft.GameType;
import fr.dd06.apis.mclauncher.minecraft.GameVersion;

import java.io.File;

public class CraftMoneyGame {

        private static final GameVersion GAME_VERSION = new GameVersion("1.12", GameType.V1_8_HIGHER);
        private static final GameInfos GAME_INFOS = new GameInfos("CraftMoney", GAME_VERSION, new GameTweak[] {
                GameTweak.FORGE
        });

        public static File CRAFTMONEY_GAME_DIR = GAME_INFOS.getGameDir();


        private void update() {
            
        }

        public void start() {

        }
}
