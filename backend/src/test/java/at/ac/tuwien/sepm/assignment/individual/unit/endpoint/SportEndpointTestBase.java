package at.ac.tuwien.sepm.assignment.individual.unit.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.endpoint.SportEndpoint;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SportDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class SportEndpointTestBase {

    @Autowired
    SportEndpoint sportEndpoint;

    @Test
    @DisplayName("Finding sport by non-existing ID should throw NotFoundException")
    public void findingSportById_nonExisting_shouldThrowNotFoundException() {
        HttpStatus errorStatus = assertThrows(ResponseStatusException.class,
            () -> sportEndpoint.getOneById(1L)).getStatus();
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        assertEquals(notFoundStatus,errorStatus);
    }

    @Test
    @DisplayName("Creating sport by non-existing ID should throw NotFoundException")
    public void creatingSport_shouldWork() {
        SportDto sport = new SportDto("Trail riding", null);
        SportDto createdSport = sportEndpoint.post(sport);
        assertEquals(sport.getName(),createdSport.getName());
        assertEquals(sport.getDescription(),createdSport.getDescription());
    }


}
