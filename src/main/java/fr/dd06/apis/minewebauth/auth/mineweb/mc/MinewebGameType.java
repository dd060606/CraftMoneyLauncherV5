package fr.dd06.apis.minewebauth.auth.mineweb.mc;

import fr.dd06.apis.mclauncher.minecraft.GameFolder;
import fr.dd06.apis.mclauncher.minecraft.GameInfos;
import fr.dd06.apis.mclauncher.minecraft.GameType;
import fr.dd06.apis.mclauncher.minecraft.auth.Account;
import fr.dd06.apis.minewebauth.auth.mineweb.utils.Get;

import java.io.File;
import java.util.ArrayList;

public abstract class MinewebGameType {
   public static final GameType V1_5_2_LOWER = new GameType() {
      public String getName() {
         return "1.5.2 or lower";
      }

      public String getMainClass(GameInfos infos) {
         return "net.minecraft.launchwrapper.Launch";
      }

      public ArrayList getLaunchArgs(GameInfos infos, GameFolder folder, Account authInfos) {
         ArrayList arguments = new ArrayList();
         arguments.add(Get.getSession.getUsername());
         arguments.add("token:" + Get.getSession.getAccessToken() + ":" + Get.getSession.getUuid());
         arguments.add("--gameDir");
         arguments.add(infos.getGameDir().getAbsolutePath());
         arguments.add("--assetsDir");
         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
         arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");
         return arguments;
      }
   };
   public static final GameType V1_7_2_LOWER = new GameType() {
      public String getName() {
         return "1.7.2 or lower";
      }

      public String getMainClass(GameInfos infos) {
         return "net.minecraft.client.main.Main";
      }

      public ArrayList getLaunchArgs(GameInfos infos, GameFolder folder, Account authInfos) {
         ArrayList arguments = new ArrayList();
         arguments.add("--username=" + Get.getSession.getUsername());
         arguments.add("--accessToken");
         arguments.add(Get.getSession.getAccessToken());
         arguments.add("--version");
         arguments.add(infos.getGameVersion().getName());
         arguments.add("--gameDir");
         arguments.add(infos.getGameDir().getAbsolutePath());
         arguments.add("--assetsDir");
         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
         arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");
         arguments.add("--userProperties");
         arguments.add("{}");
         arguments.add("--uuid");
         arguments.add(Get.getSession.getUuid());
         arguments.add("--userType");
         arguments.add("legacy");
         return arguments;
      }
   };
   public static final GameType V1_7_10 = new GameType() {
      public String getName() {
         return "1.7.10";
      }

      public String getMainClass(GameInfos infos) {
         return "net.minecraft.client.main.Main";
      }

      public ArrayList getLaunchArgs(GameInfos infos, GameFolder folder, Account authInfos) {
         ArrayList arguments = new ArrayList();
         arguments.add("--username=" + Get.getSession.getUsername());
         arguments.add("--accessToken");
         arguments.add(Get.getSession.getAccessToken());
         arguments.add("--clientToken");
         arguments.add(Get.getSession.getClientToken());
         arguments.add("--version");
         arguments.add(infos.getGameVersion().getName());
         arguments.add("--gameDir");
         arguments.add(infos.getGameDir().getAbsolutePath());
         arguments.add("--assetsDir");
         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
         arguments.add(assetsDir.getAbsolutePath());
         arguments.add("--assetIndex");
         arguments.add(infos.getGameVersion().getName());
         arguments.add("--userProperties");
         arguments.add("{}");
         arguments.add("--uuid");
         arguments.add(Get.getSession.getUuid());
         arguments.add("--userType");
         arguments.add("legacy");
         return arguments;
      }
   };
   public static final GameType V1_8_HIGHER = new GameType() {
      public String getName() {
         return "1.8 or higher";
      }

      public String getMainClass(GameInfos infos) {
         return "net.minecraft.client.main.Main";
      }

      public ArrayList getLaunchArgs(GameInfos infos, GameFolder folder, Account authInfos) {
         ArrayList arguments = new ArrayList();
         arguments.add("--username=" + Get.getSession.getUsername());
         arguments.add("--accessToken");
         arguments.add(Get.getSession.getAccessToken());
         arguments.add("--clientToken");
         arguments.add(Get.getSession.getClientToken());
         arguments.add("--version");
         arguments.add(infos.getGameVersion().getName());
         arguments.add("--gameDir");
         arguments.add(infos.getGameDir().getAbsolutePath());
         arguments.add("--assetsDir");
         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
         arguments.add(assetsDir.getAbsolutePath());
         arguments.add("--assetIndex");
         String version = infos.getGameVersion().getName();
         int first = version.indexOf(46);
         int second = version.lastIndexOf(46);
         if (first != second) {
            version = version.substring(0, version.lastIndexOf(46));
         }

         arguments.add(version);
         arguments.add("--userProperties");
         arguments.add("{}");
         arguments.add("--uuid");
         arguments.add(Get.getSession.getUuid());
         arguments.add("--userType");
         arguments.add("legacy");
         return arguments;
      }
   };

   public abstract String getName();

   public abstract String getMainClass(GameInfos var1);

   public abstract ArrayList getLaunchArgs(GameInfos var1, GameFolder var2, Account var3);
}
