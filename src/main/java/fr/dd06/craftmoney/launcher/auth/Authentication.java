package fr.dd06.craftmoney.launcher.auth;

import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;
import fr.dd06.apis.mcauth.AuthPoints;
import fr.dd06.apis.mcauth.AuthenticationException;
import fr.dd06.apis.mcauth.Authenticator;
import fr.dd06.apis.mcauth.model.AuthAgent;
import fr.dd06.apis.mcauth.model.response.AuthResponse;
import fr.dd06.apis.mcauth.model.response.RefreshResponse;
import fr.dd06.apis.mclauncher.minecraft.auth.Account;
import fr.dd06.apis.mclauncher.minecraft.auth.AccountType;

public class Authentication {

    private static final String clientToken = "@craftmoneyToken@";
    private static Account account = new Account();



    public static void authWithMojang(String username, String password) throws AuthenticationException {

        Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

        AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, clientToken);

        account.connect(AccountType.MOJANG_ACCOUNT, response.getSelectedProfile().getName(), response.getSelectedProfile().getId(), response.getAccessToken(), clientToken);
    }

    public static void authWithMojang(String token) throws AuthenticationException {
        Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
        RefreshResponse response = authenticator.refresh(token, clientToken);
        account.connect(AccountType.MOJANG_ACCOUNT, response.getSelectedProfile().getName(), response.getSelectedProfile().getId(),
                response.getAccessToken(), clientToken);
    }

    public static void authWithCraftMoney(String username, String password) {

    }

    public static void authWithCraftMoney(String token) {

    }

    public static Account getAccount() {
        return account;
    }
    public static String getClientToken() {
        return clientToken;
    }
}
