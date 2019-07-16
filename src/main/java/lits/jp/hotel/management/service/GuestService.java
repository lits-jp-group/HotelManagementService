package lits.jp.hotel.management.service;

import lits.jp.hotel.management.dtos.GuestsDTO;

import java.util.List;

public interface GuestService {
    List<GuestsDTO> getAllGuests();

    GuestsDTO findGuest(GuestsDTO guestToFind);

    GuestsDTO findGuestById(Integer id);

    GuestsDTO addGuest(GuestsDTO guestToAdd);

    Boolean removeGuestById(Integer id);

    Boolean removeGuest(GuestsDTO guestToRemove);

    GuestsDTO updateGuest(GuestsDTO initGuest, GuestsDTO updatedGuest);

    GuestsDTO updateGuestById(Integer id, GuestsDTO updatedGuest);
}