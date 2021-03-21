package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
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
}
