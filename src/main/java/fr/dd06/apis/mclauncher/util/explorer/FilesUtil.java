/*     */ package fr.dd06.apis.mclauncher.util.explorer;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;

import fr.dd06.apis.mclauncher.FailException;

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
/*     */ public class FilesUtil
/*     */ {
/*     */   public static ArrayList<File> listRecursive(File directory) {
/*  48 */     ArrayList<File> files = new ArrayList<>();
/*  49 */     File[] fs = directory.listFiles();
/*  50 */     if (fs == null) {
/*  51 */       return files;
/*     */     }
/*  53 */     for (File f : fs) {
/*     */       
/*  55 */       if (f.isDirectory()) {
/*  56 */         files.addAll(listRecursive(f));
/*     */       }
/*  58 */       files.add(f);
/*     */     } 
/*     */     
/*  61 */     return files;
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
/*     */   
/*     */   public static File get(File root, String file) {
/*  75 */     File f = new File(root, file);
/*  76 */     if (!f.exists()) {
/*  77 */       throw new FailException("Given file/directory doesn't exist !");
/*     */     }
/*  79 */     return f;
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
/*     */   public static File dir(File d) {
/*  91 */     if (!d.isDirectory()) {
/*  92 */       throw new FailException("Given directory is not one !");
/*     */     }
/*  94 */     return d;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static File dir(File root, String dir) {
/* 110 */     return dir(get(root, dir));
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
/*     */ 
/*     */   
/*     */   public static File[] list(File dir) {
/* 125 */     File[] files = dir(dir).listFiles();
/*     */     
/* 127 */     return (files == null) ? new File[0] : files;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\explorer\FilesUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */