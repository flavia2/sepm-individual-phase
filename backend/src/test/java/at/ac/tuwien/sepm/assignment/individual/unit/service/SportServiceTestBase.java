package at.ac.tuwien.sepm.assignment.individual.unit.service;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.SportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SportServiceTestBase {

    @Autowired
    SportService sportService;

    @Test
    @DisplayName("Finding sport by non-existing ID should throw NotFoundException")
    public void findingSportById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> sportService.getOneById(1L));
    }

    @Test
    @DisplayName("Creating sport by non-existing ID should throw NotFoundException")
    public void creatingSport_shouldWork() {
        Sport sport = new Sport("Trail riding", null);
        Sport createdSport = sportService.createSport(sport);
        assertEquals(sport.getName(),createdSport.getName());
        assertEquals(sport.getDescription(),createdSport.getDescription());
    }


}
