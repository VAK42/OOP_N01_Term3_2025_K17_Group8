import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class ExportTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final ExportList exportList = new ExportList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== Export Menu ===");
      System.out.println("1. Add Export");
      System.out.println("2. Edit Export");
      System.out.println("3. Delete Export");
      System.out.println("4. View All Exports");
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
      double total = pdPrice * quantity;
      Export export = new Export(UUID.randomUUID(), product, user, pdPrice, quantity, total, LocalDateTime.now());
      exportList.add(export);
      System.out.println("Export Added Successfully");
    } catch (Exception e) {
      System.out.println("Error Adding Export: " + e.getMessage());
    }
  }

  public static void testEdit() {
    try {
      System.out.print("Enter Export Id To Edit: ");
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
      Product product = new Product(UUID.randomUUID(), pdName, pdPrice, new Category(UUID.randomUUID(), "Updated"),
          pdDesc, pdStock);
      User user = new User(UUID.randomUUID(), username, password, email, role);
      double total = pdPrice * quantity;
      boolean edited = exportList.edit(id, product, user, pdPrice, quantity, total, LocalDateTime.now());
      System.out.println(edited ? "Export Edited Successfully" : "Export Not Found");
    } catch (Exception e) {
      System.out.println("Error Editing Export: " + e.getMessage());
    }
  }

  public static void testDelete() {
    try {
      System.out.print("Enter Export Id To Delete: ");
      UUID id = UUID.fromString(scanner.nextLine());
      boolean deleted = exportList.delete(id);
      System.out.println(deleted ? "Export Deleted Successfully" : "Export Not Found");
    } catch (Exception e) {
      System.out.println("Error Deleting Export: " + e.getMessage());
    }
  }

  public static void viewAll() {
    System.out.println("\nAll Exports");
    for (Export e : exportList.getAll()) {
      System.out.println("Export Id: " + e.getEpid());
      System.out.println("Product: " + e.getProduct().getPdname());
      System.out.println("Category: " + e.getProduct().getCategory().getName());
      System.out.println("User: " + e.getUser().getUsername());
      System.out.println("Email: " + e.getUser().getEmail());
      System.out.println("Quantity: " + e.getPdquantity());
      System.out.println("Total Price: " + e.getPdtotalprice());
      System.out.println("Date: " + e.getDate());
      System.out.println("------------");
    }
  }
}