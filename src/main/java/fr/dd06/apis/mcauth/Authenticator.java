/*     */ package fr.dd06.apis.mcauth;
/*     */ 
/*     */ import com.google.gson.Gson;

import fr.dd06.apis.mcauth.model.AuthAgent;
import fr.dd06.apis.mcauth.model.AuthError;
import fr.dd06.apis.mcauth.model.request.AuthRequest;
import fr.dd06.apis.mcauth.model.request.InvalidateRequest;
import fr.dd06.apis.mcauth.model.request.RefreshRequest;
import fr.dd06.apis.mcauth.model.request.SignoutRequest;
import fr.dd06.apis.mcauth.model.request.ValidateRequest;
import fr.dd06.apis.mcauth.model.response.AuthResponse;
import fr.dd06.apis.mcauth.model.response.RefreshResponse;

/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import javax.net.ssl.HttpsURLConnection;
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
/*     */ public class Authenticator
/*     */ {
/*     */   public static final String MOJANG_AUTH_URL = "https://authserver.mojang.com/";
/*     */   private String authURL;
/*     */   private AuthPoints authPoints;
/*     */   
/*     */   public Authenticator(String authURL, AuthPoints authPoints) {
/*  79 */     this.authURL = authURL;
/*  80 */     this.authPoints = authPoints;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AuthResponse authenticate(AuthAgent agent, String username, String password, String clientToken) throws AuthenticationException {
/* 100 */     AuthRequest request = new AuthRequest(agent, username, password, clientToken);
/* 101 */     return (AuthResponse)sendRequest(request, AuthResponse.class, this.authPoints.getAuthenticatePoint());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RefreshResponse refresh(String accessToken, String clientToken) throws AuthenticationException {
/* 118 */     RefreshRequest request = new RefreshRequest(accessToken, clientToken);
/* 119 */     return (RefreshResponse)sendRequest(request, RefreshResponse.class, this.authPoints.getRefreshPoint());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate(String accessToken) throws AuthenticationException {
/* 136 */     ValidateRequest request = new ValidateRequest(accessToken);
/* 137 */     sendRequest(request, null, this.authPoints.getValidatePoint());
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
/*     */   
/*     */   public void signout(String username, String password) throws AuthenticationException {
/* 151 */     SignoutRequest request = new SignoutRequest(username, password);
/* 152 */     sendRequest(request, null, this.authPoints.getSignoutPoint());
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
/*     */   
/*     */   public void invalidate(String accessToken, String clientToken) throws AuthenticationException {
/* 166 */     InvalidateRequest request = new InvalidateRequest(accessToken, clientToken);
/* 167 */     sendRequest(request, null, this.authPoints.getInvalidatePoint());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object sendRequest(Object request, Class<?> model, String authPoint) throws AuthenticationException {
/*     */     String response;
/* 187 */     Gson gson = new Gson();
/*     */ 
/*     */     
/*     */     try {
/* 191 */       response = sendPostRequest(this.authURL + authPoint, gson.toJson(request));
/* 192 */     } catch (IOException e) {
/* 193 */       throw new AuthenticationException(new AuthError("Can't send the request : " + e.getClass().getName(), e.getMessage(), "Unknown"));
/*     */     } 
/*     */     
/* 196 */     if (model != null) {
/* 197 */       return gson.fromJson(response, model);
/*     */     }
/* 199 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String sendPostRequest(String url, String json) throws AuthenticationException, IOException {
/*     */     InputStream is;
/* 217 */     byte[] jsonBytes = json.getBytes("UTF-8");
/* 218 */     URL serverURL = new URL(url);
/* 219 */     HttpsURLConnection connection = (HttpsURLConnection)serverURL.openConnection();
/* 220 */     connection.setRequestMethod("POST");
/*     */ 
/*     */     
/* 223 */     connection.setDoOutput(true);
/* 224 */     connection.setRequestProperty("Accept-Charset", "UTF-8");
/* 225 */     connection.setRequestProperty("Content-Type", "application/json");
/* 226 */     connection.setRequestProperty("Content-Length", String.valueOf(jsonBytes.length));
/* 227 */     DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
/* 228 */     wr.write(jsonBytes, 0, jsonBytes.length);
/* 229 */     wr.flush();
/* 230 */     wr.close();
/*     */     
/* 232 */     connection.connect();
/*     */     
/* 234 */     int responseCode = connection.getResponseCode();
/*     */     
/* 236 */     if (responseCode == 204) {
/* 237 */       connection.disconnect();
/* 238 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 242 */     if (responseCode == 200) {
/* 243 */       is = connection.getInputStream();
/*     */     } else {
/* 245 */       is = connection.getErrorStream();
/*     */     } 
/*     */ 
/*     */     
/* 249 */     BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 250 */     String response = br.readLine();
/*     */     try {
/* 252 */       br.close();
/* 253 */     } catch (IOException e) {
/* 254 */       e.printStackTrace();
/*     */     } 
/* 256 */     connection.disconnect();
/*     */     
/* 258 */     while (response != null && response.startsWith("﻿")) {
/* 259 */       response = response.substring(1);
/*     */     }
/* 261 */     if (responseCode != 200) {
/* 262 */       Gson gson = new Gson();
/*     */       
/* 264 */       if (response != null && !response.startsWith("{")) {
/* 265 */         throw new AuthenticationException(new AuthError("Internal server error", response, "Remote"));
/*     */       }
/* 267 */       throw new AuthenticationException((AuthError)gson.fromJson(response, AuthError.class));
/*     */     } 
/*     */     
/* 270 */     return response;
/*     */   }
/*     */ }


/* Location:              C:\Users\dd06a\OneDrive\Bureau\Developpement\Launcher minecraft\New PvPClient\APIs\openauth-1.0.4.jar!\re\alwyn974\openauth\Authenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */