package com.vak.oop.controller;

import com.vak.oop.model.Report;
import com.vak.oop.service.ReportService;
import com.vak.oop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/reports")
public class ReportController {
  private final ReportService reportService;
  private final UserService userService;

  public ReportController(ReportService reportService, UserService userService) {
    this.reportService = reportService;
    this.userService = userService;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("reports", reportService.findAll());
    return "report/index";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("report", new Report());
    model.addAttribute("users", userService.findAll());
    return "report/form";
  }

  @PostMapping
  public String save(@ModelAttribute Report report, Model model) {
    boolean success = reportService.save(report);
    if (!success) {
      model.addAttribute("error", "Failed to save report");
      model.addAttribute("users", userService.findAll());
      return "report/form";
    }
    return "redirect:/reports";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable UUID id, Model model) {
    reportService.findById(id).ifPresentOrElse(
      rp -> model.addAttribute("report", rp),
      () -> model.addAttribute("error", "Report not found")
    );
    model.addAttribute("users", userService.findAll());
    return "report/form";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    boolean success = reportService.delete(id);
    if (!success) {
      model.addAttribute("error", "Failed to delete report");
    }
    return "redirect:/reports";
  }
}
