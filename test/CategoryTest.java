import java.util.Scanner;
import java.util.UUID;

public class CategoryTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final CategoryList categoryList = new CategoryList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== Category Menu ===");
      System.out.println("1. Add Category");
      System.out.println("2. Edit Category");
      System.out.println("3. Delete Category");
      System.out.println("4. View All Categories");
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
      System.out.print("Enter Category Name: ");
      String name = scanner.nextLine();
      UUID id = UUID.randomUUID();
      Category category = new Category(id, name);
      categoryList.add(category);
      System.out.println("Category Added: " + id + " - " + name);
    } catch (Exception e) {
      System.out.println("Error Adding Category: " + e.getMessage());
    }
  }

  public static void testEdit() {
    try {
      System.out.print("Enter Category Id To Edit: ");
      UUID id = UUID.fromString(scanner.nextLine());
      System.out.print("Enter New Category Name: ");
      String newName = scanner.nextLine();
      boolean result = categoryList.edit(id, newName);
      System.out.println(result
          ? "Category Edited: " + id
          : "Category Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid Uuid Format");
    } catch (Exception e) {
      System.out.println("Error Editing Category: " + e.getMessage());
    }
  }

  public static void testDelete() {
    try {
      System.out.print("Enter Category Id To Delete: ");
      UUID id = UUID.fromString(scanner.nextLine());
      boolean result = categoryList.delete(id);
      System.out.println(result
          ? "Category Deleted: " + id
          : "Category Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid Uuid Format");
    } catch (Exception e) {
      System.out.println("Error Deleting Category: " + e.getMessage());
    }
  }

  public static void viewAll() {
    System.out.println("\nAll Categories");
    for (Category cat : categoryList.getAll()) {
      System.out.println(cat.getCategoryId() + ": " + cat.getName());
    }
  }
}