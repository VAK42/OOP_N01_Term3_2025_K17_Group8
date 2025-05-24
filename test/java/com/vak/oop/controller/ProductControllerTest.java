package com.vak.oop.controller;

import com.vak.oop.model.ProductEntity;
import com.vak.oop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ProductControllerTest {
  private ProductService productService;
  private ProductLogic logic;

  @BeforeEach
  public void setup() {
    productService = mock(ProductService.class);
    logic = new ProductLogic(productService);
  }

  @Test
  public void testDeleteProductConfirmed() {
    ProductEntity product = new ProductEntity();
    logic.deleteProduct(product, true);
    verify(productService).deleteProduct(product);
  }

  @Test
  public void testDeleteProductCancelled() {
    ProductEntity product = new ProductEntity();
    logic.deleteProduct(product, false);
    verify(productService, never()).deleteProduct(product);
  }

  @Test
  public void testDeleteProductNull() {
    logic.deleteProduct(null, true);
    verify(productService, never()).deleteProduct(any());
  }

  static class ProductLogic {
    private final ProductService productService;

    public ProductLogic(ProductService productService) {
      this.productService = productService;
    }

    public void deleteProduct(ProductEntity selectedProduct, boolean userConfirmed) {
      if (selectedProduct != null && userConfirmed) {
        productService.deleteProduct(selectedProduct);
      }
    }
  }
}