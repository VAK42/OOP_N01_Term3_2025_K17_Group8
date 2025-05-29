import java.time.LocalDateTime;
import java.util.UUID;

public class ImportTest {

    public static void test() {
        System.out.println("=== TestImport ===");

        UUID ipid = UUID.randomUUID();

        CategoryEntity category = new CategoryEntity(UUID.randomUUID(), "Linh kiện");
        ProductEntity product = new ProductEntity(UUID.randomUUID(), "Sản phẩm B", 200.0, category, "Thông tin sản phẩm", 100);

        UserEntity user = new UserEntity(UUID.randomUUID(), "user456", "password456", "user456@example.com", "customer");

        Double pdprice = 200.0;
        Integer pdquantity = 5;
        LocalDateTime date = LocalDateTime.now();

        ImportEntity importEntity = new ImportEntity(ipid, product, user, pdprice, pdquantity, date);

        System.out.println("Ipid: " + importEntity.getIpid());
        System.out.println("Product name: " + importEntity.getProduct().getPdname());
        System.out.println("User username: " + importEntity.getUser().getUsername());
        System.out.println("Pdprice: " + importEntity.getPdprice());
        System.out.println("Pdquantity: " + importEntity.getPdquantity());
        System.out.println("Date: " + importEntity.getDate());

        importEntity.setPdquantity(8);
        importEntity.setPdprice(210.0);

        System.out.println("Updated Pdquantity: " + importEntity.getPdquantity());
        System.out.println("Updated Pdprice: " + importEntity.getPdprice());

        System.out.println("=== End TestImport ===");
    }
}
