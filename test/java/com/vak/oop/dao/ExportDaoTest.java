package com.vak.oop.dao;

import com.vak.oop.model.ExportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExportDaoTest {
  private EntityManager em;
  private ExportDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new ExportDao(em);
  }

  @Test
  public void testGetAllExports() {
    @SuppressWarnings("unchecked") TypedQuery<ExportEntity> query = (TypedQuery<ExportEntity>) mock(TypedQuery.class);
    List<ExportEntity> mockResult = List.of(mock(ExportEntity.class), mock(ExportEntity.class));
    when(em.createQuery("SELECT e FROM ExportEntity e", ExportEntity.class)).thenReturn(query);
    when(query.getResultList()).thenReturn(mockResult);
    List<ExportEntity> result = dao.getAllExports();
    assertEquals(2, result.size());
  }

  @Test
  public void testGetExportsByPage() {
    @SuppressWarnings("unchecked") TypedQuery<ExportEntity> query = (TypedQuery<ExportEntity>) mock(TypedQuery.class);
    List<ExportEntity> mockResult = List.of(mock(ExportEntity.class));
    when(em.createQuery("SELECT p FROM ExportEntity p", ExportEntity.class)).thenReturn(query);
    when(query.setFirstResult(10)).thenReturn(query); // (page 2 - 1) * pageSize 10 = 10
    when(query.setMaxResults(10)).thenReturn(query);
    when(query.getResultList()).thenReturn(mockResult);
    List<ExportEntity> result = dao.getExportsByPage(2, 10);
    assertEquals(1, result.size());
  }

  @Test
  public void testGetTotalExportCount() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery("SELECT COUNT(p) FROM ExportEntity p", Long.class)).thenReturn(query);
    when(query.getSingleResult()).thenReturn(42L);
    int count = dao.getTotalExportCount();
    assertEquals(42, count);
  }
}