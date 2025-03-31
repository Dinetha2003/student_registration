package org.example;
import java.sql.*;
import java.util.*;

public class CourseManagement {
        // Selecting all records
    public static void selectAllRecords() throws SQLException {
        final String SELECT_ALL_COURSES = "SELECT * FROM course;";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String CourId = rs.getString("CourId");
                String Cname = rs.getString("Cname");
                int Points = rs.getInt("Points");
                String Dept = rs.getString("Dept");
                System.out.println(CourId + " | " + Cname + " | " + Points + " | " + Dept);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    // Inserting a record
    public static void insertRecord() {
        final String INSERT_COURSE_SQL = "INSERT INTO course(CourId, Cname, Points, Dept) VALUES (?,?,?,?);";
        Scanner sc = new Scanner(System.in);

        String CourId;
        do {
            System.out.print("Enter A Course ID: ");
            CourId = sc.nextLine().trim();
        }
        while (!CourId.matches("^[a-zA-Z0-9]+$"));

        String Cname;
        do {
            System.out.print("Enter A Course Name: ");
            Cname = sc.nextLine().trim();
        }
        while (!Cname.matches("^[a-zA-Z]+$"));

        int Points;
        boolean validPoint;
        do {
            try {
                System.out.print("Enter Points: ");
                Points = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                // Check if the number is within the valid range (1-100)
                validPoint = (Points >= 1 && Points <= 100);

                if (!validPoint) {
                    System.out.println("Please enter a valid points under 1 to 100");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a numeric point.");
                sc.nextLine(); // Clear the invalid input
                validPoint = false;
                Points = -1; // Assign an invalid value to keep the loop running
            }
        } while (!validPoint);

        String Dept;
        do {
            System.out.print("Enter the Department: ");
            Dept = sc.nextLine().trim();
        }
        while (!Dept.matches("^[a-zA-Z]+$"));

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_SQL)) {
            preparedStatement.setString(1, CourId);
            preparedStatement.setString(2, Cname);
            preparedStatement.setInt(3, Points);
            preparedStatement.setString(4, Dept);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new course was inserted successfully!");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    // Updating a record
    public static void updateRecord() throws SQLException {
        final String UPDATE_COURSE_SQL = "UPDATE course SET CourId = ?, Cname = ?, Points = ?, Dept = ? WHERE CourId = ?";
        Scanner scanner = new Scanner(System.in);

        String oldCourId;
        do {
            System.out.print("Enter the  Old Course ID: ");
            oldCourId = scanner.nextLine().trim();
        }
        while (!oldCourId.matches("^[a-zA-Z0-9]+$"));
        String newCourId;
        do {
            System.out.print("Enter the New Course ID: ");
            newCourId = scanner.nextLine().trim();
        }
        while (!newCourId.matches("^[a-zA-Z0-9]+$"));

        String Cname;
        do {
            System.out.print("Enter Course Name: ");
            Cname = scanner.nextLine().trim();
        }
        while (!Cname.matches("^[a-zA-Z]+$"));

        int Points;
        boolean validPoint;
        do {
            try {
                System.out.print("Enter Points: ");
                Points = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Check if the number is within the valid range (1-100)
                validPoint = (Points >= 1 && Points <= 100);

                if (!validPoint) {
                    System.out.println("Please enter a valid points under 1 to 100");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a numeric point.");
                scanner.nextLine(); // Clear the invalid input
                validPoint = false;
                Points = -1; // Assign an invalid value to keep the loop running
            }
        } while (!validPoint);

        String Dept;
        do {
            System.out.print("Enter Department: ");
            Dept = scanner.nextLine().trim();
        }
        while (!Dept.matches("^[a-zA-Z]+$"));

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_SQL)) {
            preparedStatement.setString(1, newCourId);
            preparedStatement.setString(2, Cname);
            preparedStatement.setInt(3, Points);
            preparedStatement.setString(4, Dept);
            preparedStatement.setString(5, oldCourId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Course record updated successfully.");
            } else {
                System.out.println("No course found with the given ID.");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    // Deleting a record
    public static void deleteRecord() {
        final String DELETE_COURSE_SQL = "DELETE FROM course WHERE CourId = ?;";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Course ID to delete: ");
        String CourId = scanner.nextLine().trim();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL)) {
            preparedStatement.setString(1, CourId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with CourId: " + CourId);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public static class Main {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("\nSelect your option:");
                    System.out.println("1. Insert Course");
                    System.out.println("2. Show all Courses");
                    System.out.println("3. Update Course");
                    System.out.println("4. Delete Course");
                    System.out.println("5. Exit");
//                    int choice = scanner.nextInt();
//                    scanner.nextLine();
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
                                System.out.println("Please enter valid choices between 1 and 5");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a numeric point.");
                            scanner.nextLine(); // Clear the invalid input
                            validchoice = false;
                            choice = -1; // Assign an invalid value to keep the loop running
                        }
                    } while (!validchoice);

                    if (choice == 1) {
                        insertRecord();
                    } else if (choice == 2) {
                        selectAllRecords();
                    } else if (choice == 3) {
                        updateRecord();
                    } else if (choice == 4) {
                        deleteRecord();
                    } else if (choice == 5) {
                        System.out.println("Exiting program...");
                        scanner.close();
                        return;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

