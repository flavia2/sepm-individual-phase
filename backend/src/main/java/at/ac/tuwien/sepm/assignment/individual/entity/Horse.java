package at.ac.tuwien.sepm.assignment.individual.entity;

import at.ac.tuwien.sepm.assignment.individual.util.Gender;

import java.util.*;
import java.time.LocalDate;

public class Horse {

    private Long id;
    private String name;
    private String description;
    private LocalDate birthday;
    private Gender gender;
    private Long sport;
    private Long mother;
    private Long father;

    public Horse(){}

    public Horse(Long id, String name, String description, LocalDate birthday, Gender gender, Long sport, Long mother, Long father) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.birthday = birthday;
        this.gender = gender;
        this.sport = sport;
        this.mother = mother;
        this.father = father;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getSport() {
        return sport;
    }

    public void setSport(Long sport) {
        this.sport = sport;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMother() {
        return mother;
    }

    public void setMother(Long mother) {
        this.mother = mother;
    }

    public Long getFather() {
        return father;
    }

    public void setFather(Long father) {
        this.father = father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return name.equals(horse.name) &&
            Objects.equals(description, horse.description) &&
            birthday.equals(horse.birthday) &&
            Objects.equals(gender, horse.gender) &&
            Objects.equals(sport, horse.sport) &&
            Objects.equals(mother,horse.mother) &&
            Objects.equals(father,horse.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    protected String fieldsString() {
        return "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", birthday=" + birthday + ", gender='" + gender + '\'' + ", sport='" + sport + '\'' + " mother='" + mother +'\''+ " father='" + father +'\'';
    }

    @Override
    public String toString() {
        return "Horse{ " + fieldsString() + " }";
    }
}