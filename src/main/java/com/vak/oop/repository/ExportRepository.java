package com.vak.oop.repository;

import com.vak.oop.model.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExportRepository extends JpaRepository<Export, UUID> {
  Page<Export> findByProduct_PdNameContainingIgnoreCase(String keyword, Pageable pageable);
}