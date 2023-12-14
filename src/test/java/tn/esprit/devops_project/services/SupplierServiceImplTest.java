package tn.esprit.devops_project.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceImplTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierServiceImpl supplierService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void retrieveAllSuppliers() {
        // Mocking behavior
        when(supplierRepository.findAll()).thenReturn(Arrays.asList(new Supplier(), new Supplier()));

        // Testing the service method
        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        // Verification
        assertNotNull(suppliers);
        assertEquals(2, suppliers.size());
    }

    @Test
    void addSupplier() {
        // Mocking behavior
        Supplier supplierToAdd = new Supplier();
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplierToAdd);

        // Testing the service method
        Supplier addedSupplier = supplierService.addSupplier(supplierToAdd);

        // Verification
        assertNotNull(addedSupplier);
        assertEquals(supplierToAdd, addedSupplier);
    }

    @Test
    void updateSupplier() {
        // Mocking behavior
        Supplier supplierToUpdate = new Supplier();
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplierToUpdate);

        // Testing the service method
        Supplier updatedSupplier = supplierService.updateSupplier(supplierToUpdate);

        // Verification
        assertNotNull(updatedSupplier);
        assertEquals(supplierToUpdate, updatedSupplier);
    }

    @Test
    void deleteSupplier() {
        // Mocking behavior
        doNothing().when(supplierRepository).deleteById(anyLong());

        // Testing the service method
        assertDoesNotThrow(() -> supplierService.deleteSupplier(1L));

        // Verification
        verify(supplierRepository, times(1)).deleteById(1L);
    }

    @Test
    void retrieveSupplier() {
        // Mocking behavior
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(new Supplier()));

        // Testing the service method
        Supplier retrievedSupplier = supplierService.retrieveSupplier(1L);

        // Verification
        assertNotNull(retrievedSupplier);
    }
}