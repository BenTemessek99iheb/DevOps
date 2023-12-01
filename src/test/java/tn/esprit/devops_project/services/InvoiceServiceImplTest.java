package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)

class InvoiceServiceImplTest {
    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    void retrieveAllInvoices() {
        List<Invoice> mockInvoices = Arrays.asList(new Invoice(), new Invoice());
        Mockito.when(invoiceRepository.findAll()).thenReturn(mockInvoices);

        List<Invoice> result = invoiceService.retrieveAllInvoices();

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    void cancelInvoice() {
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        mockInvoice.setArchived(false);

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));

        invoiceService.cancelInvoice(invoiceId);

        Mockito.verify(invoiceRepository).save(mockInvoice);
        assertEquals(true, mockInvoice.getArchived());
    }

    @Test
    void retrieveInvoice() {
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();

        Mockito.when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));

        Invoice result = invoiceService.retrieveInvoice(invoiceId);

        assertNotNull(result);
        Mockito.verify(invoiceRepository).findById(invoiceId);
    }

    @Test
    void getInvoicesBySupplier() {

    }

    @Test
    void assignOperatorToInvoice() {
    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
    }
}