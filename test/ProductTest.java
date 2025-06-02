import java.util.Scanner;

public class ProductTest {
  private static final Scanner scanner = new Scanner(System.in);
  private static final ProductList productList = new ProductList();

  public static void startMenu() {
    while (true) {
      System.out.println("\n=== Product Menu ===");
      System.out.println("1. Add Product");
      System.out.println("2. Edit Product");
      System.out.println("3. Delete Product");
      System.out.println("4. View All Products");
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
      System.out.print("Enter Product Name: ");
      String pdName = scanner.nextLine();
      System.out.print("Enter Product Price: ");
      double pdPrice = Double.parseDouble(scanner.nextLine());
      System.out.print("Enter Product Info: ");
      String pdInfo = scanner.nextLine();
      System.out.print("Enter Product Quantity: ");
      int pdQuantity = Integer.parseInt(scanner.nextLine());
      System.out.print("Enter Category Name: ");
      String catName = scanner.nextLine();
      Category category = new Category(java.util.UUID.randomUUID(), catName);
      Product product = new Product(java.util.UUID.randomUUID(), pdName, pdPrice, category, pdInfo, pdQuantity);
      productList.add(product);
      System.out.println("Product Added Successfully");
    } catch (Exception e) {
      System.out.println("Error Adding Product");
    }
  }

  private static void testEdit() {
    try {
      System.out.print("Enter Product Id To Edit: ");
      java.util.UUID pdid = java.util.UUID.fromString(scanner.nextLine());
      System.out.print("Enter New Product Name: ");
      String newName = scanner.nextLine();
      System.out.print("Enter New Product Price: ");
      double newPrice = Double.parseDouble(scanner.nextLine());
      System.out.print("Enter New Product Info: ");
      String newInfo = scanner.nextLine();
      System.out.print("Enter New Product Quantity: ");
      int newQuantity = Integer.parseInt(scanner.nextLine());
      System.out.print("Enter New Category Name: ");
      String newCatName = scanner.nextLine();
      Category newCategory = new Category(java.util.UUID.randomUUID(), newCatName);
      boolean edited = productList.edit(pdid, newName, newPrice, newCategory, newInfo, newQuantity);
      System.out.println(edited ? "Product Edited Successfully" : "Product Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Editing Product");
    }
  }

  private static void testDelete() {
    try {
      System.out.print("Enter Product Id To Delete: ");
      java.util.UUID pdid = java.util.UUID.fromString(scanner.nextLine());
      boolean deleted = productList.delete(pdid);
      System.out.println(deleted ? "Product Deleted Successfully" : "Product Not Found");
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid UUID Format");
    } catch (Exception e) {
      System.out.println("Error Deleting Product");
    }
  }

  private static void viewAll() {
    System.out.println("\nAll Products:");
    for (Product p : productList.getAll()) {
      System.out.println("Product Id: " + p.getPdid());
      System.out.println("Name: " + p.getPdname());
      System.out.println("Price: " + p.getPdprice());
      System.out.println("Info: " + p.getPdinfo());
      System.out.println("Quantity: " + p.getPdquantity());
      System.out.println("Category: " + p.getCategory().getName());
      System.out.println("------------");
    }
  }
}