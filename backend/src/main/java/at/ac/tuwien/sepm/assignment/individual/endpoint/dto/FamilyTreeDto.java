package at.ac.tuwien.sepm.assignment.individual.endpoint.dto;

import java.time.LocalDate;

public class FamilyTreeDto {
    private Long id;
    private String name;
    private LocalDate birthday;
    private FamilyTreeDto[] mother;
    private FamilyTreeDto[] father;

    public FamilyTreeDto(){}
    public FamilyTreeDto(Long id, String name, LocalDate birthday, FamilyTreeDto[] mother, FamilyTreeDto[] father){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.mother = mother;
        this.father = father;
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

    public FamilyTreeDto[] getMother() {
        return mother;
    }

    public void setMother(FamilyTreeDto[] mother) {
        this.mother = mother;
    }

    public FamilyTreeDto[] getFather() {
        return father;
    }

    public void setFather(FamilyTreeDto[] father) {
        this.father = father;
    }
}