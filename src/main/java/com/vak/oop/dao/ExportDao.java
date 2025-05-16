package com.vak.oop.dao;

import com.vak.oop.Service.ExportService;
import com.vak.oop.model.ExportEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ExportDao (EntityManager entityManager){
    public List<ExportEntity> getAllExports(){
        String jpql = "SELECT e FROM ExportEntity e";
        TypedQuery<ExportEntity> query  = entityManager.createQuery(jpql, ExportEntity.class);
        return query.getResultList();
    }
    public List<ExportEntity> getExportsByPage(int page, int pageSize){
        TypedQuery<ExportEntity> query= entityManager.createQuery("SELLECT p FROM ExportEntity p", ExportEntity.class);
    }
}
