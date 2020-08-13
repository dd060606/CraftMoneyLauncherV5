/*     */ package fr.dd06.apis.mclauncher.external;
/*     */ 
/*     */ import java.io.File;
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
/*     */ public class ExternalLaunchProfile
/*     */ {
/*     */   private String mainClass;
/*     */   private String classPath;
/*     */   private List<String> vmArgs;
/*     */   private List<String> args;
/*     */   private boolean redirectErrorStream = true;
/*     */   private String macDockName;
/*     */   private File directory;
/*     */   
/*     */   public ExternalLaunchProfile(String mainClass, String classPath) {
/*  83 */     this(mainClass, classPath, null, null);
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
/*     */   public ExternalLaunchProfile(String mainClass, String classPath, List<String> vmArgs, List<String> args) {
/*  97 */     this(mainClass, classPath, vmArgs, args, false, null, null);
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
/*     */   
/*     */   public ExternalLaunchProfile(String mainClass, String classPath, List<String> vmArgs, List<String> args, boolean redirectErrorStream, String macDockName, File directory) {
/* 114 */     this.mainClass = mainClass;
/* 115 */     this.classPath = classPath;
/* 116 */     this.vmArgs = vmArgs;
/* 117 */     this.args = args;
/* 118 */     this.redirectErrorStream = redirectErrorStream;
/* 119 */     this.macDockName = macDockName;
/* 120 */     this.directory = directory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMainClass() {
/* 130 */     return this.mainClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMainClass(String mainClass) {
/* 140 */     this.mainClass = mainClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassPath() {
/* 150 */     return this.classPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassPath(String classPath) {
/* 160 */     this.classPath = classPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getVmArgs() {
/* 170 */     return this.vmArgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVmArgs(List<String> vmArgs) {
/* 180 */     this.vmArgs = vmArgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getArgs() {
/* 190 */     return this.args;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArgs(List<String> args) {
/* 200 */     this.args = args;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRedirectErrorStream() {
/* 210 */     return this.redirectErrorStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRedirectErrorStream(boolean redirectErrorStream) {
/* 220 */     this.redirectErrorStream = redirectErrorStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMacDockName() {
/* 230 */     return this.macDockName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMacDockName(String macDockName) {
/* 240 */     this.macDockName = macDockName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getDirectory() {
/* 250 */     return this.directory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirectory(File directory) {
/* 260 */     this.directory = directory;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\external\ExternalLaunchProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */