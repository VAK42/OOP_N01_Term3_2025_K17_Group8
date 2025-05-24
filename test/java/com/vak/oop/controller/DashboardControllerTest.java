package com.vak.oop.controller;

import com.vak.oop.service.DashboardService;
import com.vak.oop.service.DashboardService.Product;
import com.vak.oop.service.DashboardService.RevenueData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardControllerTest {
  private DashboardService dashboardService;
  private DashboardControllerLogic controllerLogic;

  @BeforeEach
  public void setup() {
    dashboardService = mock(DashboardService.class);
    controllerLogic = new DashboardControllerLogic(dashboardService);
  }

  @Test
  public void testUpdateStatistics() {
    LocalDate date = LocalDate.of(2025, 5, 24);
    when(dashboardService.getMonthlyRevenue(date)).thenReturn(1500.0);
    when(dashboardService.getYearlyRevenue(date)).thenReturn(18000.0);
    when(dashboardService.getBestSellerForMonth(date)).thenReturn("ProductA");
    when(dashboardService.getBestSellerForYear(date)).thenReturn("ProductB");
    Stats stats = controllerLogic.updateStatistics(date);
    assertEquals("$1500.00", stats.totalRevenueMonth);
    assertEquals("$18000.00", stats.totalRevenueYear);
    assertEquals("ProductA", stats.bestSellerMonth);
    assertEquals("ProductB", stats.bestSellerYear);
  }

  @Test
  public void testUpdateLineChartData() {
    LocalDate date = LocalDate.of(2025, 5, 24);
    List<RevenueData> revenueDataList = List.of(new RevenueData(1, 100.0), new RevenueData(2, 200.0));
    when(dashboardService.getRevenueLineData(date)).thenReturn(revenueDataList);
    List<XYData> lineChartData = controllerLogic.updateLineChartData(date);
    assertEquals(2, lineChartData.size());
    assertEquals("Jan", lineChartData.get(0).month);
    assertEquals(100.0, lineChartData.get(0).revenue);
    assertEquals("Feb", lineChartData.get(1).month);
    assertEquals(200.0, lineChartData.get(1).revenue);
  }

  @Test
  public void testUpdatePieChartData() {
    LocalDate date = LocalDate.of(2025, 5, 24);
    List<Product> bestSellers = List.of(new Product("Product1", 10), new Product("Product2", 30));
    when(dashboardService.getBestSellerData(date)).thenReturn(bestSellers);
    List<PieData> pieData = controllerLogic.updatePieChartData(date);
    assertEquals(2, pieData.size());
    assertEquals("Product1 25.0%", pieData.get(0).label);
    assertEquals(10, pieData.get(0).quantity);
    assertEquals("Product2 75.0%", pieData.get(1).label);
    assertEquals(30, pieData.get(1).quantity);
  }

  static class Stats {
    String totalRevenueMonth;
    String totalRevenueYear;
    String bestSellerMonth;
    String bestSellerYear;

    public Stats(String totalRevenueMonth, String totalRevenueYear, String bestSellerMonth, String bestSellerYear) {
      this.totalRevenueMonth = totalRevenueMonth;
      this.totalRevenueYear = totalRevenueYear;
      this.bestSellerMonth = bestSellerMonth;
      this.bestSellerYear = bestSellerYear;
    }
  }

  static class XYData {
    String month;
    double revenue;

    public XYData(String month, double revenue) {
      this.month = month;
      this.revenue = revenue;
    }
  }

  static class PieData {
    String label;
    int quantity;

    public PieData(String label, int quantity) {
      this.label = label;
      this.quantity = quantity;
    }
  }

  static class DashboardControllerLogic {
    private final DashboardService dashboardService;

    public DashboardControllerLogic(DashboardService dashboardService) {
      this.dashboardService = dashboardService;
    }

    public Stats updateStatistics(LocalDate date) {
      double monthlyRevenue = dashboardService.getMonthlyRevenue(date);
      double yearlyRevenue = dashboardService.getYearlyRevenue(date);
      String bestMonth = dashboardService.getBestSellerForMonth(date);
      String bestYear = dashboardService.getBestSellerForYear(date);
      return new Stats(String.format("$%.2f", monthlyRevenue), String.format("$%.2f", yearlyRevenue), bestMonth, bestYear);
    }

    public List<XYData> updateLineChartData(LocalDate date) {
      var data = dashboardService.getRevenueLineData(date);
      return data.stream().map(r -> {
        String monthName = java.time.Month.of(r.month()).name().substring(0, 3);
        monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
        return new XYData(monthName, r.revenue());
      }).toList();
    }

    public List<PieData> updatePieChartData(LocalDate date) {
      var bestSellers = dashboardService.getBestSellerData(date);
      int totalQuantity = bestSellers.stream().mapToInt(Product::quantity).sum();
      return bestSellers.stream().map(p -> {
        double percentage = ((double) p.quantity() / totalQuantity) * 100;
        String label = String.format("%s %.1f%%", p.name(), percentage);
        return new PieData(label, p.quantity());
      }).toList();
    }
  }
}