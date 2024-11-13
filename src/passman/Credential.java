package passman;

import java.util.Date;

public class Credential {
    private String type;
    private String name;
    private String username;
    private String password;
    private Date lastUpdated;

    public Credential(String type, String name, String username, String password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
        this.lastUpdated = new Date();
    }
    
    // Constructor for website credentials
    public Credential(String type, String name, String username, String password, String additionalInfo) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Constructor for game credentials
    public Credential(String type, String name, String username, String password, String additionalInfo, String developer) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
