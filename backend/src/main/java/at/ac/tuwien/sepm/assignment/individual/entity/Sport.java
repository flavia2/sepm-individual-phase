package at.ac.tuwien.sepm.assignment.individual.entity;

import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Objects;

public class Sport {
    private Long id;
    private String name;
    private String description;

    public Sport() {
    }

    public Sport(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Sport(Long id, String name, String description) {
        this(name, description);
        this.id = id;
    }

    public Sport(String name) {
        this.name = name;
    }

    public Sport(Long id, String name) {
        this(name);
        this.id = id;
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
        Sport sport = (Sport) o;
        return Objects.equals(id, sport.id) &&
            Objects.equals(name, sport.name) &&
            Objects.equals(description, sport.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    protected String fieldsString() {
        return "id=" + id + ", name='" + name + '\'' + ", description='" + description +"\'";
    }

    @Override
    public String toString() {
        return "Sport{ " + fieldsString() +" }";
    }
}
