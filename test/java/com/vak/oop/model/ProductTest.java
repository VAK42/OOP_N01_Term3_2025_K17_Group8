package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
  @Test
  public void testProductFields() {
    Product product = new Product();
    UUID id = UUID.randomUUID();
    String name = "Laptop";
    BigDecimal price = new BigDecimal("1499.99");
    String info = "High-performance Gaming Laptop";
    int quantity = 10;
    Category category = new Category();
    product.setPdId(id);
    product.setPdName(name);
    product.setPdPrice(price);
    product.setPdInfo(info);
    product.setPdQuantity(quantity);
    product.setCategory(category);
    assertEquals(id, product.getPdId());
    assertEquals(name, product.getPdName());
    assertEquals(price, product.getPdPrice());
    assertEquals(info, product.getPdInfo());
    assertEquals(quantity, product.getPdQuantity());
    assertEquals(category, product.getCategory());
  }
}