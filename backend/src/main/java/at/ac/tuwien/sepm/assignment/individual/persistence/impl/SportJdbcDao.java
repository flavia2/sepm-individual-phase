package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Sport;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.SportDao;
import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SportJdbcDao implements SportDao {

    private static final String TABLE_NAME = "Sport";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SportJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sport getOneById(Long id) {
        LOGGER.trace("getOneById({})", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Sport> sports = jdbcTemplate.query(sql, this::mapRow, id);

        if (sports.isEmpty()) throw new NotFoundException("Could not find sport with id " + id);

        return sports.get(0);
    }

    @Override
    public Sport createSport(Sport sport) throws PersistenceException {
        LOGGER.trace("Creating a sport with name: {}", sport.getName());
        final String createSql = "INSERT INTO " + TABLE_NAME + " (name, description) VALUES (?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement pSt = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
                pSt.setObject(1, sport.getName());
                pSt.setObject(2, sport.getDescription() != null ? sport.getDescription() : null);
                return pSt;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new PersistenceException("Sport with name \"" + sport.getName() + "\" could not be created.", e);
        }
        sport.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return sport;
    }


    private Sport mapRow(ResultSet resultSet, int i) throws SQLException {
        final Sport sport = new Sport();
        sport.setId(resultSet.getLong("id"));
        sport.setName(resultSet.getString("name"));
        return sport;
    }
}
