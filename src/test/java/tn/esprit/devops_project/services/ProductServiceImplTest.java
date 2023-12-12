package tn.esprit.devops_project.services;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void addProduct() {
        Long stockId = 1L;
        Stock mockStock = new Stock();
        mockStock.setIdStock(stockId);
        mockStock.setTitle("test stock");

        Product product = new Product();
        product.setTitle("Test Product");

        // Mock the behavior of stockRepository to return mockStock when findById is called
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(mockStock));

        // Mock the behavior of productRepository.save to return the product
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the method under test
        Product addedProduct = productService.addProduct(product, stockId);

        // Assert that the product is not null and is returned with the correct stock
        assertNotNull(addedProduct);
        assertEquals(mockStock, addedProduct.getStock());
        verify(stockRepository).findById(stockId);
        verify(productRepository).save(product);

    }

    @Test
    void retrieveProduct() {
    }

    @Test
    void retreiveAllProduct() {
    }

    @Test
    void retrieveProductByCategory() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void retreiveProductStock() {
    }
}