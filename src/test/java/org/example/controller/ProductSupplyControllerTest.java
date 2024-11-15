package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductSupplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateSupply() throws Exception {
        mockMvc.perform(post("/api/supplies")
                        .param("documentName", "Supply-001")
                        .param("productId", "1")
                        .param("quantity", "100"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.documentName").value("Supply-001"))
                .andExpect(jsonPath("$.quantity").value(100));
    }
}

