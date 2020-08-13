/*    */ package fr.dd06.apis.mclauncher.minecraft.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConnectToServer
/*    */ {
/*    */   private static String server;
/*    */   private static String serverPort;
/* 11 */   private static Boolean connectEnable = Boolean.valueOf(false);
/*    */   
/*    */   public ConnectToServer(String host) {
/* 14 */     this(host, "25565");
/*    */   }
/*    */   
/*    */   public ConnectToServer(String host, String port) {
/* 18 */     server = host;
/* 19 */     serverPort = port;
/* 20 */     connectEnable = Boolean.valueOf(true);
/*    */   }
/*    */   
/*    */   public static Boolean isActived() {
/* 24 */     return connectEnable;
/*    */   }
/*    */   
/*    */   public static void setActived(Boolean actived) {
/* 28 */     connectEnable = actived;
/*    */   }
/*    */   
/*    */   public static String getServer() {
/* 32 */     return server;
/*    */   }
/*    */   
/*    */   public static void setServer(String host) {
/* 36 */     server = host;
/*    */   }
/*    */   
/*    */   public static String getServerPort() {
/* 40 */     return serverPort;
/*    */   }
/*    */   
/*    */   public static void setServerPort(String port) {
/* 44 */     serverPort = port;
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraf\\util\ConnectToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */