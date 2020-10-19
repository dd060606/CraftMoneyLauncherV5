package fr.dd06.craftmoney.launcher.auth;

import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;
import fr.dd06.apis.javautils.java.util.crypter.JavaCrypt;
import fr.dd06.apis.mcauth.AuthPoints;
import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.apis.mcauth.Authenticator;
import fr.dd06.apis.mcauth.model.AuthAgent;
import fr.dd06.apis.mcauth.model.response.AuthResponse;
import fr.dd06.apis.mcauth.model.response.RefreshResponse;
import fr.dd06.apis.mclauncher.minecraft.auth.Account;
import fr.dd06.apis.mclauncher.minecraft.auth.AccountType;
import fr.dd06.apis.minewebauth.auth.exception.DataEmptyException;
import fr.dd06.apis.minewebauth.auth.exception.DataWrongException;
import fr.dd06.apis.minewebauth.auth.exception.ServerNotFoundException;
import fr.dd06.apis.minewebauth.auth.mineweb.AuthMineweb;
import fr.dd06.apis.minewebauth.auth.mineweb.utils.TypeConnection;
import fr.dd06.craftmoney.CraftMoneyLauncher;

import java.io.IOException;

public class Authentication {

    private static final String clientToken = "@craftmoneyToken@";
    private static Account account = new Account();




    public static void authWithMojang(String username, String password) throws AuthenticationException {

        Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

        AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, clientToken);

        account.connect(AccountType.MOJANG_ACCOUNT, response.getSelectedProfile().getName(), response.getSelectedProfile().getId(), response.getAccessToken(), clientToken);
    }

    public static void authWithMojang(String token, CraftMoneyLauncher main) throws AuthenticationException {
        Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
        RefreshResponse response = authenticator.refresh(token, clientToken);
        account.connect(AccountType.MOJANG_ACCOUNT, response.getSelectedProfile().getName(), response.getSelectedProfile().getId(),
                response.getAccessToken(), clientToken);

        main.getAccountDataConfig().reloadConfiguration();
        main.getAccountDataConfig().getConfiguration().put("token", account.getAccessToken());
        main.getAccountDataConfig().saveConfiguration();

    }

    public static void authWithCraftMoney(String username,String password) throws Exception{
        AuthMineweb.setTypeConnection(TypeConnection.launcher);
        AuthMineweb.setUrlRoot("https://craftmoney.fr");
        AuthMineweb.setUsername(username);
        AuthMineweb.setPassword(password);
        AuthMineweb.start();



    }
    public static void authWithCraftMoney(String token, CraftMoneyLauncher main) throws Exception{
        JavaCrypt crypt = new JavaCrypt();
        crypt.setCryptKey("fee23FAE3");
        AuthMineweb.setTypeConnection(TypeConnection.launcher);
        AuthMineweb.setUrlRoot("https://craftmoney.fr");
        AuthMineweb.setUsername(token.split(",")[0]);
        AuthMineweb.setPassword(crypt.decrypt(token.split(",")[1]));
        AuthMineweb.start();

    }



    public static Account getAccount() {
        return account;
    }
    public static String getClientToken() {
        return clientToken;
    }
}
