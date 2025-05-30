import java.time.LocalDateTime;
import java.util.UUID;

public class ImportTest {
  public static void runTest() {
    UUID importId = UUID.randomUUID();
    Category category = new Category(UUID.randomUUID(), "Electronics");
    Product product = new Product(UUID.randomUUID(), "Smartphone", 800.0, category, "Flagship Phone With OLED Display", 50);
    User user = new User(UUID.randomUUID(), "janedoe", "securepass", "janedoe@example.com", "staff");
    Double price = product.getPdprice();
    Integer quantity = 5;
    LocalDateTime now = LocalDateTime.now();
    Import newImport = new Import(importId, product, user, price, quantity, now);
    System.out.println("Import ID: " + newImport.getIpid());
    System.out.println("Product Name: " + newImport.getProduct().getPdname());
    System.out.println("Product Price: " + newImport.getPdprice());
    System.out.println("Category: " + newImport.getProduct().getCategory().getName());
    System.out.println("User: " + newImport.getUser().getUsername());
    System.out.println("Email: " + newImport.getUser().getEmail());
    System.out.println("Quantity: " + newImport.getPdquantity());
    System.out.println("Date: " + newImport.getDate());
  }
}