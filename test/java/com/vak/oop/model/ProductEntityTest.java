package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductEntityTest {
  @Test
  public void testGetterSetters() {
    ProductEntity product = new ProductEntity();
    CategoryEntity category = new CategoryEntity();
    String name = "Laptop";
    Double price = 1500.00;
    String info = "High Performance Laptop";
    Integer quantity = 10;
    product.setPdname(name);
    product.setPdprice(price);
    product.setCategory(category);
    product.setPdinfo(info);
    product.setPdquantity(quantity);
    assertEquals(name, product.getPdname());
    assertEquals(price, product.getPdprice());
    assertEquals(category, product.getCategory());
    assertEquals(info, product.getPdinfo());
    assertEquals(quantity, product.getPdquantity());
  }
}