
import java.util.UUID;

public class TestImport {

    public static void test() {
        System.out.println("=== TestImport ===");

        UUID ipid = UUID.randomUUID();

        ProductEntity product = new ProductEntity();
        product.setPdname("Sản phẩm B");
        product.setPdprice(200.0);

        UserEntity user = new UserEntity(UUID.randomUUID(), "user456", "password456", "user456@example.com", "customer");

        Double pdprice = 200.0;
        Integer pdquantity = 5;
        LocalDateTime date = LocalDateTime.now();

        ImportEntity im
