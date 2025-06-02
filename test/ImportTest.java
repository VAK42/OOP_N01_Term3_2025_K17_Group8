import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class ImportTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final ImportList importList = new ImportList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== Import Menu ===");
      System.out.println("1. Add Import");
      System.out.println("2. Edit Import");
      System.out.println("3. Delete Import");
      System.out.println("4. View All Imports");
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

  public static void testAdd() {
    try {
      System.out.print("Enter Product Name: ");
      String pdName = scanner.nextLine();
      System.out.print("Enter Product Price: ");
      double pdPrice = Double.parseDouble(scanner.nextLine());
      System.out.print("Enter Product Description: ");
      String pdDesc = scanner.nextLine();
      System.out.print("Enter Product Stock: ");
      int pdStock = Integer.parseInt(scanner.nextLine());
      System.out.print("Enter Category Name: ");
      String catName = scanner.nextLine();
      System.out.print("Enter Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter Password: ");
      String password = scanner.nextLine();
      System.out.print("Enter Email: ");
      String email = scanner.nextLine();
      System.out.print("Enter Role: ");
      String role = scanner.nextLine();
      System.out.print("Enter Quantity: ");
      int quantity = Integer.parseInt(scanner.nextLine());
      Category category = new Category(UUID.randomUUID(), catName);
      Product product = new Product(UUID.randomUUID(), pdName, pdPrice, category, pdDesc, pdStock);
      User user = new User(UUID.randomUUID(), username, password, email, role);
      Import imp = new Import(UUID.randomUUID(), product, user, pdPrice, quantity, LocalDateTime.now());
      importList.add(imp);
      System.out.println("Import Added Successfully");
    } catch (Exception e) {
      System.out.println("Error Adding Import: " + e.getMessage());
    }
  }

  public static void testEdit() {
    try {
      System.out.print("Enter Import Id To Edit: ");
      UUID id = UUID.fromString(scanner.nextLine());
      System.out.print("Enter New Product Name: ");
      String pdName = scanner.nextLine();
      System.out.print("Enter New Product Price: ");
      double pdPrice = Double.parseDouble(scanner.nextLine());
      System.out.print("Enter New Product Description: ");
      String pdDesc = scanner.nextLine();
      System.out.print("Enter New Product Stock: ");
      int pdStock = Integer.parseInt(scanner.nextLine());
      System.out.print("Enter New Username: ");
      String username = scanner.nextLine();
      System.out.print("Enter New Password: ");
      String password = scanner.nextLine();
      System.out.print("Enter New Email: ");
      String email = scanner.nextLine();
      System.out.print("Enter New Role: ");
      String role = scanner.nextLine();
      System.out.print("Enter New Quantity: ");
      int quantity = Integer.parseInt(scanner.nextLine());
      Category category = new Category(UUID.randomUUID(), "Updated");
      Product product = new Product(UUID.randomUUID(), pdName, pdPrice, category, pdDesc, pdStock);
      User user = new User(UUID.randomUUID(), username, password, email, role);
      boolean edited = importList.edit(id, product, user, pdPrice, quantity, LocalDateTime.now());
      System.out.println(edited ? "Import Edited Successfully" : "Import Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Editing Import: " + e.getMessage());
    }
  }

  public static void testDelete() {
    try {
      System.out.print("Enter Import Id To Delete: ");
      UUID id = UUID.fromString(scanner.nextLine());
      boolean deleted = importList.delete(id);
      System.out.println(deleted ? "Import Deleted Successfully" : "Import Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Deleting Import: " + e.getMessage());
    }
  }

  public static void viewAll() {
    System.out.println("\nAll Imports:");
    for (Import imp : importList.getAll()) {
      System.out.println("Import Id: " + imp.getIpid());
      System.out.println("Product: " + imp.getProduct().getPdname());
      System.out.println("Category: " + imp.getProduct().getCategory().getName());
      System.out.println("User: " + imp.getUser().getUsername());
      System.out.println("Email: " + imp.getUser().getEmail());
      System.out.println("Quantity: " + imp.getPdquantity());
      System.out.println("Price: " + imp.getPdprice());
      System.out.println("Date: " + imp.getDate());
      System.out.println("------------");
    }
  }
}