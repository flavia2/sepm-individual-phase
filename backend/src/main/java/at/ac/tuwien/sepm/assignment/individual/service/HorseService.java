package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;

public interface HorseService {
    /**
     * Creates a horse with given parameters in database.
     *
     * @param horse that should be created in database.
     * @return horse that was created in database.
     */
    Horse createHorse(Horse horse);
}
