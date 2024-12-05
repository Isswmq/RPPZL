package com.prodmaster.service;

import com.prodmaster.dao.ProductDAO;
import com.prodmaster.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }
}
