package passman;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Admin {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String idProof;
    private String idNumber;
    private String email;
    private String password;
    private List<User> users;

    public Admin(String firstName, String lastName, Date dob, String phoneNumber, String idProof, String idNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.phoneNumber = phoneNumber;
        this.idProof = idProof;
        this.idNumber = idNumber;
        this.email = email;
        this.password = password;
        this.users = new ArrayList<>();
    }


    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(String enteredPassword) {
        return enteredPassword.equals(this.password);
    }


    public boolean login(String enteredEmail, String enteredPassword) {
        return enteredEmail.equals(email) && enteredPassword.equals(password);
    }
    
   
    public void viewAllCredentials(PassManDatabase database) {
        System.out.println("All Credentials:");

        for (User user : users) {
            List<Credential> credentials = user.getCredentials();
            for (Credential credential : credentials) {
                System.out.println("User: " + user.getUsername());
                System.out.println("Type: " + credential.getType());
                System.out.println("Name: " + credential.getName());
                System.out.println("Username: " + credential.getUsername());
                System.out.println("Password: " + credential.getPassword());
                System.out.println("Last Updated: " + credential.getLastUpdated());
                System.out.println("--------");
            }
        }

        if (users.isEmpty()) {
            System.out.println("No users available.");
        }
    }

    public void editCredential(PassManDatabase database, String username) {
        User user = database.getUserByUsername(username);

        if (user != null) {
            user.editExistingEntry();
        } else {
            System.out.println("User not found.");
        }
    }

    public void deleteCredential(PassManDatabase database, String username) {
        User user = database.getUserByUsername(username);

        if (user != null) {
            user.deleteEntry();
        } else {
            System.out.println("User not found.");
        }
    }
    
    

    public void deleteUser(PassManDatabase database, String username) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(username)) {
                iterator.remove();
                database.removeUser(user);
                System.out.println("User deleted successfully: " + username);
                return;
            }
        }

        System.out.println("User not found.");
    }
    
    public void registerUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User registered successfully: " + newUser.getUsername());

        PassManDatabase passManDatabase = null;
		passManDatabase = new PassManDatabase();
        passManDatabase.addUser(newUser);
    }

    private boolean isValidPassword(String password) {
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
    
    public String toCsvString() {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                firstName, lastName, dateFormat.format(dateOfBirth),
                phoneNumber, idProof, idNumber, email, password);
    }
    
    public static Admin fromCsvString(String csvString) {
        String[] tokens = csvString.split(",");
        
        if (tokens.length != 8) {
            return null;
        }

        String firstName = tokens[0];
        String lastName = tokens[1];
        String dob = tokens[2];
        String phoneNumber = tokens[3];
        String idProof = tokens[4];
        String idNumber = tokens[5];
        String email = tokens[6];
        String password = tokens[7];

        Date dateOfBirth = parseDate(dob, "dd-mm-yyyy");

        return new Admin(firstName, lastName, dateOfBirth, phoneNumber, idProof, idNumber, email, password);
    }

    private static Date parseDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
           
            System.err.println("Error parsing date. Using default date.");
            return new Date();
        }
    }
}
