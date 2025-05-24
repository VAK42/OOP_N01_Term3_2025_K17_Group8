package com.vak.oop.dao;

import com.vak.oop.model.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductDaoTest {
  private EntityManager em;
  private ProductDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new ProductDao(em);
  }

  @Test
  public void testGetAllProducts() {
    @SuppressWarnings("unchecked") TypedQuery<ProductEntity> query = (TypedQuery<ProductEntity>) mock(TypedQuery.class);
    when(em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class)).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(ProductEntity.class)));
    List<ProductEntity> result = dao.getAllProducts();
    assertEquals(1, result.size());
  }

  @Test
  public void testSaveProduct() {
    ProductEntity product = mock(ProductEntity.class);
    EntityTransaction tx = mock(EntityTransaction.class);
    when(em.getTransaction()).thenReturn(tx);
    dao.saveProduct(product);
    verify(tx).begin();
    verify(em).persist(product);
    verify(tx).commit();
  }

  @Test
  public void testUpdateProduct() {
    ProductEntity product = mock(ProductEntity.class);
    EntityTransaction tx = mock(EntityTransaction.class);
    when(em.getTransaction()).thenReturn(tx);
    dao.updateProduct(product);
    verify(tx).begin();
    verify(em).merge(product);
    verify(tx).commit();
  }

  @Test
  public void testDeleteProduct() {
    ProductEntity product = mock(ProductEntity.class);
    EntityTransaction tx = mock(EntityTransaction.class);
    ProductEntity merged = mock(ProductEntity.class);
    when(em.getTransaction()).thenReturn(tx);
    when(em.merge(product)).thenReturn(merged);
    dao.deleteProduct(product);
    verify(tx).begin();
    verify(em).remove(merged);
    verify(tx).commit();
  }

  @Test
  public void testGetProductsByPage() {
    @SuppressWarnings("unchecked") TypedQuery<ProductEntity> query = (TypedQuery<ProductEntity>) mock(TypedQuery.class);
    when(em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class)).thenReturn(query);
    when(query.setFirstResult(5)).thenReturn(query);
    when(query.setMaxResults(5)).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(ProductEntity.class)));
    List<ProductEntity> result = dao.getProductsByPage(2, 5);
    assertEquals(1, result.size());
  }

  @Test
  public void testGetTotalProductCount() {
    @SuppressWarnings("unchecked") TypedQuery<Long> query = (TypedQuery<Long>) mock(TypedQuery.class);
    when(em.createQuery("SELECT COUNT(p) FROM ProductEntity p", Long.class)).thenReturn(query);
    when(query.getSingleResult()).thenReturn(20L);
    int count = dao.getTotalProductCount();
    assertEquals(20, count);
  }

  @Test
  public void testGetFilteredProducts() {
    @SuppressWarnings("unchecked") TypedQuery<ProductEntity> query = (TypedQuery<ProductEntity>) mock(TypedQuery.class);
    String name = "laptop";
    String jpql = "SELECT p FROM ProductEntity p WHERE (:name IS NULL OR LOWER(p.pdname) LIKE LOWER(CONCAT('%', :name, '%'))) ORDER BY p.pdprice DESC";
    when(em.createQuery(jpql, ProductEntity.class)).thenReturn(query);
    when(query.setParameter("name", name)).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(ProductEntity.class)));
    List<ProductEntity> result = dao.getFilteredProducts(name, "Price DESC");
    assertEquals(1, result.size());
  }
}