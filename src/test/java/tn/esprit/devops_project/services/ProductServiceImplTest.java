package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addProduct() {
        // Arrange (setup)
        Product productToAdd = new Product();
        Stock existingStock = new Stock();
        existingStock.setIdStock(1L);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(existingStock));
        when(productRepository.save(any(Product.class))).thenReturn(productToAdd);

        // Act (execution)
        Product addedProduct = productService.addProduct(productToAdd, 1L);

        // Assert (verification)
        assertNotNull(addedProduct);
        assertSame(existingStock, addedProduct.getStock());
        verify(stockRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void retrieveProduct() {
        // Mocking the behavior of the repository
        Product mockProduct = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockProduct));

        // Call the method from your service
        Product retrievedProduct = productService.retrieveProduct(1L);

        // Assertions
        assertNotNull(retrievedProduct);
        assertSame(mockProduct, retrievedProduct);
    }

    @Test
    void retreiveAllProduct() {
        // Mocking the behavior of the repository
        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));

        // Call the method from your service
        List<Product> products = productService.retreiveAllProduct();

        // Assertions
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void retrieveProductByCategory() {
        // Mocking the behavior of the repository
        ProductCategory category = ProductCategory.ELECTRONICS;
        when(productRepository.findByCategory(category)).thenReturn(Arrays.asList(new Product(), new Product()));

        // Call the method from your service
        List<Product> productsByCategory = productService.retrieveProductByCategory(category);

        // Assertions
        assertNotNull(productsByCategory);
        assertEquals(2, productsByCategory.size());
    }


    @Test
    void retreiveProductStock() {
        // Mocking the behavior of the repository
        when(productRepository.findByStockIdStock(anyLong())).thenReturn(Arrays.asList(new Product(), new Product()));

        // Call the method from your service
        List<Product> productsInStock = productService.retreiveProductStock(1L);

        // Assertions
        assertNotNull(productsInStock);
        assertEquals(2, productsInStock.size());
    }
}