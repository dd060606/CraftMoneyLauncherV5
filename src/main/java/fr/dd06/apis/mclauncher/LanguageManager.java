/*     */ package fr.dd06.apis.mclauncher;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class LanguageManager {
/*   7 */   public static final HashMap<String, String> ENGLISH = new HashMap<>();
/*     */   
/*   9 */   public static final HashMap<String, String> FRENCH = new HashMap<>();
/*     */   
/*  11 */   private static HashMap<String, String> currentLangSet = ENGLISH;
/*     */   
/*     */   static {
/*  14 */     ENGLISH.put("hi-ext", "OpenLauncherLib 3.0.4 by Adrien 'Litarvan' Navratil - External Launching System");
/*  15 */     ENGLISH.put("jre-custom", "Java Custom Enabled | by Alwyn974");
/*  16 */     ENGLISH.put("mineweb-enable", "Mineweb Enabled | by Alwyn974");
/*  17 */     ENGLISH.put("connect-server", "Automatic connection to the server | by Alwyn974");
/*  18 */     ENGLISH.put("skin-utils", "You are using SkinUtils | By Alwyn974");
/*  19 */     ENGLISH.put("skin-error", "Invalid url, image not found : ");
/*  20 */     ENGLISH.put("options", "Options");
/*  21 */     ENGLISH.put("ram", "RAM");
/*  22 */     ENGLISH.put("warn", "Warning");
/*  23 */     ENGLISH.put("splash-interrupted", "Splash wait time was interrupted !");
/*  24 */     ENGLISH.put("ex-caught", "Exception caught !");
/*  25 */     ENGLISH.put("report-error", "Unable to write the crash report !");
/*  26 */     ENGLISH.put("ram-empty", "Can't read ram : File is empty");
/*  27 */     ENGLISH.put("writing-crash", "Writing crash report to");
/*  28 */     ENGLISH.put("load-fail", "Can't load the given jar");
/*  29 */     ENGLISH.put("jar-notfound", "Can't find the given jar");
/*  30 */     ENGLISH.put("loading", "Loading file");
/*  31 */     ENGLISH.put("mc-check", "Checking Minecraft directory");
/*  32 */     ENGLISH.put("mc-int", "Creating internal launching profile for Minecraft");
/*  33 */     ENGLISH.put("mc-ext", "Creating external launching profile for Minecraft");
/*  34 */     ENGLISH.put("mc-cp", "Generating classpath");
/*  35 */     ENGLISH.put("log-err", "Error while writing the logs !");
/*  36 */     ENGLISH.put("log-end", "Error, logging ended suddenly");
/*  37 */     ENGLISH.put("launching", "Launching program. It is now");
/*  38 */     ENGLISH.put("init", "Initializing main class");
/*  39 */     ENGLISH.put("start", "Starting");
/*  40 */     ENGLISH.put("total", "Total time");
/*  41 */     ENGLISH.put("security", "Detected certificate information error, please delete META-INF in your JAR");
/*  42 */     ENGLISH.put("nat", "Loading the natives");
/*  43 */     ENGLISH.put("done", "Done");
/*  44 */     ENGLISH.put("ent", "Entire command");
/*     */     
/*  46 */     FRENCH.put("hi-ext", "OpenLauncherLib 3.0.4 par Adrien 'Litarvan' Navratil - Systeme de lancement externe");
/*  47 */     FRENCH.put("jre-custom", "Java Custom Activé | by Alwyn974");
/*  48 */     FRENCH.put("mineweb-enable", "Mineweb Activé | by Alwyn974");
/*  49 */     FRENCH.put("connect-server", "Connexion automatique au Serveur | by Alwyn974");
/*  50 */     FRENCH.put("skin-utils", "Vous utilisez SkinUtils | By Alwyn974");
/*  51 */     FRENCH.put("skin-error", "Url invalide, image introuvable : ");
/*  52 */     FRENCH.put("options", "Options");
/*  53 */     FRENCH.put("ram", "RAM");
/*  54 */     FRENCH.put("warn", "Attention");
/*  55 */     FRENCH.put("splash-interrupted", "Le temps d'attente du splash a été interrompu !");
/*  56 */     FRENCH.put("ex-caught", "Exception attrapee !");
/*  57 */     FRENCH.put("report-error", "Impossible d'écrire le crash report !");
/*  58 */     FRENCH.put("ram-empty", "Impossible de lire la RAM : Le fichier est vide");
/*  59 */     FRENCH.put("writing-crash", "Ecriture du crash report dans");
/*  60 */     FRENCH.put("load-fail", "Impossible de charger le jar");
/*  61 */     FRENCH.put("jar-notfound", "Impossible de trouver le jar");
/*  62 */     FRENCH.put("loading", "Chargement du fichier");
/*  63 */     FRENCH.put("mc-check", "Verification du dossier de Minecraft");
/*  64 */     FRENCH.put("mc-int", "Creation d'un profil de lancement interne pour Minecraft");
/*  65 */     FRENCH.put("mc-ext", "Creation d'un profil de lancement externe pour Minecraft");
/*  66 */     FRENCH.put("mc-cp", "Generation du classpath");
/*  67 */     FRENCH.put("log-err", "Erreur en ecrivant les logs !");
/*  68 */     FRENCH.put("log-end", "Erreur, le systeme de logs s'est brusquement arrete");
/*  69 */     FRENCH.put("launching", "Lancement du programme. Il est actuellement");
/*  70 */     FRENCH.put("init", "Initialization de la classe principale");
/*  71 */     FRENCH.put("start", "Lancement de");
/*  72 */     FRENCH.put("total", "Temps total");
/*  73 */     FRENCH.put("security", "Une erreur de certification a ete detectee, merci de supprimer le dossier META-INF de votre .jar");
/*  74 */     FRENCH.put("nat", "Chargement des natives");
/*  75 */     FRENCH.put("done", "Termine");
/*  76 */     FRENCH.put("ent", "Commande entiere");
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
/* 103 */     if (Locale.getDefault().getLanguage().toLowerCase().startsWith("fr"))
/* 104 */       setLang(FRENCH); 
/*     */   }
/*     */   
/*     */   public static String lang(String... keys) {
/*     */     String total = "";
/*     */     for (String key : keys) {
/*     */       String text = currentLangSet.get(key);
/*     */       if (text == null)
/*     */         text = ENGLISH.get(key); 
/*     */       total = total + ((text == null) ? key : text) + " ";
/*     */     } 
/*     */     return total;
/*     */   }
/*     */   
/*     */   public static void setLang(HashMap<String, String> langSet) {
/*     */     currentLangSet = langSet;
/*     */   }
/*     */   
/*     */   public static HashMap<String, String> getCurrentLangSet() {
/*     */     return currentLangSet;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openlauncherlib-3.0.4.jar!\re\alwyn974\openlauncherlib\LanguageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */