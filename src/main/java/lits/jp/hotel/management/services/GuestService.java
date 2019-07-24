package lits.jp.hotel.management.services;

import java.util.List;
import lits.jp.hotel.management.dtos.GuestsDTO;

public interface GuestService {
  List<GuestsDTO> getAllGuests();

  GuestsDTO findGuestById(Integer id);

  GuestsDTO addGuest(GuestsDTO guestToAdd);

  Boolean removeGuestById(Integer id);

  Boolean removeGuest(GuestsDTO guestToRemove);

  GuestsDTO updateGuestById(Integer id, GuestsDTO updatedGuest);
}
