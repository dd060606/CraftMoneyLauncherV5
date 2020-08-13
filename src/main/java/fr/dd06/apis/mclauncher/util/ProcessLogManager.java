/*     */ package fr.dd06.apis.mclauncher.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcessLogManager
/*     */   extends Thread
/*     */ {
/*     */   private boolean print = true;
/*     */   private BufferedReader reader;
/*     */   private File toWrite;
/*     */   private BufferedWriter writer;
/*     */   
/*     */   public ProcessLogManager(InputStream input) {
/*  69 */     this(input, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProcessLogManager(InputStream input, File toWrite) {
/*  80 */     this.reader = new BufferedReader(new InputStreamReader(input));
/*  81 */     this.toWrite = toWrite;
/*     */     
/*  83 */     if (toWrite != null) {
/*     */       
/*     */       try {
/*  86 */         this.writer = new BufferedWriter(new FileWriter(toWrite));
/*     */       }
/*  88 */       catch (IOException e) {
/*     */         
/*  90 */         LogUtil.err(new String[] { "log-err", e.toString() });
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*     */       String line;
/* 100 */       while ((line = this.reader.readLine()) != null) {
/*     */         
/* 102 */         if (this.print) {
/* 103 */           System.out.println(line);
/*     */         }
/* 105 */         if (this.writer != null) {
/*     */           
/*     */           try {
/* 108 */             this.writer.write(line + "\n");
/*     */           }
/* 110 */           catch (IOException e) {
/*     */             
/* 112 */             LogUtil.err(new String[] { "log-err", e.toString() });
/*     */           } 
/*     */         }
/*     */       } 
/* 116 */     } catch (IOException e) {
/*     */       
/* 118 */       LogUtil.err(new String[] { "log-end", e.toString() });
/*     */       
/* 120 */       interrupt();
/*     */     } 
/*     */     
/* 123 */     if (this.writer != null) {
/*     */       
/*     */       try {
/* 126 */         this.writer.close();
/*     */       }
/* 128 */       catch (IOException iOException) {}
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
/*     */   public boolean isPrint() {
/* 140 */     return this.print;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrint(boolean print) {
/* 150 */     this.print = print;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getToWrite() {
/* 160 */     return this.toWrite;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToWrite(File toWrite) {
/* 170 */     this.toWrite = toWrite;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\ProcessLogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */