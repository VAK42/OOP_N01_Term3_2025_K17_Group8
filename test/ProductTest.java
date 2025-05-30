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
}