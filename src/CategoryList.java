import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryList {
  private final List<Category> categories = new ArrayList<>();

  public void add(Category category) {
    categories.add(category);
  }

  public boolean edit(UUID categoryId, String newName) {
    for (Category cat : categories) {
      if (cat.getCategoryId().equals(categoryId)) {
        cat.setName(newName);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID categoryId) {
    return categories.removeIf(cat -> cat.getCategoryId().equals(categoryId));
  }

  public List<Category> getAll() {
    return new ArrayList<>(categories);
  }
}