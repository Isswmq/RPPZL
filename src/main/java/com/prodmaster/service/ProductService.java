package com.prodmaster.service;

import com.prodmaster.dao.ProductDAO;
import com.prodmaster.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    public void deleteProduct(Integer id) {
        productDAO.delete(id);
    }

    public Product getProductById(Integer id) {
        return productDAO.findById(id);
    }

    public Product updateProduct(Integer id, String newName, String newDescription, BigDecimal newPrice) {
        return productDAO.update(id, newName, newDescription, newPrice);
    }
}
