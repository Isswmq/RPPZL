package com.prodmaster.service;

import com.prodmaster.dao.OperationDAO;
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

}
