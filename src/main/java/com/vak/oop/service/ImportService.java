package com.vak.oop.service;

import com.vak.oop.model.Import;
import com.vak.oop.repository.ImportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImportService {

    private final ImportRepository repo;

    public ImportService(ImportRepository repo) {
        this.repo = repo;
    }

    public List<Import> findAll() {
        return repo.findAll();
    }

    public Optional<Import> findById(UUID id) {
        return repo.findById(id);
    }

    public boolean save(Import imp) {
        try {
            repo.save(imp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(UUID id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
