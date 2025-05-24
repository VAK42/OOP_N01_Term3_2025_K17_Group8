package com.vak.oop.controller;

import com.vak.oop.model.ExportEntity;
import com.vak.oop.model.ProductEntity;
import com.vak.oop.model.UserEntity;
import com.vak.oop.service.ExportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExportControllerTest {
  private ExportService exportService;
  private ExportControllerLogic controllerLogic;

  @BeforeEach
  public void setup() {
    exportService = mock(ExportService.class);
    controllerLogic = new ExportControllerLogic(exportService);
  }

  @Test
  public void testGetPaginatedExports() {
    ExportEntity e1 = createExport(UUID.randomUUID(), "ProductA", "User1", 10.0, 2, LocalDateTime.now());
    when(exportService.getTotalExportCount()).thenReturn(5);
    when(exportService.getExportsByPage(1, 1)).thenReturn(List.of(e1));
    PaginatedResult result = controllerLogic.getPaginatedExports(1, 1);
    assertEquals(5, result.totalItems);
    assertEquals(5, result.totalPages);
    assertEquals(1, result.currentPage);
    assertEquals(1, result.exports.size());
    assertEquals("ProductA", result.exports.getFirst().getProduct().getPdname());
  }

  @Test
  public void testGetAllExportsForExcel() {
    ExportEntity e1 = createExport(UUID.randomUUID(), "ProductA", "User1", 10.0, 2, LocalDateTime.now());
    ExportEntity e2 = createExport(UUID.randomUUID(), "ProductB", "User2", 15.0, 1, LocalDateTime.now());
    when(exportService.getAllExports()).thenReturn(List.of(e1, e2));
    List<ExportEntity> result = controllerLogic.getAllExportsForExcel();
    assertEquals(2, result.size());
    assertEquals("User1", result.get(0).getUser().getUsername());
    assertEquals("ProductB", result.get(1).getProduct().getPdname());
  }

  @Test
  public void testExportDetailsForPdf() {
    UUID id = UUID.randomUUID();
    LocalDateTime dt = LocalDateTime.of(2025, 5, 24, 12, 0);
    ExportEntity export = createExport(id, "Widget", "JohnDoe", 50.0, 3, dt);
    ExportDetails details = controllerLogic.getExportDetails(export);
    assertEquals("JohnDoe", details.username);
    assertEquals("Widget", details.productName);
    assertEquals("$50.0", details.price);
    assertEquals("3", details.quantity);
    assertEquals("$150.0", details.totalPrice);
    assertEquals("2025-05-24T12:00", details.date);
    assertEquals(id.toString(), details.exportId);
  }

  private ExportEntity createExport(UUID id, String productName, String username, double price, int quantity, LocalDateTime date) {
    ExportEntity export = new ExportEntity();
    try {
      var epidField = ExportEntity.class.getDeclaredField("epid");
      epidField.setAccessible(true);
      epidField.set(export, id);
      var product = new ProductEntity();
      product.setPdname(productName);
      export.setProduct(product);
      var user = new UserEntity();
      user.setUsername(username);
      var userField = ExportEntity.class.getDeclaredField("user");
      userField.setAccessible(true);
      userField.set(export, user);
      export.setPdprice(price);
      export.setPdquantity(quantity);
      export.setDate(date);
      var totalField = ExportEntity.class.getDeclaredField("pdtotalprice");
      totalField.setAccessible(true);
      totalField.set(export, price * quantity);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return export;
  }

  static class ExportControllerLogic {
    private final ExportService exportService;

    public ExportControllerLogic(ExportService exportService) {
      this.exportService = exportService;
    }

    public PaginatedResult getPaginatedExports(int page, int itemsPerPage) {
      int totalItems = exportService.getTotalExportCount();
      int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
      List<ExportEntity> exports = exportService.getExportsByPage(page, itemsPerPage);
      return new PaginatedResult(totalItems, totalPages, page, exports);
    }

    public List<ExportEntity> getAllExportsForExcel() {
      return exportService.getAllExports();
    }

    public ExportDetails getExportDetails(ExportEntity export) {
      return new ExportDetails(export.getEpid().toString(), export.getUser().getUsername(), export.getProduct().getPdname(), "$" + export.getPdprice(), String.valueOf(export.getPdquantity()), "$" + export.getPdtotalprice(), export.getDate().toString());
    }
  }

  static class PaginatedResult {
    int totalItems;
    int totalPages;
    int currentPage;
    List<ExportEntity> exports;

    public PaginatedResult(int totalItems, int totalPages, int currentPage, List<ExportEntity> exports) {
      this.totalItems = totalItems;
      this.totalPages = totalPages;
      this.currentPage = currentPage;
      this.exports = exports;
    }
  }

  static class ExportDetails {
    String exportId;
    String username;
    String productName;
    String price;
    String quantity;
    String totalPrice;
    String date;

    public ExportDetails(String exportId, String username, String productName, String price, String quantity, String totalPrice, String date) {
      this.exportId = exportId;
      this.username = username;
      this.productName = productName;
      this.price = price;
      this.quantity = quantity;
      this.totalPrice = totalPrice;
      this.date = date;
    }
  }
}