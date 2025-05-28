package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ImportEntityTest {
  @Test
  public void testGetterSetters() {
    ImportEntity importEntity = new ImportEntity();
    ProductEntity product = new ProductEntity();
    double price = 12.50;
    int quantity = 5;
    LocalDateTime date = LocalDateTime.now();
    importEntity.setProduct(product);
    importEntity.setPdprice(price);
    importEntity.setPdquantity(quantity);
    importEntity.setDate(date);
    assertEquals(product, importEntity.getProduct());
    assertEquals(price, importEntity.getPdprice());
    assertEquals(quantity, importEntity.getPdquantity());
    assertEquals(date, importEntity.getDate());
  }
}