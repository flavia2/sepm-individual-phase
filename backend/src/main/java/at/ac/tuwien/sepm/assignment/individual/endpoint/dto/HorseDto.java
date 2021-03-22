package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import at.ac.tuwien.sepm.assignment.individual.util.Gender;

import java.time.LocalDate;
import java.util.Objects;

public class HorseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate birthday;
    private Gender gender;
    private Long sport;

    public HorseDto() {
    }

    public HorseDto(Long id, String name, String description, LocalDate birthday, Gender gender, Long sport) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.birthday = birthday;
        this.gender = gender;
        this.sport = sport;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseDto horseDto = (HorseDto) o;
        return name.equals(horseDto.name) &&
            Objects.equals(description, horseDto.description) &&
            birthday.equals(horseDto.birthday) &&
            Objects.equals(gender,horseDto.gender) &&
            Objects.equals(sport,horseDto.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    protected String fieldsString() {
        return "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", birthday=" + birthday + ", gender='" + gender + '\'' + ", sport='" + sport + '\'';
    }

    @Override
    public String toString() {
        return "HorseDto{ " + fieldsString() +" }";
    }
}