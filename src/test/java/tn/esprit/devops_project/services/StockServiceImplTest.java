package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceImplTest {
    @Autowired
    StockRepository stockRepository;

    @Test
    void addStock() {
        // Given
        Stock stock = new Stock();
        stock.setTitle("Test Stock");

        // When
        Stock savedStock = stockRepository.save(stock);

        // Then
        assertNotNull(savedStock);
        assertTrue(savedStock.getIdStock() > 0); // Check that idStock is greater than zero
        assertEquals("Test Stock", savedStock.getTitle());
    }

    @Test
    void retrieveStock() {
        // Given
        Stock stock = new Stock();
        stock.setTitle("Test Stock");
        Stock savedStock = stockRepository.save(stock);
        Long id = savedStock.getIdStock();

        // When
        Stock retrievedStock = stockRepository.findById(id).orElse(null);

        // Then
        assertNotNull(retrievedStock);
        assertEquals(id, retrievedStock.getIdStock());
        assertEquals("Test Stock", retrievedStock.getTitle());
    }

    @Test
    void retrieveAllStock() {
        // Given
        Stock stock1 = new Stock();
        stock1.setTitle("Stock 1");
        Stock stock2 = new Stock();
        stock2.setTitle("Stock 2");
        stockRepository.save(stock1);
        stockRepository.save(stock2);

        // When
        List<Stock> stockList = stockRepository.findAll();

        // Then
        assertTrue(stockList.size() >= 2);
        assertTrue(stockList.stream().anyMatch(stock -> "Stock 1".equals(stock.getTitle())));
        assertTrue(stockList.stream().anyMatch(stock -> "Stock 2".equals(stock.getTitle())));

    }
}