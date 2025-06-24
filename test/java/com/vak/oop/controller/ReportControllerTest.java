package com.vak.oop.controller;

import com.vak.oop.model.Report;
import com.vak.oop.model.User;
import com.vak.oop.service.ReportService;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportControllerTest {
  @Mock
  private ReportService reportService;
  @Mock
  private UserService userService;
  @Mock
  private org.springframework.ui.Model model;
  @InjectMocks
  private ReportController reportController;
  private AutoCloseable closeable;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void testList_NoKeyword_ReturnsIndex() {
    Page<Report> page = new PageImpl<>(List.of(new Report()));
    when(reportService.findAll(any(Pageable.class))).thenReturn(page);
    String view = reportController.list(0, 5, "", "rpName", "asc", model);
    assertEquals("index", view);
    verify(model).addAttribute(eq("reports"), anyList());
    verify(model).addAttribute("currentPage", 0);
    verify(model).addAttribute("totalPages", 1);
    verify(model).addAttribute("keyword", "");
    verify(model).addAttribute("field", "rpName");
    verify(model).addAttribute("direction", "asc");
    verify(model).addAttribute("reverseSortDir", "desc");
    verify(model).addAttribute("view", "report/list");
  }

  @Test
  void testList_WithKeyword_ReturnsIndex() {
    Page<Report> page = new PageImpl<>(List.of(new Report()));
    when(reportService.search(eq("keyword"), any(Pageable.class))).thenReturn(page);
    String view = reportController.list(1, 10, "keyword", "rpName", "desc", model);
    assertEquals("index", view);
    verify(reportService).search(eq("keyword"), any(Pageable.class));
    verify(model).addAttribute(eq("reports"), anyList());
    verify(model).addAttribute("reverseSortDir", "asc");
    verify(model).addAttribute("view", "report/list");
  }

  @Test
  void testShowCreateForm() {
    String view = reportController.showCreateForm(model);
    assertEquals("index", view);
    verify(model).addAttribute(eq("report"), any(Report.class));
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testSave_RedirectsOnSuccess() {
    Report report = new Report();
    User admin = new User();
    Page<User> adminPage = new PageImpl<>(List.of(admin));
    when(userService.findByRole(eq("admin"), any(Pageable.class))).thenReturn(adminPage);
    String view = reportController.save(report, model);
    assertEquals("redirect:/reports", view);
    verify(reportService).save(report);
    assertEquals(admin, report.getUser());
  }

  @Test
  void testSave_Exception_ReturnsIndex() {
    Report report = new Report();
    doThrow(RuntimeException.class).when(reportService).save(any(Report.class));
    String view = reportController.save(report, model);
    assertEquals("index", view);
    verify(model).addAttribute("error", "Sth Went Wrong!");
    verify(model).addAttribute("report", report);
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testShowEditForm_Success() {
    UUID id = UUID.randomUUID();
    Report report = new Report();
    when(reportService.findById(id)).thenReturn(Optional.of(report));
    String view = reportController.showEditForm(id, model);
    assertEquals("index", view);
    verify(model).addAttribute("report", report);
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testShowEditForm_Exception() {
    UUID id = UUID.randomUUID();
    when(reportService.findById(id)).thenThrow(new RuntimeException("Not Found!"));
    String view = reportController.showEditForm(id, model);
    assertEquals("index", view);
    verify(model).addAttribute("error", "Sth Went Wrong!");
    verify(model).addAttribute("view", "report/form");
  }

  @Test
  void testDelete_Success() {
    UUID id = UUID.randomUUID();
    String view = reportController.delete(id, model);
    assertEquals("redirect:/reports", view);
    verify(reportService).deleteById(id);
  }

  @Test
  void testDelete_Exception() {
    UUID id = UUID.randomUUID();
    doThrow(new RuntimeException("Error!")).when(reportService).deleteById(id);
    String view = reportController.delete(id, model);
    assertEquals("index", view);
    verify(model).addAttribute("error", "Sth Went Wrong!");
    verify(model).addAttribute("view", "report/list");
  }
}