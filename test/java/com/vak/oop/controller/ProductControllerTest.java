package com.vak.oop.controller;

import com.vak.oop.model.Product;
import com.vak.oop.model.Category;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
  private ProductService productService;
  private ProductController controller;
  private Model model;
  private Product product;

  @BeforeEach
  void setup() {
    productService = mock(ProductService.class);
    CategoryService categoryService = mock(CategoryService.class);
    controller = new ProductController(productService, categoryService);
    model = mock(Model.class);
    product = new Product();
    product.setPdId(null);
    product.setPdName("iPhone");
    product.setPdPrice(BigDecimal.valueOf(999));
    product.setPdQuantity(10);
    product.setPdInfo("Smartphone");
    product.setCategory(new Category());
  }

  @Test
  void testSaveNewProductSuccess() {
    when(productService.existsByPdName("iPhone")).thenReturn(false);
    String result = controller.save(product, model);
    assertEquals("redirect:/products", result);
    verify(productService).save(product);
  }
}