package passman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class User {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private String idProof;
    private String idNumber;
    private List<Credential> credentials;

    public User() {
        this.credentials = new ArrayList<>();
    }

    public User(String firstName, String lastName, Date dateOfBirth, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.credentials = new ArrayList<>();
    }

    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastname() {
    	return lastName;
    }
    
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    
    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
    	return dateOfBirth;
    }
    
    public List<Credential> getCredentials() {
        return credentials;
    }
    
    private boolean isValidPassword(String password) {
    	String uppercase = "[A-Z]";
    	String lowercase = "[a-z]";
    	String numbers = "[0-9]";
    	String symbols = "[@#$%^&+=!]";
    	
    	String pass = "^(?=.*" + numbers + ")(?=.*" + lowercase + ")(?=.*" + uppercase + ")(?=.*" + symbols + ").{8,}$";

        return password.matches(pass);
    }
    
    private String promptUserForPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your password:");
        String password = scanner.nextLine();

        while (!isValidPassword(password)) {
            System.out.println("Invalid password. Please make sure it has at least 8 characters, an uppercase letter, a lowercase letter, a number, and a symbol.");
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
        }

        return password;
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("User Registration:");
        System.out.print("Enter First Name: ");
        this.firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        this.lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
        String dob = scanner.nextLine();
        try {
            this.dateOfBirth = new SimpleDateFormat("dd-mm-yyyy").parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Enter Phone Number: ");
        this.phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        this.email = scanner.nextLine();
        System.out.print("Do you want to set your own password? (yes/no): ");
        boolean setOwnPassword = scanner.nextLine().equalsIgnoreCase("yes");

        if (setOwnPassword) {
            this.password = promptUserForPassword();
        } else {
            this.password = RandomPasswordGenerator.generateRandomPassword(12);
            System.out.println("Your randomly generated password is: " + this.password);
        }

        System.out.println("User registered successfully!");
    }

    public void userActivities() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nUser Activities:");
            System.out.println("1. Create a new entry");
            System.out.println("2. Edit existing entry");
            System.out.println("3. Delete entry");
            System.out.println("4. Search for credentials");
            System.out.println("5. Sort passwords by last updated date");
            System.out.println("6. View all credentials");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createNewEntry();
                    break;
                case 2:
                    editExistingEntry();
                    break;
                case 3:
                    deleteEntry();
                    break;
                case 4:
                    searchCredentials();
                    break;
                case 5:
                    sortPasswordsByLastUpdatedDate();
                    break;
                case 6:
                    viewAllCredentials();
                    break;
                case 7:
                    System.out.println("Exiting User Activities. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public boolean login(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(email) && enteredPassword.equals(password);
    }

    public void createNewEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new entry:");
        System.out.println("Select type:");
        System.out.println("1. Application");
        System.out.println("2. Website");
        System.out.println("3. Game");
        System.out.print("Enter the number of your choice: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine(); 

        String type;
        String name;
        String username;

        switch (typeChoice) {
            case 1:
                type = "Application";
                System.out.print("Enter application name: ");
                name = scanner.nextLine();
                System.out.print("Enter application username: ");
                username = scanner.nextLine();
                System.out.println("Enter application password: ");
                break;
            case 2:
                type = "Website";
                System.out.print("Enter website name: ");
                name = scanner.nextLine();
                System.out.print("Enter website URL: ");
                String websiteURL = scanner.nextLine();
                System.out.print("Enter website username: ");
                username = scanner.nextLine();
                // additional website-specific logic
                break;
            case 3:
                type = "Game";
                System.out.print("Enter game name: ");
                name = scanner.nextLine();
                System.out.print("Enter game developer: ");
                String gameDeveloper = scanner.nextLine();
                System.out.print("Enter game username: ");
                username = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }
       

     // Ask the user if they want to set their own password or generate a random one
        System.out.print("Do you want to set your own password? (yes/no): ");
        String setPasswordChoice = scanner.nextLine().toLowerCase();

        String password;

        if ("yes".equals(setPasswordChoice)) {
            // If the user wants to set their own password, prompt for it
            System.out.print("Enter password (minimum 8 characters, at least one uppercase letter, one lowercase letter, one number, and one symbol): ");
            password = scanner.nextLine();

            // Validate the password
            while (!isValidPassword(password)) {
                System.out.println("Invalid password! Please try again.");
                System.out.print("Enter password: ");
                password = scanner.nextLine();
            }
        } else {
            // Generate a random password using your existing utility class
            System.out.print("Enter the length of the random password: ");
            int passwordLength = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            password = RandomPasswordGenerator.generateRandomPassword(passwordLength);
            System.out.println("Generated password: " + password);
        }

        Credential newCredential = new Credential(type, name, username, password);
        credentials.add(newCredential);
        
       

        System.out.println("New entry created successfully!");
    }

    public void editExistingEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Edit existing entry:");
        
        System.out.println("Select the entry to edit:");
        for (int i = 0; i < credentials.size(); i++) {
            System.out.println((i + 1) + ". " + credentials.get(i).getName());
        }
        
        System.out.print("Enter the number corresponding to the entry you want to edit: ");
        
        int entryNumber = scanner.nextInt();
        scanner.nextLine(); 
        
        if (entryNumber >= 1 && entryNumber <= credentials.size()) {
            Credential credentialToEdit = credentials.get(entryNumber - 1);
            
            System.out.println("Choose what to edit:");
            System.out.println("1. Name");
            System.out.println("2. Username");
            System.out.println("3. Password");
            
            int editChoice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (editChoice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    credentialToEdit.setName(newName);
                    break;
                case 2:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    credentialToEdit.setUsername(newUsername);
                    break;
                case 3:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    credentialToEdit.setPassword(newPassword);
                    break;
                default:
                    System.out.println("Invalid choice. No edits performed.");
            }
            
            credentialToEdit.setLastUpdated(new Date());
            System.out.println("Entry edited successfully!");
        } else {
            System.out.println("Invalid entry number. No edits performed.");
        }
    }



    public void deleteEntry() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Delete entry:");
        
        System.out.println("Select the entry to delete:");
        for (int i = 0; i < credentials.size(); i++) {
            System.out.println((i + 1) + ". " + credentials.get(i).getName());
        }
        
        System.out.print("Enter the number corresponding to the entry you want to delete: ");
        
        int entryNumber = scanner.nextInt();
        scanner.nextLine(); 
        
        if (entryNumber >= 1 && entryNumber <= credentials.size()) {
            Credential credentialToDelete = credentials.get(entryNumber - 1);
            credentials.remove(credentialToDelete);
            System.out.println("Entry '" + credentialToDelete.getName() + "' deleted successfully!");
        } else {
            System.out.println("Invalid entry number. No deletions performed.");
        }
    }


   public void searchCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        List<Credential> matchingCredentials = findCredentialsByKeyword(keyword);

        if (!matchingCredentials.isEmpty()) {
            System.out.println("Matching Credentials:");
            for (Credential credential : matchingCredentials) {
                System.out.println(credential);
            }
        } else {
            System.out.println("No matching credentials found.");
        }
    }

    public void sortPasswordsByLastUpdatedDate() {
        credentials.sort((c1, c2) -> c2.getLastUpdated().compareTo(c1.getLastUpdated()));
        System.out.println("Passwords sorted by last updated date:");
        for (Credential credential : credentials) {
            System.out.println(credential);
        }
    }
    
   public void viewAllCredentials() {
        if (!credentials.isEmpty()) {
            System.out.println("All Credentials:");
            for (Credential credential : credentials) {
                System.out.println(credential);
            }
        } else {
            System.out.println("No credentials available.");
        }
    }

    public Credential findCredentialByName(String name) {
        for (Credential credential : credentials) {
            if (credential.getName().equalsIgnoreCase(name)) {
                return credential;
            }
        }
        return null;
    }

   public List<Credential> findCredentialsByKeyword(String keyword) {
        List<Credential> matchingCredentials = new ArrayList<>();
        for (Credential credential : credentials) {
            if (credential.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingCredentials.add(credential);
            }
        }
        return matchingCredentials;
    }

    private static void authenticateUser(String username, String password, User user) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
        } else {
            System.out.println("Login failed. Invalid credentials.");
            user = null;
        }
    }
    
    public String toCsvString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return String.format("%s,%s,%s,%s,%s,%s",
                firstName, lastName, dateFormat.format(dateOfBirth), phoneNumber, email, password);
    }

    public static User fromCsvString(String csvString) {
        String[] fields = csvString.split(",");
        User user = new User();
        user.firstName = fields[0];
        user.lastName = fields[1];
        try {
            user.dateOfBirth = new SimpleDateFormat("dd-mm-yyyy").parse(fields[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.phoneNumber = fields[3];
        user.email = fields[4];
        user.password = fields[5];

        return user;
    }
}
