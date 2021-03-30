package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

import java.util.List;

public interface SportService {


    /**
     * Gets the sport with a given ID.
     *
     * @param id of the sport to find.
     * @return the sport with the specified id.
     * @throws ValidationException  will be thrown if the parameters of sport are not valid.
     * @throws PersistenceException  if something goes wrong during data processing.
     * @throws NotFoundException if the sport could not be found in the system.
     */
    Sport getOneById(Long id) throws ValidationException, PersistenceException,NotFoundException;

    /**
     * Creates a sport with given parameters in database.
     *
     * @param sport that should be created in database.
     * @return sport that was created in database.
     * @throws ValidationException  will be thrown if the parameters of sport are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Sport createSport(Sport sport) throws ValidationException, PersistenceException;
    /**
     * Gets a list of all sports stored in datastore.
     *
     * @return all sports stored in datastore.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    List<Sport> getAllSports() throws PersistenceException;
}
