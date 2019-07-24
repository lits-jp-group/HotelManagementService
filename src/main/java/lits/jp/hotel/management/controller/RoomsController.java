package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {



    private RoomService roomService;
    private ModelMapper mapper;
    @Autowired
    public RoomsController(RoomService roomService, ModelMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @GetMapping
    List<RoomsDTO> showAllRooms(){
        List<Rooms> rooms = roomService.showAllRooms();
        List<RoomsDTO> roomsDTO = new ArrayList<>();
        rooms.forEach(r->roomsDTO.add(mapper.map(r, RoomsDTO.class)));
        return roomsDTO;
    }

    @PostMapping
    public  RoomsDTO addRoom(@RequestBody Rooms rooms){
        Rooms roomsSaved = roomService.addRoom(rooms);
        return mapper.map(roomsSaved, RoomsDTO.class);
    }

    @GetMapping("/bookedrooms")
    public List<RoomsDTO> showBookedRoomsOnDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Rooms> rooms = roomService.showBookedRoomsOnDate(date);
        List<RoomsDTO> roomsDTO = new ArrayList<>();
                rooms.forEach(r ->roomsDTO.add(mapper.map(r, RoomsDTO.class)));
       return  roomsDTO;
    }

    @GetMapping("/availableRooms")
    public List<RoomsDTO> availableRoomsOnDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Rooms> rooms = roomService.showAvailableRoomsOnDate(date);
        List<RoomsDTO> roomsDTO = new ArrayList<>();
        rooms.forEach(r ->roomsDTO.add(mapper.map(r, RoomsDTO.class)));
        return  roomsDTO;

    }

}
