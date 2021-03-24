package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

import java.util.List;

public interface SportDao {

    /**
     * Get the sport with given ID.
     *
     * @param id of the sport to find.
     * @return the sport with the specified id.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException   will be thrown if the sport could not be found in the database.
     */
    Sport getOneById(Long id);

    /**
     * Creates a sport with given parameters in datastore.
     *
     * @param sport sport that should be created in datastore.
     * @return sport that was created in datastore.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     */
    Sport createSport(Sport sport) throws PersistenceException;

    /**
     * Gets a list of all sports stored in datastore.
     *
     * @return all sports stored in datastore.
     * @throws PersistenceException will be thrown if something goes wrong while accessing the persistent data store.
     * @throws NotFoundException    will be thrown if the sport could not be found in the database.
     */
    List<Sport> getAllSports() throws PersistenceException, NotFoundException;
}
