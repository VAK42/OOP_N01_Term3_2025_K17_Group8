package com.vak.oop.controller;

import com.vak.oop.model.Category;
import com.vak.oop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
  private CategoryService service;
  private CategoryController controller;
  private Model model;
  private Category category;

  @BeforeEach
  void setup() {
    service = mock(CategoryService.class);
    controller = new CategoryController(service);
    model = mock(Model.class);
    category = new Category();
    category.setCategoryId(UUID.randomUUID());
    category.setName("Books");
  }

  @Test
  void testList() {
    Page<Category> page = new PageImpl<>(List.of(category));
    when(service.findAll(any(Pageable.class))).thenReturn(page);
    String result = controller.list(0, 5, "", "name", "asc", model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("categories"), eq(List.of(category)));
    verify(model).addAttribute(eq("view"), eq("category/list"));
  }

  @Test
  void testShowCreateForm() {
    String result = controller.showCreateForm(model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("category"), any(Category.class));
    verify(model).addAttribute(eq("view"), eq("category/form"));
  }

  @Test
  void testSaveNewValidCategory() {
    when(service.existsByName("Books")).thenReturn(false);
    Category newCategory = new Category();
    newCategory.setName("Books");
    String result = controller.save(newCategory, model);
    assertEquals("redirect:/categories", result);
    verify(service).save(any(Category.class));
  }

  @Test
  void testShowEditForm() {
    when(service.findById(any(UUID.class))).thenReturn(Optional.of(category));
    String result = controller.showEditForm(category.getCategoryId(), model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("category"), eq(category));
    verify(model).addAttribute(eq("view"), eq("category/form"));
  }

  @Test
  void testDelete() {
    String result = controller.delete(category.getCategoryId(), model);
    assertEquals("redirect:/categories", result);
    verify(service).deleteById(category.getCategoryId());
  }
}