package com.vak.oop.controller;

import com.vak.oop.model.Category;
import com.vak.oop.service.CategoryService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @GetMapping
  public String list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "name") String field, @RequestParam(defaultValue = "asc") String direction, Model model) {
    try {
      Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<Category> categoryPage = keyword.isBlank() ? service.findAll(pageable) : service.search(keyword, pageable);
      model.addAttribute("categories", categoryPage.getContent());
      model.addAttribute("currentPage", page);
      model.addAttribute("totalPages", categoryPage.getTotalPages());
      model.addAttribute("keyword", keyword);
      model.addAttribute("field", field);
      model.addAttribute("direction", direction);
      model.addAttribute("reverseSortDir", direction.equals("asc") ? "desc" : "asc");
      model.addAttribute("view", "category/list");
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("view", "error");
    }
    return "index";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("category", new Category());
    model.addAttribute("view", "category/form");
    return "index";
  }

  @PostMapping
  public String save(@ModelAttribute Category category, Model model) {
    try {
      boolean isNew = (category.getCategoryId() == null);
      boolean nameExisted = service.existsByName(category.getName());
      if (isNew && nameExisted) {
        model.addAttribute("error", "Sth Went Wrong!");
        model.addAttribute("category", category);
        model.addAttribute("view", "category/form");
        return "index";
      }
      if (!isNew) {
        Category existing = service.findById(category.getCategoryId()).orElseThrow();
        if (!existing.getName().equalsIgnoreCase(category.getName()) && nameExisted) {
          model.addAttribute("error", "Sth Went Wrong!");
          model.addAttribute("category", category);
          model.addAttribute("view", "category/form");
          return "index";
        }
      }
      service.save(category);
      return "redirect:/categories";
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("view", "category/form");
      return "index";
    }
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable UUID id, Model model) {
    try {
      model.addAttribute("category", service.findById(id).orElseThrow());
      model.addAttribute("view", "category/form");
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("view", "error");
    }
    return "index";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    try {
      service.deleteById(id);
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("view", "error");
      return "index";
    }
    return "redirect:/categories";
  }

  @GetMapping("/excel")
  public void exportToExcel(HttpServletResponse response) {
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=VAK.xlsx");
    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("VAK");
      Font headerFont = workbook.createFont();
      headerFont.setColor(IndexedColors.WHITE.getIndex());
      CellStyle headerStyle = workbook.createCellStyle();
      headerStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
      headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      headerStyle.setFont(headerFont);
      headerStyle.setAlignment(HorizontalAlignment.CENTER);
      headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      headerStyle.setBorderTop(BorderStyle.THIN);
      headerStyle.setBorderBottom(BorderStyle.THIN);
      headerStyle.setBorderLeft(BorderStyle.THIN);
      headerStyle.setBorderRight(BorderStyle.THIN);
      CellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      cellStyle.setBorderTop(BorderStyle.THIN);
      cellStyle.setBorderBottom(BorderStyle.THIN);
      cellStyle.setBorderLeft(BorderStyle.THIN);
      cellStyle.setBorderRight(BorderStyle.THIN);
      Row header = sheet.createRow(0);
      String[] headers = {"ID", "Name"};
      for (int i = 0; i < headers.length; i++) {
        Cell cell = header.createCell(i);
        cell.setCellValue(headers[i]);
        cell.setCellStyle(headerStyle);
      }
      List<Category> categories = service.findAll();
      int rowIdx = 1;
      for (Category category : categories) {
        Row row = sheet.createRow(rowIdx++);
        Cell idCell = row.createCell(0);
        idCell.setCellValue(category.getCategoryId().toString());
        idCell.setCellStyle(cellStyle);
        Cell nameCell = row.createCell(1);
        nameCell.setCellValue(category.getName());
        nameCell.setCellStyle(cellStyle);
      }
      sheet.autoSizeColumn(0);
      sheet.autoSizeColumn(1);
      workbook.write(response.getOutputStream());
    } catch (IOException e) {
      logger.error(String.valueOf(e));
    }
  }

  @GetMapping("/pdf/{id}")
  public void exportSingleCategoryPDF(@PathVariable UUID id, jakarta.servlet.http.HttpServletResponse response) {
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=VAKpdf");
    try {
      com.lowagie.text.Document document = new com.lowagie.text.Document(com.lowagie.text.PageSize.A6);
      com.lowagie.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
      document.open();
      com.lowagie.text.Font titleFont = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA_BOLD, 16);
      com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("VAK", titleFont);
      title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
      title.setSpacingAfter(15);
      document.add(title);
      Category category = service.findById(id).orElseThrow();
      com.lowagie.text.Font bodyFont = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA, 12);
      document.add(new com.lowagie.text.Paragraph("ID: " + category.getCategoryId(), bodyFont));
      document.add(new com.lowagie.text.Paragraph("Name: " + category.getName(), bodyFont));
      document.add(new com.lowagie.text.Paragraph("Date: " + java.time.LocalDateTime.now(), bodyFont));
      document.close();
    } catch (Exception e) {
      logger.error(String.valueOf(e));
    }
  }
}