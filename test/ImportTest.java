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

  public static void testAdd() {
    ImportList importList = new ImportList();
    Category category = new Category(UUID.randomUUID(), "Books");
    Product product = new Product(UUID.randomUUID(), "Java Programming", 45.0, category, "Comprehensive Java Guide", 100);
    User user = new User(UUID.randomUUID(), "alice", "pass123", "alice@example.com", "staff");

    Double price = product.getPdprice();
    Integer quantity = 10;
    LocalDateTime now = LocalDateTime.now();

    Import newImport = new Import(UUID.randomUUID(), product, user, price, quantity, now);
    importList.add(newImport);

    System.out.println("\n[testAdd] Import list after adding:");
    for (Import imp : importList.getAll()) {
      System.out.println("Import ID: " + imp.getIpid() + ", Product: " + imp.getProduct().getPdname());
    }
  }

  public static void testEdit() {
    ImportList importList = new ImportList();
    Category category = new Category(UUID.randomUUID(), "Gadgets");
    Product product = new Product(UUID.randomUUID(), "Smartwatch", 150.0, category, "Fitness and Notifications", 40);
    User user = new User(UUID.randomUUID(), "bob", "secure123", "bob@example.com", "staff");

    UUID importId = UUID.randomUUID();
    Import originalImport = new Import(importId, product, user, 150.0, 4, LocalDateTime.now());
    importList.add(originalImport);

    
    Product updatedProduct = new Product(UUID.randomUUID(), "Tablet", 300.0, category, "10-inch Display", 20);
    User updatedUser = new User(UUID.randomUUID(), "carol", "adminpass", "carol@example.com", "admin");
    boolean edited = importList.edit(importId, updatedProduct, updatedUser, 300.0, 2, LocalDateTime.now());

    System.out.println("\n[testEdit] Edit result: " + edited);
    for (Import imp : importList.getAll()) {
      System.out.println("Import ID: " + imp.getIpid() + ", Product: " + imp.getProduct().getPdname() + ", User: " + imp.getUser().getUsername());
    }
  }

  public static void testDelete() {
    ImportList importList = new ImportList();
    Category category = new Category(UUID.randomUUID(), "Accessories");
    Product product = new Product(UUID.randomUUID(), "Keyboard", 30.0, category, "Mechanical Keyboard", 80);
    User user = new User(UUID.randomUUID(), "david", "keypass", "david@example.com", "staff");

    UUID importId = UUID.randomUUID();
    Import importRecord = new Import(importId, product, user, 30.0, 3, LocalDateTime.now());
    importList.add(importRecord);

    boolean deleted = importList.delete(importId);

    System.out.println("\n[testDelete] Delete result: " + deleted);
    System.out.println("Remaining imports:");
    for (Import imp : importList.getAll()) {
      System.out.println("Import ID: " + imp.getIpid() + ", Product: " + imp.getProduct().getPdname());
    }
  }
}
