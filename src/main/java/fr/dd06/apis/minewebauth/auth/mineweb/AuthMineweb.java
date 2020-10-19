package fr.dd06.apis.minewebauth.auth.mineweb;

import fr.dd06.apis.minewebauth.auth.exception.DataEmptyException;
import fr.dd06.apis.minewebauth.auth.exception.DataWrongException;
import fr.dd06.apis.minewebauth.auth.exception.ServerNotFoundException;
import fr.dd06.apis.minewebauth.auth.mineweb.utils.*;
import org.apache.commons.codec.digest.DigestUtils;


import java.io.IOException;

public class AuthMineweb {
   public static Get getData;
   public static Get.getSession getSession;
   public static Get.getCore getCore;
   public static Core set;

   public static void start() throws DataWrongException, DataEmptyException, ServerNotFoundException, IOException {
      String strTpConnect = String.valueOf(Core.tpConnect);
      if (strTpConnect.equalsIgnoreCase("0")) {
         Core.log("Vous devez dï¿½finir un type de connection. Rï¿½fï¿½rencez-vous ï¿½ï¿½ la documentation.");
      } else {
         if (!Core.URLRoot.contains("/auth") && !Core.URLRoot.contains("auth")) {
            if (strTpConnect.equalsIgnoreCase(String.valueOf(TypeConnection.launcher))) {
               Core.log("Dï¿½marrage de l'authentification vers " + Get.getCore.getURLRoot());
               Core.log("Dï¿½bug Connect:" + Core.URLRoot + "/auth/start?username=" + Get.getUSERNAME() + "&password=" + Get.getPASSWORD());
               Core.log("Pass Param: USER{" + Get.getUSERNAME() + "} && PWD:{" + Get.getPASSWORD() + "}");
               String ResultAuthServer = GetData.GetData(Core.URLRoot + "/auth/start?username=" + Get.getUSERNAME() + "&password=" + Get.getPASSWORD());
               if (!ResultAuthServer.equalsIgnoreCase("success_ok")) {
                  if (ResultAuthServer.equalsIgnoreCase("error_password")) {
                     Core.log("Response Server: " + ResultAuthServer);
                     throw new DataWrongException("error_data");
                  }

                  if (ResultAuthServer.equalsIgnoreCase("Empty Get")) {
                     Core.log("Response Server: " + ResultAuthServer);
                     throw new DataEmptyException("empty_get");
                  }

                  throw new ServerNotFoundException("server_not_found");
               }

               Core.log("Response Server: " + ResultAuthServer);
               Core.isConnected = true;
               RegisterData.StartRegisterSessionLauncher();
               Core.log("===============================================");
               Core.log("Donnï¿½es enregistrï¿½es:");
               Core.log("Pseudo: " + Get.getSession.getUsername());
               Core.log("UUID: " + Get.getSession.getUuid());
               Core.log("AccessToken: " + Get.getSession.getAccessToken());
               Core.log("ClientToken: " + Get.getSession.getClientToken());
               Core.log("===============================================");
            } else {
               try {
                  RegisterData.StartRegisterSessionIngame();
                  Core.log("===============================================");
                  Core.log("Donnï¿½es enregistrï¿½es:");
                  Core.log("Pseudo: " + Get.getSession.getUsername());
                  Core.log("UUID: " + Get.getSession.getUuid());
                  Core.log("AccessToken: " + Get.getSession.getAccessToken());
                  Core.log("ClientToken: " + Get.getSession.getClientToken());
                  Core.log("===============================================");
               } catch (Exception var2) {
                  throw new DataWrongException("error_data");
               }
            }
         } else {
            Core.log("L'adresse de root (AuthMineweb.setUrlRoot) ne doit pas contenir de /auth");
         }

      }
   }

   public static void setUrlRoot(String URL) {
      Core.URLRoot = URL;
   }

   public static void setUsername(String Username) {
      Core.Username = Username;
   }

   public static void setTypeConnection(int typeConnection) {
      Core.tpConnect = typeConnection;
   }

   public static void setAccessToken(String accessToken) {
      Core.accessToken = accessToken;
   }

   public static void setPassword(String Password) {
      Password = DigestUtils.sha256Hex(Password);
      Core.Password = Password;
   }

   public static boolean isConnected() {
      return Core.isConnected;
   }

   public static void setDebug(boolean debug) {
      Core.debug = debug;
   }
}
