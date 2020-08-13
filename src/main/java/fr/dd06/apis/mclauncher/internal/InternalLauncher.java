/*     */ package fr.dd06.apis.mclauncher.internal;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;

import fr.dd06.apis.mclauncher.LaunchException;
import fr.dd06.apis.mclauncher.util.LogUtil;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class InternalLauncher
/*     */   implements ClassInitializer
/*     */ {
/*     */   private InternalLaunchProfile profile;
/*     */   private ClassInitializer initializer;
/*     */   
/*     */   public InternalLauncher(InternalLaunchProfile profile) {
/*  72 */     this(profile, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InternalLauncher(InternalLaunchProfile profile, ClassInitializer initializer) {
/*  83 */     this.profile = profile;
/*  84 */     this.initializer = (initializer != null) ? initializer : this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object launch() throws LaunchException {
/*     */     Class<?> theClass;
/*  96 */     System.err.println("##########################################################");
/*  97 */     System.err.println("  WARNING : Internal Launching doesn't work with Java 9");
/*  98 */     System.err.println("  --> YOU SHOULD USE EXTERNAL LAUNCHING INSTEAD");
/*  99 */     System.err.println("##########################################################");
/*     */     
/* 101 */     LogUtil.info(new String[] { "hi-int" });
/* 102 */     LogUtil.info(new String[] { "launching", ":", (new Date(System.currentTimeMillis())).toString() });
/* 103 */     long start = System.currentTimeMillis();
/*     */     
/* 105 */     if (this.profile.getClasspath() != null) {
/* 106 */       for (File f : this.profile.getClasspath()) {
/*     */         
/* 108 */         LogUtil.info(new String[] { "loading", ":", f.getAbsolutePath() });
/* 109 */         JarLoader.addToClasspath(f);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 115 */       theClass = ClassLoader.getSystemClassLoader().loadClass(this.profile.getTargetClass());
/*     */     }
/* 117 */     catch (Exception e) {
/*     */       
/* 119 */       if (e instanceof SecurityException && e.getMessage().contains("signer information does not match signer information of other classes in the same package")) {
/* 120 */         LogUtil.err(new String[] { "security" });
/*     */       } else {
/* 122 */         throw new UnknownMainClassException(this.profile.getTargetClass(), e);
/*     */       } 
/* 124 */       return null;
/*     */     } 
/*     */     
/* 127 */     LogUtil.info(new String[] { "init", ":", this.profile.getTargetClass() });
/*     */ 
/*     */     
/* 130 */     Method method = null;
/*     */     
/*     */     try {
/* 133 */       Method[] methods = theClass.getDeclaredMethods();
/* 134 */       for (Method m : methods) {
/* 135 */         if (m.getName().equals(this.profile.getTargetMethod()) && Arrays.equals((Object[])m.getParameterTypes(), (Object[])this.profile.getParametersTypes())) {
/*     */           
/* 137 */           method = m;
/*     */           break;
/*     */         } 
/*     */       } 
/* 141 */       if (method == null) {
/* 142 */         throw new UnknownMethodException(this.profile.getTargetMethod());
/*     */       }
/* 144 */     } catch (Exception e) {
/*     */       
/* 146 */       throw (e instanceof UnknownMethodException) ? (UnknownMethodException)e : new UnknownMethodException(this.profile.getTargetMethod(), e);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     Object initClass = null;
/* 151 */     if (!Modifier.isStatic(method.getModifiers())) {
/*     */       
/*     */       try {
/* 154 */         initClass = this.initializer.init(theClass);
/*     */       }
/* 156 */       catch (Throwable t) {
/*     */         
/* 158 */         throw new LaunchException("Can't initialize the main class", t);
/*     */       } 
/*     */     }
/* 161 */     method.setAccessible(true);
/*     */     
/* 163 */     long totalTime = System.currentTimeMillis() - start;
/* 164 */     int seconds = (int)(totalTime / 1000L) % 60;
/* 165 */     int minutes = (int)(totalTime / 60000L % 60L);
/* 166 */     int hours = (int)(totalTime / 3600000L % 24L);
/* 167 */     String strTime = hours + " hours " + minutes + " minutes " + seconds + " seconds and " + (totalTime % 1000L) + " milliseconds.";
/* 168 */     LogUtil.info(new String[] { "total", ":", strTime });
/*     */     
/* 170 */     LogUtil.info(new String[] { "start", this.profile.getTargetClass(), "->", this.profile.getTargetMethod(), "(" + Arrays.toString((Object[])this.profile.getParametersTypes()) + ")" });
/*     */ 
/*     */     
/*     */     try {
/* 174 */       return method.invoke(initClass, this.profile.getParameters());
/*     */     }
/* 176 */     catch (InvocationTargetException e) {
/*     */       
/* 178 */       Throwable thrown = e.getTargetException();
/* 179 */       if (thrown instanceof ExceptionInInitializerError) {
/*     */         
/* 181 */         ExceptionInInitializerError initError = (ExceptionInInitializerError)thrown;
/* 182 */         thrown = initError.getException();
/*     */       } 
/* 184 */       throw new LaunchException("Invoked method returned an exception", thrown);
/*     */     }
/* 186 */     catch (IllegalAccessException e) {
/*     */       
/* 188 */       throw new LaunchException("This is not supposed to happen", e);
/*     */     } 
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
/*     */   public InternalLaunchProfile getProfile() {
/* 201 */     return this.profile;
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
/*     */   public void setProfile(InternalLaunchProfile profile) {
/* 213 */     this.profile = profile;
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
/*     */   public ClassInitializer getInitializer() {
/* 225 */     return this.initializer;
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
/*     */   public void setInitializer(ClassInitializer initializer) {
/* 237 */     this.initializer = initializer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object init(Class<?> toInit) throws IllegalAccessException, InstantiationException {
/* 243 */     return toInit.newInstance();
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\internal\InternalLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */