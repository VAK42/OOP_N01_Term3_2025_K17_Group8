import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImportList {
  private final List<Import> imports = new ArrayList<>();

  public void add(Import imp) {
    imports.add(imp);
  }

  public boolean edit(UUID ipid, Product newProduct, User newUser, Double newPrice, Integer newQuantity, LocalDateTime newDate) {
    for (Import i : imports) {
      if (i.getIpid().equals(ipid)) {
        i.setProduct(newProduct);
        i.setUser(newUser);
        i.setPdprice(newPrice);
        i.setPdquantity(newQuantity);
        i.setDate(newDate);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID ipid) {
    return imports.removeIf(i -> i.getIpid().equals(ipid));
  }

  public List<Import> getAll() {
    return new ArrayList<>(imports);
  }
}