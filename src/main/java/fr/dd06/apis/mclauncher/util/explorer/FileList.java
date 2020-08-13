/*     */ package fr.dd06.apis.mclauncher.util.explorer;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
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
/*     */ public class FileList
/*     */ {
/*     */   protected List<File> files;
/*     */   
/*     */   public FileList() {
/*  51 */     this.files = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileList(List<File> files) {
/*  61 */     this.files = files;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(File... files) {
/*  71 */     add(Arrays.asList(files));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(List<File> files) {
/*  81 */     this.files.addAll(files);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(FileList list) {
/*  91 */     add(list.get());
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
/*     */   public FileList match(String regex) {
/* 104 */     ArrayList<File> matching = new ArrayList<>();
/*     */     
/* 106 */     for (File f : this.files) {
/* 107 */       if (f.getName().matches(regex))
/* 108 */         matching.add(f); 
/*     */     } 
/* 110 */     return new FileList(matching);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileList dirs() {
/* 121 */     ArrayList<File> dirs = new ArrayList<>();
/*     */     
/* 123 */     for (File f : this.files) {
/* 124 */       if (f.isDirectory())
/* 125 */         dirs.add(f); 
/*     */     } 
/* 127 */     return new FileList(dirs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileList files() {
/* 138 */     ArrayList<File> files = new ArrayList<>();
/*     */     
/* 140 */     for (File f : this.files) {
/* 141 */       if (!f.isDirectory())
/* 142 */         files.add(f); 
/*     */     } 
/* 144 */     return new FileList(files);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<File> get() {
/* 154 */     return this.files;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\explorer\FileList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */