/*     */ package fr.dd06.apis.mclauncher.internal;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ @Deprecated
/*     */ public class InternalLaunchProfile
/*     */ {
/*     */   private String targetClass;
/*     */   private String targetMethod;
/*     */   private Object[] parameters;
/*     */   private Class[] parametersTypes;
/*  62 */   private List<File> classpath = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalLaunchProfile(String targetClass) {
/*  71 */     this(targetClass, new String[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalLaunchProfile(String targetClass, String[] parameters) {
/*  82 */     this(targetClass, "main", new Object[] { parameters }, new Class[] { String[].class });
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
/*     */   public InternalLaunchProfile(String targetClass, String targetMethod, Object[] parameters, Class[] parametersTypes) {
/*  95 */     this.targetClass = targetClass;
/*  96 */     this.targetMethod = targetMethod;
/*  97 */     this.parameters = parameters;
/*  98 */     this.parametersTypes = parametersTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTargetClass() {
/* 108 */     return this.targetClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetClass(String targetClass) {
/* 118 */     this.targetClass = targetClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTargetMethod() {
/* 128 */     return this.targetMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTargetMethod(String targetMethod) {
/* 138 */     this.targetMethod = targetMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getParameters() {
/* 148 */     return this.parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameters(Object[] parameters) {
/* 158 */     this.parameters = parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class[] getParametersTypes() {
/* 168 */     return this.parametersTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParametersTypes(Class[] parametersTypes) {
/* 178 */     this.parametersTypes = parametersTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<File> getClasspath() {
/* 188 */     return this.classpath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClasspath(List<File> classpath) {
/* 198 */     this.classpath = classpath;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\internal\InternalLaunchProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */