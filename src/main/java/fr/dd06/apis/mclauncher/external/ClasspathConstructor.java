/*    */ package fr.dd06.apis.mclauncher.external;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.List;

import fr.dd06.apis.mclauncher.util.explorer.FileList;

/*    */
/*    */ 
/*    */ public class ClasspathConstructor
/*    */   extends FileList
/*    */ {
/*    */   public ClasspathConstructor() {}
/*    */   
/*    */   public ClasspathConstructor(List<File> classPath) {
/* 14 */     super(classPath);
/*    */   }
/*    */   
/*    */   public String make() {
/* 18 */     String classPath = "";
/*    */     
/* 20 */     for (int i = 0; i < this.files.size(); i++) {
/* 21 */       classPath = classPath + ((File)this.files.get(i)).getAbsolutePath() + ((i + 1 == this.files.size()) ? "" : File.pathSeparator);
/*    */     }
/* 23 */     return classPath;
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\external\ClasspathConstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */