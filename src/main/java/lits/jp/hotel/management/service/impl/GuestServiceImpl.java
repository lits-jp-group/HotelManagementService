package lits.jp.hotel.management.service.impl;

import lits.jp.hotel.management.dtos.GuestsDTO;
import lits.jp.hotel.management.exceptions.GuestNotFoundException;
import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.repository.GuestsRepository;
import lits.jp.hotel.management.service.GuestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestsRepository guestsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<GuestsDTO> getAllGuests() {
        List<GuestsDTO> guestsDTOList = new LinkedList<>();
        guestsRepository.findAll().forEach(
                x -> {guestsDTOList.add(modelMapper.map(x, GuestsDTO.class));}
        );
        if(guestsDTOList.isEmpty()) {
            throw new GuestNotFoundException("No guests found");
        }
        return guestsDTOList;
    }

    @Override
    public GuestsDTO findGuestById(Integer id) {
        return modelMapper.map(guestsRepository.findById(id).orElseThrow(
                () ->  new GuestNotFoundException("Guest with id " + id + " not found")
        ), GuestsDTO.class);
    }

    @Override
    public GuestsDTO addGuest(GuestsDTO guestToAdd) {
        return modelMapper.map(guestsRepository.save(
                modelMapper.map(guestToAdd, Guests.class)), GuestsDTO.class);
    }

    @Override
    public Boolean removeGuestById(Integer id) {
        guestsRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean removeGuest(GuestsDTO guestToRemove) {
        guestsRepository.delete(modelMapper.map(guestToRemove, Guests.class));
        return true;
    }

    @Override
    public GuestsDTO updateGuestById(Integer id, GuestsDTO updatedGuest) {
        GuestsDTO toUpdate = modelMapper.map(guestsRepository.findById(id).orElseThrow(
                () ->  new GuestNotFoundException("Guest with id " + id + " not found")
        ),GuestsDTO.class);

        Guests update = modelMapper.map(updatedGuest,Guests.class);

        update.setGuestId(id);

        return modelMapper.map(guestsRepository.save(update),GuestsDTO.class);
    }
}
