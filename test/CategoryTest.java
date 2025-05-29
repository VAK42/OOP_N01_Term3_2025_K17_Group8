
import java.util.UUID;

public class CategoryTest {

    public static void test() {
        System.out.println("=== TestCategory ===");

        UUID id = UUID.randomUUID();
        CategoryEntity category = new CategoryEntity(id, "Điện thoại");

        System.out.println("CategoryId: " + category.getCategoryId());
        System.out.println("Name: " + category.getName());

        category.setName("Laptop");
        System.out.println("Updated Name: " + category.getName());

    }
}
