package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class HorseJdbcDao implements HorseDao {
    private static final String TABLE_NAME = "Horse";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HorseJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Horse createHorse(Horse horse) throws PersistenceException {
        LOGGER.trace("Creating a horse with name: {}", horse.getName());
        final String createSql = "INSERT INTO " + TABLE_NAME + " (name, description, birthday, gender, sport) VALUES (?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement pSt = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
                pSt.setString(1, horse.getName());
                pSt.setObject(2, horse.getDescription());
                pSt.setObject(3, horse.getBirthday());
                pSt.setString(4, String.valueOf(horse.getGender()));
                pSt.setObject(5, horse.getSport());
                return pSt;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new PersistenceException("During creating horse \"" + horse.getName() + "\" an error occurred while accessing the database. ", e);
        }
        horse.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return horse;
    }
}
