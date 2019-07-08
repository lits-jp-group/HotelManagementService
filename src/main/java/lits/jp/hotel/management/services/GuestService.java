package lits.jp.hotel.management.services;

import lits.jp.hotel.management.dtos.GuestsDTO;

public interface GuestService {
    GuestsDTO findGuest(GuestsDTO guestToFind);

    GuestsDTO findGuestById(Integer id);

    GuestsDTO addGuest(GuestsDTO guestToAdd);

    Boolean removeGuestById(Integer id);

    Boolean removeGuest(GuestsDTO guestToRemove);

    GuestsDTO updateGuest(GuestsDTO initGuest, GuestsDTO updatedGuest);

    GuestsDTO updateGuestById(Integer id, GuestsDTO updatedGuest);
}
