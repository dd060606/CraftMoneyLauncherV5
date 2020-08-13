/*     */ package fr.dd06.apis.mclauncher.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;

import fr.dd06.apis.mclauncher.minecraft.util.GameDirGenerator;
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
/*     */ public class GameInfos
/*     */ {
/*     */   private String serverName;
/*     */   private File gameDir;
/*     */   private GameTweak[] tweaks;
/*     */   private GameVersion gameVersion;
/*     */   
/*     */   public GameInfos(String serverName, GameVersion gameVersion, GameTweak[] tweaks) {
/*  68 */     this(serverName, GameDirGenerator.createGameDir(serverName), gameVersion, tweaks);
/*     */   }
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
/*     */   public GameInfos(String serverName, File gameDir, GameVersion gameVersion, GameTweak[] tweaks) {
/*  81 */     this.serverName = serverName;
/*  82 */     this.gameDir = gameDir;
/*  83 */     this.gameVersion = gameVersion;
/*  84 */     this.tweaks = tweaks;
/*     */     
/*  86 */     if (tweaks != null) {
/*     */       
/*  88 */       boolean forge = false;
/*  89 */       boolean shaderOrOptifine = false;
/*     */       
/*  91 */       for (GameTweak tweak : tweaks) {
/*  92 */         if (tweak.equals(GameTweak.FORGE)) {
/*     */           
/*  94 */           if (gameVersion.getGameType().equals(GameType.V1_5_2_LOWER)) {
/*  95 */             System.out.println("[OpenLauncherLib] [WARNING] You selected Forge tweaking with a version under or equals as 1.5.2, forge is supposed to be installed in the jar (not with a tweaker), the game COULD NOT START !");
/*     */           }
/*  97 */           forge = true;
/*     */         }
/*  99 */         else if (tweak.equals(GameTweak.OPTIFINE) || tweak.equals(GameTweak.SHADER)) {
/*     */           
/* 101 */           shaderOrOptifine = true;
/*     */         } 
/*     */       } 
/* 104 */       if (tweaks.length > 0 && gameVersion.getGameType().equals(GameType.V1_5_2_LOWER)) {
/* 105 */         System.out.println("[OpenLauncherLib] [WARNING] You selected tweaking with a version under or equals as 1.5.2, this isn't fully supported, and could cause bugs.");
/*     */       }
/* 107 */       if (shaderOrOptifine && forge) {
/*     */         
/* 109 */         System.out.println("[OpenLauncherLib] [WARNING] You selected Forge tweak with Optifine/Shader, they are ONLY FOR VANILLA, the game wil probably not start, so for security, Optifine/Shader was/were disabled");
/*     */         
/* 111 */         ArrayList<GameTweak> tweakList = new ArrayList<>();
/*     */         
/* 113 */         for (GameTweak tweak : tweaks) {
/* 114 */           if (!tweak.equals(GameTweak.OPTIFINE) && !tweak.equals(GameTweak.SHADER))
/* 115 */             tweakList.add(tweak); 
/*     */         } 
/* 117 */         this.tweaks = tweakList.<GameTweak>toArray(new GameTweak[tweakList.size()]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerName() {
/* 129 */     return this.serverName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getGameDir() {
/* 139 */     return this.gameDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameVersion getGameVersion() {
/* 149 */     return this.gameVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameTweak[] getGameTweaks() {
/* 159 */     return this.tweaks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGameTweak(GameTweak tweak) {
/* 171 */     for (GameTweak gameTweak : this.tweaks) {
/* 172 */       if (gameTweak.equals(tweak))
/* 173 */         return true; 
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraft\GameInfos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */