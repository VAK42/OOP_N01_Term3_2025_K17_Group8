package com.vak.oop.service;

import com.vak.oop.model.Report;
import com.vak.oop.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {
  private final ReportRepository reportRepository;

  public ReportService(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  public List<Report> findAll() {
    return reportRepository.findAll();
  }

  public Optional<Report> findById(UUID id) {
    return reportRepository.findById(id);
  }

  public boolean save(Report report) {
    try {
      reportRepository.save(report);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean delete(UUID id) {
    try {
      reportRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
