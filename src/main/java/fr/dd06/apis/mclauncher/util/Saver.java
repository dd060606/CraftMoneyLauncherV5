/*     */ package fr.dd06.apis.mclauncher.util;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.util.Properties;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Saver
/*     */ {
/*     */   private File file;
/*     */   private Properties properties;
/*     */   
/*     */   public Saver(File file) {
/*  59 */     this.file = file;
/*  60 */     this.properties = new Properties();
/*     */     
/*  62 */     if (file.exists()) {
/*  63 */       load();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String key, String value) {
/*  74 */     this.properties.setProperty(key, value);
/*  75 */     save();
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
/*     */   public String get(String key) {
/*  87 */     return this.properties.getProperty(key);
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
/*     */   public String get(String key, String def) {
/* 100 */     String value = this.properties.getProperty(key);
/* 101 */     return (value == null) ? def : value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/*     */     try {
/* 111 */       this.properties.store(new BufferedWriter(new FileWriter(this.file)), "Generated by the OpenLauncherLib Saver");
/*     */     }
/* 113 */     catch (Throwable t) {
/*     */       
/* 115 */       throw new FailException("Can't save the properties", t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/*     */     try {
/* 126 */       this.properties.load(new FileInputStream(this.file));
/*     */     }
/* 128 */     catch (Throwable t) {
/*     */       
/* 130 */       throw new FailException("Can't load the properties", t);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\Saver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */