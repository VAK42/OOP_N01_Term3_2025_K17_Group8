package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
  @Test
  public void testCategoryGettersAndSetters() {
    Category category = new Category();
    UUID id = UUID.randomUUID();
    String name = "Electronics";
    category.setCategoryId(id);
    category.setName(name);
    assertEquals(id, category.getCategoryId());
    assertEquals(name, category.getName());
  }
}