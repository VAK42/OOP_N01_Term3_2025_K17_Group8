import java.util.UUID;

public class ExportTest {
    public static void main(String[] args) {
        UUID categoryId = UUID.randomUUID();
        CategoryEntity category = new CategoryEntity(categoryId, "Electronics");

        UUID productId = UUID.randomUUID();
        ProductEntity product = new ProductEntity(
                productId,
                "Laptop ASUS",
                1599.99,
                category,
                "ASUS ROG Gaming Laptop",
                20
        );

        System.out.println("Product ID: " + product.getPdid());
        System.out.println("Product Name: " + product.getPdname());
        System.out.println("Price: $" + product.getPdprice());
        System.out.println("Category: " + product.getCategory().getName());
        System.out.println("Info: " + product.getPdinfo());
        System.out.println("Quantity: " + product.getPdquantity());
    }
}