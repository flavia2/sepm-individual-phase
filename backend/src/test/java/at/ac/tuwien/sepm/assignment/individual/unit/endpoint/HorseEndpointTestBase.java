package at.ac.tuwien.sepm.assignment.individual.unit.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.HorseEndpoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class HorseEndpointTestBase {

    @Autowired
    HorseEndpoint horseEndpoint;

    @Test
    @DisplayName("Finding horse by non-existing ID should throw NotFoundException")
    public void findingHorseById_nonExisting_shouldThrowNotFoundException() {
        HttpStatus errorStatus = assertThrows(ResponseStatusException.class,
            () -> horseEndpoint.getHorseById(300L)).getStatus();
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        assertEquals(notFoundStatus,errorStatus);
    }

    @Test
    @DisplayName("Deleting horse by non-existing ID should throw NotFoundException")
    public void deletingHorseById_nonExisting_shouldThrowNotFoundException() {
        HttpStatus errorStatus = assertThrows(ResponseStatusException.class,
            () -> horseEndpoint.delete(300L)).getStatus();
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        assertEquals(notFoundStatus,errorStatus);
    }

}
