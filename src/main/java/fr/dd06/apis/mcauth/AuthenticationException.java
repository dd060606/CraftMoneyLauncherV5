/*    */ package fr.dd06.apis.mcauth;
import fr.dd06.apis.mcauth.model.AuthError;
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
/*    */ public class AuthenticationException
/*    */   extends Exception
/*    */ {
/*    */   private AuthError model;
/*    */   
/*    */   public AuthenticationException(AuthError model) {
/* 43 */     super(model.getErrorMessage());
/* 44 */     this.model = model;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AuthError getErrorModel() {
/* 53 */     return this.model;
/*    */   }
/*    */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openauth-1.0.4.jar!\re\alwyn974\openauth\AuthenticationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */