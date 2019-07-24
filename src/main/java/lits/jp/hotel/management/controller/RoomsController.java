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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rooms") // rooms!
public class RoomsController {



    private RoomService roomService;
    private ModelMapper mapper;
    @Autowired
    public RoomsController(RoomService roomService, ModelMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }



    @GetMapping // all remove
    List<RoomsDTO> showAllRooms(){
        List<Rooms> rooms = roomService.showAllRooms();
        List<RoomsDTO> roomsDTO = new ArrayList<>();
        rooms.forEach(r->roomsDTO.add(mapper.map(r, RoomsDTO.class)));
        return roomsDTO;
    }

    @PostMapping(value = "/addRoom") // remove addRoom
    public  RoomsDTO addRoom(RoomsDTO roomsDTO){ // add @RequestBody
        return roomService.addRoom(roomsDTO);
    }

    @GetMapping("/allbookedrooms")
    public List<RoomsDTO> showBookedRoomsOnDate(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
       return null;
    }

}
