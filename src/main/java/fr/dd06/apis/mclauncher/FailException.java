/*    */ package fr.dd06.apis.mclauncher;
/*    */ 
/*    */ public class FailException
/*    */   extends RuntimeException {
/*    */   public FailException(String message) {
/*  6 */     super("Ups ! Looks like you failed : " + message);
/*    */   }
/*    */   
/*    */   public FailException(String message, Throwable cause) {
/* 10 */     super("Ups ! Looks like you failed : " + message, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\FailException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */