package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

import java.util.List;

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
     * Edit an existing horse with given parameters in database.
     *
     * @param horse that should be edited in database.
     * @return horse that was edited in database.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    Horse editHorse(Horse horse) throws PersistenceException, NotFoundException, ValidationException;

    /**
     * Delete the horse with given ID.
     *
     * @param id of the horse which should be deleted.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    void deleteHorse(Long id) throws PersistenceException, NotFoundException, ValidationException;

    /**
     * Search a horse with given parameters.
     *
     * @param horse contains parameters of the horses that should be searched.
     * @return a list of horses that matches the search parameters. If nothing matches all horses in database are returned.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    List<Horse> searchHorse(Horse horse) throws PersistenceException, NotFoundException, ValidationException;

    /**
     * Gets a list of all horses stored in datastore.
     *
     * @return all horses stored in datastore.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    List<Horse> getAllHorses();
}
