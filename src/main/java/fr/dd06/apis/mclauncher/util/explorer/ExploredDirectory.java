/*     */ package fr.dd06.apis.mclauncher.util.explorer;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
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
/*     */ public class ExploredDirectory
/*     */ {
/*     */   protected File directory;
/*     */   
/*     */   ExploredDirectory(File directory) {
/*  51 */     this.directory = directory;
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
/*     */   public FileList allRecursive() {
/*  64 */     return new FileList(FilesUtil.listRecursive(this.directory));
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
/*     */   public FileList list() {
/*  76 */     return new FileList(Arrays.asList(FilesUtil.list(this.directory)));
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
/*     */   public ExploredDirectory sub(String directory) {
/*  88 */     return new ExploredDirectory(FilesUtil.dir(this.directory, directory));
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
/*     */   public File get(String file) {
/* 100 */     return FilesUtil.get(this.directory, file);
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
/*     */   public FileList subs() {
/* 112 */     File[] files = FilesUtil.list(this.directory);
/* 113 */     ArrayList<File> dirs = new ArrayList<>();
/*     */     
/* 115 */     for (File f : files) {
/* 116 */       if (f.isDirectory())
/* 117 */         dirs.add(f); 
/*     */     } 
/* 119 */     return new FileList(dirs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileList files() {
/* 129 */     File[] files = FilesUtil.list(this.directory);
/* 130 */     ArrayList<File> fs = new ArrayList<>();
/*     */     
/* 132 */     for (File f : files) {
/* 133 */       if (!f.isDirectory())
/* 134 */         fs.add(f); 
/*     */     } 
/* 136 */     return new FileList(fs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File get() {
/* 146 */     return this.directory;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\explorer\ExploredDirectory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */