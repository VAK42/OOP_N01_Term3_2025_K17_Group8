package com.vak.oop.controller;

import com.vak.oop.model.Import;
import com.vak.oop.service.ImportService;
import com.vak.oop.service.ProductService;
import com.vak.oop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/imports")
public class ImportController {

    private final ImportService importService;
    private final ProductService productService;
    private final UserService userService;

    public ImportController(ImportService importService, ProductService productService, UserService userService) {
        this.importService = importService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("imports", importService.findAll());
        return "import/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("import", new Import());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("users", userService.findAll());
        return "import/form";
    }

    @PostMapping
    public String save(@ModelAttribute("import") Import imp, Model model) {
        boolean success = importService.save(imp);
        if (!success) {
            model.addAttribute("error", "Failed to save import.");
            model.addAttribute("products", productService.findAll());
            model.addAttribute("users", userService.findAll());
            return "import/form";
        }
        return "redirect:/imports";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        importService.findById(id).ifPresentOrElse(
                imp -> model.addAttribute("import", imp),
                () -> model.addAttribute("error", "Import not found.")
        );
        model.addAttribute("products", productService.findAll());
        model.addAttribute("users", userService.findAll());
        return "import/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id, Model model) {
        boolean success = importService.delete(id);
        if (!success) {
            model.addAttribute("error", "Failed to delete import.");
        }
        return "redirect:/imports";
    }
}
