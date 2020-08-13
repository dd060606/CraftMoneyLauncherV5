/*    */ package fr.dd06.apis.mclauncher.internal;
import fr.dd06.apis.mclauncher.LanguageManager;
import fr.dd06.apis.mclauncher.LaunchException;

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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class ClasspathException
/*    */   extends LaunchException
/*    */ {
/*    */   public static final int JAR_NOT_FOUND_ERROR = 0;
/*    */   public static final int JAR_LOADING_ERROR = 1;
/*    */   
/*    */   public ClasspathException(int type, String str) {
/* 58 */     super((type == 0) ? (LanguageManager.lang(new String[] { "jar-notfound", ":", str }) + str) : ((type == 1) ? LanguageManager.lang(new String[] { "load-fail", ":", str }) : str));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ClasspathException(int type, String str, Throwable t) {
/* 70 */     super((type == 0) ? (LanguageManager.lang(new String[] { "jar-notfound", ":", str }) + str) : ((type == 1) ? LanguageManager.lang(new String[] { "load-fail", ":", str }) : str), t);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\internal\ClasspathException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */