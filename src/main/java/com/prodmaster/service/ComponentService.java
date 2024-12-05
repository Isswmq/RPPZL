package com.prodmaster.service;

import com.prodmaster.dao.ComponentDAO;
import com.prodmaster.entity.Component;

import java.util.List;

public class ComponentService {
    private final ComponentDAO componentDAO = new ComponentDAO();

    public void saveComponent(Component component) {
        componentDAO.save(component);
    }

    public List<Component> getAllComponents() {
        return componentDAO.getAll();
    }
}
