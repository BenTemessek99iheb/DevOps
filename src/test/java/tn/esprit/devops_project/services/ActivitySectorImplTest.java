package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivitySectorImplTest {
    @InjectMocks
    private ActivitySectorImpl activitySectorService;
    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @Test
    void addActivitySector() {
        // Create a sample ActivitySector object
        ActivitySector sampleActivitySector = new ActivitySector();
        sampleActivitySector.setIdSecteurActivite(1L);
        sampleActivitySector.setCodeSecteurActivite("ABC");
        sampleActivitySector.setLibelleSecteurActivite("Sample Sector");

        // Mock the behavior of the repository save method
        Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(sampleActivitySector);

        // Call the service method to add the activity sector
        ActivitySector addedActivitySector = activitySectorService.addActivitySector(sampleActivitySector);

        // Assertions
        assertNotNull(addedActivitySector);
        assertEquals(1L, addedActivitySector.getIdSecteurActivite());
        assertEquals("ABC", addedActivitySector.getCodeSecteurActivite());
        assertEquals("Sample Sector", addedActivitySector.getLibelleSecteurActivite());

    }

    @Test
    void retrieveAllActivitySectors() {
    }

    @Test
    void deleteActivitySector() {
    }

    @Test
    void updateActivitySector() {
    }

    @Test
    void retrieveActivitySector() {
    }
}