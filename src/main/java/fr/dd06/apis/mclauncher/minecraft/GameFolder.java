/*     */ package fr.dd06.apis.mclauncher.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameFolder
/*     */ {
/*  38 */   public static final GameFolder BASIC = new GameFolder("assets", "libs", "natives", "minecraft.jar");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String assetsFolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String libsFolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String nativesFolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String mainJar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameFolder(String assetsFolder, String libsFolder, String nativesFolder, String mainJar) {
/*  70 */     this.assetsFolder = assetsFolder;
/*  71 */     this.libsFolder = libsFolder;
/*  72 */     this.nativesFolder = nativesFolder;
/*  73 */     this.mainJar = mainJar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAssetsFolder() {
/*  83 */     return this.assetsFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLibsFolder() {
/*  93 */     return this.libsFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNativesFolder() {
/* 103 */     return this.nativesFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMainJar() {
/* 113 */     return this.mainJar;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraft\GameFolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */