package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ExportEntityTest {
  @Test
  public void testGetterSetters() {
    ExportEntity export = new ExportEntity();
    ProductEntity product = new ProductEntity();
    double price = 49.99;
    int quantity = 4;
    LocalDateTime now = LocalDateTime.now();
    export.setProduct(product);
    export.setPdprice(price);
    export.setPdquantity(quantity);
    export.setDate(now);
    assertEquals(product, export.getProduct());
    assertEquals(price, export.getPdprice());
    assertEquals(quantity, export.getPdquantity());
    assertEquals(now, export.getDate());
  }
}