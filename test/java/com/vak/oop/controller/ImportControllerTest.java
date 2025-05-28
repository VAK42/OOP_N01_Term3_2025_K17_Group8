package com.vak.oop.controller;

import com.vak.oop.model.ImportEntity;
import com.vak.oop.model.ProductEntity;
import com.vak.oop.service.ImportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportControllerTest {
  private ImportService importService;
  private ImportControllerLogic controllerLogic;

  @BeforeEach
  public void setup() {
    importService = mock(ImportService.class);
    controllerLogic = new ImportControllerLogic(importService);
  }

  @Test
  public void testGetPaginatedImports() {
    ImportEntity imp = createImport(UUID.randomUUID(), "ProductA", 100.0, 5, LocalDateTime.now());
    when(importService.getTotalImportCount()).thenReturn(4);
    when(importService.getImportsByPage(1, 1)).thenReturn(List.of(imp));
    PaginatedResult result = controllerLogic.getPaginatedImports(1, 1);
    assertEquals(4, result.totalItems);
    assertEquals(4, result.totalPages);
    assertEquals(1, result.currentPage);
    assertEquals("ProductA", result.imports.getFirst().getProduct().getPdname());
  }

  @Test
  public void testGetAllImportsForExcel() {
    ImportEntity i1 = createImport(UUID.randomUUID(), "P1", 10.0, 2, LocalDateTime.now());
    ImportEntity i2 = createImport(UUID.randomUUID(), "P2", 20.0, 1, LocalDateTime.now());
    when(importService.getAllImports()).thenReturn(List.of(i1, i2));
    List<ImportEntity> all = controllerLogic.getAllImportsForExcel();
    assertEquals(2, all.size());
    assertEquals("P1", all.get(0).getProduct().getPdname());
    assertEquals("P2", all.get(1).getProduct().getPdname());
  }

  @Test
  public void testImportDetailsExportPreparation() {
    UUID id = UUID.randomUUID();
    LocalDateTime dt = LocalDateTime.of(2025, 5, 24, 13, 0);
    ImportEntity imp = createImport(id, "Mouse", 40.0, 10, dt);
    ImportDetails details = controllerLogic.getImportDetails(imp);
    assertEquals(id.toString(), details.importId);
    assertEquals("Mouse", details.productName);
    assertEquals("$40.0", details.price);
    assertEquals("10", details.quantity);
    assertEquals("2025-05-24T13:00", details.date);
  }

  private ImportEntity createImport(UUID id, String productName, double price, int quantity, LocalDateTime date) {
    ImportEntity imp = new ImportEntity();
    try {
      var ipidField = ImportEntity.class.getDeclaredField("ipid");
      ipidField.setAccessible(true);
      ipidField.set(imp, id);
      ProductEntity product = new ProductEntity();
      product.setPdname(productName);
      imp.setProduct(product);
      imp.setPdprice(price);
      imp.setPdquantity(quantity);
      imp.setDate(date);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return imp;
  }

  static class ImportControllerLogic {
    private final ImportService importService;

    public ImportControllerLogic(ImportService importService) {
      this.importService = importService;
    }

    public PaginatedResult getPaginatedImports(int page, int itemsPerPage) {
      int totalItems = importService.getTotalImportCount();
      int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
      List<ImportEntity> imports = importService.getImportsByPage(page, itemsPerPage);
      return new PaginatedResult(totalItems, totalPages, page, imports);
    }

    public List<ImportEntity> getAllImportsForExcel() {
      return importService.getAllImports();
    }

    public ImportDetails getImportDetails(ImportEntity imp) {
      return new ImportDetails(imp.getIpid().toString(), imp.getProduct().getPdname(), "$" + imp.getPdprice(), String.valueOf(imp.getPdquantity()), imp.getDate().toString());
    }
  }

  static class PaginatedResult {
    int totalItems;
    int totalPages;
    int currentPage;
    List<ImportEntity> imports;

    public PaginatedResult(int totalItems, int totalPages, int currentPage, List<ImportEntity> imports) {
      this.totalItems = totalItems;
      this.totalPages = totalPages;
      this.currentPage = currentPage;
      this.imports = imports;
    }
  }

  static class ImportDetails {
    String importId;
    String productName;
    String price;
    String quantity;
    String date;

    public ImportDetails(String importId, String productName, String price, String quantity, String date) {
      this.importId = importId;
      this.productName = productName;
      this.price = price;
      this.quantity = quantity;
      this.date = date;
    }
  }
}