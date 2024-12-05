package com.prodmaster.dao;

import com.prodmaster.entity.Operation;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;

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
}
