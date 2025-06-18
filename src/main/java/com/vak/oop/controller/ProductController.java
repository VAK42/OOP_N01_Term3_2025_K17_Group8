package com.vak.oop.controller;

import com.vak.oop.model.Product;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final CategoryService categoryService;

  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("products", productService.findAll());
    return "product/index";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("categories", categoryService.findAll());
    return "product/form";
  }

  @PostMapping
  public String save(@ModelAttribute Product product, Model model) {
    boolean success = productService.save(product);
    if (!success) {
      model.addAttribute("error", "Failed To Save Product!");
      model.addAttribute("categories", categoryService.findAll());
      return "product/form";
    }
    return "redirect:/products";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    productService.findById(id).ifPresentOrElse(product -> {
      model.addAttribute("product", product);
      model.addAttribute("categories", categoryService.findAll());
    }, () -> model.addAttribute("error", "Product Not Found!"));
    return "product/form";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    boolean success = productService.delete(id);
    if (!success) {
      model.addAttribute("error", "Failed To Delete Product!");
    }
    return "redirect:/products";
  }
}