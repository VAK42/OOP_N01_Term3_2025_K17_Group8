package com.vak.oop.service;

import com.vak.oop.model.Category;
import com.vak.oop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
  private final CategoryRepository repo;

  public CategoryService(CategoryRepository repo) {
    this.repo = repo;
  }

  public List<Category> findAll() {
    return repo.findAll();
  }

  public Optional<Category> findById(UUID id) {
    return repo.findById(id);
  }

  public boolean save(Category category) {
    try {
      repo.save(category);
      return true;
    } catch (Exception e) {
      System.err.println("Error Saving Category: " + e.getMessage());
      return false;
    }
  }

  public boolean delete(UUID id) {
    try {
      repo.deleteById(id);
      return true;
    } catch (Exception e) {
      System.err.println("Error Deleting Category: " + e.getMessage());
      return false;
    }
  }
}