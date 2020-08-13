/*    */ package fr.dd06.apis.mclauncher.minecraft.util;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameDirGenerator
/*    */ {
/*    */   public static File createGameDir(String serverName) {
/* 48 */     String os = System.getProperty("os.name").toLowerCase();
/* 49 */     if (os.contains("win"))
/* 50 */       return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\." + serverName); 
/* 51 */     if (os.contains("mac")) {
/* 52 */       return new File(System.getProperty("user.home") + "/Library/Application Support/" + serverName);
/*    */     }
/* 54 */     return new File(System.getProperty("user.home") + "/." + serverName);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraf\\util\GameDirGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */