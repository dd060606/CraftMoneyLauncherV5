/*     */ package fr.dd06.apis.mclauncher.internal;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;

import fr.dd06.apis.mclauncher.util.explorer.FileList;

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
/*     */ @Deprecated
/*     */ public class JarLoader
/*     */ {
/*     */   public static void load(File[] files) throws ClasspathException {
/*  53 */     load(Arrays.asList(files));
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
/*     */   public static void load(FileList list) throws ClasspathException {
/*  65 */     load(list.get());
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
/*     */   public static void load(List<File> files) throws ClasspathException {
/*  77 */     for (File file : files) {
/*  78 */       addToClasspath(file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addToClasspath(File jar) throws ClasspathException {
/*  90 */     if (!jar.exists()) {
/*  91 */       throw new ClasspathException(0, jar.getAbsolutePath());
/*     */     }
/*  93 */     URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();
/*  94 */     Class<URLClassLoader> classLoader = URLClassLoader.class;
/*     */ 
/*     */     
/*     */     try {
/*  98 */       Method method = classLoader.getDeclaredMethod("addURL", new Class[] { URL.class });
/*  99 */       method.setAccessible(true);
/* 100 */       method.invoke(loader, new Object[] { jar.toURI().toURL() });
/*     */     }
/* 102 */     catch (Throwable t) {
/*     */       
/* 104 */       t.printStackTrace();
/* 105 */       throw new ClasspathException(1, jar.getAbsolutePath(), t);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\internal\JarLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */