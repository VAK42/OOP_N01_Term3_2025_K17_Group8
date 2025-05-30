import java.util.UUID;

public class CategoryTest {
  public static void runTest() {
    UUID id = UUID.randomUUID();
    Category category = new Category(id, "Electronics");
    System.out.println("Category ID: " + category.getCategoryId());
    System.out.println("Category Name: " + category.getName());
  }
}