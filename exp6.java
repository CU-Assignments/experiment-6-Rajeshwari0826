import java.sql.*;
import java.util.Scanner;

// Easy Level: Fetching Employee Data
class EmployeeFetcher {
    public static void fetchEmployees() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "rajeshwari", "22****rp");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employee")) {
            System.out.println("EmpID | Name | Salary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + " | " + rs.getString("Name") + " | " + rs.getDouble("Salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Medium Level: CRUD Operations on Product Table
class ProductCRUD {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "rajeshwari", "22****rp");
    }

    public static void createProduct(int id, String name, double price, int qty) {
        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, qty);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readProducts() {
        String sql = "SELECT * FROM Product";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ProductID | Name | Price | Quantity");
            while (rs.next()) {
                System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName") + " | " + rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(int id, double price) {
        String sql = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Hard Level: MVC-based Student Management System
class Student {
    int studentID;
    String name, department;
    double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }
}

class StudentController {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB","rajeshwari", "22****rp");
    }

    public static void addStudent(Student student) {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.studentID);
            pstmt.setString(2, student.name);
            pstmt.setString(3, student.department);
            pstmt.setDouble(4, student.marks);
            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewStudents() {
        String sql = "SELECT * FROM Student";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("StudentID | Name | Department | Marks");
            while (rs.next()) {
                System.out.println(rs.getInt("StudentID") + " | " + rs.getString("Name") + " | " + rs.getString("Department") + " | " + rs.getDouble("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Fetch Employees\n2. Product CRUD\n3. Student Management\n4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    EmployeeFetcher.fetchEmployees();
                    break;
                case 2:
                    System.out.println("1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product");
                    int prodChoice = scanner.nextInt();
                    if (prodChoice == 1) {
                        System.out.print("Enter ID, Name, Price, Quantity: ");
                        ProductCRUD.createProduct(scanner.nextInt(), scanner.next(), scanner.nextDouble(), scanner.nextInt());
                    } else if (prodChoice == 2) {
                        ProductCRUD.readProducts();
                    } else if (prodChoice == 3) {
                        System.out.print("Enter Product ID and New Price: ");
                        ProductCRUD.updateProduct(scanner.nextInt(), scanner.nextDouble());
                    } else if (prodChoice == 4) {
                        System.out.print("Enter Product ID to Delete: ");
                        ProductCRUD.deleteProduct(scanner.nextInt());
                    }
                    break;
                case 3:
                    System.out.println("1. Add Student\n2. View Students");
                    int stuChoice = scanner.nextInt();
                    if (stuChoice == 1) {
                        System.out.print("Enter ID, Name, Department, Marks: ");
                        StudentController.addStudent(new Student(scanner.nextInt(), scanner.next(), scanner.next(), scanner.nextDouble()));
                    } else if (stuChoice == 2) {
                        StudentController.viewStudents();
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
