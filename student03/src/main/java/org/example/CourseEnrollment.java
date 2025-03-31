package org.example;
import java.sql.*;
import java.util.*;


public class CourseEnrollment {
//        // INSERT DATA
//        public static void insertEmployee(String name, int deptId) {
//            String sql = "INSERT INTO register (StId,CourId) VALUES (?, ?)";
//            try (Connection conn = JDBCUtils.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {
//                stmt.setString(1, StId);
//                stmt.setInt(2, CourId);
//                stmt.executeUpdate();
//                System.out.println("Employee Added!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // READ DATA WITH JOIN
//        public static void getEmployeesWithDepartment() {
//            String sql = "SELECT StId, FName, LName,Major,Email,PNumber FROM student " +
//                    "JOIN reggister ON e.dept_id = d.dept_id";
//            try (Connection conn = JDBCUtils.getConnection();
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(sql)) {
//                while (rs.next()) {
//                    System.out.println("ID: " + rs.getInt("emp_id") +
//                            ", Name: " + rs.getString("emp_name") +
//                            ", Department: " + rs.getString("dept_name"));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // UPDATE DATA
//        public static void updateEmployeeDepartment(int empId, int newDeptId) {
//            String sql = "UPDATE Employee SET dept_id = ? WHERE emp_id = ?";
//            try (Connection conn = JDBCUtils.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {
//                stmt.setInt(1, newDeptId);
//                stmt.setInt(2, empId);
//                stmt.executeUpdate();
//                System.out.println("Employee Updated!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // DELETE DATA
//        public static void deleteEmployee(int empId) {
//            String sql = "DELETE FROM Employee WHERE emp_id = ?";
//            try (Connection conn = JDBCUtils.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {
//                stmt.setInt(1, empId);
//                stmt.executeUpdate();
//                System.out.println("Employee Deleted!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }


        // Method to delete a student's record and their course association
//        private static void deletetheWholeRecord(Connection conn, int studentId) {
//            String deleteQuery = "DELETE FROM student WHERE StId = ?";
//            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
//                pstmt.setInt(1, studentId);
//                int rowsAffected = pstmt.executeUpdate();
//                System.out.println("Deleted " + rowsAffected + " record(s) for Student ID: " + studentId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Method to update a student's information and course
//        private static void updatetheWholeRecord(Connection conn, int studentId, String fName, String lName, String major, String email, String phoneNumber, int courseId, String courseName) {
//            String updateQuery = "UPDATE student st " +
//                    "JOIN course co ON st.StId = co.StId " +
//                    "SET st.FName = ?, st.LName = ?, st.Major = ?, st.Email = ?, st.PNumber = ?, " +
//                    "co.CourId = ?, co.Cname = ? " +
//                    "WHERE st.StId = ?";
//
//            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
//                pstmt.setString(1, fName);
//                pstmt.setString(2, lName);
//                pstmt.setString(3, major);
//                pstmt.setString(4, email);
//                pstmt.setString(5, phoneNumber);
//                pstmt.setInt(6, courseId);
//                pstmt.setString(7, courseName);
//                pstmt.setInt(8, studentId);
//
//                int rowsAffected = pstmt.executeUpdate();
//                System.out.println("Updated " + rowsAffected + " record(s) for Student ID: " + studentId);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Method to select all student records with course details
//        private static void selectAllRecords(Connection conn) {
//            String selectQuery = "SELECT s.StId, s.FName, s.LName, s.Major, s.Email, s.PNumber, c.CourId, c.Cname " +
//                    "FROM student s " +
//                    "JOIN course c ON s.StId = c.StId";
//
//            try (PreparedStatement pstmt = conn.prepareStatement(selectQuery);
//                 ResultSet rs = pstmt.executeQuery()) {
//
//                while (rs.next()) {
//                    System.out.println("Student ID: " + rs.getInt("StId") +
//                            ", Name: " + rs.getString("FName") + " " + rs.getString("LName") +
//                            ", Major: " + rs.getString("Major") +
//                            ", Email: " + rs.getString("Email") +
//                            ", Phone: " + rs.getString("PNumber") +
//                            ", Course ID: " + rs.getInt("CourId") +
//                            ", Course Name: " + rs.getString("Cname"));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//


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
                        StudentManagement.insertRecord();
                    } else if (choice == 2) {
                        CourseEnrollment.selectAllRecords();
                    } else if (choice == 3) {
                        CourseEnrollment.updatetheWholeRecord();
                    } else if (choice == 4) {
                        CourseEnrollment.deleteWholeRecord();
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
//            CourseEnrollment employeeDAO = new CourseEnrollment();
//
//            // Insert Employee
//            CourseEnrollment.insertEmployee("John Doe", 1);
//
//            // Read Employees with Departments
//            CourseEnrollment.getEmployeesWithDepartment();
//
//            // Update Employee Department
//            CourseEnrollment.updateEmployeeDepartment(1, 2);
//
//            // Delete Employee
//            CourseEnrollment.deleteEmployee(1);
        }
    }

    public static void deleteWholeRecord() {
        //make the join and delete <StId,FName,LName,Major,Email,PNumber>from student table and get <CourId,Cname>from course table
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to delete: ");
        String StId = scanner.nextLine();
        final String DELETE_REGISTER_ALL = "DELETE FROM register WHERE StId=?;";
        final String DELETE_STUDENT_ALL = "DELETE FROM student WHERE StId=?;";

        try ( Connection connection = JDBCUtils.getConnection();
              PreparedStatement deleteregisterstmnt = connection.prepareStatement(DELETE_REGISTER_ALL);
              PreparedStatement deletestudentstmnt = connection.prepareStatement(DELETE_STUDENT_ALL);) {
                deleteregisterstmnt.setString(1, StId);
                deletestudentstmnt.setString(1, StId);

                int registerDeleted= deleteregisterstmnt.executeUpdate();
                int studentDeleted= deletestudentstmnt.executeUpdate();

                if (studentDeleted>0){
                    System.out.println("Student deleted successfully.");
                }
                else {
                    System.out.println("StId does not exist in the database.");
                }
                if (registerDeleted>0){
                    System.out.println("Register deleted successfully.");
                }else {
                    System.out.println("Register does not exist in the database.");
                }
        }
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void updatetheWholeRecord() {
        //make the join and update <StId,FName,LName,Major,Email,PNumber>from student table and get <CourId,Cname>from course table
    }

    public static void selectAllRecords() {
        //make the join and read <StId,FName,LName,Major,Email,PNumber>from student table and get <CourId,Cname>from course table
    }
}


