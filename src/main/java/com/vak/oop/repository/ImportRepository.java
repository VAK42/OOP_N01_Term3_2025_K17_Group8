package com.vak.oop.repository;

import com.vak.oop.model.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ImportRepository extends JpaRepository<Import, UUID> {
}
