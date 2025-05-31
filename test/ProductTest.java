import java.util.UUID;

public class ProductTest {

  public static void runTest() {
    UUID productId = UUID.randomUUID();
    Category category = new Category(UUID.randomUUID(), "Accessories");
    Product product = new Product(productId, "Wireless Mouse", 25.99, category, "Ergonomic Design With Bluetooth Connectivity", 100);

    System.out.println("Product ID: " + product.getPdid());
    System.out.println("Product Name: " + product.getPdname());
    System.out.println("Product Price: " + product.getPdprice());
    System.out.println("Category: " + product.getCategory().getName());
    System.out.println("Product Info: " + product.getPdinfo());
    System.out.println("Quantity: " + product.getPdquantity());
  }

  public static void testAdd() {
    ProductList productList = new ProductList();
    Category category = new Category(UUID.randomUUID(), "Books");
    Product product = new Product(UUID.randomUUID(), "Clean Code", 40.0, category, "A Handbook of Agile Software Craftsmanship", 50);
    productList.add(product);

    System.out.println("\n[testAdd] Product list after adding:");
    for (Product p : productList.getAll()) {
      System.out.println("Product ID: " + p.getPdid() + ", Name: " + p.getPdname());
    }
  }

  public static void testEdit() {
    ProductList productList = new ProductList();
    Category category = new Category(UUID.randomUUID(), "Computers");
    Product product = new Product(UUID.randomUUID(), "Gaming Keyboard", 70.0, category, "RGB Mechanical Keyboard", 30);

    UUID productId = product.getPdid();
    productList.add(product);

    Category newCategory = new Category(UUID.randomUUID(), "Gaming Accessories");
    boolean result = productList.edit(productId, "Mechanical Gaming Keyboard", 85.0, newCategory, "Upgraded with faster switches", 40);

    System.out.println("\n[testEdit] Edit result: " + result);
    for (Product p : productList.getAll()) {
      System.out.println("Product ID: " + p.getPdid() + ", Name: " + p.getPdname() + ", Category: " + p.getCategory().getName());
    }
  }

  public static void testDelete() {
    ProductList productList = new ProductList();
    Category category = new Category(UUID.randomUUID(), "Office Supplies");
    Product product = new Product(UUID.randomUUID(), "Stapler", 10.0, category, "Heavy-duty stapler", 25);

    UUID productId = product.getPdid();
    productList.add(product);

    boolean deleted = productList.delete(productId);

    System.out.println("\n[testDelete] Delete result: " + deleted);
    System.out.println("Remaining products:");
    for (Product p : productList.getAll()) {
      System.out.println("Product ID: " + p.getPdid() + ", Name: " + p.getPdname());
    }
  }
}
