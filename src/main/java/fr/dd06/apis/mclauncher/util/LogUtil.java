/*    */ package fr.dd06.apis.mclauncher.util;
import fr.dd06.apis.mclauncher.LanguageManager;
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
/*    */ public final class LogUtil
/*    */ {
/*    */   public static void message(boolean err, String... messages) {
/* 44 */     String message = "[MCLauncher] " + LanguageManager.lang(messages);
/*    */     
/* 46 */     if (err) {
/* 47 */       System.err.println(message);
/*    */     } else {
/* 49 */       System.out.println(message);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void rawInfo(String message) {
/* 59 */     System.out.println("[MCLauncher] " + message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void rawErr(String message) {
/* 69 */     System.err.println("[MCLauncher] " + message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void info(String... messages) {
/* 79 */     message(false, messages);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void err(String... messages) {
/* 89 */     message(true, messages);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\LogUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */