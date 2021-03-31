package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SportDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.SportMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.SportService;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(SportEndpoint.BASE_URL)
public class SportEndpoint {

    static final String BASE_URL = "/sports";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final SportService sportService;
    private final SportMapper sportMapper;

    @Autowired
    public SportEndpoint(SportService sportService, SportMapper sportMapper) {
        this.sportService = sportService;
        this.sportMapper = sportMapper;
    }

    @GetMapping(value = "/{id}")
    public SportDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return sportMapper.entityToDto(sportService.getOneById(id));
        } catch (ServiceException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during getting sport. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during getting sport. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during getting sport. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SportDto post(@RequestBody SportDto sportDto) {
        LOGGER.info("POST " + BASE_URL + "/{}", sportDto.getName());
        try {
            Sport sport = sportMapper.dtoToEntity(sportDto);
            return sportMapper.entityToDto(sportService.createSport(sport));
        } catch (ServiceException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during creating sport. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during creating sport. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }
    @GetMapping
    public List<SportDto> getAllSports() {
        LOGGER.info("GET " + BASE_URL);
        try {
            return sportMapper.entitiesToDto(sportService.getAllSports());
        }catch (ServiceException e) {
            LOGGER.error("[ResponseStatusException]: Error occurred during getting all sports. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        }
    }
}
