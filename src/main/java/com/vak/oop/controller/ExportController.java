package com.vak.oop.controller;

import com.vak.oop.model.Export;
import com.vak.oop.service.ExportService;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/exports")
public class ExportController {
  private final ExportService exportService;
  private final ProductService productService;
  private final UserService userService;

  public ExportController(ExportService exportService, ProductService productService, UserService userService) {
    this.exportService = exportService;
    this.productService = productService;
    this.userService = userService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("exports", exportService.findAll());
    return "export/index";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("export", new Export());
    model.addAttribute("products", productService.findAll());
    model.addAttribute("users", userService.findAll());
    return "export/form";
  }

  @PostMapping
  public String save(@ModelAttribute Export export, Model model) {
    boolean success = exportService.save(export);
    if (!success) {
      model.addAttribute("error", "Failed to save export");
      model.addAttribute("products", productService.findAll());
      model.addAttribute("users", userService.findAll());
      return "export/form";
    }
    return "redirect:/exports";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    exportService.findById(id).ifPresentOrElse(
      exp -> model.addAttribute("export", exp),
      () -> model.addAttribute("error", "Export not found")
    );
    model.addAttribute("products", productService.findAll());
    model.addAttribute("users", userService.findAll());
    return "export/form";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    boolean success = exportService.delete(id);
    if (!success) {
      model.addAttribute("error", "Failed to delete export");
    }
    return "redirect:/exports";
  }
}