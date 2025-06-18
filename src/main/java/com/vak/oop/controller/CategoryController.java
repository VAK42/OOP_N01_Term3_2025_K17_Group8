package com.vak.oop.controller;

import com.vak.oop.model.Category;
import com.vak.oop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("categories", categoryService.findAll());
    return "category/index";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("category", new Category());
    return "category/form";
  }

  @PostMapping
  public String save(@ModelAttribute Category category, Model model) {
    boolean success = categoryService.save(category);
    if (!success) {
      model.addAttribute("error", "Failed To Save Category!");
      return "category/form";
    }
    return "redirect:/categories";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    categoryService.findById(id).ifPresentOrElse(cat -> model.addAttribute("category", cat), () -> model.addAttribute("error", "Category Not Found!"));
    return "category/form";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    boolean success = categoryService.delete(id);
    if (!success) {
      model.addAttribute("error", "Failed To Delete Category!");
    }
    return "redirect:/categories";
  }
}