package Driver;

import java.util.Scanner;
import service.StudentService;

public class SchoolDriver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("\n1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Fetch All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            StudentService service = new StudentService(); 
            
            switch (choice) {
                case 1:
                    int result = service.save();
                    if (result > 0) { 
                        System.out.println("Data saved successfully!");
                    } else {
                        System.out.println("Failed to save data.");
                    }
                    break;

                case 2: 
                	result = service.update();
                	if (result > 0) { 
                    System.out.println("Data updated successfully!");
                	} else {
                    System.out.println("Failed to update data.");
                	}
                    break;

                case 3:
                	 result = service.delete();
                     if (result > 0) { 
                         System.out.println("Data deleted successfully!");
                     } else {
                         System.out.println("Failed to delete data.");
                     }
                    break;

                case 4:
                   
                    service.fetchAll();
                    break;

                case 5:
                    
                    System.out.println("Exiting the program.");
                    return; 

                default:
                    
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
