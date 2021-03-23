package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.HorseDao;
import at.ac.tuwien.sepm.assignment.individual.util.Gender;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

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
        final String createSql = "INSERT INTO " + TABLE_NAME + " (name, description, birthday, gender, sport, parentId1, parentId2) VALUES (?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement pSt = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
                pSt.setString(1, horse.getName());
                pSt.setObject(2, horse.getDescription());
                pSt.setObject(3, horse.getBirthday());
                pSt.setString(4, String.valueOf(horse.getGender()));
                pSt.setObject(5, horse.getSport());
                pSt.setObject(6, horse.getParentId1());
                pSt.setObject(7, horse.getParentId2());
                return pSt;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new PersistenceException("During creating horse \"" + horse.getName() + "\" an error occurred while accessing the database.", e);
        }
        horse.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return horse;
    }

    @Override
    public Horse getHorseById(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Getting the horse with id: {}", id);
        final String querySql = "SELECT * FROM " + TABLE_NAME + " WHERE id= ?";
        List<Horse> horses;
        try {
            horses = jdbcTemplate.query(querySql, this::mapRow, id);
        } catch (DataAccessException e){
            throw new PersistenceException("During finding horse with id \"" + id + "\" an error occurred while accessing the database.", e);
        }
        if (horses.isEmpty()) throw new NotFoundException("Could not find horse with id " + id);

        return horses.get(0);
    }

    @Override
    public Horse editHorse(Horse horse) throws PersistenceException, NotFoundException {
        LOGGER.trace("Editing the horse \"{}\" with id: {}", horse.getName(), horse.getId());
        final String editSql = "UPDATE " + TABLE_NAME + " SET name=?, description=?, birthday=?, gender=?, sport=?, parentId1=?, parentId2=? WHERE id=?;";

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement pSt = connection.prepareStatement(editSql);
                pSt.setString(1, horse.getName());
                pSt.setObject(2, horse.getDescription());
                pSt.setObject(3, horse.getBirthday());
                pSt.setString(4, String.valueOf(horse.getGender()));
                pSt.setObject(5, horse.getSport());
                pSt.setObject(6, horse.getParentId1());
                pSt.setObject(7, horse.getParentId2());
                pSt.setLong(8, horse.getId());
                return pSt;
            });
        } catch (DataAccessException e) {
            throw new PersistenceException("During editing horse with id \"" + horse.getId() + "\" an error occurred while accessing the database.", e);
        }
        return getHorseById(horse.getId());
    }

    @Override
    public void deleteHorse(Long id) throws PersistenceException, NotFoundException {
        LOGGER.trace("Deleting the horse with id {}", id);
        if (getHorseById(id)!= null) {
            final String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

            try {
                jdbcTemplate.update(connection -> {
                    PreparedStatement pSt = connection.prepareStatement(deleteSql);
                    pSt.setLong(1, id);
                    return pSt;
                });
            } catch (DataAccessException e) {
                throw new PersistenceException("During deleting horse with id \"" + id + "\" an error occurred while accessing the database.", e);
            }
        }
    }

    @Override
    public List<Horse> searchHorse(Horse horse) throws PersistenceException, NotFoundException {
        LOGGER.trace("Searching for horses with following search parameters: name ({}), description ({}), birthday ({}), gender ({}), sport ({})", horse.getName(), horse.getDescription(), horse.getBirthday(), horse.getGender(), horse.getSport());
        String searchSql = "SELECT * FROM " + TABLE_NAME + " WHERE UPPER(name) LIKE ?" +
            " AND birthday <= ?" +
            " AND gender LIKE ?";
        String searchDescription = " AND UPPER(description) LIKE ?";
        String searchSport = " AND sport = ?";
        List<Horse> horses;
        Object [] objects = {"%" +  ((horse.getName() == null) ? "" : horse.getName()).toUpperCase() + "%",
            ((horse.getBirthday() == null) ? LocalDate.now() : horse.getBirthday()).toString(),
            "%" +  ((horse.getGender() == null) ? "" : horse.getGender()) + "%"
        };

        if(horse.getDescription() != null){
            searchSql += searchDescription;
            objects = addOne(objects.length,objects,"%" + horse.getDescription().toUpperCase() + "%");
        }
        if(horse.getSport() != null){
            searchSql += searchSport;
            objects = addOne(objects.length,objects,horse.getSport());
        }

        try {
            horses = jdbcTemplate.query(searchSql, this::mapRow, objects);
        } catch (DataAccessException e){
            throw new PersistenceException("Horse with following parameters could not be accessed: \n name:\"" + horse.getName() + "\" \n " +
                "description:\"" + horse.getDescription() + "\" \n" +
                "birthday:\"" + horse.getBirthday() + "\" \n" +
                "gender:\"" + horse.getGender() + "\" \n" +
                "sport:\"" + horse.getSport() + "\" \n", e);
        }
        if (horses.isEmpty()) throw new NotFoundException("Could not find any horse. ");

        return horses;
    }

    private Horse mapRow(ResultSet resultSet, int i) throws SQLException {
        LOGGER.trace("Mapping through all SQL columns to get value of each column.");
        final Horse horse = new Horse();
        horse.setId(resultSet.getLong("id"));
        horse.setName(resultSet.getString("name"));
        horse.setDescription(resultSet.getString("description"));
        horse.setBirthday(resultSet.getDate("birthday").toLocalDate());
        horse.setGender(Enum.valueOf(Gender.class,resultSet.getString("gender")));
        horse.setSport(resultSet.getLong("sport"));
        horse.setParentId1(resultSet.getLong("parentId1"));
        horse.setParentId2(resultSet.getLong("parentId2"));
        return horse;
    }

    // Function to add x in arr
    private static Object[] addOne(int n, Object array[], Object x){
        LOGGER.trace("Adding one element to an object array.");
        int i;
        Object newArray[] = new Object[n + 1];

        for (i = 0; i < n; i++)
            newArray[i] = array[i];
        newArray[n] = x;
        return newArray;
    }

}
