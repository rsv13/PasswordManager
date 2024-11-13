package passman;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class RandomPasswordGenerator {
    private Map<String, Account> passwords;

    public RandomPasswordGenerator() {
        this.passwords = new HashMap<>();
    }

    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    public void addWebsiteAccount(String username, String password, String websiteName, String websiteURL) {
        WebsiteAccount account = new WebsiteAccount(username, password, websiteName, websiteURL);
        passwords.put(websiteName, account);
    }

    public void addApplicationAccount(String username, String password, String appName) {
        ApplicationAccount account = new ApplicationAccount(username, password, appName);
        passwords.put(appName, account);
    }

    public void addGameAccount(String username, String password, String gameName, String gameDeveloper) {
        GameAccount account = new GameAccount(username, password, gameName, gameDeveloper);
        passwords.put(gameName, account);
    }

    public Account getAccount(String accountName) {
        return passwords.get(accountName);
    }
}
