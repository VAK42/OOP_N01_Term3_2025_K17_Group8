package com.vak.oop.repository;

import com.vak.oop.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
  Page<Report> findByRpNameContainingIgnoreCase(String keyword, Pageable pageable);
}