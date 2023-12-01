package tn.esprit.devops_project.services;

import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {
    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllOperators() {
        // Arrange
        List<Operator> mockOperators = Arrays.asList(new Operator(), new Operator());
        when(operatorRepository.findAll()).thenReturn(mockOperators);

        // Act
        List<Operator> result = operatorService.retrieveAllOperators();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(operatorRepository).findAll();
    }

    @Test
    void addOperator() {
        Operator mockOperator = new Operator();
        when(operatorRepository.save(any(Operator.class))).thenReturn(mockOperator);

        Operator result = operatorService.addOperator(mockOperator);

        assertNotNull(result);
        verify(operatorRepository).save(any(Operator.class));
    }

    @Test
    void deleteOperator() {
        Long operatorId = 1L;

        operatorService.deleteOperator(operatorId);

        verify(operatorRepository).deleteById(operatorId);

    }

    @Test
    void updateOperator() {
        // Arrange
        Operator mockOperator = new Operator();
        when(operatorRepository.save(any(Operator.class))).thenReturn(mockOperator);

        // Act
        Operator result = operatorService.updateOperator(mockOperator);

        // Assert
        assertNotNull(result);
        verify(operatorRepository).save(any(Operator.class));

    }

    @Test
    void retrieveOperator() {
        Long operatorId = 1L;
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            operatorService.retrieveOperator(operatorId);
            fail("Expected an NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Test passes
        }

    }
}