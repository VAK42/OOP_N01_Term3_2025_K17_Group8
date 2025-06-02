import java.util.Scanner;
import java.util.UUID;

public class ReportTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final ReportList reportList = new ReportList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== Report Menu ===");
      System.out.println("1. Add Report");
      System.out.println("2. Edit Report");
      System.out.println("3. Delete Report");
      System.out.println("4. View All Reports");
      System.out.println("5. Exit");
      System.out.print("Enter Choice: ");
      String choice = scanner.nextLine();
      switch (choice) {
        case "1":
          testAdd();
          break;
        case "2":
          testEdit();
          break;
        case "3":
          testDelete();
          break;
        case "4":
          viewAll();
          break;
        case "5":
          System.out.println("Exiting Menu");
          return;
        default:
          System.out.println("Invalid Choice");
      }
    }
  }

  private static void testAdd() {
    try {
      System.out.print("Enter Report Name: ");
      String rpName = scanner.nextLine();
      System.out.print("Enter Report Info: ");
      String rpInfo = scanner.nextLine();
      System.out.print("Enter User Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter User Password: ");
      String password = scanner.nextLine();
      System.out.print("Enter User Email: ");
      String email = scanner.nextLine();
      System.out.print("Enter User Role: ");
      String role = scanner.nextLine();
      User user = new User(UUID.randomUUID(), username, password, email, role);
      Report report = new Report(UUID.randomUUID(), user, rpName, rpInfo);
      reportList.add(report);
      System.out.println("Report Added Successfully");
    } catch (Exception e) {
      System.out.println("Error Adding Report");
    }
  }

  private static void testEdit() {
    try {
      System.out.print("Enter Report Id To Edit: ");
      UUID reportId = UUID.fromString(scanner.nextLine());
      System.out.print("Enter New Report Name: ");
      String newName = scanner.nextLine();
      System.out.print("Enter New Report Info: ");
      String newInfo = scanner.nextLine();
      System.out.print("Enter New User Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter New User Password: ");
      String password = scanner.nextLine();
      System.out.print("Enter New User Email: ");
      String email = scanner.nextLine();
      System.out.print("Enter New User Role: ");
      String role = scanner.nextLine();
      User newUser = new User(UUID.randomUUID(), username, password, email, role);
      boolean edited = reportList.edit(reportId, newUser, newName, newInfo);
      System.out.println(edited ? "Report Edited Successfully" : "Report Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Editing Report");
    }
  }

  private static void testDelete() {
    try {
      System.out.print("Enter Report Id To Delete: ");
      UUID reportId = UUID.fromString(scanner.nextLine());
      boolean deleted = reportList.delete(reportId);
      System.out.println(deleted ? "Report Deleted Successfully" : "Report Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Deleting Report");
    }
  }

  private static void viewAll() {
    System.out.println("\nAll Reports:");
    for (Report r : reportList.getAll()) {
      System.out.println("Report Id: " + r.getReportid());
      System.out.println("Name: " + r.getRpname());
      System.out.println("Info: " + r.getRpinfo());
      System.out.println("User Username: " + r.getUser().getUsername());
      System.out.println("User Email: " + r.getUser().getEmail());
      System.out.println("User Role: " + r.getUser().getRole());
      System.out.println("------------");
    }
  }
}