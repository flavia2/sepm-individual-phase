package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.stereotype.Component;

@Component
public class HorseMapper {
    public HorseDto entityToDto(Horse horse) {
        if (horse == null)
            return null;
        return new HorseDto(horse.getId(), horse.getName(), horse.getDescription(),  horse.getBirthday(),horse.getGender(), horse.getSport());
    }

    public Horse dtoToEntity(HorseDto horseDto){
        return new Horse(horseDto.getId(), horseDto.getName(), horseDto.getDescription(), horseDto.getBirthday(), horseDto.getGender(), horseDto.getSport());
    }
}
