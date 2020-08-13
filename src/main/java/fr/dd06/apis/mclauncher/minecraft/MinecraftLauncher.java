/*     */ package fr.dd06.apis.mclauncher.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

import fr.dd06.apis.mclauncher.JavaUtil;
import fr.dd06.apis.mclauncher.LaunchException;
import fr.dd06.apis.mclauncher.util.LogUtil;
import fr.dd06.apis.mclauncher.external.ClasspathConstructor;
import fr.dd06.apis.mclauncher.external.ExternalLaunchProfile;
import fr.dd06.apis.mclauncher.internal.InternalLaunchProfile;
import fr.dd06.apis.mclauncher.minecraft.auth.Account;
import fr.dd06.apis.mclauncher.util.explorer.Explorer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinecraftLauncher
/*     */ {
/*     */   @Deprecated
/*     */   public static InternalLaunchProfile createInternalProfile(GameInfos infos, GameFolder folder, Account account) throws LaunchException {
/*  35 */     LogUtil.info(new String[] { "mc-int", infos.getGameVersion().getName() });
/*  36 */     LogUtil.info(new String[] { "mc-check", infos.getGameDir().getAbsolutePath() });
/*     */     
/*  38 */     checkFolder(folder, infos.getGameDir());
/*     */     
/*  40 */     LogUtil.info(new String[] { "mc-cp" });
/*  41 */     List<File> libs = Explorer.dir(infos.getGameDir()).sub(folder.getLibsFolder()).allRecursive().files().match("^(.*\\.((jar)$))*$").get();
/*  42 */     libs.add(Explorer.dir(infos.getGameDir()).get(folder.getMainJar()));
/*     */     
/*  44 */     List<String> arguments = infos.getGameVersion().getGameType().getLaunchArgs(infos, folder, account);
/*     */     
/*  46 */     if (infos.getGameTweaks() != null) {
/*  47 */       for (GameTweak tweak : infos.getGameTweaks()) {
/*     */         
/*  49 */         arguments.add("--tweakClass");
/*  50 */         arguments.add(tweak.getTweakClass(infos));
/*     */       } 
/*     */     }
/*  53 */     String mainClass = (infos.getGameTweaks() == null || (infos.getGameTweaks()).length == 0) ? infos.getGameVersion().getGameType().getMainClass(infos) : "net.minecraft.launchwrapper.Launch";
/*  54 */     String[] args = arguments.<String>toArray(new String[arguments.size()]);
/*     */     
/*  56 */     InternalLaunchProfile profile = new InternalLaunchProfile(mainClass, args);
/*  57 */     profile.setClasspath(libs);
/*     */     
/*  59 */     System.setProperty("fml.ignoreInvalidMinecraftCertificates", "true");
/*     */     
/*  61 */     LogUtil.info(new String[] { "nat" });
/*     */     
/*     */     try {
/*  64 */       JavaUtil.setLibraryPath((new File(infos.getGameDir(), folder.getNativesFolder())).getAbsolutePath());
/*     */     }
/*  66 */     catch (Exception e) {
/*     */       
/*  68 */       throw new LaunchException("Can't register the natives", e);
/*     */     } 
/*     */     
/*  71 */     LogUtil.info(new String[] { "done" });
/*     */     
/*  73 */     return profile;
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
/*     */   public static ExternalLaunchProfile createExternalProfile(GameInfos infos, GameFolder folder, Account account) throws LaunchException {
/*  88 */     LogUtil.info(new String[] { "mc-ext", infos.getGameVersion().getName() });
/*  89 */     LogUtil.info(new String[] { "mc-check", infos.getGameDir().getAbsolutePath() });
/*     */     
/*  91 */     if (account == null) {
/*  92 */       throw new IllegalArgumentException("authInfos == null");
/*     */     }
/*     */     
/*  95 */     checkFolder(folder, infos.getGameDir());
/*     */     
/*  97 */     LogUtil.info(new String[] { "mc-cp" });
/*     */     
/*  99 */     ClasspathConstructor constructor = new ClasspathConstructor();
/* 100 */     constructor.add(Explorer.dir(infos.getGameDir()).sub(folder.getLibsFolder()).allRecursive().files().match("^(.*\\.((jar)$))*$").get());
/* 101 */     constructor.add(new File[] { Explorer.dir(infos.getGameDir()).get(folder.getMainJar()) });
/*     */ 
/*     */     
/* 104 */     String mainClass = (infos.getGameTweaks() == null || (infos.getGameTweaks()).length == 0) ? infos.getGameVersion().getGameType().getMainClass(infos) : "net.minecraft.launchwrapper.Launch";
/*     */ 
/*     */     
/* 107 */     String classpath = constructor.make();
/* 108 */     List<String> args = infos.getGameVersion().getGameType().getLaunchArgs(infos, folder, account);
/* 109 */     List<String> vmArgs = new ArrayList<>();
/* 110 */     vmArgs.add("-Djava.library.path=" + Explorer.dir(infos.getGameDir()).sub(folder.getNativesFolder()).get().getAbsolutePath());
/* 111 */     vmArgs.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
/* 112 */     vmArgs.add("-Dfml.ignorePatchDiscrepancies=true");
/*     */     
/* 114 */     if (infos.getGameTweaks() != null) {
/* 115 */       for (GameTweak tweak : infos.getGameTweaks()) {
/* 116 */         args.add("--tweakClass");
/* 117 */         args.add(tweak.getTweakClass(infos));
/*     */       } 
/*     */     }
/* 120 */     ExternalLaunchProfile profile = new ExternalLaunchProfile(mainClass, classpath, vmArgs, args, true, infos.getServerName(), infos.getGameDir());
/*     */     
/* 122 */     LogUtil.info(new String[] { "done" });
/*     */     
/* 124 */     return profile;
/*     */   }
/*     */   
/*     */   public static void checkFolder(GameFolder folder, File directory) throws FolderException {
/* 128 */     File assetsFolder = new File(directory, folder.getAssetsFolder());
/* 129 */     File libsFolder = new File(directory, folder.getLibsFolder());
/* 130 */     File nativesFolder = new File(directory, folder.getNativesFolder());
/* 131 */     File minecraftJar = new File(directory, folder.getMainJar());
/*     */     
/* 133 */     if (!assetsFolder.exists() || assetsFolder.listFiles() == null)
/* 134 */       throw new FolderException("Missing/Empty assets folder (" + assetsFolder.getAbsolutePath() + ")"); 
/* 135 */     if (!libsFolder.exists() || libsFolder.listFiles() == null)
/* 136 */       throw new FolderException("Missing/Empty libraries folder (" + libsFolder.getAbsolutePath() + ")"); 
/* 137 */     if (!nativesFolder.exists() || nativesFolder.listFiles() == null)
/* 138 */       throw new FolderException("Missing/Empty natives folder (" + nativesFolder.getAbsolutePath() + ")"); 
/* 139 */     if (!minecraftJar.exists())
/* 140 */       throw new FolderException("Missing main jar (" + minecraftJar.getAbsolutePath() + ")"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraft\MinecraftLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */