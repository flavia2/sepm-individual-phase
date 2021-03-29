package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.util.Objects;

public class SportDto {
    private Long id;
    private String name;
    private String description;

    public SportDto() {
    }

    public SportDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public SportDto(String name) {
        this.name = name;
    }

    public SportDto(Long id, String name) {
        this(name);
        this.id = id;
    }

    public SportDto(Long id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SportDto sportDto = (SportDto) o;
        return Objects.equals(id, sportDto.id) &&
            Objects.equals(name, sportDto.name) &&
            Objects.equals(description, sportDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    private String fieldsString() {
        return "id = " + id + ", name='" + name + '\'' + ", description='" + description +"\'";
    }

    @Override
    public String toString() {
        return "SportDto{ " + fieldsString() + " }";
    }
}
