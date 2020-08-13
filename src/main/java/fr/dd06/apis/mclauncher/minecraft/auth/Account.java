package fr.dd06.apis.mclauncher.minecraft.auth;

public class Account {
	private String username;
	private String uuid;
	private String accessToken;
	private String clientToken;
	private AccountType accountType;
	private boolean logged = false;
	
	
	public void connect(AccountType accountType, String username, String uuid, String accesToken, String clientToken) {
		this.accountType = accountType;
		this.username = username;
		this.uuid = uuid;
		this.accessToken = accesToken;
		this.clientToken = clientToken;
		if(this.username != null && this.uuid !=null && this.accessToken != null) {
			this.logged = true;
		}
		else {
			this.logged = false;
		}
	}
	
	public void connect(AccountType accountType, String username, String uuid, String accesToken) {
		this.accountType = accountType;
		this.username = username;
		this.uuid = uuid;
		this.accessToken = accesToken;
		if(this.username != null && this.uuid !=null && this.accessToken != null) {
			this.logged = true;
		}
		else {
			this.logged = false;
		}
	}
	public void disconnect() {
		this.username = null;
		this.accountType = null;
		this.uuid = null;
		this.accessToken = null;
		this.clientToken = null;
		this.logged = false;
	}
	
	public String getUsername() {
		return username;
	}
	public String getUUID() {
		return uuid;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public String getClientToken() {
		return clientToken;
	}
	public boolean isMojangAccount() {
		if(this.accountType == AccountType.MOJANG_ACCOUNT) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isLogged() {
		return logged;
	}
	
}
