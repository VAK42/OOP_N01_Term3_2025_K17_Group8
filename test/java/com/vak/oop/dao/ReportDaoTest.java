package com.vak.oop.dao;

import com.vak.oop.model.ReportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportDaoTest {
  private EntityManager em;
  private ReportDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new ReportDao(em);
  }

  @Test
  public void testDeleteReport() {
    ReportEntity report = mock(ReportEntity.class);
    ReportEntity merged = mock(ReportEntity.class);
    EntityTransaction tx = mock(EntityTransaction.class);
    when(em.getTransaction()).thenReturn(tx);
    when(em.merge(report)).thenReturn(merged);
    dao.deleteReport(report);
    verify(tx).begin();
    verify(em).remove(merged);
    verify(tx).commit();
  }

  @Test
  public void testGetReportsByPage() {
    @SuppressWarnings("unchecked") TypedQuery<ReportEntity> query = (TypedQuery<ReportEntity>) mock(TypedQuery.class);
    when(em.createQuery("SELECT p FROM ReportEntity p", ReportEntity.class)).thenReturn(query);
    when(query.setFirstResult(10)).thenReturn(query);
    when(query.setMaxResults(5)).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(ReportEntity.class)));
    List<ReportEntity> result = dao.getReportsByPage(3, 5);
    assertEquals(1, result.size());
  }

  @Test
  public void testGetTotalReportCount() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery("SELECT COUNT(p) FROM ReportEntity p", Long.class)).thenReturn(query);
    when(query.getSingleResult()).thenReturn(12L);
    int count = dao.getTotalReportCount();
    assertEquals(12, count);
  }
}