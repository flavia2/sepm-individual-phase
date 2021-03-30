package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.service.HorseService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class HorseServiceImpl implements HorseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HorseDao dao;
    private final Validator validator;

    @Autowired
    public HorseServiceImpl(HorseDao horseDao, Validator validator) {
        this.dao = horseDao;
        this.validator = validator;
    }


    @Override
    public Horse createHorse(Horse horse) throws ValidationException, PersistenceException {
        LOGGER.trace("Creating a horse with name: {}", horse.getName());
        validator.validateNewHorse(horse);
        try {
            return dao.createHorse(horse);
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Override
    public Horse getHorseById(Long id) throws ValidationException, PersistenceException, NotFoundException {
        LOGGER.trace("Getting the horse with id: {}", id);
        validator.validateId(id);
        try {
            return dao.getHorseById(id);
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }

    @Override
    public Horse editHorse(Horse horse) throws PersistenceException, NotFoundException, ValidationException {
        LOGGER.trace("Editing the horse \"{}\" with id: {}", horse.getName(), horse.getId());
        validator.validateNewHorse(horse);
        try {
            return dao.editHorse(horse);
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }

    @Override
    public void deleteHorse(Long id) throws PersistenceException, NotFoundException, ValidationException {
        LOGGER.trace("Deleting the horse with id: {}", id);
        validator.validateId(id);
        try {
            dao.deleteHorse(id);
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }

    @Override
    public List<Horse> searchHorse(Horse horse) throws PersistenceException, NotFoundException, ValidationException {
        LOGGER.trace("Searching for horses with following search parameters: name ({}), description ({}), birthday ({}), gender ({}), sport ({})", horse.getName(), horse.getDescription(), horse.getBirthday(), horse.getGender(), horse.getSport());
        validator.validateSearch(horse);
        try {
            return dao.searchHorse(horse);
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }

    @Override
    public List<Horse> getAllHorses() throws PersistenceException, NotFoundException {
        LOGGER.trace("Getting all horses from the database.");
        try {
            return dao.getAllHorses();
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }

    @Override
    public List<Horse> getFamilyTreeHorse(Long id, Long generations) throws PersistenceException,NotFoundException,ValidationException {
        LOGGER.trace("Getting family tree for horse with: id({}), generations({})", id, generations);
        validator.validateGenerations(generations);
        try {
            return dao.getFamilyTreeHorse(id, generations);
        } catch (PersistenceException e) {
            throw e;
        } catch (NotFoundException e){
            throw e;
        }
    }
}
