import java.time.LocalDateTime;
import java.util.UUID;

public class ExportTest {
  public static void runTest() {
    UUID exportId = UUID.randomUUID();
    Category category = new Category(UUID.randomUUID(), "Electronics");
    Product product = new Product(UUID.randomUUID(), "Laptop", 1200.0, category, "High-end Gaming Laptop", 10);
    User user = new User(UUID.randomUUID(), "johndoe", "password", "johndoe@example.com", "admin");
    Double price = product.getPdprice();
    Integer quantity = 2;
    Double totalPrice = price * quantity;
    LocalDateTime date = LocalDateTime.now();
    Export export = new Export(exportId, product, user, price, quantity, totalPrice, date);
    System.out.println("Export ID: " + export.getEpid());
    System.out.println("Product Name: " + export.getProduct().getPdname());
    System.out.println("Product Price: " + export.getProduct().getPdprice());
    System.out.println("Category: " + export.getProduct().getCategory().getName());
    System.out.println("User: " + export.getUser().getUsername());
    System.out.println("Email: " + export.getUser().getEmail());
    System.out.println("Quantity: " + export.getPdquantity());
    System.out.println("Total Price: " + export.getPdtotalprice());
    System.out.println("Date: " + export.getDate());
  }
}