import java.util.UUID;

public class TestProduct {

    public static void test() {
        System.out.println("=== TestProduct ===");

        UUID pdid = UUID.randomUUID();
        CategoryEntity category = new CategoryEntity(UUID.randomUUID(), "Điện tử");

        ProductEntity product = new ProductEntity(pdid, "Điện thoại XYZ", 500.0, category, "Điện thoại cao cấp", 100);

        System.out.println("Pdid: " + product.getPdid());
        System.out.println("Pdname: " + product.getPdname());
        System.out.println("Pdprice: " + product.getPdprice());
        System.out.println("Category name: " + product.getCategory().getName());
        System.out.println("Pdinfo: " + product.getPdinfo());
        System.out.println("Pdquantity: " + product.getPdquantity());

        product.setPdname("Điện thoại ABC");
        product.setPdprice(550.0);
        product.setPdquantity(120);
        product.setPdinfo("Điện thoại cao cấp, phiên bản mới");

        System.out.println("Updated Pdname: " + product.getPdname());
        System.out.println("Updated Pdprice: " + product.getPdprice());
        System.out.println("Updated Pdquantity: " + product.getPdquantity());
        System.out.println("Updated Pdinfo: " + product.getPdinfo());

        System.out.println("=== End TestProduct ===");
    }
}
