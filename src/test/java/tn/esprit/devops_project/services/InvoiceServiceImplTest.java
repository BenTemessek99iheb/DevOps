package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        // Mocking the behavior of the repository
        when(invoiceRepository.findAll()).thenReturn(Arrays.asList(new Invoice(), new Invoice()));

        // Call the method from your service
        List<Invoice> invoices = invoiceService.retrieveAllInvoices();

        // Assertions
        assertNotNull(invoices);
        assertEquals(2, invoices.size());
    }

    @Test
    void retrieveInvoice() {
        // Mocking the behavior of the repository
        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(mockInvoice));

        // Call the method from your service
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(1L);

        // Assertions
        assertNotNull(retrievedInvoice);
        assertSame(mockInvoice, retrievedInvoice);
    }

}