package fr.dd06.apis.minewebauth.auth.mineweb.utils;


import fr.dd06.apis.minewebauth.auth.exception.DataWrongException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class RegisterData {
   private static String readAll(Reader rd) throws IOException {
      StringBuilder sb = new StringBuilder();

      int cp;
      while((cp = rd.read()) != -1) {
         sb.append((char)cp);
      }

      return sb.toString();
   }

   public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
      URLConnection connection = (new URL(url)).openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      connection.connect();
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
   }

   public static void StartRegisterSessionLauncher() {
      try {
         StockData.SessionUserRegister = readJsonFromUrl(Get.getCore.getURLRoot() + "/auth/getDataLauncher?username=" + Core.Username + "&password=" + Core.Password);
      } catch (IOException var2) {
         var2.printStackTrace();
      } catch (JSONException var3) {
         var3.printStackTrace();
      }

      try {
         StockData.UsernameAuth = StockData.SessionUserRegister.get("pseudo").toString();
         StockData.UuidAuth = StockData.SessionUserRegister.get("auth-uuid").toString();
         StockData.accessTokenAuth = StockData.SessionUserRegister.get("auth-accessToken").toString();
         StockData.clientTokenAuth = StockData.SessionUserRegister.get("auth-clientToken").toString();
      } catch (JSONException var1) {
         var1.printStackTrace();
      }

   }

   public static void StartRegisterSessionIngame() throws DataWrongException {
      try {
         StockData.SessionUserRegister = readJsonFromUrl(Get.getCore.getURLRoot() + "/auth/getDataIngame?accessToken=" + Core.accessToken);
      } catch (IOException var2) {
         var2.printStackTrace();
      } catch (JSONException var3) {
         throw new DataWrongException("error_data");
      }

      try {
         StockData.UsernameAuth = StockData.SessionUserRegister.get("pseudo").toString();
         StockData.UuidAuth = StockData.SessionUserRegister.get("auth-uuid").toString();
         StockData.accessTokenAuth = StockData.SessionUserRegister.get("auth-accessToken").toString();
         StockData.clientTokenAuth = StockData.SessionUserRegister.get("auth-clientToken").toString();
      } catch (JSONException var1) {
         var1.printStackTrace();
      }

   }
}
