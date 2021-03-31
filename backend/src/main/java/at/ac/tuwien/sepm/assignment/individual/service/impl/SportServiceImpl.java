package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import at.ac.tuwien.sepm.assignment.individual.service.SportService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportServiceImpl implements SportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final SportDao dao;
    private final Validator validator;

    @Autowired
    public SportServiceImpl(SportDao sportDao, Validator validator) {
        this.dao = sportDao;
        this.validator = validator;
    }

    @Override
    public Sport getOneById(Long id) throws ValidationException, ServiceException, NotFoundException {
        LOGGER.trace("Getting the spot with id: {}", id);
        validator.validateSportId(id);
        try{
            return dao.getOneById(id);
        }catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Sport createSport(Sport sport) throws ValidationException, ServiceException {
        LOGGER.trace("Creating a sport with name: {}", sport.getName());
        validator.validateNewSport(sport);
        try{
            return dao.createSport(sport);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Sport> getAllSports() throws ServiceException{
        LOGGER.trace("Getting all sports.");
        try {
            return dao.getAllSports();
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
