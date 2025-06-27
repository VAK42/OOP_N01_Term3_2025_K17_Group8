package com.vak.oop.controller;

import com.vak.oop.model.Report;
import com.vak.oop.model.User;
import com.vak.oop.service.ReportService;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportControllerTest {
  private ReportService reportService;
  private UserService userService;
  private ReportController controller;
  private Model model;
  private Report report;

  @BeforeEach
  void setup() {
    reportService = mock(ReportService.class);
    userService = mock(UserService.class);
    controller = new ReportController(reportService, userService);
    model = mock(Model.class);
    report = new Report();
    report.setReportId(UUID.randomUUID());
    report.setRpName("Monthly Report");
    report.setRpInfo("Detail Analysis");
  }

  @Test
  void testList() {
    Page<Report> page = new PageImpl<>(List.of(report));
    when(reportService.findAll(any(Pageable.class))).thenReturn(page);
    String result = controller.list(0, 5, "", "rpName", "asc", model);
    assertEquals("index", result);
    verify(model).addAttribute("reports", List.of(report));
    verify(model).addAttribute("view", "report/list");
  }

  @Test
  void testShowCreateForm() {
    String result = controller.showCreateForm(model);
    assertEquals("index", result);
    verify(model).addAttribute(eq("report"), any(Report.class));
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testSaveReport() {
    User user = new User();
    when(userService.findByRole(eq("admin"), eq(Pageable.unpaged()))).thenReturn(new PageImpl<>(List.of(user)));
    doNothing().when(reportService).save(any(Report.class));
    String result = controller.save(report, model);
    assertEquals("redirect:/reports", result);
    verify(reportService).save(report);
  }

  @Test
  void testShowEditForm() {
    when(reportService.findById(any(UUID.class))).thenReturn(Optional.of(report));
    String result = controller.showEditForm(UUID.randomUUID(), model);
    assertEquals("index", result);
    verify(model).addAttribute("report", report);
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testDeleteReport() {
    doNothing().when(reportService).deleteById(any(UUID.class));
    String result = controller.delete(UUID.randomUUID(), model);
    assertEquals("redirect:/reports", result);
    verify(reportService).deleteById(any(UUID.class));
  }
}