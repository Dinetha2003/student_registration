package org.example;
import java.sql.*;
import java.util.*;

public class StudentManagement {
    //creating the select method for option2
    public static void selectAllRecords() throws SQLException {
        final String SELECT_ALL_STUDENTS = "SELECT * FROM student;";
        // Making a Connection and preparing statement
        try (Connection connection = JDBCUtils.getConnection();
             // Creating a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            // Executing the query and getting the result set
            ResultSet rs = preparedStatement.executeQuery();
            // Processing the result set
            while (rs.next()) {
                String StId = rs.getString("StId");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Major = rs.getString("Major");
                String Email = rs.getString("Email");
                int PNumber = rs.getInt("PNumber");

                System.out.println(StId + " | " + FName + " | " + LName + " | " + Major + " | " + Email + " | " + PNumber);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    //Inserting method-student table
    public static void insertRecord() {
        final String INSERT_STUDENTS_SQL = "INSERT INTO student(StId, FName, LName, Major, Email, PNumber) VALUES (?,?,?,?,?,?);";
        //Getting users Student ID
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        String StId = sc.nextLine().trim();
        while (!StId.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Student ID: ");
            StId = sc.nextLine().trim();
        }

        //getting the users first name
        System.out.print("Enter First Name: ");
        String FName = sc.nextLine().trim();
        while (!FName.matches("^[A-Za-z ]+$")) {//checks whether the first name is a string
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter First Name: ");
            FName = sc.nextLine().trim();
        }

        //getting the users last name
        System.out.print("Enter Last Name: ");
        String LName = sc.nextLine().trim();
        while (!LName.matches("^[A-Za-z ]+$")) {//checks whether the last name is a string
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Last Name: ");
            LName = sc.nextLine().trim();
        }

        //getting the users Major
        System.out.print("Enter Major: ");
        String Major = sc.nextLine().trim();
        while (!Major.matches("^[A-Za-z ]+$")) {//checks whether Major is a String
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Major: ");
            Major = sc.nextLine().trim();
        }

        //Getting the users Email
        String Email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter the Email: ");
            Email = sc.nextLine().trim();

            if (Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {//checks whether the email is a valid email
                validEmail = true;
            } else {
                System.out.println("Invalid email format! Please enter a valid email");
            }
        }

        //Getting the users Phone number
        int PNumber = 0;
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            try {
                System.out.print("Enter the phone number: ");
                PNumber = sc.nextInt();
                sc.nextLine();
                //checks whether the Phone number is a valid positive integer
                if (PNumber > 0) {
                    validPhoneNumber = true;
                } else {
                    System.out.println("Please enter a valid phone number without other characters");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid phone number.");
                sc.nextLine();
            }
        }

        // Making a Connection and inserting data
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {

            preparedStatement.setString(1, StId);
            preparedStatement.setString(2, FName);
            preparedStatement.setString(3, LName);
            preparedStatement.setString(4, Major);
            preparedStatement.setString(5, Email);
            preparedStatement.setInt(6, PNumber);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    //updating method-student table
    public static void updateRecord() throws SQLException {
        final String UPDATE_STUDENTS_SQL = "UPDATE student SET StId = ?, FName = ?, LName = ?, Major = ?, Email = ?, PNumber = ? WHERE StId = ?";
        Scanner scanner = new Scanner(System.in);

        // Asking user for old student ID for the update
        System.out.print("Enter Student Old ID: ");
        String oldStId = scanner.nextLine().trim();
        while (!oldStId.matches("^[a-zA-Z0-9]+$")) {//checks whether the Old Student ID is valid
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Student Old ID: ");
            oldStId = scanner.nextLine().trim();// Store old ID before preparing the statement
        }

        //Getting users Student New ID
        System.out.print("Enter Student New ID: ");
        String StId = scanner.nextLine().trim();
        while (!StId.matches("^[a-zA-Z0-9]+$")) {//checks whether the New Student ID is valid
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Student New ID: ");
            StId = scanner.nextLine().trim();}

        //Getting the users last name
        System.out.print("Enter Last Name: ");
        String LName = scanner.nextLine().trim();
        while (!LName.matches("^[A-Za-z ]+$")) {//checks whether the last name is a string
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Last Name: ");
            LName = scanner.nextLine().trim();
        }
        //getting the users first name
        System.out.print("Enter First Name: ");
        String FName = scanner.nextLine().trim();
        while (!FName.matches("^[A-Za-z ]+$")) {//checks whether the first name is a string
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter First Name: ");
            FName = scanner.nextLine().trim();
        }
        //getting the users Major
        System.out.print("Enter Major: ");
        String Major = scanner.nextLine().trim();
        while (!Major.matches("^[A-Za-z ]+$")) {//checks whether Major is a String
            System.out.println("Invalid input! Please enter only letters.");
            System.out.print("Enter Major: ");
            Major = scanner.nextLine().trim();
        }

        //Getting the users Email
        String Email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter the Email: ");
            Email = scanner.nextLine().trim();

            if (Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {//checks whether the email is a valid email
                validEmail = true;
            } else {
                System.out.println("Invalid email format! Please enter a valid email");
            }
        }

        //Getting the users Phone number
        int PNumber = 0;
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            try {
                System.out.print("Enter the phone number: ");
                PNumber = scanner.nextInt();
                scanner.nextLine();
                //checks whether the Phone number is a valid positive integer
                if (PNumber > 0) {
                    validPhoneNumber = true;
                } else {
                    System.out.println("Please enter a valid phone number without other characters");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid phone number.");
                scanner.nextLine();
            }
        }

        // Making a Connection and preparing statement
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL)) {

            // Setting parameters for the update query
            preparedStatement.setString(1, StId);
            preparedStatement.setString(2, FName);
            preparedStatement.setString(3, LName);
            preparedStatement.setString(4, Major);
            preparedStatement.setString(5, Email);
            preparedStatement.setInt(6, PNumber);
            preparedStatement.setString(7, oldStId); // Setting the WHERE clause

            // Executing the update query
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully.");
            } else {
                System.out.println("No student found with the given old ID.");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    //deleting method-student table
    public static void deleteRecord() {
        final String DELETE_STUDENT_SQL = "DELETE FROM student WHERE StId = ?;";
        Scanner scanner = new Scanner(System.in);

        // Making a Connection and preparing statement
        try ( Connection connection = JDBCUtils.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            System.out.print("Enter Student ID to delete: ");
            String StId = scanner.nextLine();

            // Setting parameter
            preparedStatement.setString(1, StId);

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Checking if the record was deleted
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with StId: " + StId);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public static class Main {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);
                // Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("\nSelect your purpose and input the corresponding number:");
                    System.out.println("1. Insert Student into database");
                    System.out.println("2. Show all records in Student table");
                    System.out.println("3. Update Student record");
                    System.out.println("4. Delete Student record");
                    System.out.println("5. Exit");

                   // int choice = scanner.nextInt();
                    int choice;
                    boolean validchoice;
                    do {
                        try {
                            System.out.print("Enter your choice here: ");
                            choice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            // Check if the number is within the valid range (1-100)
                            validchoice = (choice >= 1 && choice <= 5);

                            if (!validchoice) {
                                System.out.println("Please enter valid choices");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a numeric point.");
                            scanner.nextLine(); // Clear the invalid input
                            validchoice = false;
                            choice = -1; // Assign an invalid value to keep the loop running
                        }
                    } while (!validchoice);

                    if (choice == 1) {
                        try {
                            StudentManagement.insertRecord();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                    else if (choice == 2) {
                        try {
                            System.out.println("Showing all records of the Student table:");
                            StudentManagement.selectAllRecords();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }}
                    else if (choice == 3) {
                        try {
                            StudentManagement.updateRecord();
                        } catch (SQLException e) {
                            e.printStackTrace();}}
                    else if (choice == 4) {try {
                            StudentManagement.deleteRecord();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }}
                    else if (choice == 5) {
                        System.out.println("Exiting program...");
                        scanner.close();
                        return; // Exit main method
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                }
            } catch(NoSuchElementException e){
                System.out.println("Error: No input detected. Please provide valid input.");
            } catch(IllegalStateException e) {
                System.out.println("Error: Scanner is closed. Restart the program.");
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}