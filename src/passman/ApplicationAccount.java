package passman;

import java.text.SimpleDateFormat;

public class ApplicationAccount extends Account {
    private String appName;

    public ApplicationAccount(String username, String password, String appName) {
        super(username, password);
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }
    
    @Override
    public String toCsvString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("Application,%s,%s,%s,%s,%s,%s\n",
                getUsername() != null ? getUsername() : "",
                getPassword() != null ? getPassword() : "",
                appName != null ? appName : "",
                dateFormatter.format(getDateCreated()),
                dateFormatter.format(getDateLastUpdated())
        );
    }
}
