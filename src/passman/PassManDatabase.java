package passman;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassManDatabase {
    private static final String USER_FILE_PATH = "users.csv";
    private static final String ADMIN_FILE_PATH = "admins.csv";
    private static final String WEBSITES_FILE_PATH = "websites.csv";
    private static final String APPLICATIONS_FILE_PATH = "applications.csv";
    private static final String GAMES_FILE_PATH = "games.csv";

    private List<User> users;
    private List<Admin> admins;
    private List<WebsiteAccount> websites;
    private List<ApplicationAccount> applications;
    private List<GameAccount> games;
    

    public PassManDatabase() {
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.websites = new ArrayList<>();
        this.applications = new ArrayList<>();
        this.games = new ArrayList<>();
        
        createFilesIfNotExist();
        loadUsersFromCsv();
        loadAdminsFromCsv();
        loadWebsitesFromCsv(); 
        loadApplicationsFromCsv();  
        loadGamesFromCsv(); 
        
    }
    
    public List<User> getUsers() {
        return users;
    }

    public List<Admin> getAdmins() {
        return admins;
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public List<WebsiteAccount> getWebsites() {
        return websites;
    }

    public List<ApplicationAccount> getApplications() {
        return applications;
    }

    public List<GameAccount> getGames() {
        return games;
    }
    
    public void addWebsite(WebsiteAccount website) {
        websites.add(website);
        saveWebsitesToCsv();
    }

    public void addApplication(ApplicationAccount application) {
        applications.add(application);
        saveApplicationsToCsv();
    }

    public void addGame(GameAccount game) {
        games.add(game);
        saveGamesToCsv();
    }
    
    private void createFilesIfNotExist() {
        try {
            createFileIfNotExist(USER_FILE_PATH);
            createFileIfNotExist(ADMIN_FILE_PATH);
            createFileIfNotExist(WEBSITES_FILE_PATH);
            createFileIfNotExist(APPLICATIONS_FILE_PATH);
            createFileIfNotExist(GAMES_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error creating files: " + e.getMessage());
        }
    }

    public User authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.login(username, password)) {
            return user;
        }
        return null;
    }

    public Admin authenticateAdmin(String username, String password) {
        Admin admin = getAdminByUsername(username);
        if (admin != null && admin.login(username, password)) {
            return admin;
        }
        return null;
    }

    public void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            System.out.println("User removed successfully: " + user.getUsername());
        } else {
            System.out.println("User not found in the database.");
        }
    }
    
    private boolean isValidPassword(String password) {
        // Checking if the password has valid characters or not.
        String check = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(check);
    }

    private String promptUserForPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your password:");

        String password = scanner.nextLine();

        while (!isValidPassword(password)) {
            System.out.println("Invalid password. Please make sure it has at least 8 characters, an uppercase letter, a lowercase letter, a number, and a symbol.");
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
        }

        return password;
    }

    private void createFileIfNotExist(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file: " + filePath);
            }
        }
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToCsv();
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
        saveAdminsToCsv();
    }
    
    

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(username)) {
                return admin;
            }
        }
        return null;
    }
  

    public void saveUsersToCsv() {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH, false)) {
            for (User user : users) {
                writer.append(user.toCsvString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAdminsToCsv() {
        try (FileWriter writer = new FileWriter(ADMIN_FILE_PATH, false)) {
            for (Admin admin : admins) {
                writer.append(admin.toCsvString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    private void loadUsersFromCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromCsvString(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAdminsFromCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Admin admin = Admin.fromCsvString(line);
                if (admin != null) {
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveWebsitesToCsv() {
        try (FileWriter writer = new FileWriter(WEBSITES_FILE_PATH)) {
            for (WebsiteAccount website : websites) {
                writer.write(website.toCsvString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveApplicationsToCsv() {
        try (FileWriter writer = new FileWriter(APPLICATIONS_FILE_PATH)) {
            for (ApplicationAccount application : applications) {
                writer.write(application.toCsvString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveGamesToCsv() {
        try (FileWriter writer = new FileWriter(GAMES_FILE_PATH)) {
            for (GameAccount game : games) {
                writer.write(game.toCsvString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadWebsitesFromCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(WEBSITES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.fromCsvString(line);
                if (account instanceof WebsiteAccount) {
                    websites.add((WebsiteAccount) account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void loadApplicationsFromCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPLICATIONS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.fromCsvString(line);
                if (account instanceof ApplicationAccount) {
                    applications.add((ApplicationAccount) account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void loadGamesFromCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GAMES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = Account.fromCsvString(line);
                if (account instanceof GameAccount) {
                    games.add((GameAccount) account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}