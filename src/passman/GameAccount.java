package passman;

import java.text.SimpleDateFormat;

public class GameAccount extends Account {
    private String gameName;
    private String gameDeveloper;

    public GameAccount(String username, String password, String gameName,String gameDeveloper) {
        super(username, password);
        this.gameName = gameName;
        this.gameDeveloper = gameDeveloper;
    }

    public String getGameName() {
        return gameName;
    }
    
    public String getGameDeveloper() {
        return gameDeveloper;
    }

    @Override
    public String toCsvString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("Game,%s,%s,%s,%s,%s\n",
                getUsername() != null ? getUsername() : "",
                getPassword() != null ? getPassword() : "",
                gameName != null ? gameName : "",
                gameDeveloper != null ? gameDeveloper : "",
                dateFormatter.format(getDateCreated()),
                dateFormatter.format(getDateLastUpdated())
        );
    }
}
