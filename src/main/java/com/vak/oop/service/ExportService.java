package com.vak.oop.service;

import com.vak.oop.model.Export;
import com.vak.oop.repository.ExportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExportService {
  private final ExportRepository exportRepository;

  public ExportService(ExportRepository exportRepository) {
    this.exportRepository = exportRepository;
  }

  public List<Export> findAll() {
    return exportRepository.findAll();
  }

  public Optional<Export> findById(UUID id) {
    return exportRepository.findById(id);
  }

  public boolean save(Export export) {
    try {
      exportRepository.save(export);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean delete(UUID id) {
    try {
      exportRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}