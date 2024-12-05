package com.prodmaster.dao;

import com.prodmaster.entity.Component;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;


import java.util.List;

public class ComponentDAO {
    public void save(Component component) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(component);
            session.getTransaction().commit();
        }
    }

    public List<Component> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Component", Component.class).list();
        }
    }
}
