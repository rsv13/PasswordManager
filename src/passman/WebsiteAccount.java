package passman;

import java.text.SimpleDateFormat;

public class WebsiteAccount extends Account {
    private String websiteName;
    private String url;

    public WebsiteAccount(String username, String password, String websiteName, String websiteURL) {
        super(username, password);
        this.websiteName = websiteName;
        this.url = websiteURL;
    }

    public String getWebsiteName() {
        return websiteName;
    }
    
    public String getWebsiteURL() {
        return url;
    }

    @Override
    public String toCsvString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("Website,%s,%s,%s,%s\n",
                getUsername() != null ? getUsername() : "",
                getPassword() != null ? getPassword() : "",
                websiteName != null ? websiteName : "",
                url != null ? url : "",
                dateFormatter.format(getDateCreated()),
                dateFormatter.format(getDateLastUpdated())
        );
    }
}
