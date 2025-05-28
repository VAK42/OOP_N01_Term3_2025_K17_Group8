package com.vak.oop.dao;

import com.vak.oop.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDaoTest {
  private EntityManager em;
  private UserDao dao;

  @BeforeEach
  public void setUp() {
    em = mock(EntityManager.class);
    dao = new UserDao(em);
  }

  @SuppressWarnings("unchecked")
  private TypedQuery<UserEntity> mockUserEntityQuery() {
    return mock(TypedQuery.class);
  }

  @SuppressWarnings("unchecked")
  private TypedQuery<Long> mockLongQuery() {
    return mock(TypedQuery.class);
  }

  @SuppressWarnings("unchecked")
  private TypedQuery<String> mockStringQuery() {
    return mock(TypedQuery.class);
  }

  @Test
  public void testIsUser() {
    TypedQuery<UserEntity> query = mockUserEntityQuery();
    when(em.createQuery(anyString(), eq(UserEntity.class))).thenReturn(query);
    when(query.setParameter(eq("username"), any())).thenReturn(query);
    when(query.setMaxResults(1)).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(UserEntity.class)));
    assertTrue(dao.isUser("john"));
  }

  @Test
  public void testLoginValidate() {
    TypedQuery<UserEntity> query = mockUserEntityQuery();
    when(em.createQuery(anyString(), eq(UserEntity.class))).thenReturn(query);
    when(query.setParameter(eq("username"), any())).thenReturn(query);
    when(query.setParameter(eq("password"), any())).thenReturn(query);
    when(query.setParameter(eq("role"), any())).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(UserEntity.class)));
    assertTrue(dao.loginValidate("john", "secret"));
  }

  @Test
  public void testGetUserEmailByUsername() {
    TypedQuery<String> query = mockStringQuery();
    when(em.createQuery(anyString(), eq(String.class))).thenReturn(query);
    when(query.setParameter(eq("username"), any())).thenReturn(query);
    when(query.getSingleResult()).thenReturn("john@example.com");
    assertEquals("john@example.com", dao.getUserEmailByUsername("john"));
  }

  @Test
  public void testGetUserIdByUsername() {
    TypedQuery<Long> query = mockLongQuery();
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(query);
    when(query.setParameter(eq("username"), any())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(1L);
    assertEquals(1L, dao.getUserIdByUsername("john"));
  }

  @Test
  public void testGetUserIdByUsernameReturnsNull() {
    TypedQuery<Long> query = mockLongQuery();
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(query);
    when(query.setParameter(eq("username"), any())).thenReturn(query);
    when(query.getSingleResult()).thenThrow(new RuntimeException());
    assertNull(dao.getUserIdByUsername("nonexistent"));
  }

  @Test
  public void testDeleteUser() {
    UserEntity user = mock(UserEntity.class);
    var transaction = mock(jakarta.persistence.EntityTransaction.class);
    when(em.getTransaction()).thenReturn(transaction);
    when(em.merge(user)).thenReturn(user);
    dao.deleteUser(user);
    verify(em).remove(user);
    verify(transaction).begin();
    verify(transaction).commit();
  }

  @Test
  public void testGetUsersByPage() {
    TypedQuery<UserEntity> query = mockUserEntityQuery();
    when(em.createQuery(anyString(), eq(UserEntity.class))).thenReturn(query);
    when(query.setParameter(eq("role"), any())).thenReturn(query);
    when(query.setFirstResult(anyInt())).thenReturn(query);
    when(query.setMaxResults(anyInt())).thenReturn(query);
    when(query.getResultList()).thenReturn(List.of(mock(UserEntity.class)));
    List<UserEntity> users = dao.getUsersByPage(1, 10);
    assertEquals(1, users.size());
  }

  @Test
  public void testGetTotalUserCount() {
    TypedQuery<Long> query = mockLongQuery();
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(query);
    when(query.getSingleResult()).thenReturn(5L);
    assertEquals(5, dao.getTotalUserCount());
  }
}