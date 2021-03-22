package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

public interface HorseService {
    /**
     * Creates a horse with given parameters in database.
     *
     * @param horse that should be created in database.
     * @return horse that was created in database.
     */
    Horse createHorse(Horse horse);

    /**
     * Get the horse with given ID.
     *
     * @param id of horse to find.
     * @return the horse with the specified id.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    Horse getHorseById(Long id) throws ValidationException, PersistenceException, NotFoundException;

    /**
     * Edits an existing horse with given parameters in data base.
     *
     * @param horse horse that should be edited in data base.
     * @return horse that was edited in data base.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    Horse editHorse(Horse horse) throws PersistenceException, NotFoundException, ValidationException;
}
