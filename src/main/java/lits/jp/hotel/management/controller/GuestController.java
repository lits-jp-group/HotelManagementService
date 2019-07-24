package lits.jp.hotel.management.controller;

import java.util.List;
import lits.jp.hotel.management.dtos.GuestsDTO;
import lits.jp.hotel.management.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/guests")
public class GuestController {

  @Autowired GuestService guestService;

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
