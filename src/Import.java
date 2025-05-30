import java.time.LocalDateTime;
import java.util.UUID;

public class Import {
  private UUID ipid;
  private Product product;
  private User user;
  private Double pdprice;
  private Integer pdquantity;
  private LocalDateTime date;

  public Import(UUID ipid, Product product, User user, Double pdprice, Integer pdquantity, LocalDateTime date) {
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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
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