package com.prodmaster.dao;

import com.prodmaster.entity.TechnologyRoute;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TechnologyRouteDAO {

    public void save(TechnologyRoute technologyRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(technologyRoute);
            session.getTransaction().commit();
        }
    }

    public List<TechnologyRoute> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TechnologyRoute", TechnologyRoute.class).list();
        }
    }

    public void delete(TechnologyRoute technologyRoute) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(technologyRoute);
            session.getTransaction().commit();
        }
    }

    public TechnologyRoute getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TechnologyRoute.class, id);
        }
    }
}
