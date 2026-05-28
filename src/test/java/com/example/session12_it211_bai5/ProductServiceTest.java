package com.example.session12_it211_bai5;

import com.example.session12_it211_bai5.model.Product;
import com.example.session12_it211_bai5.repository.ProductRepository;
import com.example.session12_it211_bai5.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @Test
    void testGetById_Found() {
        Product product = new Product(1L, "Laptop", 1000.0, 5);
        when(repo.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.getById(1L);
        assertEquals("Laptop", result.getName());
    }

    @Test
    void testGetById_NotFound() {
        when(repo.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getById(999L));
    }
}