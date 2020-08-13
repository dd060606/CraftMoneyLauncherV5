/*     */ package fr.dd06.apis.mclauncher.util.ramselector;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import javax.swing.JFrame;

import fr.dd06.apis.mclauncher.util.CrashReporter;
import fr.dd06.apis.mclauncher.util.LogUtil;

/*     */
/*     */ 
/*     */ public class RamSelector
/*     */ {
/*  17 */   public static final String[] RAM_ARRAY = new String[] { "1Go", "2Go", "3Go", "4Go", "5Go", "6Go", "7Go", "8Go", "9Go", "10Go" };
/*     */   
/*     */   private File file;
/*     */   
/*  21 */   private Class<? extends AbstractOptionFrame> frameClass = (Class)OptionFrame.class;
/*     */   
/*     */   private AbstractOptionFrame frame;
/*     */   
/*     */   public RamSelector(File file) {
/*  26 */     this.file = file;
/*     */   }
/*     */ 
/*     */   
/*     */   public JFrame display() {
/*  31 */     if (this.frame == null) {
/*     */       try {
/*  33 */         Constructor[] contructors = (Constructor[])this.frameClass.getDeclaredConstructors();
/*     */         
/*  35 */         Constructor<AbstractOptionFrame> constructor = null;
/*  36 */         for (Constructor c : contructors) {
/*  37 */           if ((c.getParameterTypes()).length == 1 && c.getParameterTypes()[0] == RamSelector.class)
/*  38 */             constructor = c; 
/*     */         } 
/*  40 */         if (constructor == null) {
/*  41 */           throw new IllegalStateException("Can't load the OptionFrame class, it needs to have a constructor with just a RamSelector as argument.");
/*     */         }
/*  43 */         this.frame = constructor.newInstance(new Object[] { this });
/*  44 */         this.frame.setSelectedIndex(readRam());
/*  45 */       } catch (Exception e) {
/*  46 */         System.err.println("[OpenLauncherLib] Can't display the Ram Selector !");
/*  47 */         System.err.println(CrashReporter.makeCrashReport("OpenLauncherLib Ram Selector", e));
/*     */         
/*  49 */         return null;
/*     */       } 
/*     */     }
/*  52 */     this.frame.setVisible(true);
/*     */     
/*  54 */     return this.frame;
/*     */   }
/*     */   
/*     */   public String[] getRamArguments() {
/*  58 */     int maxRam = Integer.parseInt((this.frame == null) ? RAM_ARRAY[readRam()].replace("Go", "") : RAM_ARRAY[this.frame.getSelectedIndex()].replace("Go", "")) * 1024;
/*  59 */     int minRam = 512;
/*     */     
/*  61 */     return new String[] { "-Xms" + minRam + "M", "-Xmx" + maxRam + "M" };
/*     */   }
/*     */ 
/*     */   
/*     */   private int readRam() {
/*  66 */     BufferedReader br = null;
/*     */     try {
/*  68 */       br = new BufferedReader(new FileReader(this.file));
/*  69 */       String ramText = br.readLine();
/*     */       
/*  71 */       if (ramText != null) {
/*  72 */         return Integer.parseInt(ramText);
/*     */       }
/*  74 */       LogUtil.err(new String[] { "warn", "ram-empty" });
/*  75 */     } catch (IOException e) {
/*  76 */       System.err.println("[OpenLauncherLib] WARNING: Can't read ram : " + e);
/*     */     } finally {
/*  78 */       if (br != null) {
/*     */         try {
/*  80 */           br.close();
/*  81 */         } catch (IOException e) {
/*  82 */           System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + e);
/*     */         } 
/*     */       }
/*     */     } 
/*  86 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() {
/*  91 */     if (this.frame == null) {
/*     */       return;
/*     */     }
/*  94 */     BufferedWriter bw = null;
/*     */     try {
/*  96 */       bw = new BufferedWriter(new FileWriter(this.file));
/*  97 */       bw.write(String.valueOf(this.frame.getSelectedIndex()));
/*  98 */     } catch (IOException e) {
/*  99 */       System.err.println("[OpenLauncherLib] WARNING: Can't save ram : " + e);
/*     */     } finally {
/* 101 */       if (bw != null) {
/*     */         try {
/* 103 */           bw.close();
/* 104 */         } catch (IOException e) {
/* 105 */           System.err.println("[OpenLauncherLib] WARNING: Can't close the file : " + e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public File getFile() {
/* 112 */     return this.file;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFile(File file) {
/* 117 */     this.file = file;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<? extends JFrame> getFrameClass() {
/* 122 */     return (Class)this.frameClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFrameClass(Class<? extends AbstractOptionFrame> frameClass) {
/* 127 */     this.frameClass = frameClass;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\ramselector\RamSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */