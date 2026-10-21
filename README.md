# Password Manager CLI

An educational Java console application exploring **object-oriented programming, user/admin workflows, credential management and file handling**.

**Project status:** learning prototype. Credential encryption is a planned enhancement. Do not use this application to store real passwords or sensitive personal information.

## Features

- User and administrator registration and login flows
- Create, view, edit and delete credential records
- Separate user and administrator menus
- Password generation and input validation
- Account models for websites, applications and games

These features demonstrate application structure and CLI interaction; they do not establish production-grade credential security.

## Technology and structure

Built with Java. The source package is in `src/passman/`.

| File | Responsibility |
| --- | --- |
| `Passman.java` | Console entry point and menu workflow |
| `User.java` and `Admin.java` | User/admin behaviour |
| `PassManDatabase.java` | Data management component |
| `Credential.java` | Credential model |
| `Account.java`, `WebsiteAccount.java`, `ApplicationAccount.java`, `GameAccount.java` | Account types |
| `RandomPasswordGenerator.java` | Password-generation utility |

## Run locally

1. Install a Java JDK and a Java IDE such as Eclipse or IntelliJ IDEA.
2. Clone the repository:

~~~bash
git clone https://github.com/rsv13/PasswordManager.git
cd PasswordManager
~~~

3. Import the project into the IDE and configure its JDK/source path. Eclipse project metadata is included.
4. Run the `passman.Passman` main class.
5. Use fictional users and credentials to explore the registration, login and management menus.

## Learning focus

- Modelling related entities with Java classes and inheritance
- Separating account, credential and user responsibilities
- Building menu-driven workflows and validating input
- Understanding the additional controls required before a credential tool could be used in practice

## Planned enhancements

- Credential encryption and a reviewed authentication/storage design
- Integration with a database
- A graphical interface
- Automated tests for validation and account workflows

No application functionality has been changed by this documentation update.
