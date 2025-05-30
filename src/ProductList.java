import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductList {
  private final List<Product> products = new ArrayList<>();

  public void add(Product product) {
    products.add(product);
  }

  public boolean edit(UUID pdid, String newName, Double newPrice, Category newCategory, String newInfo, Integer newQuantity) {
    for (Product p : products) {
      if (p.getPdid().equals(pdid)) {
        p.setPdname(newName);
        p.setPdprice(newPrice);
        p.setCategory(newCategory);
        p.setPdinfo(newInfo);
        p.setPdquantity(newQuantity);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID pdid) {
    return products.removeIf(p -> p.getPdid().equals(pdid));
  }

  public List<Product> getAll() {
    return new ArrayList<>(products);
  }
}