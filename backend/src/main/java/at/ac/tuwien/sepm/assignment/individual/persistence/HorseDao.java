package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface HorseDao {
    /**
     * Creates a horse with given parameters in database.
     *
     * @param horse that should be created in database.
     * @return horse that was created in database.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Horse createHorse(Horse horse) throws PersistenceException;

    /**
     * Get the horse with given ID.
     *
     * @param id of horse to find.
     * @return the horse with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found in the database.
     */
    Horse getHorseById(Long id) throws PersistenceException, NotFoundException;

    /**
     * Edit an existing horse with given parameters in database.
     *
     * @param horse that should be edited in database.
     * @return horse that was edited in database.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found.
     */
    Horse editHorse(Horse horse) throws PersistenceException, NotFoundException;

    /**
     * Delete the horse with given ID.
     *
     * @param id of the horse which should be deleted.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found.
     */
    void deleteHorse(Long id) throws PersistenceException, NotFoundException;

    /**
     * Search for horses with given parameters.
     *
     * @param horse contains parameters of the horses that should be searched.
     * @return a list of horses that matches the search parameters. If nothing matches all horses in database are returned.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found.
     */
    List<Horse> searchHorse(Horse horse) throws PersistenceException, NotFoundException;

    /**
     * Gets a list of all horses stored in database.
     *
     * @return all horses stored in database.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found.
     */
    List<Horse> getAllHorses() throws PersistenceException, NotFoundException;

    /**
     * Gets a family tree of a specific horse.
     *
     * @param id the id of the horse, whose family tree is wanted.
     * @param generations contains number of generations that should be displayed.
     * @return all family horses of input horse.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException will be thrown if the horse could not be found.
     */
    List<Horse> getFamilyTreeHorse(Long id, Long generations);

}
