package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;

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
}
