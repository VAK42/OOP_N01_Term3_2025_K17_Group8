import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExportList {
  private final List<Export> exports = new ArrayList<>();

  public void add(Export export) {
    exports.add(export);
  }

  public boolean edit(UUID epid, Product newProduct, User newUser, Double newPrice, Integer newQuantity, Double newTotalPrice, LocalDateTime newDate) {
    for (Export e : exports) {
      if (e.getEpid().equals(epid)) {
        e.setProduct(newProduct);
        e.setUser(newUser);
        e.setPdprice(newPrice);
        e.setPdquantity(newQuantity);
        e.setPdtotalprice(newTotalPrice);
        e.setDate(newDate);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID epid) {
    return exports.removeIf(e -> e.getEpid().equals(epid));
  }

  public List<Export> getAll() {
    return new ArrayList<>(exports);
  }
}