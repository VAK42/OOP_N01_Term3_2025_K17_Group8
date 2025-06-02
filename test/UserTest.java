import java.util.Scanner;
import java.util.UUID;

public class UserTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final UserList userList = new UserList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== User Menu ===");
      System.out.println("1. Add User");
      System.out.println("2. Edit User");
      System.out.println("3. Delete User");
      System.out.println("4. View All Users");
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
      System.out.print("Enter Username ");
      String username = scanner.nextLine();
      System.out.print("Enter Password ");
      String password = scanner.nextLine();
      System.out.print("Enter Email ");
      String email = scanner.nextLine();
      System.out.print("Enter Role ");
      String role = scanner.nextLine();
      User user = new User(UUID.randomUUID(), username, password, email, role);
      userList.add(user);
      System.out.println("User Added Successfully");
    } catch (Exception e) {
      System.out.println("Error Adding User");
    }
  }

  private static void testEdit() {
    try {
      System.out.print("Enter User Id To Edit ");
      UUID userId = UUID.fromString(scanner.nextLine());
      System.out.print("Enter New Username ");
      String username = scanner.nextLine();
      System.out.print("Enter New Password ");
      String password = scanner.nextLine();
      System.out.print("Enter New Email ");
      String email = scanner.nextLine();
      System.out.print("Enter New Role ");
      String role = scanner.nextLine();
      boolean edited = userList.edit(userId, username, password, email, role);
      System.out.println(edited ? "User Edited Successfully" : "User Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Editing User");
    }
  }

  private static void testDelete() {
    try {
      System.out.print("Enter User Id To Delete ");
      UUID userId = UUID.fromString(scanner.nextLine());
      boolean deleted = userList.delete(userId);
      System.out.println(deleted ? "User Deleted Successfully" : "User Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Deleting User");
    }
  }

  private static void viewAll() {
    System.out.println("\nAll Users:");
    for (User u : userList.getAll()) {
      System.out.println("User Id: " + u.getUserId());
      System.out.println("Username: " + u.getUsername());
      System.out.println("Email: " + u.getEmail());
      System.out.println("Role: " + u.getRole());
      System.out.println("------------");
    }
  }
}