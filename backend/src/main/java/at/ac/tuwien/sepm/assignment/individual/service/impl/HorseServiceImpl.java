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
    public Horse createHorse(Horse horse) {
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
}
