package com.vak.oop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class DbConnectionTest {
  private static final Logger logger = LoggerFactory.getLogger(DbConnectionTest.class);
  private static EntityManagerFactory entityManagerFactory;

  @BeforeAll
  public static void setup() {
    try {
      entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
      logger.info("EntityManagerFactory Initialized!");
    } catch (Exception e) {
      logger.error("Failed To Create EntityManagerFactory", e);
      fail("Failed To Create EntityManagerFactory: " + e.getMessage());
    }
  }

  @Test
  public void testConnection() {
    assertNotNull(entityManagerFactory, "EntityManagerFactory Should Not Be Null!");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      assertTrue(entityManager.isOpen(), "EntityManager Should Be Open!");
      logger.info("EntityManager Opened Successfully!");
    } catch (Exception e) {
      logger.error("Failed To Open EntityManager", e);
      fail("Failed To Open EntityManager: " + e.getMessage());
    } finally {
      if (entityManager != null) {
        entityManager.close();
        logger.info("EntityManager Closed!");
      }
    }
  }

  @AfterAll
  public static void tearDown() {
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
      logger.info("EntityManagerFactory Closed!");
    }
  }
}