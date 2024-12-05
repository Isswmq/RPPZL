package com.prodmaster.dao;

import com.prodmaster.entity.Specification;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SpecificationDAO {
    public void save(Specification component) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(component);
            session.getTransaction().commit();
        }
    }

    public List<Specification> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Specification", Specification.class).list();
        }
    }
}
