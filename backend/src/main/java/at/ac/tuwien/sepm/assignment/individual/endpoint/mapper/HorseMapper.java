package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.FamilyTreeDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.util.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HorseMapper {
    public HorseDto entityToDto(Horse horse) {
        if (horse == null)
            return null;
        return new HorseDto(horse.getId(), horse.getName(), horse.getDescription(),  horse.getBirthday(),horse.getGender(), horse.getSport(),horse.getParentId1(),horse.getParentId2());
    }

    public Horse dtoToEntity(HorseDto horseDto){
        return new Horse(horseDto.getId(), horseDto.getName(), horseDto.getDescription(), horseDto.getBirthday(), horseDto.getGender(), horseDto.getSport(), horseDto.getParentId1(), horseDto.getParentId2());
    }

    public List<HorseDto> entitiesToDto(List<Horse> horses) {
        List<HorseDto> horseDtos = new ArrayList<>();
        for (Horse horse : horses) {
            horseDtos.add(entityToDto(horse));
        }
        return horseDtos;
    }
    public Horse paramsToEntity(String name, String description, LocalDate birthday, Gender gender, Long sport) {
        Horse horse = new Horse();
        horse.setName(name);
        horse.setDescription(description);
        horse.setBirthday(birthday);
        horse.setGender(gender);
        horse.setSport(sport);
        return horse;
    }
    public FamilyTreeDto createTree(Long id, List<Horse> horses){
        FamilyTreeDto treeDto = new FamilyTreeDto();

        for (Horse horse : horses){
            if(id.equals(horse.getId())){
                treeDto.setId(id);
                treeDto.setName(horse.getName());
                treeDto.setBirthday(horse.getBirthday());
                treeDto.setParent1(new FamilyTreeDto[]{createTree(horse.getParentId1(),horses)});
                treeDto.setParent2(new FamilyTreeDto[]{createTree(horse.getParentId2(),horses)});

            }
        }
        return treeDto;
    }
}
