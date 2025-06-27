package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ExportTest {
  @Test
  public void testExportFieldsAndTotalPriceCalculation() {
    Export export = new Export();
    UUID epId = UUID.randomUUID();
    Product product = new Product();
    User user = new User();
    BigDecimal price = new BigDecimal("100.50");
    int quantity = 3;
    export.setEpId(epId);
    export.setProduct(product);
    export.setPdPrice(price);
    export.setPdQuantity(quantity);
    export.setUser(user);
    export.calculateTotalPrice();
    assertEquals(epId, export.getEpId());
    assertEquals(product, export.getProduct());
    assertEquals(price, export.getPdPrice());
    assertEquals(quantity, export.getPdQuantity());
    assertEquals(user, export.getUser());
    assertNotNull(export.getDate());
    assertEquals(new BigDecimal("301.50"), export.getPdTotalPrice());
  }
}