package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentService {
    static Scanner sc = new Scanner(System.in);
    
    static String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=123";
    
    private static Connection con;
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                con = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int save() {
        int no = 0;
        
        System.out.print("Enter student ID: ");
        no = sc.nextInt();
        
        System.out.print("Enter student name: ");
        String name = sc.next();
        
        System.out.print("Enter student age: ");
        int age = sc.nextInt();
        
        System.out.print("Enter student email: ");
        String email = sc.next();
        
        System.out.print("Enter student course: ");
        String course = sc.next();
        
        String sql = "INSERT INTO student (id, name, age, email, course ) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            
            pstm.setInt(1, no);
            pstm.setString(2, name);
            pstm.setInt(3, age);
            pstm.setString(4, email);
            pstm.setString(5, course);
            
           
            int result = pstm.executeUpdate();
            
            if (result > 0) {
                System.out.println("Student record inserted successfully!");
            } else {
                System.out.println("Failed to insert student record.");
            }
            return result; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return no; 
    }
    public int update() {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();

        System.out.print("Enter new student name: ");
        String name = sc.next();
        
        System.out.print("Enter new student age: ");
        int age = sc.nextInt();
        
        System.out.print("Enter new student email: ");
        String email = sc.next();
        
        System.out.print("Enter new student course: ");
        String course = sc.next();
        
        String sql = "UPDATE student SET name = ?, age = ?, course = ?, email = ? WHERE id = ?";
        
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, name);
            pstm.setInt(2, age);
            pstm.setString(3, course);
            pstm.setString(4, email);
            pstm.setInt(5, id);
            
            int result = pstm.executeUpdate();
            
            if (result > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("Failed to update student record or student not found.");
            }
            return result; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0; 
    }
    public int delete() {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();
        
        String sql = "DELETE FROM student WHERE id = ?";
        
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
            
            int result = pstm.executeUpdate();
            
            if (result > 0) {
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("Student Not Found");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0; 
    }

    public void fetchAll() {
        String sql = "SELECT * FROM student";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String course = rs.getString("course");
                
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Email: " + email + ", Course: " + course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        
        StudentService service = new StudentService();
        service.save();
        service.update();
        service.delete();
        service.fetchAll();
    }
}
