package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDate;

public class FamilyTreeDto {
    private Long id;
    private String name;
    private LocalDate birthday;
    private FamilyTreeDto[] parent1;
    private FamilyTreeDto[] parent2;

    public FamilyTreeDto(){}
    public FamilyTreeDto(Long id, String name, LocalDate birthday, FamilyTreeDto[] parent1, FamilyTreeDto[] parent2){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.parent1 = parent1;
        this.parent2 = parent2;
    }
    public FamilyTreeDto(Long id, String name, LocalDate birthday){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public FamilyTreeDto[] getParent1() {
        return parent1;
    }

    public void setParent1(FamilyTreeDto[] parent1) {
        this.parent1 = parent1;
    }

    public FamilyTreeDto[] getParent2() {
        return parent2;
    }

    public void setParent2(FamilyTreeDto[] parent2) {
        this.parent2 = parent2;
    }
}