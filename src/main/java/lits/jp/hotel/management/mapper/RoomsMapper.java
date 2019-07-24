package lits.jp.hotel.management.mapper;

import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.models.Rooms;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomsMapper {

  private ModelMapper mapper;

  @Autowired
  public RoomsMapper(ModelMapper mapper) {
    this.mapper = mapper;
    mapper
        .getConfiguration()
        .setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC);
    mapper
        .getConfiguration()
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PROTECTED);
  }

  public Rooms toEntity(RoomsDTO roomsDTO) {
    return mapper.map(roomsDTO, Rooms.class);
  }

  public RoomsDTO toDto(Rooms rooms) {
    return mapper.map(rooms, RoomsDTO.class);
  }
}
