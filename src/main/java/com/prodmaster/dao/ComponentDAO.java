package com.prodmaster.dao;

import com.prodmaster.entity.Component;
import com.prodmaster.util.HibernateUtil;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;


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

    public void deleteComponent(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("delete from Component c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result == 0) {
                throw new EntityNotFoundException("Component with id " + id + " not found.");
            }
        }
    }

    public Component findComponentById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Component component = session.createQuery("from Component c where c.id = :id", Component.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (component == null) {
                throw new EntityNotFoundException("Component with id " + id + " not found.");
            }

            return component;
        }
    }

    public Component updateComponent(Integer id, String newName, int newQuantity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("update Component c set c.name = :name, c.quantity = :quantity where c.id = :id")
                    .setParameter("name", newName)
                    .setParameter("quantity", newQuantity)
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result > 0) {
                return session.get(Component.class, id);
            }

            return null;
        }
    }
}
