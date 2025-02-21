# Customer and Project Management System
## Overview
The **Customer and Project Management System** is a Java-based application
developed to efficiently manage customer data and their associated projects. It
is composed of several classes that work together to provide a user-friendly
interface for adding, updating, and tracking clients and projects. This system is
ideal for companies needing to maintain accurate records of customer
interactions, project statuses, and related details.
## Files Included
- **Client.java**
Defines the `Client` class, which represents a customer. This class stores
personal details such as name, surname, email, telephone number, address, and
maintains a list of projects associated with the client.
- **Project.java**
Defines the `Project` class, which encapsulates project-related information. It
holds details such as project name, type, URL, date, city, status, and price.
- **Gui.java**
Implements a graphical user interface (GUI) using Java Swing. It facilitates
the management of clients and their projects through tables, input forms, and
interactive dialogs, ensuring that the system is accessible to users with minimal
technical expertise.
## Detailed Class Descriptions
### `Client` Class
This class is responsible for managing all customer-related information.
- **Attributes:**
- `name` *(String)*: Customer's first name.
- `surname` *(String)*: Customer's last name.
- `email` *(String)*: Email address used for communication.
- `telephoneNumber` *(String)*: Contact phone number.
- `address` *(String)*: Residential or business address.
- `clientProjects` *(Vector<Project>)*: Collection of projects associated with
the customer.
- **Key Methods:**
- **Setters and Getters:**
Update and retrieve individual attributes.
- **setClientProjects(Project project):**
Adds a new project to the customer's project list.
- **removeClientProject(int index):**
Removes a project from the list based on its index.
- **toString() / toStringClient() / toStringClientProjects():**
Returns formatted strings containing customer details and their projects,
which is useful for debugging and generating reports.
### `Project` Class
This class manages all details pertinent to a project.
- **Attributes:**
- `name` *(String)*: Project name.
- `type` *(String)*: Category or type of the project.
- `url` *(String)*: Web link related to the project.
- `date` *(String)*: Date in the format `dd-mm-yyyy`.

- `city` *(String)*: Location of the project.
- `status` *(String)*: Current status, e.g., “Finished” or “Running”.
- `price` *(double)*: Financial cost associated with the project.
- **Key Methods:**
- **Setters and Getters:**
Methods to modify and access the project’s attributes.
- **toString():**
Provides a comprehensive string representation of the project details, useful
for display and logging.
### `Gui` Class
This class provides a fully interactive graphical interface using Java Swing
components.
- **Functionalities:**
- **Client Management:**
- **Adding Clients:** Users can input client details (name, surname, email,
telephone, address) via text fields. The system validates that no fields
are empty, checks the email format, ensures the telephone number consists
of exactly 10 digits, and verifies that both email and telephone are
unique.
- **Deleting Clients:** Clients can be removed by selecting a row in the
table and clicking the “Delete Client” button.
- **Project Management:**
- **Viewing Projects:** Double-clicking on a client opens a new window
displaying all associated projects.
- **Adding Projects:** Within the new window, users can add projects by
providing details such as project name, type, URL, date (with day, month,
and year spinners for validation), city, status (selectable as “Finished”
or “Running”), and price. The program validates the URL format (ensuring
it starts with “http://” or “https://”) and checks for valid date input
(accounting for leap years).
- **Deleting Projects:** Similar to client deletion, users can select a
project from the table and remove it with the provided button.
## Setup and Usage Instructions
### Prerequisites
- **Java Development Kit (JDK) 8 or higher**
Ensure that the JDK is installed and properly configured in your system's PATH.
- **Development Environment:**
A terminal or command prompt is required for compiling and executing the Java
files. A basic understanding of command-line operations is beneficial.
### Compilation
1. Open a terminal in the directory containing the files.
2. Compile the Java files using the following command:
```java
javac Client.java Project.java Gui.java
```
### Execution
To launch the graphical user interface, execute:
```java
java Gui
```
This will open the main window of the application, where you can manage clients
and projects interactively.

### Functional Overview
Input Validation:
The system rigorously checks input fields to prevent errors:
- **Email Validation**: Ensures that the email contains an “@” symbol.
- **Telephone Number**: Must be exactly 10 digits and unique across clients.
- **URL Validation**: Project URLs must begin with “http://” or “https://”.
- **Date Validation**: Uses spinner components to restrict date input and validates the day based on month and year, including leap year considerations.
#### Error Handling: 
If invalid input is detected or if a duplicate email/telephone number is found, the system will display a clear error message in a dialog box. This immediate feedback helps users correct mistakes on the fly.
#### User Interface: 
The application uses tables for displaying client and project data. Users can easily interact with data by selecting rows to perform deletion or by double-clicking to open a detailed project management window.

### Troubleshooting
#### Compilation Errors:
- Ensure that your file names match the public class names.
- Verify that you have installed JDK 8 or later.
- Check that all files (Client.java, Project.java, Gui.java) are in the same directory.
#### Runtime Errors:
- If the GUI does not display correctly, make sure that your system supports Java Swing.
- For errors related to invalid input, refer to the error messages provided by the application and correct the respective fields.
#### GUI-Specific Issues:
- **Input Field Resets**: After adding a client or project, input fields are automatically cleared. If they do not, ensure that your Java runtime environment is functioning correctly.
- **Window Management**: When opening the project management window, if it fails to appear, verify that your system’s window management settings are not interfering with Java Swing components.
#### Final Notes
This application has been designed with usability and reliability in mind. The
extensive input validations and error handling mechanisms are intended to prevent
common mistakes and to guide the user through the process of managing customer
and project data efficiently. Every effort has been made to ensure that a user,
even with minimal technical knowledge, can operate this system with full
confidence.
#### License
This project is released under the MIT License.
#### Author
Developed for the Customer and Project Management System.
