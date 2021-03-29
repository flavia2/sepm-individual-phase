package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HorseDaoTestBase {

    @Autowired
    HorseDao horseDao;

    @Test
    @DisplayName("Finding horse by non-existing ID should throw NotFoundException")
    public void findingHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> horseDao.getHorseById(300L));
    }

    @Test
    @DisplayName("Deleting horse by non-existing ID should throw NotFoundException")
    public void deletingHorseById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> horseDao.deleteHorse(300L));
    }

}
