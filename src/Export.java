import java.time.LocalDateTime;
import java.util.UUID;

public class Export {
  private UUID epid;
  private Product product;
  private User user;
  private Double pdprice;
  private Integer pdquantity;
  private Double pdtotalprice;
  private LocalDateTime date;

  public Export(UUID epid, Product product, User user, Double pdprice, Integer pdquantity, Double pdtotalprice, LocalDateTime date) {
    this.epid = epid;
    this.product = product;
    this.user = user;
    this.pdprice = pdprice;
    this.pdquantity = pdquantity;
    this.pdtotalprice = pdtotalprice;
    this.date = date;
  }

  public UUID getEpid() {
    return epid;
  }

  public void setEpid(UUID epid) {
    this.epid = epid;
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

  public Double getPdtotalprice() {
    return pdtotalprice;
  }

  public void setPdtotalprice(Double pdtotalprice) {
    this.pdtotalprice = pdtotalprice;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}