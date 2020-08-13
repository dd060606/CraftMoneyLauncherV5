/*     */ package fr.dd06.apis.mclauncher.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.JOptionPane;
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
/*     */ public class CrashReporter
/*     */ {
/*     */   private File dir;
/*     */   private String name;
/*     */   
/*     */   public CrashReporter(String name, File dir) {
/*  61 */     this.name = name;
/*  62 */     this.dir = dir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void catchError(Exception e, String message) {
/*     */     String msg;
/*  73 */     LogUtil.err(new String[] { "ex-caught" });
/*     */     
/*  75 */     System.out.println(makeCrashReport(this.name, e));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  81 */       File report = writeError(e);
/*  82 */       msg = "\nThe crash report is in : " + report.getAbsolutePath() + "";
/*     */     }
/*  84 */     catch (IOException e2) {
/*     */       
/*  86 */       LogUtil.err(new String[] { "report-error" });
/*  87 */       e.printStackTrace();
/*  88 */       msg = "\nAnd unable to write the crash report :( : " + e2;
/*     */     } 
/*     */     
/*  91 */     JOptionPane.showMessageDialog(null, message + "\n" + e + "\n" + msg, "Error", 0);
/*     */     
/*  93 */     System.exit(1);
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
/*     */   public File writeError(Exception e) throws IOException {
/* 108 */     int number = 0; File file;
/* 109 */     while ((file = new File(this.dir, "crash-" + number + ".txt")).exists()) {
/* 110 */       number++;
/*     */     }
/* 112 */     LogUtil.info(new String[] { "writing-crash", file.getAbsolutePath() });
/*     */     
/* 114 */     file.getParentFile().mkdirs();
/*     */     
/* 116 */     FileWriter fw = new FileWriter(file);
/*     */     
/* 118 */     fw.write(makeCrashReport(this.name, e));
/*     */     
/* 120 */     fw.close();
/*     */     
/* 122 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getDir() {
/* 131 */     return this.dir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDir(File dir) {
/* 140 */     this.dir = dir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 149 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 158 */     this.name = name;
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
/*     */   public static String makeCrashReport(String projectName, Exception e) {
/* 171 */     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 172 */     Date date = new Date();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     String report = "# " + projectName + " Crash Report\n\r#\n\r# At : " + dateFormat.format(date) + "\n\r#\n\r# Exception : " + e.getClass().getSimpleName() + "\n\r";
/*     */     
/* 180 */     report = report + "\n\r# " + e.toString();
/*     */     
/* 182 */     StackTraceElement[] stackTrace = e.getStackTrace();
/* 183 */     for (StackTraceElement element : stackTrace) {
/* 184 */       report = report + "\n\r#     " + element;
/*     */     }
/* 186 */     Throwable cause = e.getCause();
/* 187 */     if (cause != null) {
/*     */       
/* 189 */       report = report + "\n\r# Caused by: " + cause.toString();
/*     */       
/* 191 */       StackTraceElement[] causeStackTrace = cause.getStackTrace();
/* 192 */       for (StackTraceElement element : causeStackTrace) {
/* 193 */         report = report + "\n\r#     " + element;
/*     */       }
/*     */     } 
/* 196 */     return report;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\CrashReporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */