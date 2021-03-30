package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(HorseEndpoint.BASE_URL)
public class HorseEndpoint {

    static final String BASE_URL = "/horses";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseService horseService;
    private final HorseMapper horseMapper;

    @Autowired
    public HorseEndpoint(HorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorseDto post(@RequestBody HorseDto horseDto) {
        LOGGER.info("POST " + BASE_URL + "/{}", horseDto.getName());
        try {
            Horse horse = horseMapper.dtoToEntity(horseDto);
            return horseMapper.entityToDto(horseService.createHorse(horse));
        } catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NullPointerException e) {
            LOGGER.error("[NullPointerException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HorseDto getHorseById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return horseMapper.entityToDto(horseService.getHorseById(id));
        }  catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NullPointerException e) {
            LOGGER.error("[NullPointerException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HorseDto edit(@PathVariable Long id, @RequestBody HorseDto horseDto) {
        LOGGER.info("PUT " + BASE_URL + "/{}", id);
        try {
            horseDto.setId(id);
            Horse horse = horseMapper.dtoToEntity(horseDto);
            return horseMapper.entityToDto(horseService.editHorse(horse));
        } catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NullPointerException e) {
            LOGGER.error("[NullPointerException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("DELETE " + BASE_URL + "/{}", id);
        try {
            horseService.deleteHorse(id);
        } catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occurred in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occurred in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HorseDto> searchHorse(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "birthday", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                                 @RequestParam(value = "gender", required = false) Gender gender,
                                 @RequestParam(value = "sport", required = false, defaultValue = "0") @NumberFormat Long sport) {
        if (sport == 0){
            sport = null;
        }
        LOGGER.info("GET (search) " + BASE_URL + "/{}", name);
        try {
            return horseMapper.entitiesToDto(horseService.searchHorse(horseMapper.paramsToEntity(name, description, birthday, gender, sport)));
        } catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occured in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occured in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occured in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<HorseDto> getAllHorses() {
        LOGGER.info("GET " + BASE_URL);
        try {
            return horseMapper.entitiesToDto(horseService.getAllHorses());
        } catch (PersistenceException e) {
            LOGGER.error("[PersistenceException]: Error occured in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.error("[ValidationException]: Error occured in service layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (NotFoundException e) {
            LOGGER.error("[NotFoundException]: Error occured in persistence layer. Full Stacktrace: " + e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
