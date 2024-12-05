package com.prodmaster.dao;

import com.prodmaster.entity.Product;
import com.prodmaster.util.HibernateUtil;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;

import java.math.BigDecimal;
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

    public void delete(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result == 0) {
                throw new EntityNotFoundException("Product with id " + id + " not found.");
            }
        }
    }

    public Product findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.createQuery("from Product p where p.id = :id", Product.class)
                    .setParameter("id", id)
                    .uniqueResult();

            if (product == null) {
                throw new EntityNotFoundException("Product with id " + id + " not found.");
            }

            return product;
        }
    }

    public Product update(Integer id, String newName, String newDescription, BigDecimal newPrice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            int result = session.createQuery("update Product p set p.name = :name, p.description = :description, p.price = :price where p.id = :id")
                    .setParameter("name", newName)
                    .setParameter("description", newDescription)
                    .setParameter("price", newPrice)
                    .setParameter("id", id)
                    .executeUpdate();

            session.getTransaction().commit();

            if (result > 0) {
                return session.get(Product.class, id);
            }

            return null;
        }
    }
}
