package passman;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Account {
    private String username;
    private String password;
    private Date dateCreated;
    private Date dateLastUpdated;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public Account(String username, String password, Date dateCreated, Date dateLastUpdated) {
        this.username = username;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateLastUpdated = dateLastUpdated;
    }

    public Account(String username, String password) {
        this(username, password, new Date(), new Date());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public SimpleDateFormat getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(SimpleDateFormat dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
        this.dateLastUpdated = new Date();
    }

    public abstract String toCsvString();

    public static Account fromCsvString(String csv) {
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastUpdated=" + dateLastUpdated +
                '}';
    }
}
