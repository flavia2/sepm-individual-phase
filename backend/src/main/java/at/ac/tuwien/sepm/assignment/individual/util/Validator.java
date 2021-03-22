package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

import at.ac.tuwien.sepm.assignment.individual.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());



    public void validateNewSport(Sport sport) {
    }

    public void validateNewHorse(Horse horse) throws ValidationException, NullPointerException {
        LOGGER.trace("Validating parameters of new horse created.");

        if (horse.getName() == null && horse.getBirthday() == null && horse.getDescription() == null && horse.getGender() == null && horse.getSport() == null) {
            throw new NullPointerException("The horse needs parameters: name, gender and birthday.");
        }
        if (horse.getName() == null) {
            throw new NullPointerException("The horse needs a name.");
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
        if (horse.getGender() == null){
            throw new NullPointerException("The horse needs a gender.");
        }
        if (horse.getBirthday() == null){
            throw new NullPointerException("The horse needs a birthday.");
        }
        if (horse.getBirthday() != null) {
            if (horse.getBirthday().isBefore(LocalDate.now().minusYears(30)) || horse.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("The horse´s age is not valid.");
            }
        }
    }
}
