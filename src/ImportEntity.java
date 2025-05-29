import java.time.LocalDateTime;
import java.util.UUID;

public class ImportEntity {

    private UUID ipid;
    private ProductEntity product;
    private UserEntity user;
    private Double pdprice;
    private Integer pdquantity;
    private LocalDateTime date;

    public ImportEntity(UUID ipid, ProductEntity product, UserEntity user, Double pdprice, Integer pdquantity, LocalDateTime date) {
        this.ipid = ipid;
        this.product = product;
        this.user = user;
        this.pdprice = pdprice;
        this.pdquantity = pdquantity;
        this.date = date;
    }

    public UUID getIpid() {
        return ipid;
    }

    public void setIpid(UUID ipid) {
        this.ipid = ipid;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Double getPdprice() {
        return pdprice;
    }

    public void setPdprice(Double pdprice) {
        this.pdprice = pdprice;
    }

    public Integer getPdquantity() {
        return pdquantity;
    }

    public void setPdquantity(Integer pdquantity) {
        this.pdquantity = pdquantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
