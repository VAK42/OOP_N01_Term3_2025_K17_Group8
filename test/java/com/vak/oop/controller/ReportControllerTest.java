package com.vak.oop.controller;

import com.vak.oop.model.ReportEntity;
import com.vak.oop.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ReportControllerTest {
  private ReportService reportService;
  private ReportLogic logic;

  @BeforeEach
  public void setup() {
    reportService = mock(ReportService.class);
    logic = new ReportLogic(reportService);
  }

  @Test
  public void testDeleteReportConfirmed() {
    ReportEntity report = new ReportEntity();
    logic.deleteReport(report, true);
    verify(reportService).deleteReport(report);
  }

  @Test
  public void testDeleteReportCancelled() {
    ReportEntity report = new ReportEntity();
    logic.deleteReport(report, false);
    verify(reportService, never()).deleteReport(report);
  }

  @Test
  public void testDeleteReportNull() {
    logic.deleteReport(null, true);
    verify(reportService, never()).deleteReport(any());
  }

  static class ReportLogic {
    private final ReportService reportService;

    public ReportLogic(ReportService reportService) {
      this.reportService = reportService;
    }

    public void deleteReport(ReportEntity selected, boolean userConfirmed) {
      if (selected != null && userConfirmed) {
        reportService.deleteReport(selected);
      }
    }
  }
}