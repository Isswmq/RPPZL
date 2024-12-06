package com.prodmaster.service;

import com.prodmaster.dao.OperationDAO;
import com.prodmaster.entity.Component;
import com.prodmaster.entity.Operation;

import java.util.List;

public class OperationService {

    private final OperationDAO operationDAO = new OperationDAO();

    public void saveOperation(Operation operation) {
        operationDAO.save(operation);
    }

    public List<Operation> getAllOperations() {
        return operationDAO.getAll();
    }

    public void deleteOperation(Integer id) {
        operationDAO.delete(id);
    }

    public Operation findOperationById(Integer id) {
        return operationDAO.findById(id);
    }

    public Operation updateOperation(Integer id, String newName, Integer newDuration) {
        return operationDAO.update(id, newName, newDuration);
    }
}
