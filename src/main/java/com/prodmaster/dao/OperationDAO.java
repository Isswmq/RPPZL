package com.prodmaster.dao;

import com.prodmaster.entity.Operation;
import com.prodmaster.entity.Product;
import com.prodmaster.util.HibernateUtil;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;

public class OperationDAO {

    public void save(Operation operation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(operation);
            session.getTransaction().commit();
        }
    }

    public List<Operation> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Operation ", Operation.class).list();
        }
    }

    public void delete(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("delete from Operation o where o.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result == 0) {
                throw new EntityNotFoundException("Operation with id " + id + " not found.");
            }
        }
    }

    public Operation findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Operation operation = session.createQuery("from Operation p where p.id = :id", Operation.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (operation == null) {
                throw new EntityNotFoundException("Operation with id " + id + " not found.");
            }

            return operation;
        }
    }

    public Operation update(Integer id, String newName, Integer newDuration) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("update Operation p set p.name = :name, p.duration = :duration where p.id = :id")
                    .setParameter("name", newName)
                    .setParameter("duration", newDuration)
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result > 0) {
                return session.get(Operation.class, id);
            }

            return null;
        }
    }
}
