package com.vak.oop.service;

import com.vak.oop.model.Product;
import com.vak.oop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Optional<Product> findById(UUID id) {
    return productRepository.findById(id);
  }

  public boolean save(Product product) {
    try {
      productRepository.save(product);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean delete(UUID id) {
    try {
      productRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}