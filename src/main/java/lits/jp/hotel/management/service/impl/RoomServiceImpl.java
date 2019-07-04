package lits.jp.hotel.management.service.impl;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.mapper.RoomsMapper;
import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.repository.RoomsRepository;
import lits.jp.hotel.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomsMapper roomsMapper;
    @Autowired
    RoomsRepository roomsRepository;

    @Override
    public RoomsDTO addRoom(RoomsDTO roomsDTO) {
        Rooms rooms=roomsMapper.toEntity(roomsDTO);
        return roomsMapper.toDto(roomsRepository.save(rooms));
    }


    @Override
    public void deleteRoom(int number) {
        roomsRepository.deleteByNumber(number);
    }

    @Override
    public List<RoomsDTO> showAllRooms() {
        List<Rooms> list = (List<Rooms>) roomsRepository.findAll();
        return list.stream().map(rooms -> roomsMapper.toDto(rooms)).collect(Collectors.toList());
    }

    @Override
    public List<BookingsDTO> showBookingHistory(int number) {
        List<BookingsDTO> list = new ArrayList<>();
        return null;
    }
}
