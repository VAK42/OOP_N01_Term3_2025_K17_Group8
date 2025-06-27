package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ImportTest {
  @Test
  public void testImportFields() {
    Import imp = new Import();
    UUID id = UUID.randomUUID();
    Product product = new Product();
    User user = new User();
    BigDecimal price = new BigDecimal("75.25");
    int quantity = 10;
    Timestamp now = new Timestamp(System.currentTimeMillis());
    imp.setIpId(id);
    imp.setProduct(product);
    imp.setPdPrice(price);
    imp.setPdQuantity(quantity);
    imp.setUser(user);
    imp.setDate(now);
    assertEquals(id, imp.getIpId());
    assertEquals(product, imp.getProduct());
    assertEquals(price, imp.getPdPrice());
    assertEquals(quantity, imp.getPdQuantity());
    assertEquals(user, imp.getUser());
    assertEquals(now, imp.getDate());
  }
}