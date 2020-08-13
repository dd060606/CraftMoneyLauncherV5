/*    */ package fr.dd06.apis.mclauncher.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */
/*    */ import java.awt.Image;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SplashScreen
/*    */   extends JFrame
/*    */ {
/*    */   public SplashScreen(String title, Image image) {
/* 14 */     setTitle(title);
/* 15 */     setUndecorated(true);
/* 16 */     setDefaultCloseOperation(3);
/* 17 */     setSize(image.getWidth(this), image.getHeight(this));
/* 18 */     setLocationRelativeTo(null);
/* 19 */     setContentPane(new SplashPanel(image));
/*    */   }
/*    */   
/*    */   public Thread displayFor(final long time) {
/* 23 */     Thread thread = new Thread()
/*    */       {
/*    */         public void run() {
/* 26 */           SplashScreen.this.setVisible(true);
/*    */           
/*    */           try {
/* 29 */             sleep(time);
/* 30 */           } catch (InterruptedException e) {
/* 31 */             LogUtil.err(new String[] { "warn", " : ", "splash-interrupted" });
/*    */           } 
/*    */           
/* 34 */           SplashScreen.this.setVisible(false);
/*    */         }
/*    */       };
/* 37 */     thread.start();
/*    */     
/* 39 */     return thread;
/*    */   }
/*    */   
/*    */   public void display() {
/* 43 */     setVisible(true);
/*    */   }
/*    */   
/*    */   public void stop() {
/* 47 */     setVisible(false);
/*    */   }
/*    */   
/*    */   public void setTransparent() {
/* 51 */     setBackground(new Color(0, 0, 0, 0));
/* 52 */     getContentPane().setBackground(new Color(0, 0, 0, 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public SplashPanel getContentPane() {
/* 57 */     return (SplashPanel)super.getContentPane();
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\SplashScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */