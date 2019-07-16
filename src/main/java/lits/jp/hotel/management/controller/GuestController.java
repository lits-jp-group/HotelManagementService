package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.dtos.GuestsDTO;
import lits.jp.hotel.management.exceptions.GuestNotFoundException;
import lits.jp.hotel.management.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/guests")
public class GuestController {

    @Autowired
    GuestService guestService;

    @GetMapping
    public List<GuestsDTO> getAllGuests() throws Exception {
        return guestService.getAllGuests();
    }

    @GetMapping(value = "/find")
    public GuestsDTO findAGuest(@RequestParam Integer id) throws Exception {
        return guestService.findGuestById(id);
    }

    @PostMapping(value = "/register")
    public GuestsDTO addGuest(@RequestBody @Validated GuestsDTO guest) throws Exception {
         return guestService.addGuest(guest);
    }
}
