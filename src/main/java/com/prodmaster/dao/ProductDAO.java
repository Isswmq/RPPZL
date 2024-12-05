package com.prodmaster.dao;

import com.prodmaster.entity.Product;
import com.prodmaster.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductDAO {
    public void save(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public List<Product> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }
}
