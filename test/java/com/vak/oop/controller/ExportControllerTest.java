package com.vak.oop.controller;

import com.vak.oop.model.Export;
import com.vak.oop.model.User;
import com.vak.oop.service.ExportService;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ExportControllerTest {
  private ExportService exportService;
  private ProductService productService;
  private UserService userService;
  private ExportController controller;
  private Model model;
  private Export export;

  @BeforeEach
  void setup() {
    exportService = mock(ExportService.class);
    productService = mock(ProductService.class);
    userService = mock(UserService.class);
    controller = new ExportController(exportService, productService, userService);
    model = mock(Model.class);
    export = new Export();
    export.setEpId(UUID.randomUUID());
  }

  @Test
  void testListWithoutKeyword() {
    Page<Export> page = new PageImpl<>(List.of(export));
    when(exportService.findAll(any(Pageable.class))).thenReturn(page);
    String result = controller.list(0, 5, "", "date", "desc", model);
    assertEquals("index", result);
    verify(model).addAttribute("exports", List.of(export));
    verify(model).addAttribute("view", "export/list");
  }

  @Test
  void testShowCreateForm() {
    when(productService.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of()));
    String result = controller.showCreateForm(model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("export"), any(Export.class));
    verify(model).addAttribute(eq("products"), anyList());
    verify(model).addAttribute("view", "export/form");
  }

  @Test
  void testSaveExport() {
    User user = new User();
    when(userService.findByRole(eq("admin"), eq(Pageable.unpaged()))).thenReturn(new PageImpl<>(List.of(user)));
    doNothing().when(exportService).save(any(Export.class));
    String result = controller.save(export, model);
    assertEquals("redirect:/exports", result);
    verify(exportService).save(export);
  }

  @Test
  void testShowEditForm() {
    when(exportService.findById(any(UUID.class))).thenReturn(Optional.of(export));
    when(productService.findAll(Pageable.unpaged())).thenReturn(Page.empty());
    String result = controller.showEditForm(UUID.randomUUID(), model);
    assertEquals("index", result);
    verify(model).addAttribute("export", export);
    verify(model).addAttribute(eq("products"), anyList());
    verify(model).addAttribute("view", "export/form");
  }

  @Test
  void testDeleteExport() {
    doNothing().when(exportService).deleteById(any(UUID.class));
    String result = controller.delete(UUID.randomUUID(), model);
    assertEquals("redirect:/exports", result);
    verify(exportService).deleteById(any(UUID.class));
  }
}