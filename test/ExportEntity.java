
import java.util.UUID;

public class TestExport {

    public static void test() {
        System.out.println("=== TestExport ===");

        UUID epid = UUID.randomUUID();

        ProductEntity product = new ProductEntity();
        product.setPdname("Sản phẩm A");
        product.setPdprice(150.0);

        UserEntity user = new UserEntity(UUID.randomUUID(), "user123", "password", "user@example.com", "customer");

        Double pdprice = 150.0;
        Integer pdquantity = 10;
        Double pdtotalprice = pdprice * pdquantity;
        LocalDateTime date = LocalDateTime.now();

        ExportEntity export = new ExportEntity(epid, product, user, pdprice, pdquantity, pdtotalprice, date);

        System.out.println("Epid: " + export.getEpid());
        System.out.println("Product name: " + export.getProduct().getPdname());
        System.out.println("User username: " + export.getUser().getUsername());
        System.out.println("Pdprice: " + export.getPdprice());
        System.out.println("Pdquantity: " + export.getPdquantity());
        System.out.println("Pdtotalprice: " + export.getPdtotalprice());
        System.out.println("Date: " + export.getDate());

        export.setPdquantity(20);
        export.setPdtotalprice(export.getPdprice() * export.getPdquantity());

        System.out.println("Updated Pdquantity: " + export.getPdquantity());
        System.out.println("Updated Pdtotalprice: " + export.getPdtotalprice());

        System.out.println("=== End TestExport ===");
    }
}
