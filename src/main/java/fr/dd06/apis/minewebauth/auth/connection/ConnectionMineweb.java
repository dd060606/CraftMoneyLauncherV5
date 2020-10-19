package fr.dd06.apis.minewebauth.auth.connection;

public class ConnectionMineweb {
   private static String URLRoot;
   private static int typeConnection;

   public static void setUrlRoot(String URLRootSet) {
      URLRoot = URLRootSet;
   }

   public static void setTypeConnection(int typeConnectionSet) {
      typeConnection = typeConnectionSet;
   }

   public static String getUrlRoot() {
      return URLRoot;
   }

   public static int getTypeConnection() {
      return typeConnection;
   }
}
