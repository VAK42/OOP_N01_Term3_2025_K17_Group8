package com.vak.oop.dao;

import com.vak.oop.model.ImportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportDaoTest {
  private EntityManager em;
  private ImportDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new ImportDao(em);
  }

  @Test
  public void testGetAllImports() {
    @SuppressWarnings("unchecked") TypedQuery<ImportEntity> query = (TypedQuery<ImportEntity>) mock(TypedQuery.class);
    List<ImportEntity> mockResult = List.of(mock(ImportEntity.class), mock(ImportEntity.class));
    when(em.createQuery("SELECT i FROM ImportEntity i", ImportEntity.class)).thenReturn(query);
    when(query.getResultList()).thenReturn(mockResult);
    List<ImportEntity> result = dao.getAllImports();
    assertEquals(2, result.size());
  }

  @Test
  public void testSaveImport() {
    ImportEntity importEntity = mock(ImportEntity.class);
    EntityTransaction tx = mock(EntityTransaction.class);
    when(em.getTransaction()).thenReturn(tx);
    dao.saveImport(importEntity);
    verify(tx).begin();
    verify(em).persist(importEntity);
    verify(tx).commit();
  }

  @Test
  public void testGetImportsByPage() {
    @SuppressWarnings("unchecked") TypedQuery<ImportEntity> query = (TypedQuery<ImportEntity>) mock(TypedQuery.class);
    List<ImportEntity> mockResult = List.of(mock(ImportEntity.class));
    when(em.createQuery("SELECT p FROM ImportEntity p", ImportEntity.class)).thenReturn(query);
    when(query.setFirstResult(5)).thenReturn(query); // page=2, pageSize=5
    when(query.setMaxResults(5)).thenReturn(query);
    when(query.getResultList()).thenReturn(mockResult);
    List<ImportEntity> result = dao.getImportsByPage(2, 5);
    assertEquals(1, result.size());
  }

  @Test
  public void testGetTotalImportCount() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery("SELECT COUNT(p) FROM ImportEntity p", Long.class)).thenReturn(query);
    when(query.getSingleResult()).thenReturn(10L);
    int count = dao.getTotalImportCount();
    assertEquals(10, count);
  }
}