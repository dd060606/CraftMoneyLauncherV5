/*    */ package fr.dd06.apis.mclauncher;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.lang.reflect.Field;

import fr.dd06.apis.mclauncher.util.LogUtil;

/*    */
/*    */ 
/*    */ public class JavaUtil
/*    */ {
/*    */   public static String pathJava;
/* 11 */   public static Boolean javaCustom = Boolean.valueOf(false);
/*    */   
/*    */   public static String[] getSpecialArgs() {
/* 14 */     return new String[] { "-XX:-UseAdaptiveSizePolicy", "-XX:+UseConcMarkSweepGC" };
/*    */   }
/*    */   
/*    */   public static String macDockName(String name) {
/* 18 */     return "-Xdock:name=" + name;
/*    */   }
/*    */   
/*    */   public static String getJavaCommand() {
/* 22 */     if (javaCustom.booleanValue()) {
/* 23 */       LogUtil.info(new String[] { "jre-custom" });
/* 24 */       return pathJava;
/*    */     } 
/* 26 */     return System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
/*    */   }
/*    */ 
/*    */   
/*    */   public static String setJavaCommandLauncher(String path) {
/* 31 */     return pathJava = path;
/*    */   }
/*    */   public static Boolean setJavaCustom(Boolean enabled) {
/* 34 */     return javaCustom = enabled;
/*    */   }
/*    */   
/*    */   public static void setLibraryPath(String path) throws Exception {
/* 38 */     System.setProperty("java.library.path", path);
/*    */     
/* 40 */     Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
/* 41 */     fieldSysPath.setAccessible(true);
/* 42 */     fieldSysPath.set((Object)null, (Object)null);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\JavaUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */