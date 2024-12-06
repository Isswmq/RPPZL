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

    public void deleteComponent(Integer id) {
        componentDAO.deleteComponent(id);
    }

    public Component findComponentById(Integer id) {
        return componentDAO.findComponentById(id);
    }

    public Component updateComponent(Integer id, String newName, int newQuantity) {
        return componentDAO.updateComponent(id, newName, newQuantity);
    }

}
