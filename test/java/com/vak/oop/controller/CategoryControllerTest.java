package com.vak.oop.controller;

import com.vak.oop.model.Category;
import com.vak.oop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(CategoryControllerTest.MockConfig.class)
public class CategoryControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private CategoryService categoryService;

  @TestConfiguration
  static class MockConfig {
    @Bean
    public CategoryService categoryService() {
      return Mockito.mock(CategoryService.class);
    }
  }

  @Test
  void testList() throws Exception {
    Category category = new Category();
    category.setName("Test");
    Mockito.when(categoryService.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(category)));
    mockMvc.perform(get("/categories")).andExpect(status().isOk()).andExpect(model().attributeExists("categories")).andExpect(view().name("index"));
  }

  @Test
  void testShowCreateForm() throws Exception {
    mockMvc.perform(get("/categories/new")).andExpect(status().isOk()).andExpect(model().attributeExists("category")).andExpect(view().name("index"));
  }

  @Test
  void testSaveNewCategory_Success() throws Exception {
    String name = "New Category";
    Mockito.when(categoryService.existsByName(name)).thenReturn(false);
    mockMvc.perform(post("/categories").param("name", name)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/categories"));
  }

  @Test
  void testSaveNewCategory_Duplicate() throws Exception {
    String name = "Duplicate";
    Mockito.when(categoryService.existsByName(name)).thenReturn(true);
    mockMvc.perform(post("/categories").param("name", name)).andExpect(status().isOk()).andExpect(model().attribute("error", "Sth Went Wrong!")).andExpect(view().name("index"));
  }

  @Test
  void testShowEditForm() throws Exception {
    UUID id = UUID.randomUUID();
    Category category = new Category();
    category.setCategoryId(id);
    category.setName("Edit");
    Mockito.when(categoryService.findById(id)).thenReturn(Optional.of(category));
    mockMvc.perform(get("/categories/edit/" + id)).andExpect(status().isOk()).andExpect(model().attributeExists("category")).andExpect(view().name("index"));
  }

  @Test
  void testDeleteCategory() throws Exception {
    UUID id = UUID.randomUUID();
    mockMvc.perform(get("/categories/delete/" + id)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/categories"));
  }
}