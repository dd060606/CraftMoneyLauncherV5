/*    */ package fr.dd06.apis.mclauncher.util.ramselector;
/*    */ 
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public abstract class AbstractOptionFrame
/*    */   extends JFrame
/*    */ {
/*    */   private RamSelector selector;
/*    */   
/*    */   public AbstractOptionFrame(RamSelector selector) {
/* 11 */     this.selector = selector;
/*    */   }
/*    */   
/*    */   public RamSelector getSelector() {
/* 15 */     return this.selector;
/*    */   }
/*    */   
/*    */   public abstract int getSelectedIndex();
/*    */   
/*    */   public abstract void setSelectedIndex(int paramInt);
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\ramselector\AbstractOptionFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */