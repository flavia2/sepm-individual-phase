package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;

import java.util.List;

public interface HorseService {
    /**
     * Creates a horse with given parameters in database.
     *
     * @param horse that should be created in database.
     * @return horse that was created in database.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     */
    Horse createHorse(Horse horse) throws ValidationException, ServiceException;

    /**
     * Get the horse with given ID.
     *
     * @param id of horse to find.
     * @return the horse with the specified id.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    Horse getHorseById(Long id) throws ValidationException, ServiceException, NotFoundException;

    /**
     * Edit an existing horse with given parameters in database.
     *
     * @param horse that should be edited in database.
     * @return horse that was edited in database.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    Horse editHorse(Horse horse) throws ValidationException, ServiceException, NotFoundException;

    /**
     * Delete the horse with given ID.
     *
     * @param id of the horse which should be deleted.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    void deleteHorse(Long id) throws ValidationException, ServiceException, NotFoundException;

    /**
     * Search a horse with given parameters.
     *
     * @param horse contains parameters of the horses that should be searched.
     * @return a list of horses that matches the search parameters. If nothing matches all horses in database are returned.
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    List<Horse> searchHorse(Horse horse) throws ValidationException, ServiceException, NotFoundException;

    /**
     * Gets a list of all horses stored in datastore.
     *
     * @return all horses stored in datastore.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     */
    List<Horse> getAllHorses() throws ServiceException;

    /**
     * Generate for specific horse
     * @param id the search parameters for the horses
     * @param generations contains number of generations that should be displayed.
     * @return all horses in tree structure
     * @throws ValidationException  will be thrown if the parameters of horse are not valid.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     * @throws NotFoundException    will be thrown if the horse could not be found.
     */
    List<Horse> getFamilyTreeHorse(Long id, Long generations) throws ValidationException, ServiceException, NotFoundException;

    /**
     * Get the child horse with given parent horse ID.
     *
     * @param id of horse.
     * @return all child horses with the specified parent id.
     * @throws ServiceException will be thrown if if something goes wrong during data processing.
     */
    List<Horse> getAllChildrenByParentId(Long id) throws ServiceException, NotFoundException;
}
