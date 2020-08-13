/*    */ package fr.dd06.apis.mclauncher.util;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import javax.swing.JPanel;
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
/*    */ class SplashPanel
/*    */   extends JPanel
/*    */ {
/*    */   private Image image;
/*    */   
/*    */   public SplashPanel(Image image) {
/* 68 */     this.image = image;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paintComponent(Graphics g) {
/* 73 */     g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\SplashPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */