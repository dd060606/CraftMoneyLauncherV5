/*     */ package fr.dd06.apis.mclauncher.external;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;

import fr.dd06.apis.mclauncher.JavaUtil;
import fr.dd06.apis.mclauncher.LaunchException;
import fr.dd06.apis.mclauncher.util.LogUtil;
import fr.dd06.apis.mclauncher.util.ProcessLogManager;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExternalLauncher
/*     */ {
/*     */   private BeforeLaunchingEvent launchingEvent;
/*     */   private ExternalLaunchProfile profile;
/*     */   private boolean logsEnabled = true;
/*     */   
/*     */   public ExternalLauncher(ExternalLaunchProfile profile) {
/*  21 */     this(profile, null);
/*     */   }
/*     */   
/*     */   public ExternalLauncher(ExternalLaunchProfile profile, BeforeLaunchingEvent launchingEvent) {
/*  25 */     this.profile = profile;
/*  26 */     this.launchingEvent = launchingEvent;
/*     */   }
/*     */   
/*     */   public boolean isLogsEnabled() {
/*  30 */     return this.logsEnabled;
/*     */   }
/*     */   
/*     */   public void setLogsEnabled(boolean logsEnabled) {
/*  34 */     this.logsEnabled = logsEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public Process launch() throws LaunchException {
/*  39 */     LogUtil.info(new String[] { "hi-ext" });
/*     */     
/*  41 */     ProcessBuilder builder = new ProcessBuilder(new String[0]);
/*  42 */     ArrayList<String> commands = new ArrayList<>();
/*  43 */     commands.add(JavaUtil.getJavaCommand());
/*  44 */     commands.addAll(Arrays.asList(JavaUtil.getSpecialArgs()));
/*     */     
/*  46 */     if (this.profile.getMacDockName() != null && System.getProperty("os.name").toLowerCase().contains("mac"))
/*  47 */       commands.add(JavaUtil.macDockName(this.profile.getMacDockName())); 
/*  48 */     if (this.profile.getVmArgs() != null) {
/*  49 */       commands.addAll(this.profile.getVmArgs());
/*     */     }
/*  51 */     commands.add("-cp");
/*  52 */     commands.add(this.profile.getClassPath());
/*     */     
/*  54 */     commands.add(this.profile.getMainClass());
/*     */     
/*  56 */     if (this.profile.getArgs() != null) {
/*  57 */       commands.addAll(this.profile.getArgs());
/*     */     }
/*  59 */     if (this.profile.getDirectory() != null) {
/*  60 */       builder.directory(this.profile.getDirectory());
/*     */     }
/*  62 */     if (this.profile.isRedirectErrorStream()) {
/*  63 */       builder.redirectErrorStream(true);
/*     */     }
/*  65 */     if (this.launchingEvent != null) {
/*  66 */       this.launchingEvent.onLaunching(builder);
/*     */     }
/*  68 */     builder.command(commands);
/*     */     
/*  70 */     String entireCommand = "";
/*  71 */     for (String command : commands) {
/*  72 */       entireCommand = entireCommand + command + " ";
/*     */     }
/*  74 */     LogUtil.info(new String[] { "ent", ":", entireCommand });
/*  75 */     LogUtil.info(new String[] { "start", this.profile.getMainClass() });
/*     */     
/*     */     try {
/*  78 */       Process p = builder.start();
/*     */       
/*  80 */       if (this.logsEnabled) {
/*  81 */         ProcessLogManager manager = new ProcessLogManager(p.getInputStream());
/*  82 */         manager.start();
/*     */       } 
/*     */       
/*  85 */       return p;
/*  86 */     } catch (IOException e) {
/*  87 */       throw new LaunchException("Cannot launch !", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BeforeLaunchingEvent getLaunchingEvent() {
/*  92 */     return this.launchingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLaunchingEvent(BeforeLaunchingEvent launchingEvent) {
/*  97 */     this.launchingEvent = launchingEvent;
/*     */   }
/*     */   
/*     */   public ExternalLaunchProfile getProfile() {
/* 101 */     return this.profile;
/*     */   }
/*     */   
/*     */   public void setProfile(ExternalLaunchProfile profile) {
/* 105 */     this.profile = profile;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\external\ExternalLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */