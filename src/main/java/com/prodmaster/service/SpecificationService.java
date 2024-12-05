package com.prodmaster.service;

import com.prodmaster.dao.SpecificationDAO;
import com.prodmaster.entity.Specification;

import java.util.List;

public class SpecificationService {

    private final SpecificationDAO specificationDAO = new SpecificationDAO();

    public void saveSpecialization(Specification specification) {
        specificationDAO.save(specification);
    }

    public List<Specification> getAllSpecializations() {
        return specificationDAO.getAll();
    }
}

