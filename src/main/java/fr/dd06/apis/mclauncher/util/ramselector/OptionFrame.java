/*    */ package fr.dd06.apis.mclauncher.util.ramselector;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JLabel;

import fr.dd06.apis.mclauncher.LanguageManager;
/*    */ 
/*    */ public class OptionFrame
/*    */   extends AbstractOptionFrame
/*    */ {
/*    */   private JLabel ramLabel;
/*    */   private JComboBox<String> ramBox;
/*    */   
/*    */   public OptionFrame(RamSelector selector) {
/* 16 */     super(selector);
/*    */     
/* 18 */     setTitle(LanguageManager.lang(new String[] { "options" }));
/* 19 */     setResizable(false);
/* 20 */     setSize(275, 100);
/* 21 */     setLocationRelativeTo((Component)null);
/* 22 */     setLayout((LayoutManager)null);
/*    */     
/* 24 */     this.ramLabel = new JLabel(LanguageManager.lang(new String[] { "ram" }) + " : ");
/* 25 */     this.ramLabel.setBounds(15, 20, 45, 25);
/* 26 */     add(this.ramLabel);
/*    */     
/* 28 */     this.ramBox = new JComboBox<>(RamSelector.RAM_ARRAY);
/* 29 */     this.ramBox.setBounds(65, 20, 195, 25);
/* 30 */     add(this.ramBox);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSelectedIndex() {
/* 35 */     return this.ramBox.getSelectedIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSelectedIndex(int index) {
/* 40 */     this.ramBox.setSelectedIndex(index);
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherli\\util\ramselector\OptionFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */