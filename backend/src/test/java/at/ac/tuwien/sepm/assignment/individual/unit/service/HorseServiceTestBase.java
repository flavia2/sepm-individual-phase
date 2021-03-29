package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HorseServiceTestBase {

    @Autowired
    HorseService horseService;

    @Test
    @DisplayName("Finding horse by non-existing ID should throw NotFoundException")
    public void findingHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> horseService.getHorseById(300L));
    }

    @Test
    @DisplayName("Deleting horse by non-existing ID should throw NotFoundException")
    public void deletingHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> horseService.deleteHorse(300L));
    }

}
