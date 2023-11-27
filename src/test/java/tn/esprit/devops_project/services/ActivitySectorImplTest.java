package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        Long sectorId = 1L;
        ActivitySector mockSector = new ActivitySector();
        mockSector.setIdSecteurActivite(sectorId);
        mockSector.setCodeSecteurActivite("code1");
        mockSector.setLibelleSecteurActivite("Sector 1");


        Mockito.when(activitySectorRepository.findById(sectorId)).thenReturn(Optional.of(mockSector));

        ActivitySector foundSector = activitySectorService.retrieveActivitySector(sectorId);

        assertNotNull(foundSector);
        assertEquals(sectorId, foundSector.getIdSecteurActivite());
        assertEquals("Sector 1", foundSector.getLibelleSecteurActivite());

    }

    @Test
    void deleteActivitySector() {
        Long sectorId = 1L;
        Mockito.doNothing().when(activitySectorRepository).deleteById(sectorId);

        activitySectorService.deleteActivitySector(sectorId);

        Mockito.verify(activitySectorRepository, Mockito.times(1)).deleteById(sectorId);

    }

    @Test
    void updateActivitySector() {
        ActivitySector existingSector = new ActivitySector();
        existingSector.setIdSecteurActivite(1L);
        existingSector.setCodeSecteurActivite("code1");
        existingSector.setLibelleSecteurActivite("sector 1");

        ActivitySector updatedSector = new ActivitySector();
        updatedSector.setIdSecteurActivite(1L);
        updatedSector.setCodeSecteurActivite("Code1");
        updatedSector.setLibelleSecteurActivite("Updated Sector");

        Mockito.when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(existingSector));
        Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(updatedSector);

        ActivitySector result = activitySectorService.updateActivitySector(updatedSector);

        assertNotNull(result);
        assertEquals("Updated Sector", result.getLibelleSecteurActivite());

    }


}