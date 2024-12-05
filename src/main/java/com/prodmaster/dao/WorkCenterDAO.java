package com.prodmaster.dao;

import com.prodmaster.entity.WorkCenter;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class WorkCenterDAO {

    public void save(WorkCenter workCenter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(workCenter);
            session.getTransaction().commit();
        }
    }

    public List<WorkCenter> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from WorkCenter", WorkCenter.class).list();
        }
    }

    public void delete(WorkCenter workCenter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(workCenter);
            session.getTransaction().commit();
        }
    }

    public WorkCenter getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(WorkCenter.class, id);
        }
    }
}
