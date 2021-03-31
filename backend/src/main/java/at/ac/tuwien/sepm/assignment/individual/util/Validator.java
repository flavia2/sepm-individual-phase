package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final HorseDao horseDao;

    public Validator(HorseDao horseDao) {
        this.horseDao = horseDao;
    }

    public void validateNewSport(Sport sport) throws ValidationException {
        LOGGER.trace("Validating parameters of new sport created.");
        if (sport.getName() == null) {
            throw new ValidationException("The sport needs a name.");
        }
        if (sport.getName() != null) {
            if (sport.getName().length() == 0 || sport.getName().length() > 255) {
                throw new ValidationException("The name of the sport must be in the range between 1 and 255 characters.");
            }
        }
        if (sport.getDescription() != null) {
            if (sport.getDescription().length() <= 0 || sport.getDescription().length() > 500) {
                throw new ValidationException("The description of the sport must be in the range between 1 and 500 characters.\"");
            }
        }
    }

    public void validateNewHorse(Horse horse) throws ValidationException {
        LOGGER.trace("Validating parameters of new horse created.");

        if (horse.getName() == null && horse.getBirthday() == null && horse.getDescription() == null && horse.getGender() == null && horse.getSport() == null) {
            throw new ValidationException("The horse needs parameters: name, gender and birthday.");
        }
        if (horse.getName() == null) {
            throw new ValidationException("The horse needs a name.");
        }
        if (horse.getName() != null) {
            if (horse.getName().length() == 0 || horse.getName().length() > 255) {
                throw new ValidationException("The name of the horse must be in the range between 1 and 255 characters.");
            }
        }
        if (horse.getDescription() != null) {
            if (horse.getName().length() == 0 || horse.getDescription().length() > 500) {
                throw new ValidationException("The description of the horse must be in the range between 1 and 500 characters.");
            }
        }
        if (horse.getBirthday() == null) {
            throw new ValidationException("The horse needs a birthday.");
        }
        if (horse.getBirthday() != null) {
            if (horse.getBirthday().isBefore(LocalDate.now().minusYears(30)) || horse.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("The horse´s age is not valid.");
            }
        }
        if (horse.getGender() == null) {
            throw new ValidationException("The horse needs a gender.");
        }

    }

    public void validateId(Long id) throws ValidationException {
        LOGGER.trace("Validating id of the horse.");
        if (id == null) {
            throw new ValidationException("The horse needs an id.");
        }
        if (id <= 0) {
            throw new ValidationException("The id of the horse must be greater than 0.");
        }
    }

    public void validateSearch(Horse horse) throws ValidationException {
        LOGGER.trace("Validating search parameters of a horse.");
        if (horse.getName() != null) {
            if (horse.getName().length() == 0 || horse.getName().length() > 255) {
                throw new ValidationException("The name of the horse must be in the range between 1 and 255 characters.");
            }
        }
        if (horse.getDescription() != null) {
            if (horse.getDescription().length() > 500) {
                throw new ValidationException("The description can only have up to 500 characters.");
            }
        }

        if (horse.getBirthday() != null) {
            if (horse.getBirthday().isBefore(LocalDate.of(1990, 1, 1)) || horse.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("The horse´s age is not valid.");
            }
        }

    }

    public void validateGenerations(Long generations) throws ValidationException {
        LOGGER.trace("Validating generations for family tree request.");
        if (generations != null) {
            if (generations <= 0) {
                throw new ValidationException("Generations must be greater than 0.");
            }
        }
    }

    public void validateParents(Horse horse) throws ValidationException {
        LOGGER.trace("Validating parameters of parents.");
        if (horse.getMother() != null) {
            try {
                Horse parent1 = horseDao.getHorseById(horse.getMother());
                if (parent1.getBirthday().isAfter(horse.getBirthday())) {
                    throw new ValidationException("Horse must be younger than mother's age: " + parent1.getBirthday());
                }
            } catch (PersistenceException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        if (horse.getFather() != null) {
            try {
                Horse parent2 = horseDao.getHorseById(horse.getFather());
                if (parent2.getBirthday().isAfter(horse.getBirthday())) {
                    throw new ValidationException("Horse must be younger than father's age: " + parent2.getBirthday());
                }
            } catch (PersistenceException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        if (horse.getMother() != null && horse.getFather() != null) {
            try {
                Horse parent1 = horseDao.getHorseById(horse.getMother());
                Horse parent2 = horseDao.getHorseById(horse.getFather());
                if (parent1.getGender().equals(parent2.getGender())) {
                    throw new ValidationException("Parents must have differnt gender.");
                }
            }catch (PersistenceException e) {
                throw new ServiceException(e.getMessage(), e);
            }

        }
    }

    public void validateSportId(Long id) throws ValidationException {
        LOGGER.trace("Validating id of the sport.");
        if (id == null) {
            throw new ValidationException("The sport needs an id.");
        }
        if (id <= 0) {
            throw new ValidationException("The id of the sport must be greater than 0.");
        }
    }
}
