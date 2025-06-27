package com.vak.oop.controller;

import com.vak.oop.model.Import;
import com.vak.oop.model.Product;
import com.vak.oop.model.User;
import com.vak.oop.service.ImportService;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ImportControllerTest {
  private ImportService importService;
  private ProductService productService;
  private UserService userService;
  private ImportController controller;
  private Model model;
  private Import imp;

  @BeforeEach
  void setup() {
    importService = mock(ImportService.class);
    productService = mock(ProductService.class);
    userService = mock(UserService.class);
    controller = new ImportController(importService, productService, userService);
    model = mock(Model.class);
    imp = new Import();
    imp.setIpId(UUID.randomUUID());
    imp.setPdPrice(BigDecimal.valueOf(100));
    imp.setPdQuantity(5);
    imp.setDate(new Timestamp(System.currentTimeMillis()));
    imp.setProduct(new Product());
    imp.setUser(new User());
  }

  @Test
  void testList() {
    Page<Import> page = new PageImpl<>(List.of(imp));
    when(importService.findAll(any(Pageable.class))).thenReturn(page);
    String result = controller.list(0, 5, "", "date", "desc", model);
    assertEquals("index", result);
    verify(model).addAttribute("imports", List.of(imp));
    verify(model).addAttribute("view", "import/list");
  }

  @Test
  void testShowCreateForm() {
    when(productService.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(List.of(new Product())));
    String result = controller.showCreateForm(model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("import"), any(Import.class));
    verify(model).addAttribute(eq("products"), anyList());
    verify(model).addAttribute("view", "import/form");
  }

  @Test
  void testSave() {
    when(userService.findByRole(eq("admin"), eq(Pageable.unpaged()))).thenReturn(new PageImpl<>(List.of(new User())));
    doNothing().when(importService).save(any(Import.class));
    String result = controller.save(imp, model);
    assertEquals("redirect:/imports", result);
    verify(importService).save(imp);
  }

  @Test
  void testShowEditForm() {
    when(importService.findById(any(UUID.class))).thenReturn(Optional.of(imp));
    when(productService.findAll(Pageable.unpaged())).thenReturn(Page.empty());
    String result = controller.showEditForm(UUID.randomUUID(), model);
    assertEquals("index", result);
    verify(model).addAttribute("import", imp);
    verify(model).addAttribute("view", "import/form");
  }

  @Test
  void testDelete() {
    doNothing().when(importService).deleteById(any(UUID.class));
    String result = controller.delete(UUID.randomUUID(), model);
    assertEquals("redirect:/imports", result);
    verify(importService).deleteById(any(UUID.class));
  }
}