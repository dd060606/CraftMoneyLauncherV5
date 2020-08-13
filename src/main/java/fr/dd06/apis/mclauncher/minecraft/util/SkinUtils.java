/*     */ package fr.dd06.apis.mclauncher.minecraft.util;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import javax.imageio.ImageIO;

import fr.dd06.apis.mclauncher.util.LogUtil;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkinUtils
/*     */ {
/*  16 */   private String headPath = "https://minotar.net/avatar/%s";
/*  17 */   private String helmPath = "https://minotar.net/helm/%s";
/*  18 */   private String isoPath = "https://minotar.net/cube/%s";
/*  19 */   private String bodyPath = "https://minotar.net/body/%s";
/*  20 */   private String bodyArmorPath = "https://minotar.net/armor/body/%s";
/*  21 */   private String bustPath = "https://minotar.net/armor/bust/%s";
/*  22 */   private String bustArmorPath = "https://minotar.net/armor/bust/%s";
/*  23 */   private String skinPath = "https://minotar.net/avatar/%s";
/*  24 */   private static SkinUtils instance = new SkinUtils();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SkinUtils getInstance() {
/*  30 */     LogUtil.info(new String[] { "skin-utils" });
/*  31 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getHead(String username) {
/*  39 */     return getImage(String.format(this.headPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getHead(String username, int width) {
/*  48 */     return getImage(String.format(this.headPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getHeadWithHelm(String username) {
/*  56 */     return getImage(String.format(this.helmPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getHeadWithHelm(String username, int width) {
/*  65 */     return getImage(String.format(this.helmPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getIsometricHead(String username) {
/*  73 */     return getImage(String.format(this.isoPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getIsometricHead(String username, int width) {
/*  82 */     return getImage(String.format(this.isoPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBody(String username) {
/*  90 */     return getImage(String.format(this.bodyPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBody(String username, int width) {
/*  99 */     return getImage(String.format(this.bodyPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBodyWithArmor(String username) {
/* 107 */     return getImage(String.format(this.bodyArmorPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBodyWithArmor(String username, int width) {
/* 116 */     return getImage(String.format(this.bodyArmorPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBust(String username) {
/* 124 */     return getImage(String.format(this.bustPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBust(String username, int width) {
/* 133 */     return getImage(String.format(this.bustPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBustWithArmor(String username) {
/* 141 */     return getImage(String.format(this.bustArmorPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBustWithArmor(String username, int width) {
/* 150 */     return getImage(String.format(this.bustArmorPath + "/" + width + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getUserSkin(String username) {
/* 158 */     return getImage(String.format(this.skinPath + ".png", new Object[] { username }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage(String url) {
/* 166 */     BufferedImage skin = null;
/*     */     try {
/* 168 */       skin = ImageIO.read(new URL(url));
/* 169 */     } catch (IOException e) {
/* 170 */       LogUtil.err(new String[] { "skin-error", url });
/*     */     } 
/* 172 */     return skin;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraf\\util\SkinUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */