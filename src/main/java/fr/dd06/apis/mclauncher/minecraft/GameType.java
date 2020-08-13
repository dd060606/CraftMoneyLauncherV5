/*     */ package fr.dd06.apis.mclauncher.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;

import fr.dd06.apis.mclauncher.util.LogUtil;
import fr.dd06.apis.mclauncher.minecraft.auth.Account;
import fr.dd06.apis.mclauncher.minecraft.util.ConnectToServer;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GameType
/*     */ {
/*  46 */   public static final GameType V1_5_2_LOWER = new GameType()
/*     */     {
/*     */       
/*     */       public String getName()
/*     */       {
/*  51 */         return "1.5.2 or lower";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String getMainClass(GameInfos infos) {
/*  57 */         return "net.minecraft.launchwrapper.Launch";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, Account account) {
/*  63 */         ArrayList<String> arguments = new ArrayList<>();
/*     */         
/*  65 */         if (ConnectToServer.isActived().booleanValue()) {
/*  66 */           LogUtil.info(new String[] { "connect-server" });
/*  67 */           arguments.add("--server=" + ConnectToServer.getServer());
/*  68 */           arguments.add("--port=" + ConnectToServer.getServerPort());
/*     */         } 
/*     */         
/*  71 */         arguments.add(account.getUsername());
/*     */         
/*  73 */         arguments.add("token:" + account.getAccessToken() + ":" + account.getUUID());
/*     */         
/*  75 */         arguments.add("--gameDir");
/*  76 */         arguments.add(infos.getGameDir().getAbsolutePath());
/*     */         
/*  78 */         arguments.add("--assetsDir");
/*  79 */         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
/*  80 */         arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");
/*     */         
/*  82 */         return arguments;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final GameType V1_7_2_LOWER = new GameType()
/*     */     {
/*     */       
/*     */       public String getName()
/*     */       {
/*  94 */         return "1.7.2 or lower";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String getMainClass(GameInfos infos) {
/* 100 */         return "net.minecraft.client.main.Main";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, Account account) {
/* 106 */         ArrayList<String> arguments = new ArrayList<>();
/*     */         
/* 108 */         if (ConnectToServer.isActived().booleanValue()) {
/* 109 */           LogUtil.info(new String[] { "connect-server" });
/* 110 */           arguments.add("--server=" + ConnectToServer.getServer());
/* 111 */           arguments.add("--port=" + ConnectToServer.getServerPort());
/*     */         } 
/*     */         
/* 114 */         arguments.add("--username=" + account.getUsername());
/*     */         
/* 116 */         arguments.add("--accessToken");
/* 117 */         arguments.add(account.getAccessToken());
/*     */         
/* 119 */         arguments.add("--version");
/* 120 */         arguments.add(infos.getGameVersion().getName());
/*     */         
/* 122 */         arguments.add("--gameDir");
/* 123 */         arguments.add(infos.getGameDir().getAbsolutePath());
/*     */         
/* 125 */         arguments.add("--assetsDir");
/* 126 */         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
/* 127 */         arguments.add(assetsDir.getAbsolutePath() + "/virtual/legacy/");
/*     */         
/* 129 */         arguments.add("--userProperties");
/* 130 */         arguments.add("{}");
/*     */         
/* 132 */         arguments.add("--uuid");
/* 133 */         arguments.add(account.getUUID());
/*     */         
/* 135 */         arguments.add("--userType");
/* 136 */         arguments.add("legacy");
/*     */         
/* 138 */         return arguments;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final GameType V1_7_10 = new GameType()
/*     */     {
/*     */       
/*     */       public String getName()
/*     */       {
/* 150 */         return "1.7.10";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String getMainClass(GameInfos infos) {
/* 156 */         return "net.minecraft.client.main.Main";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, Account account) {
/* 162 */         ArrayList<String> arguments = new ArrayList<>();
/*     */         
/* 164 */         if (ConnectToServer.isActived().booleanValue()) {
/* 165 */           LogUtil.info(new String[] { "connect-server" });
/* 166 */           arguments.add("--server=" + ConnectToServer.getServer());
/* 167 */           arguments.add("--port=" + ConnectToServer.getServerPort());
/*     */         } 
/*     */         
/* 170 */         arguments.add("--username=" + account.getUsername());
/*     */         
/* 172 */         arguments.add("--accessToken");
/* 173 */         arguments.add(account.getAccessToken());
/*     */         
/* 175 */         if (account.getClientToken() != null) {
/*     */           
/* 177 */           arguments.add("--clientToken");
/* 178 */           arguments.add(account.getClientToken());
/*     */         } 
/*     */         
/* 181 */         arguments.add("--version");
/* 182 */         arguments.add(infos.getGameVersion().getName());
/*     */         
/* 184 */         arguments.add("--gameDir");
/* 185 */         arguments.add(infos.getGameDir().getAbsolutePath());
/*     */         
/* 187 */         arguments.add("--assetsDir");
/* 188 */         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
/* 189 */         arguments.add(assetsDir.getAbsolutePath());
/*     */         
/* 191 */         arguments.add("--assetIndex");
/* 192 */         arguments.add(infos.getGameVersion().getName());
/*     */         
/* 194 */         arguments.add("--userProperties");
/* 195 */         arguments.add("{}");
/*     */         
/* 197 */         arguments.add("--uuid");
/* 198 */         arguments.add(account.getUUID());
/*     */         
/* 200 */         arguments.add("--userType");
/* 201 */         arguments.add("legacy");
/*     */         
/* 203 */         return arguments;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public static final GameType V1_8_HIGHER = new GameType()
/*     */     {
/*     */       
/*     */       public String getName()
/*     */       {
/* 215 */         return "1.8 or higher";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String getMainClass(GameInfos infos) {
/* 221 */         return "net.minecraft.client.main.Main";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public ArrayList<String> getLaunchArgs(GameInfos infos, GameFolder folder, Account account) {
/* 227 */         ArrayList<String> arguments = new ArrayList<>();
/*     */         
/* 229 */         if (ConnectToServer.isActived().booleanValue()) {
/* 230 */           LogUtil.info(new String[] { "connect-server" });
/* 231 */           arguments.add("--server=" + ConnectToServer.getServer());
/* 232 */           arguments.add("--port=" + ConnectToServer.getServerPort());
/*     */         } 
/*     */         
/* 235 */         arguments.add("--username=" + account.getUsername());
/*     */         
/* 237 */         arguments.add("--accessToken");
/* 238 */         arguments.add(account.getAccessToken());
/*     */         
/* 240 */         if (account.getClientToken() != null) {
/*     */           
/* 242 */           arguments.add("--clientToken");
/* 243 */           arguments.add(account.getClientToken());
/*     */         } 
/*     */         
/* 246 */         arguments.add("--version");
/* 247 */         arguments.add(infos.getGameVersion().getName());
/*     */         
/* 249 */         arguments.add("--gameDir");
/* 250 */         arguments.add(infos.getGameDir().getAbsolutePath());
/*     */         
/* 252 */         arguments.add("--assetsDir");
/* 253 */         File assetsDir = new File(infos.getGameDir(), folder.getAssetsFolder());
/* 254 */         arguments.add(assetsDir.getAbsolutePath());
/*     */         
/* 256 */         arguments.add("--assetIndex");
/*     */         
/* 258 */         String version = infos.getGameVersion().getName();
/*     */         
/* 260 */         int first = version.indexOf('.');
/* 261 */         int second = version.lastIndexOf('.');
/*     */         
/* 263 */         if (first != second)
/*     */         {
/* 265 */           version = version.substring(0, version.lastIndexOf('.'));
/*     */         }
/*     */         
/* 268 */         arguments.add(version);
/*     */         
/* 270 */         arguments.add("--userProperties");
/* 271 */         arguments.add("{}");
/*     */         
/* 273 */         arguments.add("--uuid");
/* 274 */         arguments.add(account.getUUID());
/*     */         
/* 276 */         arguments.add("--userType");
/* 277 */         arguments.add("legacy");
/*     */         
/* 279 */         return arguments;
/*     */       }
/*     */     };
/*     */   
/*     */   public abstract String getName();
/*     */   
/*     */   public abstract String getMainClass(GameInfos paramGameInfos);
/*     */   
/*     */   public abstract ArrayList<String> getLaunchArgs(GameInfos paramGameInfos, GameFolder paramGameFolder, Account account);
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraft\GameType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */