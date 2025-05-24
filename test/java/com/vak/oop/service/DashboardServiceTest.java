package com.vak.oop.service;

import com.vak.oop.dao.DashboardDao;
import com.vak.oop.service.DashboardService.Product;
import com.vak.oop.service.DashboardService.RevenueData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardServiceTest {
  private DashboardDao dashboardDao;
  private DashboardService dashboardService;

  @BeforeEach
  public void setUp() {
    dashboardDao = mock(DashboardDao.class);
    dashboardService = new DashboardService(dashboardDao);
  }

  @Test
  public void testGetRevenueLineData() {
    LocalDate date = LocalDate.of(2025, 1, 1);
    when(dashboardDao.getRevenueLineData(2025)).thenReturn(List.of(new Object[]{1, 1000.0}, new Object[]{2, 2000.5}));
    List<RevenueData> result = dashboardService.getRevenueLineData(date);
    assertEquals(2, result.size());
    assertEquals(new RevenueData(1, 1000.0), result.get(0));
    assertEquals(new RevenueData(2, 2000.5), result.get(1));
  }

  @Test
  public void testGetBestSellerData() {
    LocalDate date = LocalDate.of(2025, 5, 1);
    when(dashboardDao.getBestSellerData(5, 2025)).thenReturn(List.of(new Object[]{"Product A", 30}, new Object[]{"Product B", 20}));
    List<Product> result = dashboardService.getBestSellerData(date);
    assertEquals(2, result.size());
    assertEquals(new Product("Product A", 30), result.get(0));
    assertEquals(new Product("Product B", 20), result.get(1));
  }
}