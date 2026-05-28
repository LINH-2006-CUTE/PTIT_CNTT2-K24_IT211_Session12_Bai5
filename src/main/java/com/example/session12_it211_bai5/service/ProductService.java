package com.example.session12_it211_bai5.service;

import com.example.session12_it211_bai5.model.Product;
import com.example.session12_it211_bai5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> getAll() { return repo.findAll(); }

    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product addProduct(Product p) { return repo.save(p); }

    public void deleteProduct(Long id) { repo.deleteById(id); }
}