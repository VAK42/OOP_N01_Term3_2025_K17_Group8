package com.vak.oop.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardDaoTest {
  private EntityManager em;
  private DashboardDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new DashboardDao(em);
  }

  @Test
  public void testGetMonthlyRevenue() {
    @SuppressWarnings("unchecked") TypedQuery<Double> query = (TypedQuery<Double>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Double.class))).thenReturn(query);
    when(query.setParameter(eq("month"), anyInt())).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(1234.56);
    Double result = dao.getMonthlyRevenue(5, 2024);
    assertEquals(1234.56, result);
  }

  @Test
  public void testGetYearlyRevenue() {
    @SuppressWarnings("unchecked") TypedQuery<Double> query = (TypedQuery<Double>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Double.class))).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(9876.54);
    Double result = dao.getYearlyRevenue(2024);
    assertEquals(9876.54, result);
  }

  @Test
  public void testGetBestSellerForMonth() {
    @SuppressWarnings("unchecked") TypedQuery<String> query = (TypedQuery<String>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(String.class))).thenReturn(query);
    when(query.setParameter(eq("month"), anyInt())).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.setMaxResults(1)).thenReturn(query);
    when(query.getResultStream()).thenReturn(Stream.of("BestProduct"));
    String result = dao.getBestSellerForMonth(5, 2024);
    assertEquals("BestProduct", result);
  }

  @Test
  public void testGetBestSellerForMonth_NoResult() {
    @SuppressWarnings("unchecked") TypedQuery<String> query = (TypedQuery<String>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(String.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyInt())).thenReturn(query);
    when(query.setMaxResults(anyInt())).thenReturn(query);
    when(query.getResultStream()).thenReturn(Stream.of());
    String result = dao.getBestSellerForMonth(5, 2024);
    assertEquals("-", result);
  }

  @Test
  public void testGetBestSellerForYear() {
    @SuppressWarnings("unchecked") TypedQuery<String> query = (TypedQuery<String>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(String.class))).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.setMaxResults(1)).thenReturn(query);
    when(query.getResultStream()).thenReturn(Stream.of("TopProduct"));
    String result = dao.getBestSellerForYear(2024);
    assertEquals("TopProduct", result);
  }

  @Test
  public void testGetRevenueLineData() {
    @SuppressWarnings("unchecked") TypedQuery<Object[]> query = (TypedQuery<Object[]>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Object[].class))).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    List<Object[]> expected = Arrays.asList(new Object[]{1, 100.0}, new Object[]{2, 200.0});
    when(query.getResultList()).thenReturn(expected);
    List<Object[]> result = dao.getRevenueLineData(2024);
    assertEquals(expected, result);
  }

  @Test
  public void testGetBestSellerData() {
    @SuppressWarnings("unchecked") TypedQuery<Object[]> query = (TypedQuery<Object[]>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Object[].class))).thenReturn(query);
    when(query.setParameter(eq("month"), anyInt())).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.setMaxResults(3)).thenReturn(query);
    List<Object[]> expected = Arrays.asList(new Object[]{"P1", 30}, new Object[]{"P2", 20});
    when(query.getResultList()).thenReturn(expected);
    List<Object[]> result = dao.getBestSellerData(5, 2024);
    assertEquals(expected, result);
  }

  @Test
  public void testHasAnyData_True() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(query);
    when(query.setParameter(eq("month"), anyInt())).thenReturn(query);
    when(query.setParameter(eq("year"), anyInt())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(5L);
    boolean result = dao.hasAnyData(LocalDate.of(2024, 5, 1));
    assertTrue(result);
  }

  @Test
  public void testHasAnyData_False() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyInt())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(0L);
    boolean result = dao.hasAnyData(LocalDate.of(2024, 5, 1));
    assertFalse(result);
  }
}