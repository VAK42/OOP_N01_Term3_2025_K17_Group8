package com.vak.oop.dao;

import com.vak.oop.model.CategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryDaoTest {
  @Test
  public void testGetAllCategories() {
    EntityManager em = mock(EntityManager.class);
    @SuppressWarnings("unchecked") TypedQuery<CategoryEntity> query = (TypedQuery<CategoryEntity>) mock(TypedQuery.class);
    List<CategoryEntity> expected = Arrays.asList(new CategoryEntity(), new CategoryEntity());
    when(em.createQuery("SELECT e FROM CategoryEntity e", CategoryEntity.class)).thenReturn(query);
    when(query.getResultList()).thenReturn(expected);
    CategoryDao dao = new CategoryDao(em);
    List<CategoryEntity> actual = dao.getAllCategories();
    assertEquals(expected, actual);
    verify(em).createQuery("SELECT e FROM CategoryEntity e", CategoryEntity.class);
    verify(query).getResultList();
  }
}