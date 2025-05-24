package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryEntityTest {
  @Test
  public void testNameGetterSetter() {
    CategoryEntity category = new CategoryEntity();
    category.setName("Electronics");
    assertEquals("Electronics", category.getName());
  }
}