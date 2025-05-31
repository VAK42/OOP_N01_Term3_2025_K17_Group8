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
    Category category = new Category(UUID.randomUUID(), "Books");

    categoryList.add(category);
    System.out.println("\n[testAdd] Categories after adding:");
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

    System.out.println("\n[testEdit] Edit result: " + edited);
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

    System.out.println("\n[testDelete] Delete result: " + deleted);
    System.out.println("Remaining categories:");
    for (Category c : categoryList.getAll()) {
      System.out.println(c.getCategoryId() + ": " + c.getName());
    }
  }
}
