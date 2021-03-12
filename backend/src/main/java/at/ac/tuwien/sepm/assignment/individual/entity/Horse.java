package at.ac.tuwien.sepm.assignment.individual.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Horse {
    private enum gender{
        MALE, FEMALE
    }
    private Long id;
    private String name;
    private String description;
    private LocalDate birthday;
    private Long sport;

    public Horse(Long id, String name, String description, LocalDate birthday, Long sport) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.birthday = birthday;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Long getSport() {
        return sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(id, horse.id) && Objects.equals(name, horse.name) && Objects.equals(description, horse.description) && Objects.equals(birthday, horse.birthday) && Objects.equals(sport, horse.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, birthday, sport);
    }

    protected String fieldsString() {
        return "id=" + id + ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", birthday=" + birthday +
            ", sport=" + sport;
    }

    @Override
    public String toString() {
        return "Horse{" + fieldsString() + '}';
    }
}
