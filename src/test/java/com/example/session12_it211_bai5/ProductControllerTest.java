package com.example.session12_it211_bai5;

import com.example.session12_it211_bai5.controller.ProductController;
import com.example.session12_it211_bai5.exception.GlobalExceptionHandler;
import com.example.session12_it211_bai5.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(GlobalExceptionHandler.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetById_404() throws Exception {
        when(service.getById(999L)).thenThrow(new RuntimeException("Not found"));
        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound());
    }
}