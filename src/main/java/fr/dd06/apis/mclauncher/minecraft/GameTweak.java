/*    */ package fr.dd06.apis.mclauncher.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class GameTweak
/*    */ {
/*    */   public static final String LAUNCHWRAPPER_MAIN_CLASS = "net.minecraft.launchwrapper.Launch";
/*    */   
/* 46 */   public static final GameTweak FORGE = new GameTweak()
/*    */     {
/*    */       
/*    */       public String getName()
/*    */       {
/* 51 */         return "FML Tweaker";
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public String getTweakClass(GameInfos infos) {
/* 57 */         if (infos.getGameVersion().getGameType().equals(GameType.V1_8_HIGHER)) {
/* 58 */           return "net.minecraftforge.fml.common.launcher.FMLTweaker";
/*    */         }
/* 60 */         return "cpw.mods.fml.common.launcher.FMLTweaker";
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public static final GameTweak OPTIFINE = new GameTweak()
/*    */     {
/*    */       
/*    */       public String getName()
/*    */       {
/* 72 */         return "Optifine Tweaker";
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public String getTweakClass(GameInfos infos) {
/* 78 */         return "optifine.OptiFineTweaker";
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public static final GameTweak SHADER = new GameTweak()
/*    */     {
/*    */       
/*    */       public String getName()
/*    */       {
/* 90 */         return "Shader Tweaker";
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public String getTweakClass(GameInfos infos) {
/* 96 */         if (infos.getGameVersion().getName().contains("1.8")) {
/* 97 */           return "shadersmod.launch.SMCTweaker";
/*    */         }
/* 99 */         return "shadersmodcore.loading.SMCTweaker";
/*    */       }
/*    */     };
/*    */   
/*    */   public abstract String getName();
/*    */   
/*    */   public abstract String getTweakClass(GameInfos paramGameInfos);
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\minecraft\GameTweak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */