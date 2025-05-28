package com.vak.oop.controller;

import com.vak.oop.model.ImportEntity;
import com.vak.oop.model.ProductEntity;
import com.vak.oop.service.ImportService;
import com.vak.oop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportCreateControllerTest {
  private ImportService importService;
  private ProductService productService;
  private ImportCreateLogic logic;

  @BeforeEach
  public void setup() {
    importService = mock(ImportService.class);
    productService = mock(ProductService.class);
    logic = new ImportCreateLogic(importService, productService);
  }

  @Test
  public void testMissingInputs() {
    String message = logic.validateAndSubmit(null, "", "");
    assertEquals("Please Select A Product Or Enter Both Quantity And Price!", message);
  }

  @Test
  public void testInvalidQuantity() {
    ProductEntity product = new ProductEntity();
    String message = logic.validateAndSubmit(product, "abc", "100");
    assertEquals("Invalid Quantity! Enter A Positive Number!", message);
  }

  @Test
  public void testInvalidPrice() {
    ProductEntity product = new ProductEntity();
    String message = logic.validateAndSubmit(product, "10", "-50");
    assertEquals("Invalid Price! Enter A Non-negative Number.", message);
  }

  @Test
  public void testSuccessfulImport() {
    ProductEntity product = new ProductEntity();
    product.setPdquantity(5);
    product.setPdprice(100.0);
    String result = logic.validateAndSubmit(product, "3", "120");
    assertEquals("Success", result);
    assertEquals(8, product.getPdquantity());
    verify(productService).updateProduct(product);
    verify(importService).saveImport(any(ImportEntity.class));
  }

  static class ImportCreateLogic {
    private final ImportService importService;
    private final ProductService productService;

    public ImportCreateLogic(ImportService importService, ProductService productService) {
      this.importService = importService;
      this.productService = productService;
    }

    public String validateAndSubmit(ProductEntity selectedProduct, String quantityText, String priceText) {
      if (selectedProduct == null || quantityText == null || quantityText.isEmpty() || priceText == null || priceText.isEmpty()) {
        return "Please Select A Product Or Enter Both Quantity And Price!";
      }
      int importQuantity;
      double importPrice;
      try {
        importQuantity = Integer.parseInt(quantityText);
        if (importQuantity <= 0) throw new NumberFormatException();
      } catch (NumberFormatException e) {
        return "Invalid Quantity! Enter A Positive Number!";
      }
      try {
        importPrice = Double.parseDouble(priceText);
        if (importPrice < 0) throw new NumberFormatException();
      } catch (NumberFormatException e) {
        return "Invalid Price! Enter A Non-negative Number.";
      }
      selectedProduct.setPdquantity(selectedProduct.getPdquantity() + importQuantity);
      productService.updateProduct(selectedProduct);
      ImportEntity importEntity = new ImportEntity();
      importEntity.setProduct(selectedProduct);
      importEntity.setPdquantity(importQuantity);
      importEntity.setPdprice(importPrice);
      importEntity.setDate(LocalDateTime.now());
      importService.saveImport(importEntity);
      return "Success";
    }
  }
}