# Password Manager

## Overview

The **Password Manager** is a Java-based application designed to securely manage and store user credentials. It provides functionality for both regular users and admins, allowing them to register, log in, manage credentials, and perform administrative tasks such as viewing users and credentials. The application ensures that sensitive information is protected by strong password policies and encryption.

This project was developed as part of a university course and aims to provide a simple yet secure solution for managing passwords and sensitive data.

## Features

- **User Registration and Login**: Allows users to register and log in securely using their email and password.
- **Admin Registration and Login**: Provides a separate interface for admin users to manage users and credentials.
- **Password Generation**: Supports the generation of strong passwords and the ability for users to create their own passwords.
- **Credential Management**: Users can create, edit, delete, and view their credentials.
- **Admin Dashboard**: Admins can manage all user credentials, including the ability to view, edit, and delete them, as well as manage users.
- **Password Validation**: Ensures passwords meet strength requirements, including length, uppercase, lowercase, numbers, and special characters.

## Technologies Used

- **Java**: The primary programming language for building the application.
- **SimpleDateFormat**: Used for handling and parsing date inputs.
- **Custom Database**: A simple in-memory database simulation for storing user and admin data.

## Installation

1. **Clone the repository** to your local machine:
    ```bash
    git clone https://github.com/rsv13/Password-Manager.git
    ```

2. **Compile and run the application**:
    - Open the project folder in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
    - Build and run the `Passman` class.

## Getting Started

When the application starts, the user is presented with a menu where they can choose from the following options:

1. **Register as a User**: Create a new user account.
2. **Register as an Admin**: Create a new admin account with enhanced privileges.
3. **Login as a User**: Access your user account by logging in with your email and password.
4. **Login as an Admin**: Access the admin dashboard to manage users and credentials.

After logging in, both users and admins have specific functionalities based on their roles. Users can create, edit, and delete their credentials, while admins have access to a broader range of features including managing other users and viewing credentials.

### Admin Functions:
- View the list of registered users.
- View the list of other admins.
- View and manage user credentials.
- Delete users and their credentials.

### User Functions:
- Add, view, edit, and delete personal credentials (e.g., usernames and passwords for websites, apps, etc.).

## Code Structure

- **Passman.java**: The main class containing the application logic, user and admin authentication, and the menu system.
- **User.java**: A class that represents a regular user, with functionality for registering and managing credentials.
- **Admin.java**: A class that extends User, adding extra functionality for admin users, such as managing other users and their credentials.
- **PassManDatabase.java**: A class that simulates an in-memory database to store user and admin information, along with their credentials.
- **RandomPasswordGenerator.java**: A utility class to generate random passwords for users and admins.
  
## How It Works

1. **User Registration**: Users are prompted to provide their details (name, email, password, etc.). After registration, they are able to manage their credentials.
2. **Admin Registration**: Admins are asked to provide their details along with optional ID proof. After registering, they have access to additional features like managing users and credentials.
3. **Credential Management**: Users can store their credentials in the system, and these credentials are kept secure through the implementation of password policies.
4. **Login**: Both users and admins must log in using their email and password. Admins have additional privileges for managing the system.
5. **Password Generation**: If users want a secure password, they can generate one through the application, which follows strong security practices.

## User Experience

Upon launching the application, users and admins will be greeted with a simple text-based menu. The interface guides them through the different options, allowing them to register, log in, and manage their data in an easy-to-use manner.

## Future Enhancements

- **Encryption**: Adding encryption to store passwords securely.
- **Database Integration**: Integrating with a real database (such as MySQL or MongoDB) for persistent storage.
- **Graphical User Interface (GUI)**: Implementing a GUI for better user experience using frameworks like JavaFX.
- **Cloud Synchronization**: Allowing users to sync their passwords across multiple devices.

## Conclusion

This Password Manager project demonstrates a practical approach to storing and managing sensitive information with user and admin functionalities. It combines the use of Java's core libraries with secure password policies to ensure the safety of user data.