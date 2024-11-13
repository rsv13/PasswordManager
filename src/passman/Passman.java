package passman;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Passman {
    private static Scanner scanner = new Scanner(System.in);
    private static PassManDatabase database = new PassManDatabase();
    private static User currentUser;
    private static Admin currentAdmin;

    public static void main(String[] args) {

        displayLogo();

        int choice;
        do {
            System.out.println("Welcome to the Password Manager App!");
            System.out.println("How would you like to proceed today?");
            System.out.println("1. Register as a User");
            System.out.println("2. Register as an Admin");
            System.out.println("3. Login as a User");
            System.out.println("4. Login as an Admin");
            System.out.println("5. Exit");

            System.out.print("Enter the number of your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    registerAdmin();
                    break;
                case 3:
                    loginUser(false);
                    break;
                case 4:
                    loginUser(true);
                    break;
                case 5:
                    System.out.println("Exiting the Password Manager App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void registerUser() {
        User newUser = new User();
        newUser.register();
        database.addUser(newUser);
        currentUser = newUser;
        System.out.println("User registered successfully. Welcome, " + newUser.getUsername() + "!");
        currentUser.userActivities();
    }

    private static void loginUser(boolean isAdmin) {
        System.out.println("Enter your credentials to login:");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (isAdmin) {
            System.out.println("Attempting to authenticate admin...");
            currentAdmin = database.authenticateAdmin(email, password);
            if (currentAdmin != null) {
                System.out.println("Login successful. Welcome, " + currentAdmin.getEmail() + "!");
                adminMenu(currentAdmin);
            } else {
                System.out.println("Admin login failed. Invalid credentials.");
            }
        } else {
            System.out.println("Attempting to authenticate user...");
            currentUser = database.authenticateUser(email, password);
            if (currentUser != null) {
                System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                currentUser.userActivities();
            } else {
                System.out.println("User login failed. Invalid credentials.");
            }
        }
    }

    private static void adminMenu(Admin admin) {
        int choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1. View User List");
            System.out.println("2. View Admin List");
            System.out.println("3. View All Credentials");
            System.out.println("4. Edit Credential");
            System.out.println("5. Delete Credential");
            System.out.println("6. Delete User");
            System.out.println("7. Logout");

            System.out.print("Enter the number of your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewUserList();
                    break;
                case 2:
                    viewAdminList();
                    break;
                case 3:
                    admin.viewAllCredentials(database);
                    break;
                case 4:
                    System.out.print("Enter the username of the user whose credential you want to edit: ");
                    String usernameToEdit = scanner.nextLine();
                    admin.editCredential(database, usernameToEdit);
                    break;
                case 5:
                    System.out.print("Enter the username of the user whose credential you want to delete: ");
                    String usernameToDelete = scanner.nextLine();
                    admin.deleteCredential(database, usernameToDelete);
                    break;
                case 6:
                    System.out.print("Enter the username of the user you want to delete: ");
                    String userToDelete = scanner.nextLine();
                    admin.deleteUser(database, userToDelete);
                    break;
                case 7:
                    System.out.println("Logging out. Goodbye, " + admin.getEmail() + "!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void registerAdmin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Registration:");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
        String dobString = scanner.nextLine();

        // Parse the dobString into a Date object
        Date dob = null;
        try {
            dob = new SimpleDateFormat("dd-mm-yyyy").parse(dobString);
        } catch (ParseException e) {
            System.out.println("Error parsing the date of birth. Please enter it in the correct format (dd-mm-yyyy).");
        }

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // Prompt whether to submit ID proof
        System.out.print("Do you want to submit ID proof? (yes/no): ");
        String submitIdProofChoice = scanner.nextLine();

        String idProofType = null;
        String idNumber = null;

        if ("yes".equalsIgnoreCase(submitIdProofChoice)) {
            System.out.println("Select ID proof type:");
            System.out.println("1. Passport");
            System.out.println("2. Driver's License");
            System.out.println("3. Military ID");
            System.out.print("Enter the number of your choice: ");
            int idProofTypeChoice = scanner.nextInt();
            scanner.nextLine();

            switch (idProofTypeChoice) {
                case 1:
                    idProofType = "Passport";
                    break;
                case 2:
                    idProofType = "Driver's License";
                    break;
                case 3:
                    idProofType = "Military ID";
                    break;
                default:
                    System.out.println("Invalid choice. ID proof not submitted.");
                    return; // Exit the method
            }

            System.out.print("Enter ID Number: ");
            idNumber = scanner.nextLine();
        } else if ("no".equalsIgnoreCase(submitIdProofChoice)) {
            System.out.println("You chose not to submit ID proof.");
            // Provide options to go back to the main menu or exit the app
            System.out.println("1. Go back to the main menu and register as a normal user");
            System.out.println("2. Exit the app");
            System.out.print("Enter the number of your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Return to registration menu
                    System.out.println("Going back to the main menu to register as a normal user...");
                    main(null);
                    break;
                case 2:
                    // Exit app
                    System.out.println("Exiting the app...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Exiting the app...");
                    System.exit(0);
            }
        } else {
            System.out.println("Invalid choice. ID proof not submitted.");
            return;
        }

        System.out.print("Do you want to enter your own password? (yes/no): ");
        String enterOwnPasswordChoice = scanner.nextLine();

        String password = null;

        if ("yes".equalsIgnoreCase(enterOwnPasswordChoice)) {
            // Prompt the user to enter their own password
            password = promptUserForPassword();
        } else if ("no".equalsIgnoreCase(enterOwnPasswordChoice)) {
            // Generate a random password
            password = generateRandomPassword();
            System.out.println("Generated password: " + password);
        } else {
            System.out.println("Invalid choice. Exiting the app...");
            System.exit(0); // Terminate the program
        }

        Admin newAdmin = new Admin(firstName, lastName, dob, phoneNumber, idProofType, idNumber, email, password);
        database.addAdmin(newAdmin);
        System.out.println("Admin registered successfully: " + email);
        adminMenu(newAdmin);
    }

    private static String promptUserForPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your password:");

        String password = scanner.nextLine();

        while (!isValidPassword(password)) {
            System.out.println(
                    "Invalid password. Please make sure it has at least 8 characters, an uppercase letter, a lowercase letter, a number, and a symbol.");
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
        }

        return password;
    }

    private static boolean isValidPassword(String password) {
        // Password validation criteria using a regular expression
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(regex);
    }

    // Example method to generate a random password
    private static String generateRandomPassword() {
        return RandomPasswordGenerator.generateRandomPassword(12); // Adjust the length as needed
    }

    public void viewAllCredentials() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            List<Credential> credentials = user.getCredentials();

            System.out.println("All Credentials:");

            for (Credential credential : credentials) {
                System.out.println("Type: " + credential.getType());
                System.out.println("Name: " + credential.getName());
                System.out.println("Username: " + credential.getUsername());
                System.out.println("Password: " + credential.getPassword());
                System.out.println("Last Updated: " + credential.getLastUpdated());
                System.out.println("--------");
            }

            if (credentials.isEmpty()) {
                System.out.println("No credentials available.");
            }
        } else {
            System.out.println("Invalid user type for viewing credentials.");
        }
    }

    private static void displayLogo() {
        System.out.println("    ____               __  __             ");
        System.out.println("   |  _ \\ __ _ ___ ___|  \\/  | __ _ _ __  ");
        System.out.println("   | |_) / _` / __/ __| |\\/| |/ _` | '_ \\ ");
        System.out.println("   |  __/ (_| \\__ \\__ \\ |  | | (_| | | | |");
        System.out.println("   |_|   \\__,_|___/___/_|  |_\\\\__,_|_| |_|");
        System.out.println();
    }

    private static void createNewCredential() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            user.createNewEntry();
        } else {
            System.out.println("Invalid user type for creating credentials.");
        }
    }

    private static void editCredential() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            user.editExistingEntry();
        } else {
            System.out.println("Invalid user type for editing credentials.");
        }
    }

    private static void deleteCredential() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            user.deleteEntry();
        } else {
            System.out.println("Invalid user type for deleting credentials.");
        }
    }

    private static void searchCredentials() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            user.searchCredentials();
        } else {
            System.out.println("Invalid user type for searching credentials.");
        }
    }

    private static void sortCredentials() {
        if (currentUser instanceof User) {
            User user = (User) currentUser;
            user.sortPasswordsByLastUpdatedDate();
        } else {
            System.out.println("Invalid user type for sorting credentials.");
        }
    }

    private static void viewUserList() {
        List<User> userList = database.getAllUsers();

        if (!userList.isEmpty()) {
            System.out.println("List of Users:");

            for (User user : userList) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Fullname: " + user.getFirstName() + user.getLastname());
                System.out.println("Email ID:" + user.getPhoneNumber());
                System.out.println("Date of Birth:" + user.getDateOfBirth());
                System.out.println("--------");
            }
        } else {
            System.out.println("No users found.");
        }
    }

    private static void viewAdminList() {
        List<Admin> admins = database.getAdmins();

        if (admins.isEmpty()) {
            System.out.println("No admins found.");
        } else {
            System.out.println("List of Admins:");
            for (Admin admin : admins) {
                System.out.println("Admin ID: " + admin.getIdProof());
                System.out.println("Name: " + admin.getFirstName() + admin.getLastName());
                System.out.println("Date of Birth: " + admin.getDateOfBirth());
                System.out.println("Phone Number: " + admin.getPhoneNumber());
                System.out.println("--------");
            }
        }
    }
}