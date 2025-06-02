import java.util.UUID;

public class CategoryTest {
  public static void runTest() {
    UUID id = UUID.randomUUID();
    Category category = new Category(id, "Electronics");
    System.out.println("Category ID: " + category.getCategoryId());
    System.out.println("Category Name: " + category.getName());
  }

  public static void testAdd() {
    CategoryList categoryList = new CategoryList();
    UUID id = UUID.randomUUID();
    Category category = new Category(id, "Books");
    categoryList.add(category);
    System.out.println("Category Add: ");
    for (Category c : categoryList.getAll()) {
      System.out.println(c.getCategoryId() + ": " + c.getName());
    }
  }

  public static void testEdit() {
    CategoryList categoryList = new CategoryList();
    UUID id = UUID.randomUUID();
    Category category = new Category(id, "Music");
    categoryList.add(category);
    boolean edited = categoryList.edit(id, "Music & Audio");
    System.out.println("Category Edit: " + edited);
    for (Category c : categoryList.getAll()) {
      System.out.println(c.getCategoryId() + ": " + c.getName());
    }
  }

  public static void testDelete() {
    CategoryList categoryList = new CategoryList();
    UUID id = UUID.randomUUID();
    Category category = new Category(id, "Movies");
    categoryList.add(category);
    boolean deleted = categoryList.delete(id);
    System.out.println("Category Delete: " + deleted);
    for (Category c : categoryList.getAll()) {
      System.out.println(c.getCategoryId() + ": " + c.getName());
    }
  }
}