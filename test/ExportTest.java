import java.time.LocalDateTime;
import java.util.UUID;

public class ExportTest {
  public static void runTest() {
    UUID exportId = UUID.randomUUID();
    Category category = new Category(UUID.randomUUID(), "Electronics");
    Product product = new Product(UUID.randomUUID(), "Laptop", 4200.0, category, "MSI Titan 18HX", 10);
    User user = new User(UUID.randomUUID(), "vak", "password", "vak@gmail.com", "admin");
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

  public static void testAdd() {
    ExportList exportList = new ExportList();
    Category category = new Category(UUID.randomUUID(), "Books");
    Product product = new Product(UUID.randomUUID(), "Java Book", 50.0, category, "Learn Java", 100);
    User user = new User(UUID.randomUUID(), "thq", "password", "thq@gmail.com", "user");
    Double price = product.getPdprice();
    int quantity = 3;
    Double total = price * quantity;
    LocalDateTime date = LocalDateTime.now();
    Export export = new Export(UUID.randomUUID(), product, user, price, quantity, total, date);
    exportList.add(export);
    System.out.println("Export Add: ");
    for (Export e : exportList.getAll()) {
      System.out.println("Export ID: " + e.getEpid());
      System.out.println("Product: " + e.getProduct().getPdname());
    }
  }

  public static void testEdit() {
    ExportList exportList = new ExportList();
    Category category = new Category(UUID.randomUUID(), "Toys");
    Product product = new Product(UUID.randomUUID(), "Lego", 80.0, category, "Creative Blocks", 50);
    User user = new User(UUID.randomUUID(), "ptmn", "password", "ptmn@gmail.com", "user");
    UUID exportId = UUID.randomUUID();
    Export export = new Export(exportId, product, user, 80.0, 1, 80.0, LocalDateTime.now());
    exportList.add(export);
    Product newProduct = new Product(UUID.randomUUID(), "Remote Car", 100.0, category, "RC Car", 30);
    User newUser = new User(UUID.randomUUID(), "lewandowski", "12345678", "lewy@barca.com", "admin");
    boolean edited = exportList.edit(exportId, newProduct, newUser, 100.0, 2, 200.0, LocalDateTime.now());
    System.out.println("Export Edit: " + edited);
    for (Export e : exportList.getAll()) {
      System.out.println("Export ID: " + e.getEpid());
      System.out.println("Product: " + e.getProduct().getPdname());
      System.out.println("User: " + e.getUser().getUsername());
    }
  }

  public static void testDelete() {
    ExportList exportList = new ExportList();
    Category category = new Category(UUID.randomUUID(), "Accessories");
    Product product = new Product(UUID.randomUUID(), "Mouse", 25.0, category, "Wireless Mouse", 100);
    User user = new User(UUID.randomUUID(), "messi", "87654321", "messi@barca.com", "user");
    UUID exportId = UUID.randomUUID();
    Export export = new Export(exportId, product, user, 25.0, 4, 100.0, LocalDateTime.now());
    exportList.add(export);
    boolean deleted = exportList.delete(exportId);
    System.out.println("Export Delete: " + deleted);
    for (Export e : exportList.getAll()) {
      System.out.println("Export ID: " + e.getEpid());
      System.out.println("Product: " + e.getProduct().getPdname());
    }
  }
}