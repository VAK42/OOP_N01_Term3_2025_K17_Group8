import java.util.UUID;

public class ProductEntity {

    private UUID pdid;
    private String pdname;
    private Double pdprice;
    private CategoryEntity category;
    private String pdinfo;
    private Integer pdquantity;

    public ProductEntity(UUID pdid, String pdname, Double pdprice, CategoryEntity category, String pdinfo, Integer pdquantity) {
        this.pdid = pdid;
        this.pdname = pdname;
        this.pdprice = pdprice;
        this.category = category;
        this.pdinfo = pdinfo;
        this.pdquantity = pdquantity;
    }

    public UUID getPdid() {
        return pdid;
    }

    public void setPdid(UUID pdid) {
        this.pdid = pdid;
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public Double getPdprice() {
        return pdprice;
    }

    public void setPdprice(Double pdprice) {
        this.pdprice = pdprice;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getPdinfo() {
        return pdinfo;
    }

    public void setPdinfo(String pdinfo) {
        this.pdinfo = pdinfo;
    }

    public Integer getPdquantity() {
        return pdquantity;
    }

    public void setPdquantity(Integer pdquantity) {
        this.pdquantity = pdquantity;
    }
}
